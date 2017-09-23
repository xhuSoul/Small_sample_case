package jnetcapforcapture;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;

public class MyPcapPacketHandler<Object> implements JPacketHandler<Object> {

	@Override
	public void nextPacket(JPacket packet, Object arg1) {
		// TODO Auto-generated method stub
//		System.out.println("++++++++++++++++++++++++++");
		// TODO Auto-generated method stub
//		PacketMatch packetMatch = PacketMatch.getInstance();
		Packe p=new Packe();
		
	p.handlePacket(packet);
	}

}