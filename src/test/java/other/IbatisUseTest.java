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
                "" +
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
