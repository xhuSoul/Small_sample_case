package UI;

import jnetcapforcapture.Packe;
import layoutrule.NetRuleCala;

public class Test extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub

		/*	用户访问IP
		攻击流量
		应用层攻击威胁程度 
		网络层攻击威胁程度
		CPU使用率
		服务率


		ANRC值
		异常IP数（攻击数）
		TCP包
		HTTP包
		UDP包
		总接受包*/
		
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
//				System.out.println("攻击的总流量"+PacketMatch.getIpsum());
//				System.out.println("http的接收包"+PacketMatch.getHttprequestconut());
//				System.out.println("http的发送包"+PacketMatch.getHttpresponsecount());
//				System.out.println("http的http的包"+PacketMatch.getHttpsum());
//				System.out.println("tcp包"+PacketMatch.getTcpsum());
//				System.out.println("udp包"+PacketMatch.getUdpsum());
//				System.out.println("异常ip的总数的"+NetRuleCala.getUnnomalMap().size());
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
