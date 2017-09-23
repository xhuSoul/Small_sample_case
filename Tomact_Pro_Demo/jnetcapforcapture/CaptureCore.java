package jnetcapforcapture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;


public class CaptureCore extends Thread {
	private static Pcap pcap;

	// ��ȡ��ֵ�����ݴ洢��

	private static List<Float> netsever = new ArrayList<Float>();
	private static List<Float> websever = new ArrayList<Float>();
	//ͳ������
//	private static List<Integer> Real_Time_Array=new ArrayList<Integer>();
	private static double []Real=new double[25];


	// ʱ���
	private static long start = System.currentTimeMillis();
	private static long start2 = System.currentTimeMillis();
	private static long start3 = System.currentTimeMillis();
	private static boolean s;

	private static MyPcapPacketHandler<Object> myhandler = new MyPcapPacketHandler<Object>();// ���������

	@Override
	public void run() {
		List<PcapIf> devs = new ArrayList<PcapIf>();
		StringBuilder errsb = new StringBuilder();
		int r = Pcap.findAllDevs(devs, errsb);
		int snaplen = Pcap.DEFAULT_SNAPLEN;// ����65536
		int flags = Pcap.MODE_PROMISCUOUS;// ����ģʽ
		int timeout = 1;
		PcapIf device = devs.get(1);

		if (r != Pcap.OK || devs.isEmpty()) {
			System.out.println("δ��ȡ�б�");
		}

		pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errsb); // ������

		// TODO Auto-generated method stub
		try {
//			while (true) {

				s = true;
//				Clear.clearValue();// �����е�ֵ���
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

	public static void startCaptureAt() throws IOException, Exception {// ѡ��һ����������ץ��
		int i=0;
		while (s) {
		
			// ��ʼץ��
			pcap.dispatch(1, myhandler, "Jnetcap");
			
			if ((System.currentTimeMillis() - start3) >= 5000) {
				start3 = System.currentTimeMillis();
				System.out.println("���http������"+Packe.getHttprequestconut());
				System.out.println("���http����Ӧ"+Packe.getHttpresponsecount());
				System.out.println("���ip���Ķ���"+Packe.getIpsum());
				System.out.println("���tcp�Ķ���"+Packe.getTcpsum());
				System.out.println("���http�İ�����"+Packe.getHttpsum());

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
