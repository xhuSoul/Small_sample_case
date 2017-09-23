package layoutrule;

import java.util.ArrayList;
import java.util.List;

import com.xhu.www.ServiceRate_Servelet;

import cpu.AllInfo;

public class WebRuleCala {

	// ������Ӧ�ò��CPU�仯�ʵĻ�ȡ
		public static float getCPUGradientAve(List<Float> sFloats) {
			List<Float> s1 = new ArrayList<Float>();
			s1 = sFloats;
			// cpuռ�ȵļ���
			float sever = ((float)1.0*(s1.get(AllInfo.getCpusever().size()-1) +s1.get(AllInfo.getCpusever().size()-2)+s1.get(AllInfo.getCpusever().size()-3) + s1.get(AllInfo.getCpusever().size()-4)+s1.get(AllInfo.getCpusever().size()-5)) / 5);
			// cpuռ�ȵķ�����
			return sever;
		}

	// �����Ӧ�ò�ķ����ʵĻ�ȡ
	
	public static float getHttpServerAve (List<Float> sFloats) {
		
		List<Float> s1 = new ArrayList<Float>();
		s1 = sFloats;
		float sever = ((float)1.0*s1.get(4)+s1.get(3)+s1.get(2)+s1.get(0)+s1.get(1))/5;
		
		return sever;
	}
	
	
	// �����Ӧ�ò�ķ����ʵĻ�ȡ,http��Ӧ��������http��������֮�ȣ�
	public static float getHttpServer(int httprequste, int httpreponse) {

		float s = 0;

		if (httprequste > 0) {
			s =(float)1.0*httpreponse / httprequste;
		} else {
			s = 0;
		}
		return s;

	}

	// Ӧ�ò�Ĺ���һ��ȡƽ����ANRCֵ
//	  ANRC=�������/���ֵ�IP��
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
