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

//���Ĵ�����
public class Packe {

	// ��Ҫͳ�Ƶİ�������
	private static int ipsum;
	// �������յ��İ�������
	private static int countacceptpack;
	// ���Ͱ�������
	private static int countsendpack;

	// ����http����Ӧ�ı�������
	private static int httpresponsecount;
	// ����http����Ӧ�������ĵ�����
	private static int httprequestconut;

	// ����������������ͳ��
	private static int udpsum;
	private static int tcpsum;

	private static int httpsum;

	// ���ֵ�ip,�Լ����յ��İ�������
	private static Map<String, Integer> mapfordosip = new HashMap<String, Integer>();

	// ��Ҫͳ�Ƶ�����**********************************************************************************

	private static PacketMatch pm;

	// �����������
	private Ip4 ip = new Ip4();
	private Tcp tcp = new Tcp();
	private Udp udp = new Udp();
	private Http http = new Http();
	
	


	public void handlePacket(JPacket packet) {// ���ݰ�ͷѡ��ͬ�Ĺ���
		
		if (packet.hasHeader(ip)) {
			ipsum++;// ��¼�ܵ�����
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

	// ������ķ���
	private void handleIp(JPacket packet) {
		
		// System.out.println("����������"+ipsum);

		packet.getHeader(ip);
		byte[] sIP = new byte[4], dIP = new byte[4];
		sIP = ip.source();
		dIP = ip.destination();

		String srcIP = org.jnetpcap.packet.format.FormatUtils.ip(sIP);
		String dstIP = org.jnetpcap.packet.format.FormatUtils.ip(dIP);

		// ��ȡIP�Ͷ�Ӧ��IP��������

		// System.out.println(string);
		// ������ip���Ǳ�����ip����ôͳ��ip��Ӧ��ip������
		
			if (mapfordosip.get(srcIP) == null)
				mapfordosip.put(srcIP, 1);
			else
				mapfordosip.replace(srcIP, mapfordosip.get(srcIP) + 1);
		
		// �жϽ��պͷ��͵������İ�
		// System.out.println("dasfsafsacasfsa");
		if (getIp().equals(dstIP)){
			// ��ȡ���հ�������
			countacceptpack++;
			// System.out.println("��ȡ����������"+countacceptpack);

		} else if (getIp().equals(srcIP)) {
			countsendpack++;
			// System.out.println("���Ͱ���������"+countsendpack);
		}

	}

	private void handleHttp(JPacket packet) {
		packet.getHeader(http); 
		if (http.hasField(Request.Accept_Encoding)){
			httprequestconut++;
//			System.out.println("���Ͱ�http"+":"+httprequestconut);
		} else if (http.hasField(Response.ResponseCode)){
			httpresponsecount++;
		}	
	}

	// ��ȡ������
	
	public static int getCountacceptpack() {
		return countacceptpack;
	}




}