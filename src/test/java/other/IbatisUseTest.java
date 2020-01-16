package other;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一些Ibatis的使用类
 * @author lgren
 * @since 2019-12-10 09:26
 */
public class IbatisUseTest {
    @Test
    public void name1() {
        Map<String, String[]> allSqlMap = getIbatisSql("" +
                "15:12:07.371 [RMI TCP Connection(2)-127.0.0.1] DEBUG java.sql.PreparedStatement - {pstm-100004} Executing Statement:      SELECT      a.yva001 AS yva001,      a.yve001 AS yve001,      a.yve002 AS yve002,      a.yva002 AS yva002,      a.yva003 AS yva003,      a.yva004 AS yva004,      a.yva005 AS yva005,      a.aae100 AS aae100,      a.aae011 AS aae011,      a.aae036 AS aae036,      a.yvk010 AS yvk010,      a.yva043 AS yva043,      a.yva006 AS yva006,      a.yva007 AS yva007,      a.yva008 AS yva008,      a.yva009 AS yva009,      a.yva010 AS yva010,      a.yva011 AS yva011         FROM      VA01 a          where       a.aae100 = ?                                                                                                                   a.yva009 = ?                              \n" +
                "15:12:07.371 [RMI TCP Connection(2)-127.0.0.1] DEBUG java.sql.PreparedStatement - {pstm-100004} Parameters: [1, 1]" +
                "" +
                "" +
                "" +
                "" +
                "" +
                "");
        allSqlMap.forEach((k,v) -> System.out.println(k + "\n" + v[1] + "\n\n"));
    }

    private Map<String, String[]> getIbatisSql(String allStr) {
        Map<String, String[]> map = new HashMap<>();

        String[] allLine = allStr.split("\\n");
        
        Pattern sqlP = Pattern.compile("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}.+java\\.sql\\.PreparedStatement.+\\{pstm-(\\d+)}.+Executing Statement:(.*)");
        Pattern paramP = Pattern.compile("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}.+java\\.sql\\.PreparedStatement.+\\{pstm-(\\d+)}.+Parameters: \\[(.*?)].*");
        Pattern paramTypeP = Pattern.compile("\\d{2}:\\d{2}:\\d{2}\\.\\d{3}.+java\\.sql\\.PreparedStatement.+\\{pstm-(\\d+)}.+Types: \\[(.*?)].*");
        for (String line : allLine) {
            Matcher matcher;
            String lineCleared = line.replaceAll("\\s+", " ");
            if ((matcher = sqlP.matcher(lineCleared)).find()) {
                String thisNum = matcher.group(1);
                String thisSql = matcher.group(2).trim();
                map.computeIfAbsent(thisNum, k -> new String[4])[0] = thisSql;
            } else if ((matcher = paramP.matcher(lineCleared)).find()) {
                String thisNum = matcher.group(1);
                String thisParam = matcher.group(2);
                String[] sqlAllArr = map.computeIfAbsent(thisNum, k -> new String[4]);
                sqlAllArr[2] = thisParam;
                String sqlResult = sqlAllArr[0];
                if (!thisParam.trim().isEmpty()) {
                    String[] paramArr = thisParam.split(", ");
                    for (String paramVar : paramArr) {
                        sqlResult = sqlResult.replaceFirst("\\?", "'" + paramVar + "'");
                    }
                }
                sqlAllArr[1] = sqlResult;
            } else if ((matcher = paramTypeP.matcher(lineCleared)).find()) {
                String thisNum = matcher.group(1);
                String thisParamType = matcher.group(2);
                map.computeIfAbsent(thisNum, k -> new String[4])[3] = thisParamType;
            }
        }
        return map;
    }
}
