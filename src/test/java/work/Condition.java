package work;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * 查询条件
 * @author Lgren
 * @create 2018-12-04 17:29
 **/
@Data
@Accessors(chain = true)
public class Condition {
//    private List<Condition> prevCondition;// 之前
//    private List<Condition> nextCondition;// 之后
    private List<String> prevDataList;// 之前数据
    private List<String> nextDataList;// 之后数据
    private String data;// 数据
    private int start;// 开始的编号
    private int end;// 结束 的编号

    public Condition addNextData(String nextData) {
        if (StringUtils.isNotBlank(nextData)) {
            nextDataList = ofNullable(nextDataList).orElse(new ArrayList<>(10));
            nextDataList.add(nextData);
        }
        return this;
    }
    public Condition addPrevData(String prevData) {
        if (StringUtils.isNotBlank(prevData)) {
            prevDataList = ofNullable(prevDataList).orElse(new ArrayList<>(10));
            prevDataList.add(prevData);
        }
        return this;
    }


    public static void main(String[] args) {
        List<String> strList = Lists.newArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

        Condition c1 = new Condition().setData("A").addNextData("B");
        Condition c2 = new Condition().addPrevData("A").setData("B");
        Condition c3 = new Condition().addPrevData("A").setData("C");
        Condition c4 = new Condition().addPrevData("A").setData("D");
        Condition c5 = new Condition().addPrevData("A").setData("E");
        Condition c6 = new Condition().addPrevData("A").setData("F");
        getCondition(Lists.newArrayList(c1, c2, c3, c4, c5, c6));
    }

    public static List<Condition> getCondition(List<Condition> conditionList) {
        return null;
    }
}
