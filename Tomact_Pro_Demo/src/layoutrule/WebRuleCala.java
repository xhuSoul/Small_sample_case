package layoutrule;

import java.util.ArrayList;
import java.util.List;

import com.xhu.www.ServiceRate_Servelet;

import cpu.AllInfo;

public class WebRuleCala {

	// 规则三应用层的CPU变化率的获取
		public static float getCPUGradientAve(List<Float> sFloats) {
			List<Float> s1 = new ArrayList<Float>();
			s1 = sFloats;
			// cpu占比的计算
			float sever = ((float)1.0*(s1.get(AllInfo.getCpusever().size()-1) +s1.get(AllInfo.getCpusever().size()-2)+s1.get(AllInfo.getCpusever().size()-3) + s1.get(AllInfo.getCpusever().size()-4)+s1.get(AllInfo.getCpusever().size()-5)) / 5);
			// cpu占比的服务率
			return sever;
		}

	// 规则二应用层的服务率的获取
	
	public static float getHttpServerAve (List<Float> sFloats) {
		
		List<Float> s1 = new ArrayList<Float>();
		s1 = sFloats;
		float sever = ((float)1.0*s1.get(4)+s1.get(3)+s1.get(2)+s1.get(0)+s1.get(1))/5;
		
		return sever;
	}
	
	
	// 规则二应用层的服务率的获取,http响应报文数与http请求报文数之比，
	public static float getHttpServer(int httprequste, int httpreponse) {

		float s = 0;

		if (httprequste > 0) {
			s =(float)1.0*httpreponse / httprequste;
		} else {
			s = 0;
		}
		return s;

	}

	// 应用层的规则一获取平均的ANRC值
//	  ANRC=请求次数/出现的IP数
	public static float getAnrcAve(int requste, int ipsum) {

		float s = 0;
		if (ipsum > 0) {
			s = (float)1.0 * requste /  ipsum;
		} else {
			s = 0;
		}

		return s;
	}

}
