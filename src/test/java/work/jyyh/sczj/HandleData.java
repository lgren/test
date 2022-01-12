package work.jyyh.sczj;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.Session;
import cn.hutool.db.ds.simple.SimpleDataSource;
import com.lgren.poi.poi3_17.n211227.ExcelRead;
import lombok.SneakyThrows;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class HandleData {
    DataSource ds = new SimpleDataSource("jdbc:oracle:thin:@10.160.11.66:1521/pdb01", "zsk1233", "zsk1233");
    DataSource dsgs = new SimpleDataSource("jdbc:oracle:thin:@172.20.21.213:1521/pdb20", "zsk123", "zsk123");

    @Test
    public void test() {
        System.out.println(5+10+3+15+20+18+2+23+4);
    }

    @Test
    public void test1() {
        Map<String, String> allBusiness = getAllBusiness("/Users/lgren/Work/YH/0项目/1知识库/知识/知识库目录树(1).xlsx");

        Map<String, Set<Vk01PO>> map = getVk01(allBusiness, "/Users/lgren/Work/YH/0项目/1知识库/知识/", "01_政策法规".split("[-_]"));
        Set<Vk01PO> vk01Set = map.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        // List<List<Vk01PO>> collect = vk01Set.stream().collect(Collectors.groupingBy(Vk01PO::getYvk001, Collectors.toList()))
        //         .values().stream().filter(l -> l.size() > 1).collect(Collectors.toList());
        // commitDb(dsgs, "VK011", vk01Set);
        System.out.println();
    }

    /**
     * @param typeArr 01_政策法规 02_问题解答 03_新闻快讯 04_时政新闻 05_名词解释 06_其他
     */
    private Map<String, Set<Vk01PO>> getVk01(Map<String, String> allBusinessMap, String pathStr, String[] typeArr) {
        String type = typeArr[0];
        String typeStr = typeArr[1];
        File path = new File(pathStr + typeStr);
        List<File> fileList = FileUtil.loopFiles(path, f -> FileNameUtil.isType(f.getName(), "xls", "xlsx"));
        return fileList.stream().collect(LinkedHashMap::new, (r, f) -> {
            ExcelRead.LSheet sheet = new ExcelRead(f).sheet(0).setWhichRowIsCellKey(2);
            r.put(FileUtil.mainName(f), sheet.range(3)
                    .mapToObj(i -> sheet.getRowV(i, Vk01PO.class))
                    .peek(o -> o.setYvk014(type))// 政策法规
                    .peek(o -> o.setYve901(convertMapping(allBusinessMap, o.getYve901())))// 业务领域
                    .filter(o -> o.getYve901() != null)
                    .collect(Collectors.toSet()));
        }, HashMap::putAll);
    }

    private Map<String, String> getAllBusiness(String pathname) {
        ExcelRead.LSheet sheet = new ExcelRead(pathname)
                .sheet(0).setWhichRowIsCellKey(2);
        return sheet.range(3)
                .mapToObj(sheet::getRowV)
                .collect(Collectors.toMap(o -> (String) o.get("类别全称"), o -> (String) o.get("类别编码")));
    }

    private String convertMapping(Map<String, String> mapping, String data) {
        return Optional.of(Arrays.stream(data.split("；"))
                .map(s -> mapping.getOrDefault(s, s)).collect(Collectors.joining(",")))
                .filter(StrUtil::isNotBlank).orElse(null);
    }

    @SneakyThrows
    private void commitDb(DataSource ds, String table, Collection<?> coll) {
        Session session = Session.create(ds);
        session.beginTransaction();
        for (Object o : coll) {
            session.insert(Entity.create(table).parseBean(o));
        }
        session.commit();
    }
}
