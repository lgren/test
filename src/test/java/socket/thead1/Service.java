package socket.thead1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 * @author lgren
 * @since 2020-09-11 11:37 上午
 */
public class Service extends Thread {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);


        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            while (true) {
                Socket s = ss.accept();
                threadPool.submit(() -> {
                    try {
                        System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");
                        Thread.sleep(1000);
                        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        //读取客户端发送来的消息
                        String mess = br.readLine();
                        System.out.println("客户端：" + mess);
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                        bw.write(mess + "\n");
                        bw.flush();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
