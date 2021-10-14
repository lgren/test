package base;

import com.lgren.util.LIOUtil;
import com.lgren.util.UpOrDownUtil;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * IO测试
 * @author lgren
 * @since 2020-06-04 11:28 上午
 */
public class IOTest {
    @Test
    public void name() throws IOException {
        String str = "你好呀";
        try (InputStream in = new ByteArrayInputStream(str.getBytes());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            UpOrDownUtil.copyMoreOut(in, out);
            out.close();
            byte[] bytes = out.toByteArray();
            System.out.println(new String(bytes));
        }
    }

    @Test
    public void name1() throws IOException {
        String str = "你好呀";
        try (InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(str.getBytes()))) {
            int data;
            while ((data = in.read()) != -1) {
                System.out.printf(String.valueOf(((char) data)));
            }
        }
    }

    @Test
    public void name2() throws IOException {
        String str = "你好呀还行吧换个行吧";
        try (InputStream in = new ByteArrayInputStream(str.getBytes());
             InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
             BufferedReader bf = new BufferedReader(isr);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            String newLine;
            bf.mark(11);
            while((newLine = bf.readLine()) != null){
                System.out.println(newLine.length() + newLine+"\n");
            }
            bf.reset();
            bf.mark(8);
            while((newLine = bf.readLine()) != null){
                System.out.println(newLine+"\n");
            }
            bf.reset();
            while((newLine = bf.readLine()) != null){
                System.out.println(newLine+"\n");
            }

            // UpOrDownUtil.copyMoreOut(in, out);
            // System.out.println(new String(out.toByteArray()));

            // BufferedInputStream bin = new BufferedInputStream(new FileInputStream(""));
            // bin.read();
            // BufferedOutputStream bout = new BufferedOutputStream(out);
            // bout.write(12);
            // BufferedInputStream bin = new BufferedInputStream(in);
            // bin.read();
        }
    }

    @Test
    public void name3() throws IOException {
        String str = "nihaoya, womeixiangdaishizheyangzide.";
        try (BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(str.getBytes()), 24)) {
            bis.mark(38);
            System.out.println(new String(LIOUtil.readBytes(bis, false)));
            System.out.println("----------------------------------------");
            bis.reset();
            System.out.println(new String(LIOUtil.readBytes(bis, false)));
            // System.out.println("----------------------------------------");
            // bis.reset();
            // System.out.println(new String(LIOUtil.readBytes(bis, false)));
            // System.out.println("----------------------------------------");
            // bis.reset();
            // System.out.println(new String(LIOUtil.readBytes(bis, false)));
        }
    }

    @Test
    public void name4() {
        try {
            // 初始化一个字节数组，内有10个字节的数据
            byte[] bytes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
            // 用一个ByteArrayInputStream来读取这个字节数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            // 将ByteArrayInputStream包含在一个BufferedInputStream
            BufferedInputStream bis = new BufferedInputStream(in, 10);

            //超出缓冲区，情景4
            bis.mark(12);
            // bis.mark(11);// 报错，请自己分析一下，很容易的

            int c;
            while ( (c = bis.read()) != -1) {
                System.out.print(c + ",");
            }

            System.out.println("\nreset");
            bis.reset();
            while ( (c = bis.read()) != -1) {
                System.out.print(c + ",");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
