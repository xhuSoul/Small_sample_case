package jnetcapforcapture;



import java.util.HashMap;
import java.util.Map;

import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.JPacket;

import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Http.Request;
import org.jnetpcap.protocol.tcpip.Http.Response;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

//包的处理类
public class Packe {

	// 需要统计的包的数量
	private static int ipsum;
	// 计数接收到的包的数量
	private static int countacceptpack;
	// 发送包的数量
	private static int countsendpack;

	// 计数http层响应的报文数、
	private static int httpresponsecount;
	// 计数http层响应的请求报文的数量
	private static int httprequestconut;

	// 各个包类型数量的统计
	private static int udpsum;
	private static int tcpsum;

	private static int httpsum;

	// 出现的ip,以及接收到的包的数量
	private static Map<String, Integer> mapfordosip = new HashMap<String, Integer>();

	// 需要统计的数据**********************************************************************************

	private static PacketMatch pm;

	// 定义包的类型
	private Ip4 ip = new Ip4();
	private Tcp tcp = new Tcp();
	private Udp udp = new Udp();
	private Http http = new Http();
	
	


	public void handlePacket(JPacket packet) {// 根据包头选择不同的规则
		
		if (packet.hasHeader(ip)) {
			ipsum++;// 记录总的流量
			handleIp(packet);

		}

		if (packet.hasHeader(udp)) {
			
			udpsum++;
		}

		if (packet.hasHeader(tcp)) {
			tcpsum++;
		}

		if (packet.hasHeader(http)) {
			httpsum++;
			handleHttp(packet);
		}
	}

	public static int getIpsum() {
		return ipsum;
	}

	public static void setIpsum(int ipsum) {
		Packe.ipsum = ipsum;
	}

	public static int getCountsendpack() {
		return countsendpack;
	}

	public static void setCountsendpack(int countsendpack) {
		Packe.countsendpack = countsendpack;
	}

	public static int getHttpresponsecount() {
		return httpresponsecount;
	}

	public static void setHttpresponsecount(int httpresponsecount) {
		Packe.httpresponsecount = httpresponsecount;
	}

	public static int getHttprequestconut() {
		return httprequestconut;
	}

	public static void setHttprequestconut(int httprequestconut) {
		Packe.httprequestconut = httprequestconut;
	}

	public static int getUdpsum() {
		return udpsum;
	}

	public static void setUdpsum(int udpsum) {
		Packe.udpsum = udpsum;
	}

	public static int getTcpsum() {
		return tcpsum;
	}

	public static void setTcpsum(int tcpsum) {
		Packe.tcpsum = tcpsum;
	}

	public static int getHttpsum() {
		return httpsum;
	}

	public static void setHttpsum(int httpsum) {
		Packe.httpsum = httpsum;
	}

	public static Map<String, Integer> getMapfordosip() {
		return mapfordosip;
	}

	public static void setMapfordosip(Map<String, Integer> mapfordosip) {
		Packe.mapfordosip = mapfordosip;
	}

	public static PacketMatch getPm() {
		return pm;
	}

	public static void setPm(PacketMatch pm) {
		Packe.pm = pm;
	}

	public Ip4 getIp() {
		return ip;
	}

	public void setIp(Ip4 ip) {
		this.ip = ip;
	}

	public Tcp getTcp() {
		return tcp;
	}

	public void setTcp(Tcp tcp) {
		this.tcp = tcp;
	}

	public Udp getUdp() {
		return udp;
	}

	public void setUdp(Udp udp) {
		this.udp = udp;
	}

	public Http getHttp() {
		return http;
	}

	public void setHttp(Http http) {
		this.http = http;
	}

	public static void setCountacceptpack(int countacceptpack) {
		Packe.countacceptpack = countacceptpack;
	}

	// 处理包的方法
	private void handleIp(JPacket packet) {
		
		// System.out.println("包的总流量"+ipsum);

		packet.getHeader(ip);
		byte[] sIP = new byte[4], dIP = new byte[4];
		sIP = ip.source();
		dIP = ip.destination();

		String srcIP = org.jnetpcap.packet.format.FormatUtils.ip(sIP);
		String dstIP = org.jnetpcap.packet.format.FormatUtils.ip(dIP);

		// 获取IP和对应的IP包的数量

		// System.out.println(string);
		// 如果这个ip不是本机的ip，那么统计ip对应的ip的数量
		
			if (mapfordosip.get(srcIP) == null)
				mapfordosip.put(srcIP, 1);
			else
				mapfordosip.replace(srcIP, mapfordosip.get(srcIP) + 1);
		
		// 判断接收和发送的网络层的包
		// System.out.println("dasfsafsacasfsa");
		if (getIp().equals(dstIP)){
			// 获取接收包的数量
			countacceptpack++;
			// System.out.println("获取包的总流量"+countacceptpack);

		} else if (getIp().equals(srcIP)) {
			countsendpack++;
			// System.out.println("发送包的总流量"+countsendpack);
		}

	}

	private void handleHttp(JPacket packet) {
		packet.getHeader(http); 
		if (http.hasField(Request.Accept_Encoding)){
			httprequestconut++;
//			System.out.println("发送包http"+":"+httprequestconut);
		} else if (http.hasField(Response.ResponseCode)){
			httpresponsecount++;
		}	
	}

	// 获取本机的
	
	public static int getCountacceptpack() {
		return countacceptpack;
	}




}