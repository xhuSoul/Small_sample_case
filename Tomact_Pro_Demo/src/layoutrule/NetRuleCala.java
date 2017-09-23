package layoutrule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import jnetcapforcapture.Packe;

public class NetRuleCala {

	// ��ȡ25���е�5��ֵ����ƽ���ķ�����
	public static float getAverageSever(List<Float> s) {
		List<Float> s1 = new ArrayList<Float>();
		s1 = s;

		// ��������ʱ仯�ķ���
//		float sever = Math.abs((s1.get(4) - s1.get(2)) / 2- (s1.get(2) - s1.get(0)) / 2);
		float sever = 2-((float)1.0*(s1.get(4) +s1.get(3)+s1.get(2) + s1.get(0)+s1.get(1))/5);
		//ServiceRate_Servelet.setServicerate(sever);
		return sever;

	}

	// ��÷�����IP��ʱ���t�ķ�����=��IP��ʱ���t���͵����ݰ�����/��IP��ʱ���t���ܵ����ݰ�����
	public static float getNetSever(int accpsum, int sendsum) {

		if (accpsum > 0) {
			float sumsever = (float)1.0 * sendsum / accpsum;
			return sumsever;

		} else {
			return 0;
		}
	}

	/*
	 * // ����ƽ���仯�� if (s[4]<s[0]) { float t[] = WebRuleCala.getOrderSequnce(s);
	 * // �����ֵ��ȥ�ֱ��ȥ����ֵ��ȥƽ�� float sum = 0; for (int i = 3; i > 0; i--) { sum =
	 * sum + t[4] - t[i]; } return sum / 4; }else { return 0; }
	 * 
	 * 
	 * }
	 */

	// ���쳣��ipɸѡ����
	public static Map<String, Integer> getUnnomalMap() {
		// IP��Ӧ�İ�������
		Map<String, Integer> mapforrules = new HashMap<String, Integer>();
		// �쳣��ip��Ӧ�İ�������
		Map<String, Integer> unnomalmap = new HashMap<String, Integer>();

		mapforrules = Packe.getMapfordosip();
		Iterator<Entry<String, Integer>> iterator = mapforrules.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Integer> entry = (Map.Entry<java.lang.String, java.lang.Integer>) iterator
					.next();
			if (entry.getKey().equals(Packe.getAdressip())) {
				if (entry.getValue() >= 1000) {
					unnomalmap.put(entry.getKey(), entry.getValue());
				}
			}
		
		}

		return unnomalmap;
	}

	// ��ȡ�쳣IP��ƽ����
	public static int getUnnomalipPackegeAverage() {
		Map<String, Integer> unnomalmap = new HashMap<String, Integer>();
		unnomalmap = NetRuleCala.getUnnomalMap();

		int sum = 0;
		Iterator<Entry<String, Integer>> iterator = unnomalmap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Integer> entry = (Map.Entry<java.lang.String, java.lang.Integer>) iterator
					.next();
			sum = sum + entry.getValue();
		}

		if (unnomalmap.size() > 0) {
			return unnomalmap.size()-1;
		} else {
			return 0;
		}
	}

}
