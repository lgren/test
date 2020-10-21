package socket.base1;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

/**
 * 客户端
 * @author lgren
 * @since 2020-09-11 11:45 上午
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            try {
                System.out.println("连接到主机：localhost ，端口号：" + 2222);
                Socket client = new Socket("localhost", 2222);
                System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                out.writeUTF("Hello from " + client.getLocalSocketAddress());
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println("服务器响应： " + in.readUTF());
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void name() {
        try {
            System.out.println("连接到主机：localhost ，端口号：" + 2222);
            Socket client = new Socket("localhost", 2222);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
