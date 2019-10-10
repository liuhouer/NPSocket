package cn.northpark.npsocket.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author w_zhangyang
 * socket client和server进行消息通信：server端
 */
public class Server {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(2000);
		
		System.out.println("服务器准备就绪~");
		System.out.println("服务器信息：~"+server.getInetAddress()+",端口："+server.getLocalPort());
		
		for (;;) {
			//等待客户端连接
			Socket client = server.accept();
			
			//客户端构建异步线程
			ClientHander clientHander = new ClientHander(client);
			clientHander.start();
		}
	
	}
	
	
	/**
	 * @author w_zhangyang
	 * 客户端的处理
	 */
	private static class ClientHander extends Thread{
		private Socket socket;
		private boolean flag = true;
		ClientHander(Socket socket) {
			// TODO Auto-generated constructor stub
			this.socket = socket;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println("有客户端连接："+socket.getInetAddress()+socket.getPort());
			
			
			
			try {
				//输出流
				PrintStream output = new PrintStream(socket.getOutputStream());
				
				//输入流
				BufferedReader input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				
				do {
					//从客户端拿数据
					String readLine = input.readLine();
					
					if("bye".equalsIgnoreCase(readLine)) {
						flag = false;
					}else {
						System.out.println(readLine);
						output.println("长度："+readLine.length());
					}
				} while (flag);
				
				
				output.close();
				input.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("客户端异常断开");
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("客户端退出~"+socket.getInetAddress()+socket.getPort());
		}
		
		
	}
}
