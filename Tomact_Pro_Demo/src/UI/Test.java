package UI;

import jnetcapforcapture.Packe;
import layoutrule.NetRuleCala;

public class Test extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub

		/*	�û�����IP
		��������
		Ӧ�ò㹥����в�̶� 
		����㹥����в�̶�
		CPUʹ����
		������


		ANRCֵ
		�쳣IP������������
		TCP��
		HTTP��
		UDP��
		�ܽ��ܰ�*/
		
		while (true) {
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			System.out.println(	Packe.getHttprequestconut());
			System.out.println(Packe.getHttpresponsecount());
			
		}		
	
//			while (true) {
////				System.out.println(PacketMatch.getMapfordosip().size()+"\n");
//				System.out.println("������������"+PacketMatch.getIpsum());
//				System.out.println("http�Ľ��հ�"+PacketMatch.getHttprequestconut());
//				System.out.println("http�ķ��Ͱ�"+PacketMatch.getHttpresponsecount());
//				System.out.println("http��http�İ�"+PacketMatch.getHttpsum());
//				System.out.println("tcp��"+PacketMatch.getTcpsum());
//				System.out.println("udp��"+PacketMatch.getUdpsum());
//				System.out.println("�쳣ip��������"+NetRuleCala.getUnnomalMap().size());
//				System.out.println("s*********************************************************************************");
//				try {
//					sleep(5000);
//				
//			}
//		catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

		
		
	}

}
