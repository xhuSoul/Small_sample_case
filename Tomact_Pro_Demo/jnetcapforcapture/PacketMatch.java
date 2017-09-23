package jnetcapforcapture;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;

import java.util.Map;

import org.jnetpcap.packet.JPacket;

import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Http.Request;
import org.jnetpcap.protocol.tcpip.Http.Response;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

//���Ĵ�����
public class PacketMatch {

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
		//ipsum++;
		if (packet.hasHeader(http)) {
			httpsum++;
		handleHttp(packet);
		}
		
		if (packet.hasHeader(ip)) {
			ipsum++;// ��¼�ܵ�����
		//	handleIp(packet);

		}

		if (packet.hasHeader(udp)) {
			
			udpsum++;
		}

		if (packet.hasHeader(tcp)) {
			tcpsum++;
		}

		
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
		if (PacketMatch.getIp().contains(dstIP)) {
			// ��ȡ���հ�������
			countacceptpack++;
			// System.out.println("��ȡ����������"+countacceptpack);

		} else if (PacketMatch.getIp().contains(srcIP)) {
			countsendpack++;
			// System.out.println("���Ͱ���������"+countsendpack);
		}

	}

	private void handleHttp(JPacket packet) {
		packet.getHeader(http);
		if (http.hasField(Request.Accept)) {
			httprequestconut++;
//			System.out.println("���Ͱ�http"+":"+httprequestconut);
		} else if (http.hasField(Response.ResponseCode)) {
			httpresponsecount++;
		}
	}

	// ��ȡ������
	public static String getIp() {

		String string = null;

		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface nif = netInterfaces.nextElement();
				Enumeration<InetAddress> iparray = nif.getInetAddresses();
				while (iparray.hasMoreElements()) {
					string = string + iparray.nextElement().getHostAddress();

				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return string;

	}

	public static int getCountacceptpack() {
		return countacceptpack;
	}

	public static void setCountacceptpack(int countacceptpack) {
		PacketMatch.countacceptpack = countacceptpack;
	}

	public static int getCountsendpack() {
		return countsendpack;
	}

	public static void setCountsendpack(int countsendpack) {
		PacketMatch.countsendpack = countsendpack;
	}

	public static int getHttpresponsecount() {
		return httpresponsecount;
	}

	public static void setHttpresponsecount(int httpresponsecount) {
		PacketMatch.httpresponsecount = httpresponsecount;
	}

	public static int getHttprequestconut() {
		return httprequestconut;
	}

	public static void setHttprequestconut(int httprequestconut) {
		PacketMatch.httprequestconut = httprequestconut;
	}

	public static PacketMatch getPm() {
		return pm;
	}

	public static void setPm(PacketMatch pm) {
		PacketMatch.pm = pm;
	}

	public Tcp getTcp() {
		return tcp;
	}

	public void setTcp(Tcp tcp) {
		this.tcp = tcp;
	}

	public Http getHttp() {
		return http;
	}

	public void setHttp(Http http) {
		this.http = http;
	}

	public static Map<String, Integer> getMapfordosip() {
		return mapfordosip;
	}

	public static void setMapfordosip(Map<String, Integer> mapfordosip) {
		PacketMatch.mapfordosip = mapfordosip;
	}

	public void setIp(Ip4 ip) {
		this.ip = ip;
	}

	public static PacketMatch getInstance() {
		if (pm == null) {
			pm = new PacketMatch();
		}
		return pm;
	}

	public static int getIpsum() {
		return ipsum;
	}

	public static void setIpsum(int ipsum) {
		PacketMatch.ipsum = ipsum;
	}

	public static int getUdpsum() {
		return udpsum;
	}

	public static void setUdpsum(int udpsum) {
		PacketMatch.udpsum = udpsum;
	}

	public static int getTcpsum() {
		return tcpsum;
	}

	public static void setTcpsum(int tcpsum) {
		PacketMatch.tcpsum = tcpsum;
	}

	public static int getHttpsum() {
		return httpsum;
	}

	public static void setHttpsum(int httpsum) {
		PacketMatch.httpsum = httpsum;
	}



}