package jnetcapforcapture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;


public class CaptureCore extends Thread {
	private static Pcap pcap;

	// 获取的值得数据存储类

	private static List<Float> netsever = new ArrayList<Float>();
	private static List<Float> websever = new ArrayList<Float>();
	//统计流量
//	private static List<Integer> Real_Time_Array=new ArrayList<Integer>();
	private static double []Real=new double[25];


	// 时间戳
	private static long start = System.currentTimeMillis();
	private static long start2 = System.currentTimeMillis();
	private static long start3 = System.currentTimeMillis();
	private static boolean s;

	private static MyPcapPacketHandler<Object> myhandler = new MyPcapPacketHandler<Object>();// 处理包的类

	@Override
	public void run() {
		List<PcapIf> devs = new ArrayList<PcapIf>();
		StringBuilder errsb = new StringBuilder();
		int r = Pcap.findAllDevs(devs, errsb);
		int snaplen = Pcap.DEFAULT_SNAPLEN;// 长度65536
		int flags = Pcap.MODE_PROMISCUOUS;// 混杂模式
		int timeout = 1;
		PcapIf device = devs.get(1);

		if (r != Pcap.OK || devs.isEmpty()) {
			System.out.println("未获取列表");
		}

		pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errsb); // 打开网卡

		// TODO Auto-generated method stub
		try {
//			while (true) {

				s = true;
//				Clear.clearValue();// 将所有的值清空
				System.out.println("********************************");
				CaptureCore.startCaptureAt();
//			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		CaptureCore sCaptureCore=new CaptureCore();
		sCaptureCore.start();
		
		
		
	}

	public static void startCaptureAt() throws IOException, Exception {// 选择一个网卡开启抓包
		int i=0;
		while (s) {
		
			// 开始抓包
			pcap.dispatch(1, myhandler, "Jnetcap");
			
			if ((System.currentTimeMillis() - start3) >= 5000) {
				start3 = System.currentTimeMillis();
				System.out.println("输出http的请求"+Packe.getHttprequestconut());
				System.out.println("输出http的响应"+Packe.getHttpresponsecount());
				System.out.println("输出ip包的多少"+Packe.getIpsum());
				System.out.println("输出tcp的多少"+Packe.getTcpsum());
				System.out.println("输出http的包数量"+Packe.getHttpsum());

			}
//			
//

		}
	}

	public static double[] getReal() {
		return Real;
	}

	public static void setReal(double[] real) {
		Real = real;
	}

	public static List<Float> getNetsever() {
		return netsever;
	}

	public static void setNetsever(List<Float> netsever) {
		CaptureCore.netsever = netsever;
	}

	public static List<Float> getWebsever() {
		return websever;
	}

	public static void setWebsever(List<Float> websever) {
		CaptureCore.websever = websever;
	}
}
