package Arithmetic;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TrafficAttack extends Thread{
	public static int Start_Time = 0; //�㷨��ʼʱ��  ��ʼֵΪ0
	private final double SamplesNo = 25; //��������T
	private static double t1 = 1 ,t2 = 2 ,t3 = 2	; //t1 ���� u����   t2����S���� t3����A����
	private double u[] ,S[], A[], N[], y[]; //
	//private final int Detection_Time = 25;//�涨 ���ʱ��Ϊ25��
	
	int i;
	/**
	 * �ӷ�������ȡ����ʱ���Ӧ��T��ͳ������ ��ʼ��N����
	 * */
	public  void getTotalTraffic(int Query_Time,double [] TotalArray) {
		this.initializationArray();//��ʼ������ u �� S ��Ϊ���Ǹ�ֵΪ0
		TrafficAttack.Start_Time = Query_Time; //Ϊ�㷨��ʼʱ�丳ֵ
		N = new double[(int)SamplesNo];
		N = TotalArray;
	}
	
	/**
	 * ����Ϊ��λ  ��ȡ��ǰʱ��
	 * */
	public int getStart_Time() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		String s = simpleDateFormat.format(new Date());
		int index1=s.indexOf(":");
		int index2=s.indexOf(":",index1+1);
		int hh=Integer.parseInt(s.substring(0,index1));
		int mi=Integer.parseInt(s.substring(index1+1,index2));
		int ss=Integer.parseInt(s.substring(index2+1));
		return (hh*60*60+mi*60+ss);
	}
	
	/**
	 * ��ʼ�� S u y  A����
	 * */
	public void initializationArray() {
		u = new double[(int)SamplesNo];
		S = new double[(int)SamplesNo];
		y = new double[(int)SamplesNo];
		A = new double[(int)SamplesNo];
	}


	/**
	 * 3-4��  ��ֵ u[0]=N[0] S[0]=y[0] 
	 * */
	public  void Real_time_traffic(int Start_Time){
		S[0] = y[0];
		u[0] = N[0];
	}
	
	/**
	 * ��y��ֵ
	 * 
	 * */
	public void run1(double[] Real_Time_Array) {

		y = Real_Time_Array;
	}


	/**
	 * ���� u[t],����������u��
	 * */
	public void Calculation_u() {
		while(TrafficAttack.t1 < SamplesNo ){
			u[(int) TrafficAttack.t1] = ((TrafficAttack.t1-1)/TrafficAttack.t1) * u[(int) (TrafficAttack.t1-1)]
					+ (1/TrafficAttack.t1)*((y[(int) TrafficAttack.t1]/N[(int) TrafficAttack.t1])-1);
			TrafficAttack.t1++;
		}
		
	}
	

	/**
	 * ����S[t],����������S��
	 * */
	public void Calculation_S() {
		
		while(TrafficAttack.t2 < SamplesNo){
			S[(int) TrafficAttack.t2] =  Math.sqrt((TrafficAttack.t2)/(TrafficAttack.t2-1)*((A[(int) TrafficAttack.t2])-Math.pow(u[(int) TrafficAttack.t2], 2)));
			TrafficAttack.t2++;
		}
		
	}
	
	
	/**
	 * ����A[t] ������������A�С�
	 * */
	public void Calculation_A(){
		
		while(TrafficAttack.t3 < SamplesNo){
			A[(int) TrafficAttack.t3] = (TrafficAttack.t3 -1 )/(TrafficAttack.t3)*A[(int) (TrafficAttack.t3-1)] + 
					1/(TrafficAttack.t3)*Math.pow(((y[(int) TrafficAttack.t3])/(N[(int) TrafficAttack.t3])-1), 2);
			TrafficAttack.t3++;
		}
	}
	
	//��ʽ
	public double leftSection(){
		double UT = ((SamplesNo-1)/SamplesNo) * u[(int)SamplesNo-1]
				+ (1/SamplesNo)*((y[(int)SamplesNo-1]/N[(int)SamplesNo-1])-1); //u[T]
		
		double ST = ((SamplesNo)/(SamplesNo-1))*((SamplesNo -1 )/(SamplesNo)*A[(int)SamplesNo-1] + 
				1/(SamplesNo)*Math.pow(((y[(int)SamplesNo-1])/(N[(int)SamplesNo-1])-1), 2));
		return UT - (1.96*(ST/Math.sqrt(SamplesNo)));//2.0930ȡֵ�����ɶ�
	}
	//��ʽ
	public double rightSection(){
		double UT = ((SamplesNo-1)/SamplesNo) * u[(int)SamplesNo-1]
				+ (1/SamplesNo)*((y[(int)SamplesNo-1]/N[(int)SamplesNo-1])-1);
		double ST = ((SamplesNo)/(SamplesNo-1))*((SamplesNo -1 )/(SamplesNo)*A[(int)SamplesNo-1] + 
				1/(SamplesNo)*Math.pow(((y[(int)SamplesNo-1])/(N[(int)SamplesNo-1])-1), 2));
		return UT + (1.96*(ST/Math.sqrt(SamplesNo)));
	}
	
	
	/**
	 * ���Ժ���
	 * */
	public String Start_Testing(double[] TotalArray,double[] Real_Time_Array){
		
		TrafficAttack demo = new TrafficAttack();
		demo.getTotalTraffic(demo.getStart_Time(),TotalArray);
		demo.Real_time_traffic(TrafficAttack.Start_Time);
		demo.run1(Real_Time_Array);
		demo.Calculation_u();
		demo.Calculation_A();
		demo.Calculation_S();
		return demo.getResult();
		
	}

	/**
	 * �����ú������������������(-0.5,0.5) ���Ƚ� 
	 *
	 */
	public	String getResult(){
		if(leftSection()> -0.5 && rightSection() < 0.5){
			return "�������� ";
		}else{
			return "DDos����";
		}
	}
	
	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		double [] Total_Array = {1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9,1,2 ,3,4,5,6,7};
//		double [] Real_Time_Array = {1,2,3,4,5,6,7,8,3,1,2,4,4,5,6,11,2,9,1,2,4,4,5,6,3};
//		System.out.println(new TrafficAttack().Start_Testing(Total_Array,Real_Time_Array));
//	}
	
	public static  String getArithmeticResult(double []Total_Array,double[] Real_Time_Array ) {
		return new TrafficAttack().Start_Testing(Total_Array,Real_Time_Array);
		
	}

	
}



