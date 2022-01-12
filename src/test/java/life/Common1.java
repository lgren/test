package life;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.lgren.poi.poi3_17.n211227.ExcelRead;
import org.junit.Test;

import java.util.Map;

public class Common1 {
    String code = "激活码";
    String name = "游戏名称（ctrl+F查找）";
    String download = "下载链接";
    String downloadCode = "下载链接";
    String other = "修改器/历代游戏";

    @Test
    public void test() {
        ExcelRead.LSheet sheet = new ExcelRead("/Users/lgren/Downloads/qq/游戏菜单新A-1.xlsx").sheet(0).setWhichRowIsCellKey(1);
        JSONObject data = sheet.range(2)
                .mapToObj(sheet::getRowV)
                .filter(n -> n.get("游戏名称（ctrl+F查找）") != null)
                .collect(JSONObject::new, (r, n) -> r.set(n.get("游戏名称（ctrl+F查找）").toString(), n), Map::putAll);

        CollUtil.newArrayList(
                "了不起的修仙模拟器"
                , "奥日和黑暗森林：终极版"
                ,"边缘世界 环世界"
                ,"刺客信条8：奥德赛"
                ,"帝国时代合集"
                ,"方舟：生存进化"
                ,"辐射4"
                ,"FIFA19/18/17/15/14/13/12/11/10全家桶"
                ,"GTA5侠盗猎车5v1.54纯净版|容量98GB纯净版"
                ,"GTA5侠盗猎车5GTA5MOD版A"
                ,"古墓丽影：暗影11"
                ,"鬼泣5"
                ,"罗马2：全面战争-帝皇版"
                ,"尼尔机械纪元"
                ,"全面战争战锤2"
                ,"使命召唤14：二战"
                ,"上古卷轴5"
                ,"塞尔达传说：荒野之息 旷野之息"
                ,"实况足球PES2019+2018"
                ,""
        );
        System.out.println(data);
    }

    private void print(JSONObject data) {
        StringBuilder sb = new StringBuilder(data.getStr(name));
        if (StrUtil.isNotBlank(data.getStr(code))) {
            sb.append("--").append(data.getStr(code));
        }
        sb.append("\n");
        sb.append(data.getStr(download));
        if (StrUtil.isNotBlank(data.getStr(downloadCode))) {
            sb.append("--").append(data.getStr(downloadCode));
        }
        sb.append("\n");
        if (StrUtil.isNotBlank(data.getStr(other))) {
            sb.append(data.getStr(other));
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }
}
