package work.jyyh.sczj;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.Session;
import cn.hutool.db.ds.simple.SimpleDataSource;
import com.lgren.poi.poi3_17.new_read.LRowCommon;
import com.lgren.poi.poi3_17.new_read.LWorkbookCommon;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonTest {

    @SneakyThrows
    @Test
    public void vk011Transfer() {
        List<Entity> list = Db.use(ds).query("SELECT * FROM VK012345");
        // List<Vk012345> query = Db.use(ds).query("SELECT * FROM VK012345", Vk012345.class);

        System.out.println();
        // Session session = Session.create(dsgs);
        // session.beginTransaction();
        // list.forEach(o -> insert(session, Entity.create("VK012345").parseBean(o)));
        // session.commit();
    }

    @SneakyThrows
    @Test
    public void vk012345Transfer() {
        List<Vk01PO> vk01List = Db.use(ds).query("SELECT * FROM VK011", Vk01PO.class);

        Session session = Session.create(dsgs);
        session.beginTransaction();
        vk01List.forEach(o -> insert(session, Entity.create("VK011").parseBean(o)));
        session.commit();
    }

    @SneakyThrows
    @Test
    public void vk011() {
        File path = new File("/Users/lgren/Work/YH/0项目/0知识库/知识/政策法规");
        List<File> fileList = FileUtil.loopFiles(path);
        Map<String, Set<Vk01PO>> map = new LinkedHashMap<>();
        Map<String, String> allBusinessMap = getAllBusiness();
        for (File file : fileList) {
            Workbook wb = LWorkbookCommon.getWorkbook(new FileInputStream(file));
            int title = 2;
            Sheet sheetAt0 = wb.getSheetAt(0);
            map.put(FileUtil.mainName(file), IntStream.range(3, sheetAt0.getLastRowNum() + 1)
                    .mapToObj(i -> LRowCommon.getRowV(sheetAt0, i, title, Vk01PO.class, (int[]) null))
                    .peek(o -> o.setYvk014("01"))// 政策法规
                    .peek(o -> o.setYve901(Optional.of(Arrays.stream(o.getYve901().split("；")).map(allBusinessMap::get).collect(Collectors.joining(","))).filter(StrUtil::isNotBlank).orElse(null)))// 业务领域
                    .filter(o -> o.getYve901() != null)
                    .collect(Collectors.toSet()));
        }
        Set<Vk01PO> vk01Set = map.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        // List<List<Vk01PO>> collect = vk01Set.stream().collect(Collectors.groupingBy(Vk01PO::getYvk001, Collectors.toList()))
        //         .values().stream().filter(l -> l.size() > 1).collect(Collectors.toList());

        Session session = Session.create(dsgs);
        session.beginTransaction();
        vk01Set.forEach(o -> insert(session, Entity.create("VK011").parseBean(o)));
        session.commit();
    }


    @SneakyThrows
    @Test
    public void vk16() {
        File path = new File("/Users/lgren/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/5ef3fa66b2442390ec9dffee99161d94/Message/MessageTemp/dca27640183ac028139490ab47fd1a79/File/政策法规");
        List<File> fileList = FileUtil.loopFiles(path);

        AtomicInteger id = new AtomicInteger(10_0000);
        Map<String, Set<Vk16>> map = new LinkedHashMap<>();
        Map<String, String> allBusinessMap = getAllBusiness();
        for (File file : fileList) {
            Workbook wb = LWorkbookCommon.getWorkbook(new FileInputStream(file));
            int title = 2;
            Sheet sheetAt0 = wb.getSheetAt(0);
            map.put(FileUtil.mainName(file), IntStream.range(3, sheetAt0.getLastRowNum() + 1)
                    .mapToObj(i -> LRowCommon.getRowV(sheetAt0, i, title, Vk01PO.class, (int[]) null))
                    .peek(o -> o.setYvk014("01"))// 政策法规
                    .flatMap(o -> Arrays.stream(o.getYve901().split("；")).map(allBusinessMap::get).map(s -> {o.setYve901(s);return o;}))// 业务领域
                    .filter(o -> o.getYve901() != null)
                    .map(o -> Vk16.builder()
                            .yvk160(String.valueOf(id.incrementAndGet()))// 知识业务领域ID
                            .yvk001(o.getYvk001())// 知识ID
                            .yve901(o.getYve901())// 业务领域ID
                            // .aae011()// 经办人ID
                            .aae036(o.getAae037())// 经办时间
                            .aae017(o.getAae017())// 经办机构
                            .aae012(o.getAae012())// 经办人名称
                            .aae100("1")// 有效标识
                            .build())
                    .collect(Collectors.toSet()));
        }
        Session session = Session.create(dsgs);
        session.beginTransaction();
        map.forEach((k,v) -> v.forEach(o -> insert(session, Entity.create("VK16").parseBean(o))));
        session.commit();
    }

    @SneakyThrows
    @Test
    public void vk0123() {
        File path = new File("/Users/lgren/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/5ef3fa66b2442390ec9dffee99161d94/Message/MessageTemp/dca27640183ac028139490ab47fd1a79/File/政策法规");
        List<File> fileList = FileUtil.loopFiles(path);
        Map<String, List<Vk0123>> map = new LinkedHashMap<>();
        for (File file : fileList) {
            Workbook wb = LWorkbookCommon.getWorkbook(new FileInputStream(file));
            Sheet sheetAt0 = wb.getSheetAt(0);
            map.put(FileUtil.mainName(file), IntStream.range(3, sheetAt0.getLastRowNum() + 1)
                    // 这个 2 是标题[表头]的位置
                    .mapToObj(i -> LRowCommon.getRowV(sheetAt0, i, 2, Vk0123.class, (int[]) null))
                    .collect(Collectors.toList()));
        }
        Map<String, String> allBusinessMap = getAllBusiness();
        AtomicInteger all = new AtomicInteger();

        AtomicInteger notFind = new AtomicInteger();
        Set<String> notFindList = new LinkedHashSet<>();
        AtomicInteger more = new AtomicInteger();
        Set<String> moreList = new LinkedHashSet<>();
        map.forEach((k,v) -> v.forEach(o -> {
            all.incrementAndGet();
            allBusinessMap.computeIfAbsent(o.getYvk006(), k1 -> {
                System.out.println(o);
                System.out.println(k1);
                System.out.println("------------------------------------------------------------");
                if (k1.contains("；")) {
                    more.incrementAndGet();
                    moreList.add(k1);
                } else {
                    notFind.incrementAndGet();
                    notFindList.add(k1);
                }
                return null;
            });
        }));

        System.out.println("==========总共数==========");
        System.out.println(all.get());
        System.out.println("==========总共数==========");

        System.out.println("==========未找到==========");
        System.out.println(notFind.get());
        notFindList.forEach(System.out::println);
        System.out.println("==========未找到==========");

        System.out.println("==========多领域==========");
        System.out.println(more.get());
        moreList.forEach(System.out::println);
        System.out.println("==========多领域==========");

        // Session session = Session.create(ds);
        // session.beginTransaction();
        // map.forEach((k,v) -> v.forEach(o -> insert(session, Entity.create("VK0123").parseBean(o))));
        // session.commit();
    }

    @SneakyThrows
    private Map<String, String> getAllBusiness() {
        File file = new File("/Users/lgren/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/com.tencent.xinWeChat/2.0b4.0.9/5ef3fa66b2442390ec9dffee99161d94/Message/MessageTemp/45557764c596b3b8f7694ac23deb6a7c/File/知识库目录树(1).xlsx");
        Workbook wb = LWorkbookCommon.getWorkbook(new FileInputStream(file));
        Sheet sheetAt0 = wb.getSheetAt(0);
        Map<String, String> allBusiness = IntStream.range(3, sheetAt0.getLastRowNum() + 1)
                .mapToObj(i -> LRowCommon.getRowV(sheetAt0, i, 2, (int[]) null))
                .collect(Collectors.toMap(o -> (String)o.get("类别全称"), o -> (String)o.get("类别编码")));
        return allBusiness;
    }

    DataSource ds = new SimpleDataSource("jdbc:oracle:thin:@10.160.11.66:1521/pdb01", "zsk1233", "zsk1233");
    DataSource dsgs = new SimpleDataSource("jdbc:oracle:thin:@172.20.21.213:1521/pdb20", "zsk123", "zsk123");

    // Map<String, String> needMap = MapUtil.<String, String>builder()
    //         .put("政策法规->专业技术人员管理->职称政策管理；政策法规->职业能力建设->高技能人才管理", "")
    //         .put("政策法规->职业能力建设->技能人员职业资格证书管理；政策法规->专业技术人员管理->留学人员和回国（来华）专家管理", "")
    //         .put("政策法规->社会保险综合->其他；政策法规->医疗保险->其他", "")
    //         .put("政策法规->专业技术人员管理->职称政策管理；政策法规->职业能力建设->高技能人才管理", "")
    //         .put("政策法规->养老保险->机关事业单位养老保险；政策法规->养老保险->基本制度", "")
    //         .put("政策法规->养老保险->城镇职工基本养老保险；政策法规->养老保险->机关事业单位养老保险", "")
    //         .put("政策法规->养老保险->基本制度；政策法规->养老保险->离退休人员管理服务", "")
    //         .put("政策法规->就业促进->就业管理；政策法规->失业保险->失业保险与再就业联动", "")
    //         .put("政策法规->就业促进->积极的就业政策；政策法规->失业保险->其他", "")
    //         .put("政策法规->就业促进->就业管理；政策法规->就业促进->困难人员就业；政策法规->就业促进->大学生就业", "")
    //         .put("政策法规->就业促进->积极的就业政策；政策法规->就业促进->创业指导服务", "")
    //         .put("政策法规->就业促进->大学生就业；政策法规->就业促进->其他", "")
    //         .put("政策法规->就业促进->积极的就业政策；政策法规->就业促进->其他", "")
    //         .put("政策法规->专业技术人员管理->职称政策管理；政策法规->专业技术人员管理->职称评审", "")
    //         .put("政策法规->就业促进->就业管理；政策法规->失业保险->失业保险与再就业联动", "")
    //         .put("政策法规->就业促进->积极的就业政策；政策法规->失业保险->其他",  "")
    //         .build();

    @SneakyThrows
    private void insert(Session s, Entity o) {
        s.insert(o);
    }
}
