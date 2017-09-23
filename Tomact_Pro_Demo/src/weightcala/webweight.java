package weightcala;



public class webweight {
	
	public  static double getWebWeight(int AnrcAve,float  httpServerAve,float cpugradientave){
//		CPUGradientAve
//		HttpServerAve
//		getAnrcAve
		
	double webforwight =  AnrcAve* 0.309 + httpServerAve* 0.528
				+ cpugradientave* 0.109;
		System.out.println("应用层的权重值"+webforwight);
	return webforwight;
		
		
//		double netforwight = unnomalipsum * 0.125 + unnomalippackegeaverage* 0.375
//				+ avesever* 0.4165 +  cpugradientave* 0.0835;
//		return netforwight;
		
	}

}
