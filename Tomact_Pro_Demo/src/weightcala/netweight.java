package weightcala;


public class netweight {
//	 // ����һ�쳣��ip����
//	 private int unnomalipsum = NetRuleCala.getUnnomalMap().size();
//	
//	 // ������쳣ip����ƽ����
//	 private int unnomalippackegeaverage = NetRuleCala.getUnnomalipPackegeAverage();
//	
//	 // �����������ʵĻ�ȡ
//	 private float avesever = NetRuleCala.getAverageSever();
//	
//	 // �����Ļ�ȡ25�е�CPU�仯�ʵ�ƽ��ֵ
//	 private float cpugradientave=NetRuleCala.getCPUGradientAve();
	 
	 
	
public static double getNetWeight(int unnomalipsum,int unnomalippackegeaverage,float  avesever,float cpugradientave){
	
	double netforwight = unnomalipsum * 0.125 + unnomalippackegeaverage* 0.375
			+ avesever* 0.4165 +  cpugradientave* 0.0835;
			System.out.println("����ĳ��������ֵ"+netforwight);
	return netforwight;
	
}
}
