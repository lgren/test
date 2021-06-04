package work.jyyh.hmhn;

import cn.hutool.core.util.ArrayUtil;
import com.hankcs.hanlp.seg.common.Term;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TODO
 * @author lgren
 * @since 2021-05-24 5:48 下午
 */
@Data
public class SegmentItem {
    private final String content;
    private final List<Term> termList;
    private List<String> termStrList;
    private String termStrListStr;

    private String[] ignoreArr = {"-", "(", ")", "（", "）", ".", "“", "”", "、"};

    public List<String> getTermStrList() {
        if (termStrList == null) {
            termStrList = termList.stream()
                    .map(o -> o.word)
                    .filter(s -> !ArrayUtil.contains(ignoreArr, s))
                    .collect(Collectors.toList());
        }
        return termStrList;
    }

    public String getTermStrListStr() {
        if (termStrListStr == null) {
            termStrListStr = String.join(" ", getTermStrList());
        }
        return termStrListStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentItem that = (SegmentItem) o;
        return Objects.equals(getTermStrListStr(), that.getTermStrListStr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTermStrListStr());
    }

    // @Override
    // public String toString() {
    //     return content + " => " + String.join(" ", getTermStrList()) + "\n";
    // }

    @Override
    public String toString() {
        return String.join(" ", getTermStrList()) + "\n";
    }
}
