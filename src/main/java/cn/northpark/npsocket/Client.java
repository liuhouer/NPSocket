package cn.northpark.npsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author w_zhangyang
 * socket client和server进行消息通信：client端
 */
public class Client {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		Socket socket = new Socket();
		socket.setSoTimeout(3000);
		
		
		socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000),3000);
		
		System.out.println("已发起服务器连接~");
		System.out.println("客户端信息："+socket.getLocalAddress() +",端口"+socket.getLocalPort());
		System.out.println("服务端信息："+socket.getInetAddress()+",端口"+socket.getPort());
		
		
		
		try {
			todo(socket);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常关闭");
		}
		
		
		//关闭资源
		socket.close();
		System.out.println("客户端退出~");
		
	}

	private static void todo(Socket socket) throws IOException{
		// TODO Auto-generated method stub
		//获取键盘的输入流
		InputStream in = System.in;
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		
		//得到socket输出流 转换为打印流
		OutputStream outputStream = socket.getOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		
		//得到socket输入流 转换为 读取流
		InputStream inputStream = socket.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		
		boolean flag = true;
		
		
		do {
			//读取键盘数据，发送到服务器
			String str = input.readLine();
			printStream.println(str);
			
			
			//读取服务器的返回信息
			String echo = bufferedReader.readLine();
			
			if("bye".equalsIgnoreCase(echo)){
				flag = false;
			}else {
				System.out.println(echo);
			}
		} while (flag);
		
		
		inputStream.close();
		outputStream.close();
	}

}
