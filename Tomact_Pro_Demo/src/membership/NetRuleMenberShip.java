package membership;

import com.xhu.www.ServiceRate_Servelet;

//这个类用来计算隶属度

/*1 ：当不同的异常IP同时访问一个端口的不同异常个数超过10个、50个、100个，对其隶属度取离散值1、2、3。
同理：
2 ：当同一源IP发给同一目的IP的包数量超过1000个、5000个、10000个，对其隶属度取离散值1、2、3。

3 ：当5s内目的IP的平均服务率低于6、4、2，对其隶属度取离散值1、2、3。
4：当进程的CPU用量/IP变化大于0.5、0.25、0.125，对其隶属度取离散值3、2、*/

public class NetRuleMenberShip
{


/*	规则一：异常的ip数
	25秒内，如果超过十个不同的异常IP同时访问一个端口②时，则为DDoS攻击。
	需要的网络属性：端口号、IP数*/

	public static int getNetRuleOne(int ca)
	{
		int capcount =0;
		int i = 0;
		i = ca;
		if (i >= 10 && i<=50)
		{
			capcount = 1;

		} else if (50 > i && i <=100)
		{

			capcount = 2;
		} else if (i > 100)
		{
			capcount = 3;
		}
		
		return capcount;

	}
	
	
	/*	规则二： 异常IP，25秒内包平均数量
	25s内，同一源IP发给目的IP的数量。
	     需要的网络属性：异常的IP的包的平均数量。*/

	public static int getNetRuleTwo(int ca)
	{
		int j = 0;
		int i = 0;
		i = ca;
		if (30000 >= i && i > 20000)
		{
			j = 1;

		} else if (50000 >= i && i >30000)
		{
			j = 2;

		} else if (i > 50000)
		{
			j = 3;
		}
		
		return j;
	}

	
//	25秒内的5个5s平均服务率呈现出明显下降趋势。
	
	public static int getServiceforddos(double s)
	{
		int serviceforddos = 0;
		ServiceRate_Servelet.setServicerate(s);
		System.out.println(s);
		//System.out.println("dsadsa"+ServiceRate_Servelet.getServicerate());
		double service = s;
		if (service > 0 && service <= 1.4) {
			serviceforddos = 1;
		} else if (service > 1.4 && service < 1.6) {
			serviceforddos = 2;
		} else if (service >= 1.6) {
			serviceforddos = 3;
		}
		
		return serviceforddos;
	}
	
	
	/*	规则四：进程的CPU变化
	25s内，当（进程的CPU的变化率/IP数）变小时，则遭受攻击。
	需要的属性：进程的CPU占用率，总的IP量*/
	
	public static int getCPUService(double s)
	{
		int cpuservice = 0;
		double service = s;
		if (service <=5&&service>=2) {
			cpuservice = 1;
		} else if (service >5&&service<10) {
			cpuservice = 2;
		} else if (service >=10) {
			cpuservice = 3;
		}
		return cpuservice;
	}
	

	

	
	
}