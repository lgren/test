package socket.thead1;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 客户端
 * @author lgren
 * @since 2020-09-11 11:45 上午
 */
public class Client {
    public static void main(String[] args) {
        IntStream.range(0, 20).parallel().forEach(i -> {
            try {
                Socket s = new Socket("127.0.0.1",8888);

                //构建IO
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                //向服务器端发送一条消息
                bw.write("客户端" + i + "输入消息~~~\n");
                bw.flush();

                //读取服务器返回的消息
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String mess = br.readLine();
                System.out.println("服务器："+mess);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void name() {
        try {
            Socket s = new Socket("127.0.0.1",8888);

            //构建IO
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            bw.write("客户端输入消息~~~\n");
            bw.flush();

            //读取服务器返回的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String mess = br.readLine();
            System.out.println("服务器："+mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
