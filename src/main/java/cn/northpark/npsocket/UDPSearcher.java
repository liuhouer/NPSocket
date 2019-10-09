package cn.northpark.npsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author w_zhangyang
 * UDP搜索者，用于搜索服务支持方
 */
public class UDPSearcher {

	public static void main(String[] args) throws IOException {

		System.out.println("UDP provider started 。。");
		//作为搜索者，系统自动分配端口
		DatagramSocket ds = new DatagramSocket();
		
		
		//构建一份请求数据
		String requestData = "hello world !";
		byte[] requestDataByte = requestData.getBytes();
		DatagramPacket requestPack = new DatagramPacket(requestDataByte, requestDataByte.length);
		
		requestPack.setAddress(InetAddress.getLocalHost());
		requestPack.setPort(20000);
		
		ds.send(requestPack);

		//构建接收实体
		final byte[] buf = new byte[512];
		DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
		ds.receive(receivePack);
		
		//打印接收到的信息和发送者的信息
		//发送者的IP
		String ip = receivePack.getAddress().getHostAddress();
		int port = receivePack.getPort();
		int dataLen = receivePack.getLength();
		String data = new String(receivePack.getData());
		System.out.println("UDPSearcher receive from ip:"+ip+"\t port:"+port+"\t data:"+data);
		//完成
		System.out.println("UDPSearcher finished...");
		
		//关闭
		ds.close();
	
	}

}
