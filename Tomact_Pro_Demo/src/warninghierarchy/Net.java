package warninghierarchy;

import com.xhu.www.SuddenVisit_Servelet;

import cpu.AllInfo;
import jnetcapforcapture.CaptureCore;
import jnetcapforcapture.Packe;
import layoutrule.NetRuleCala;
import layoutrule.WebRuleCala;
import membership.NetRuleMenberShip;
import weightcala.netweight;

public class Net {

	public static String  Netcalculationweight() {
		//����һ�쳣ip����		
		int unnomalipsum = NetRuleMenberShip.getNetRuleOne(NetRuleCala.getUnnomalMap().size());
		System.out.println("�쳣��IP����"+unnomalipsum);

//һ��ʱ���ڰ���������
//		int packetsum = Packe.getIpsum();
		int packetsum=NetRuleMenberShip.getNetRuleTwo(Packe.getIpsum());
		System.out.println("һ��ʱ���ڵ��ܰ�"+packetsum);

		// �����������ʵĻ�ȡ
		float avesever = NetRuleMenberShip.getServiceforddos(NetRuleCala.getAverageSever(CaptureCore.getNetsever()));

		// �����Ļ�ȡ25�е�CPU�仯�ʵ�ƽ��ֵ
		float cpugradientave = NetRuleMenberShip.getCPUService(WebRuleCala.getCPUGradientAve(AllInfo.getCpusever()));

		double netforwight2 = netweight.getNetWeight(unnomalipsum, packetsum, avesever, cpugradientave);

		if (netforwight2 > 1.1 && netforwight2 <= 1.375) {
			System.out.println("��ɫԤ��");
			SuddenVisit_Servelet.setNetAttack("��ɫԤ��");
			return "��ɫԤ��";
//			UI.getNetBule().setBackground(Color.BLUE);
		} else if (netforwight2 > 1.375 && netforwight2 <= 2.125 && packetsum != 3 ) {
			//System.out.println("��ɫԤ��");
//			UI.getNetYellow().setBackground(Color.YELLOW);
			SuddenVisit_Servelet.setNetAttack("��ɫԤ��");
			return "��ɫԤ��";
		} else if ((netforwight2 > 2.125 && netforwight2 <= 3) && packetsum == 3 && avesever == 3) {
			System.out.println("��ɫԤ��");
			SuddenVisit_Servelet.setNetAttack("��ɫԤ��");
			return "��ɫԤ��";
//			UI.getNetRed().setBackground(Color.RED);
		} else {
			System.out.println("����");
			SuddenVisit_Servelet.setNetAttack("����");
			return "����";
//		UI.getNetNormal().setBackground(Color.GREEN);
			

			
			
		}

	}

}
