package work.jyyh.archives.db.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * TODO
 * 注意 为了支持英文
 *  1.aa10中的aaa103和aaa101需要调整大小为100
 *  2.uh70中YUH702需要调整大小为100
 * @author lgren
 * @since 2020-12-22 11:51 上午
 */
public class HandleTables {
    // private static final String dictPathname = "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/翻译字典.ldict";
    // 最终前版 (?<=(["']))(?:\\.|[^'\\\n])*?[\u4e00-\u9fa5](?:\\.|[^'\\\n])*(?=\1)
    // 查找带有中文的字符串 分割中的[^']这个现目前只能每次修改为" 或者 '
    private final static Pattern FIND_P = Pattern.compile("(?<=([\"']))(?:\\\\.|[^'\\\\\\n])*?[\\u4e00-\\u9fa5](?:\\\\.|[^'\\\\\\n])*(?=\\1)");

    private final static Comparator<String> TRANSLATE_COMPARATOR = (o1, o2) -> o2.length() > o1.length() ? 1 : Objects.equals(o2.length(), o1.length()) ? o2.compareTo(o1) : -1;

    // 20210308使用
    @Test
    public void findAndCheckStr1() {
        String dictPathname = "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict";
        CollUtil.newArrayList(
                "/Users/lgren/Desktop/久远银海/档案国际化/p1.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-1.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-2.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-3.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-4.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-4-1.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-4-2.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p3.sql"
        ).forEach(s -> {
            System.out.println(s);
            findAndCheckBase(s, dictPathname);
        });
        open(dictPathname);
    }

    // 20210308使用
    @Test
    public void handleStr1() {
        String dictPathname = "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict";
        CollUtil.newArrayList(
                "/Users/lgren/Desktop/久远银海/档案国际化/p1.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-1.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-2.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-3.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-4.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-4-1.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p2-4-2.sql",
                "/Users/lgren/Desktop/久远银海/档案国际化/p3.sql"
        ).forEach(s -> {
            System.out.println(s);
            handle(s, "/Users/lgren/Desktop/久远银海/档案国际化/%1$s/" + FileUtil.mainName(s) + ".sql", dictPathname);
        });
    }

    @Test
    public void findAndCheckStr() {
        String dictPathname = "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict";
        CollUtil.newArrayList(
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/aa10.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ab01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ac01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_blob_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_calendars.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_cron_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_fired_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_job_details.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_job_msgs.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_locks.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_paused_trigger_grps.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_scheduler_state.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_simple_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_simprop_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/seq.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/serveraddress.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/signrecord.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taadminyab139scope.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tabseq.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tacommonmenu.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconfig.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconfigsyspath.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconsolemodule.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconsolemodulelocation.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconsolemoduleprivilege.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tadataaccessdimension.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tafield.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tafieldauthrity.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/talimitrate.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/talocalcacheversion.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tamanagermg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tamenu.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tamenupositionyab003.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tamessagestate.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taonlinelog.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taonlinestatlog.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taorg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taorgmg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taposition.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tarunqianad52reference.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tarunqianprintsetup.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tashareposition.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tauser.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tauserposition.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tayab003levelmg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tayab003scope.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tayab139mg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ud01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ud02.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ud03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ud10.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0a.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0b.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0c.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0d.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0e.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0f.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0g.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0h.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0j.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0k.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0m.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0n.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0p.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0q.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0r.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue02.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue04.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue05.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue05a1.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue06.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue07.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue08.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue09.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue10.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue16.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue18.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue19.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue20.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue21.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue30.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf04.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf05.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf05a1.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf06.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf07.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf11.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf12.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf13.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf14.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf20.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf21.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf22.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf23.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf24.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf25.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf26.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf27.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf28.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf29.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug6a.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug6c.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug30.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug31.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug32.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug33.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug34.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug40.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug42.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug43.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug44.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug45.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug46.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug50.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug52.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug53.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug54.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug55.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug56.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug57.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug58.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug59.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug60.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug61.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug62.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug63.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug64.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug65.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug69.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7b.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7g.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7h.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7j.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7k.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7l.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7m.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7n.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh70.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh71.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh74.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh75.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh76.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh77.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh78.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh79.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj00.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj02.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj04.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj05.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj08.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj14.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur02.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur04.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur05.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur06.sql"
        ).forEach(s -> {
            System.out.println(s);
            findAndCheckBase(s, dictPathname);
        });
        open(dictPathname);
    }

    @Test
    public void handleStr() {
        String dictPathname = "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict";
        CollUtil.newArrayList(
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/aa10.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ab01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ac01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_blob_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_calendars.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_cron_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_fired_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_job_details.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_job_msgs.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_locks.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_paused_trigger_grps.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_scheduler_state.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_simple_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_simprop_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/qrtz_triggers.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/seq.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/serveraddress.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/signrecord.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taadminyab139scope.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tabseq.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tacommonmenu.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconfig.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconfigsyspath.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconsolemodule.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconsolemodulelocation.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taconsolemoduleprivilege.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tadataaccessdimension.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tafield.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tafieldauthrity.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/talimitrate.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/talocalcacheversion.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tamanagermg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tamenu.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tamenupositionyab003.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tamessagestate.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taonlinelog.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taonlinestatlog.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taorg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taorgmg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/taposition.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tarunqianad52reference.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tarunqianprintsetup.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tashareposition.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tauser.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tauserposition.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tayab003levelmg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tayab003scope.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/tayab139mg.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ud01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ud02.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ud03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ud10.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0a.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0b.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0c.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0d.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0e.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0f.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0g.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0h.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0j.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0k.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0m.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0n.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0p.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0q.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue0r.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue02.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue04.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue05.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue05a1.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue06.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue07.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue08.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue09.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue10.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue16.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue18.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue19.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue20.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue21.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ue30.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf04.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf05.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf05a1.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf06.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf07.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf11.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf12.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf13.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf14.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf20.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf21.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf22.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf23.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf24.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf25.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf26.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf27.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf28.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uf29.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug6a.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug6c.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug30.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug31.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug32.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug33.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug34.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug40.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug42.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug43.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug44.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug45.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug46.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug50.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug52.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug53.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug54.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug55.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug56.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug57.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug58.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug59.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug60.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug61.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug62.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug63.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug64.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug65.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ug69.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7b.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7g.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7h.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7j.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7k.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7l.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7m.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh7n.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh70.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh71.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh74.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh75.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh76.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh77.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh78.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uh79.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj00.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj02.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj04.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj05.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj08.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/uj14.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur01.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur02.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur03.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur04.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur05.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data/ur06.sql"
        ).forEach(s -> {
            System.out.println(s);
            handle(s, "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data_translate/%1$s/" + FileUtil.mainName(s) + ".sql", dictPathname);
        });
    }

    @Test
    public void findAndCheck() {
        CollUtil.newArrayList(
                // Select.DAZH_TABLES_SQL,
                // Select.DAZH_BUSINESS_DATA_SQL,
                // Select.DAZH_FRAME_DATA_SQL
                Select.DZDA_HW_20210125_FRAME
        ).forEach(this::findAndCheckBase);
        open(Select.DZDA_HW_20210125_FRAME.dictPathname);
    }

    @Test
    public void handle() {
        CollUtil.newArrayList(
                // Select.DAZH_TABLES_SQL,
                // Select.DAZH_BUSINESS_DATA_SQL,
                // Select.DAZH_FRAME_DATA_SQL
                Select.DZDA_HW_20210125_FRAME
        ).forEach(this::handle);
    }

    private void findAndCheckBase(Select select) {
        findAndCheckBase(select.oriPathname, select.dictPathname);
    }

    private void findAndCheckBase(String tables, String dictPathname) {
        String content = FileUtil.readUtf8String(tables);
        List<String> list = ReUtil.findAllGroup0(FIND_P, content);

        Object[] dictMappingArr = {Locale.SIMPLIFIED_CHINESE, Locale.US};
        File dictFile = getOrCreateFile(dictPathname, "#" + ArrayUtil.join(dictMappingArr, "<=>") + "\n");
        LDict lDict = new LDict(dictFile, dictMappingArr);
        lDict.addIfNot(list);
        if (lDict.checkKeyAndValueRepeat()) {
            throw new RuntimeException("具有重复");
        }
    }

    private void handle(Select select) {
        handle(select.oriPathname, select.outPathnameFormat, select.dictPathname);
    }

    private void handle(String tables, String tablesEnUs, String dictPathname) {
        String content = FileUtil.readUtf8String(tables);
        List<String> list = ReUtil.findAllGroup0(FIND_P, content);

        File dictFile = getOrCreateFile(dictPathname, "# 翻译字典\n\n");
        LDict lDict = new LDict(dictFile, new Object[]{Locale.SIMPLIFIED_CHINESE, Locale.US});
        lDict.addIfNot(list);
        if (lDict.checkKeyAndValueRepeat()) {
            throw new RuntimeException("具有重复");
        }

        // key1为语言, key2为源寻找数据, v为翻译后数据
        LTable<Object, String> replaceDict = new LTable<>();
        // k1: 类型 k: 源寻找数据 v1: 值
        lDict.getKVTable().forEach((k,v) -> v.forEach((k1, v1) -> replaceDict.put(v1.getValue(), k1, k)));

        // k1为语言, k2为源寻找数据, v为翻译后数据 v1为
        replaceDict.forEach((k1, i1) -> {
            String resultContent = StrUtil.replace(content, FIND_P, p -> Optional.of(i1.get(p.group(0)))
                    .map(s -> s.replaceAll("'", "''"))
                    .orElseThrow(() -> new RuntimeException("不存在")));
            FileUtil.writeUtf8String(resultContent, String.format(tablesEnUs, k1));
        });
    }

    public static File getOrCreateFile(String fileName, String notExistsDefaultContent) {
        File file = Optional.of(new File(fileName)).filter(f -> f.getParentFile().exists()).orElseThrow(() -> new RuntimeException("目录不存在"));
        if (!file.exists()) {
            FileUtil.writeUtf8String(notExistsDefaultContent, file);
        }
        return file;
    }

    public static void open(String pathOrPathname) {
        String osName = System.getProperty("os.name");
        if (osName == null) {
            return;
        }
        try {
            if (osName.contains("Mac")) {
                Runtime.getRuntime().exec("open " + pathOrPathname);
            } else if (osName.contains("Windows")) {
                Runtime.getRuntime().exec("cmd /c start " + pathOrPathname);
            } else {
                System.out.println("未找到文件输出目录:" + pathOrPathname);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    enum Select {
        DAZH_TABLES_SQL("/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/dazh_hw.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/%1$s/dazh_hw_%1$s.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/翻译字典.ldict"),

        DAZH_BUSINESS_DATA_SQL("/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/dazh_hw_business_data.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/%1$s/dazh_hw_business_data_%1$s.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/翻译字典.ldict"),

        DAZH_FRAME_DATA_SQL("/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/dazh_hw_frame_data.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/%1$s/dazh_hw_frame_data_%1$s.sql",
                "/Users/lgren/Project/Java/My/AGit/test/src/main/resource/work/档案国际化/sql/翻译字典.ldict"),

        DAZH_FRAME_PG_SQL("/Users/lgren/Desktop/档案国际化/pg/ta402-postgresql-all.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/ta402-postgresql-all_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DAZH_PG_SQL_20210125("/Users/lgren/Desktop/档案国际化/pg/pg_hw_20210125.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/pg_hw_20210125_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DAZH_PG_SQL_20210125_1("/Users/lgren/Desktop/档案国际化/pg/pg_hw_20210125_1.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/pg_hw_20210125_1_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DAZH_PG_SQL_20210125_2("/Users/lgren/Desktop/档案国际化/pg/pg_hw_20210125_2.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/pg_hw_20210125_2_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DAZH_PG_SQL_FRAME_20210125("/Users/lgren/Desktop/档案国际化/pg/pg_hw_frame_20210125.sql",
                "/Users/lgren/Desktop/档案国际化/pg/%1$s/pg_hw_frame_20210125_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        DZDA_HW_20210125_FRAME("/Users/lgren/Desktop/档案国际化/pg/dzda_hw_20210125_frame.sql",
                "/Users/lgren/Desktop/档案国际化/pg/dzda_mysql_data_translate/dzda_hw_20210125_frame_%1$s.sql",
                "/Users/lgren/Desktop/档案国际化/pg/翻译字典.ldict"),

        ;
        private String oriPathname;
        private String outPathnameFormat;
        private String dictPathname;

        Select(String oriPathname, String outPathnameFormat, String dictPathname) {
            this.oriPathname = oriPathname;
            this.outPathnameFormat = outPathnameFormat;
            this.dictPathname = dictPathname;
        }
    }

}
