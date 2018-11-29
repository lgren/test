package test.poi;

import com.lgren.annotation.FieldName;
import com.lgren.entity.Teacher;
import com.lgren.poi.poi3_17.LWorkbook;
import com.lgren.util.LgrenUtil;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import test.data.TeacherData;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import static java.util.Optional.ofNullable;

/**
 * TODO
 * @author Lgren
 * @create 2018-11-29 10:09
 **/
public class PoiTest {
    @Test
    public void 多线程写poi测试() throws IOException {
        List<Teacher> teacherList = TeacherData.TEACHER_LIST;
        Field[] fields = Teacher.class.getDeclaredFields();// 所有字段
        //        List<Method> methodList = new ArrayList<>(fields.length);// 所有字段对应的get方法
        ConcurrentLinkedDeque<Method> methodList = new ConcurrentLinkedDeque<>();// 所有字段对应的get方法
        LWorkbook lxwb = LWorkbook.XLS;
        // 表头
        for (int i = 0; i < fields.length; i++) {
            FieldName rowNameApi = fields[i].getAnnotation(FieldName.class);
            String rowName = ofNullable(rowNameApi).map(FieldName::name).orElse(fields[i].getName());
            methodList.add(ReflectionUtils.findMethod(Teacher.class, "get" + StringUtils.capitalize(fields[i].getName())));
            lxwb.sheet("one").cell(0, i).setCellValue(rowName).setCellStyle(lxwb.center());
        }
        lxwb.sheet("one").getSheet().createFreezePane(0, 1, 0, 1);// 冻结第一行
        LgrenUtil.forEach(teacherList, (t, i) ->
                LgrenUtil.forEach(methodList, (m, j) ->
                        lxwb.sheet("one").cell(i + 1, j)
                                .setCellValue(ofNullable(ReflectionUtils.invokeMethod(m, t)).map(Object::toString).orElse("--"))
                                .setCellStyle(lxwb.center())
                )
        );
        lxwb.sheet("one").autoSizeColumnByFirstCol();
        lxwb.write("E://测试一下下");
        System.out.println();
    }
}
