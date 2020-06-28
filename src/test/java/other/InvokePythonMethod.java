// package other;
//
// import com.google.common.collect.Lists;
// import com.lgren.util.LgrenUtil;
// import org.junit.Test;
// import org.python.core.*;
// import org.python.util.PythonInterpreter;
//
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
//
// /**
//  * TODO
//  * @author lgren
//  * @since 2020-03-16 5:11 下午
//  */
// public class InvokePythonMethod {
//     // 解决问题: https://blog.csdn.net/xfei365/article/details/50996727
//     @Test
//     public void name1() {
//         PythonInterpreter interpreter = new PythonInterpreter();
//         // interpreter.exec("sys.path.append('/Users/lgren/Project/Python/JYYH/similarity_net/py')");//我们自己写的
//         // interpreter.exec("import jieba");
//         interpreter.execfile("/Users/lgren/Project/Python/JYYH/similarity_net/run.py");
//
//         // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
//         PyFunction pyFunction = interpreter.get("javaTest", PyFunction.class);
//         Lists.newArrayList("英雄联盟怎么玩啊,我不懂啊", "谁知道英雄联盟怎么玩呀啊,我不懂啊");
//         PyArray pyArray = new PyArray(String.class, new String[]{"英雄联盟怎么玩啊,我不懂啊", "谁知道英雄联盟怎么玩呀啊,我不懂啊"});
//         PyString pyString = new PyString("lol怎么玩啊,我不懂啊");
//
//         //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
//         PyObject pyobj = pyFunction.__call__(pyArray, pyString);
//         System.out.println("the answer is: " + pyobj);
//     }
// }
