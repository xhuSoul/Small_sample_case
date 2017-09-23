package weightcala;


public class netweight {
//	 // 规则一异常的ip数的
//	 private int unnomalipsum = NetRuleCala.getUnnomalMap().size();
//	
//	 // 规则二异常ip包的平均数
//	 private int unnomalippackegeaverage = NetRuleCala.getUnnomalipPackegeAverage();
//	
//	 // 规则三服务率的获取
//	 private float avesever = NetRuleCala.getAverageSever();
//	
//	 // 规则四获取25中的CPU变化率的平均值
//	 private float cpugradientave=NetRuleCala.getCPUGradientAve();
	 
	 
	
public static double getNetWeight(int unnomalipsum,int unnomalippackegeaverage,float  avesever,float cpugradientave){
	
	double netforwight = unnomalipsum * 0.125 + unnomalippackegeaverage* 0.375
			+ avesever* 0.4165 +  cpugradientave* 0.0835;
			System.out.println("计算的出的网络层值"+netforwight);
	return netforwight;
	
}
}
