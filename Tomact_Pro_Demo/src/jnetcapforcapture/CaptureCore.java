package jnetcapforcapture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

import clearthevalue.Clear;
import layoutrule.NetRuleCala;
import layoutrule.WebRuleCala;
import warninghierarchy.Net;
import warninghierarchy.Web;


public class CaptureCore extends Thread {
	private static Pcap pcap;

	// ��ȡ��ֵ�����ݴ洢��

	private static List<Float> netsever = new ArrayList<Float>();
	private static List<Float> websever = new ArrayList<Float>();
	//ͳ������
//	private static List<Integer> Real_Time_Array=new ArrayList<Integer>();
	private static double []Real=new double[25];


	// ʱ���
	@SuppressWarnings("unused")
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
			while (true) {

				s = true;
				Clear.clearValue();// �����е�ֵ���
				System.out.println("********************************");
				CaptureCore.startCaptureAt();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	


	@SuppressWarnings("unused")
	public static void startCaptureAt() throws IOException, Exception {// ѡ��һ����������ץ��
		int i=0;
		while (s) {
			
			// ��ʼץ��
			pcap.dispatch(1, myhandler, "Jnetcap");
			
			if ((System.currentTimeMillis() - start) >= 5000) {
				start = System.currentTimeMillis();
				// System.out.println("��һ�λ�ȡֵ ");
				float s1 = NetRuleCala.getNetSever(Packe.getCountacceptpack(), Packe.getCountsendpack());
				netsever.add(s1);
				// System.out.println("��·������������"+s1);
				// System.out.println(s1);

				float s2 = WebRuleCala.getHttpServer(Packe.getHttprequestconut(),
						Packe.getHttpresponsecount());
				websever.add(s2);
				// System.out.println("Ӧ�ò�����������"+s2);
				 System.out.println("ͳ����ʷ���������ڴ�"+":"+Packe.getIpsum());
		 

				
				 System.out.println("ץ���İ��а�����ip��"+Packe.getMapfordosip().size());
				 System.out.println("������������"+Packe.getIpsum());
				 System.out.println("���Ͱ�"+Packe.getCountsendpack());
				 System.out.println("���հ�"+Packe.getCountacceptpack());
				 System.out.println("http�Ľ��հ�"+Packe.getHttprequestconut());
				 System.out.println("http�ķ��Ͱ�"+Packe.getHttpresponsecount());
				 System.out.println("tcp��"+Packe.getTcpsum());
				 System.out.println("udp��"+Packe.getUdpsum());
				 System.out.println("�쳣ip��������"+NetRuleCala.getUnnomalMap().size());
				
			
				i++;
				
			}
			

			if (start2 + 5 * 5000 <= System.currentTimeMillis()&&netsever.size()>=5&&websever.size()>=5) {
				start2 = System.currentTimeMillis();
				// System.out.println("������");
//				Net.Netcalculationweight();
				Net.Netcalculationweight();
				Web.Applicationcalculationweight();
				s = false;
			}
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






