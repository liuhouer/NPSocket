package cn.northpark.npsocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author w_zhangyang
 * 	UDP提供者，用于提供服务
 */
public class UDPProvider {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("UDP provider started 。。");
		//作为接受者，指定一个端口用于接收数据
		DatagramSocket ds = new DatagramSocket(20000);
		
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
		System.out.println("UDP provider receive from ip:"+ip+"\t port:"+port+"\t data:"+data);
		
		//构建一份回送数据
		String responseData = "receive data with len :"+dataLen;
		byte[] responseDataByte = responseData.getBytes();
		DatagramPacket responsePack = new DatagramPacket(responseDataByte, responseDataByte.length, receivePack.getAddress(), receivePack.getPort());
		
		ds.send(responsePack);

		//完成
		System.out.println("udp provider finished...");
		
		//关闭
		ds.close();
	}

}
