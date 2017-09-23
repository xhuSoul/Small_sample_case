package warninghierarchy;


import com.xhu.www.SuddenVisit_Servelet;
import cpu.AllInfo;
import jnetcapforcapture.CaptureCore;
import jnetcapforcapture.Packe;
import layoutrule.WebRuleCala;
import membership.WebMenberShip;
import weightcala.webweight;

public class Web {

	public static String Applicationcalculationweight() {
		// ����һ��ȡanrcֵ��������
		int AnrcAve = WebMenberShip.getWebRuleOne(WebRuleCala.getAnrcAve(Packe.getHttpresponsecount(), Packe.getMapfordosip().size()));
		// �����������
		float httpServerAve = WebMenberShip.getWebRuleTwo(WebRuleCala.getHttpServerAve(CaptureCore.getWebsever()));
		// ������cpu�Ļ�ȡ
		float cpuGradientAve = WebMenberShip.getCPUService(WebRuleCala.getCPUGradientAve(AllInfo.getCpusever()));

		double apllicationforwight21 = webweight.getWebWeight(AnrcAve, httpServerAve, cpuGradientAve);

		if (AnrcAve != 3 && httpServerAve == 1 && cpuGradientAve != 3 && apllicationforwight21 >= 1
				&& apllicationforwight21 < 1.418) {
			SuddenVisit_Servelet.setWebAttack("��ɫԤ��");
			 return "��ɫԤ��";
//			System.out.println("��ɫԤ��");
			//UI.getWebBule().setBackground(Color.BLUE);
			
		} else if ((AnrcAve == 2 && httpServerAve == 1 && apllicationforwight21 < 2.109
				&& apllicationforwight21 >= 1.418)
				|| (httpServerAve == 2 && apllicationforwight21 < 2.109 && apllicationforwight21 >= 1.418)) {
			//UI.getWebYellow().setBackground(Color.YELLOW);
			SuddenVisit_Servelet.setWebAttack("��ɫԤ��");
			return "��ɫԤ��";
//			System.out.println("��ɫԤ��");
		} else if ((AnrcAve == 3 && apllicationforwight21 >= 2.109 && apllicationforwight21 <= 3)
				|| (httpServerAve == 3 && apllicationforwight21 >= 2.109 && apllicationforwight21 <= 3) || (AnrcAve != 1
						&& httpServerAve != 1 && apllicationforwight21 >= 2.109 && apllicationforwight21 <= 3)) {
//UI.getWebRed().setBackground(Color.RED);
			SuddenVisit_Servelet.setWebAttack("��ɫԤ��");
			 return "��ɫԤ��";
//			System.out.println("��ɫԤ��");
		} else {
			//UI.getWebNormal().setBackground(Color.GREEN);
			SuddenVisit_Servelet.setWebAttack("����");
		 return "����";
//			System.out.println("����");
		}
	}

}
