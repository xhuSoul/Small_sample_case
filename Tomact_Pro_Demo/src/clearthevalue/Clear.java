package clearthevalue;


import jnetcapforcapture.Packe;


public class Clear {

	public static void clearValue() {

		Packe.setCountacceptpack(0);

		Packe.setCountsendpack(0);

		Packe.setHttpresponsecount(0);

		Packe.setHttprequestconut(0);

		Packe.getMapfordosip().clear();
		
		Packe.setIpsum(0);

		Packe.setUdpsum(0);

		Packe.setTcpsum(0);

		Packe.setHttpsum(0);

	}

}
