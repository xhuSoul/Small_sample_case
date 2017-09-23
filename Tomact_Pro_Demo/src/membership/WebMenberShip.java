package membership;

import com.xhu.www.ServiceRate_Servelet;

public class WebMenberShip
{

	public static int getWebRuleOne(float anrc)
	{
		int j = 0;
		int i = 0;
		i = (int)anrc;
		if (50<= i && i <100)
		{
			j = 1;

		} else if (300 > i && i >=100)
		{
			j = 2;

		} else if (i >=300 )
		{
			j = 3;
		}
		
		return j;
	}
	
	public static int getWebRuleTwo(float sever)
	{
		int j = 0;
		float i = 0;
		i = sever;
		if (0.96>= i && i >=0.90)
		{
			j = 1;

		} else if (0.90> i && i >=0.85)
		{
			j = 2;

		} else if (i<0.85 )
		{
			j = 3;
		}
		
		return j;
	}
	
	public static int getCPUService(double s)
	{
		int cpuservice = 0;
		double service = s;
		if (service <=0.5&&service>=1) {
			cpuservice = 1;
		} else if (service >1&&service<2) {
			cpuservice = 2;
		} else if (service >=2) {
			cpuservice = 3;
		}
		return cpuservice;
	}
	


	
}
