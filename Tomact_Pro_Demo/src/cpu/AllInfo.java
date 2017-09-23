package cpu;

import java.util.ArrayList;
import java.util.List;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Ps;

import com.xhu.www.Cpu_Servelet;

import jnetcapforcapture.Packe;

public class AllInfo extends Thread {
	private Sigar sigar = new Sigar();
	public static int temp = 0;
	public static String str = "";
	private static List<Float> cpusever = new ArrayList<Float>();
	private static float cpu = 0;

	// 获取进程的相关信息
	@SuppressWarnings("static-access")
	private List<ProcessInfo> getProcessInfo() {
		Ps ps = new Ps();
		List<ProcessInfo> processInfos = new ArrayList<ProcessInfo>();
		try {
			long[] pids = sigar.getProcList();
			for (long pid : pids) {
				@SuppressWarnings("unchecked")
				List<String> list = ps.getInfo(sigar, pid);
				ProcessInfo info = new ProcessInfo();
				for (int i = 0; i <= list.size(); i++) {
					switch (i) {
					case 0:
						info.setPid(list.get(0));
						break;
					case 1:
						info.setUser(list.get(1));
						break;
					case 2:
						info.setStartTime(list.get(2));
						break;
					case 3:
						info.setMemSize(list.get(3));
						break;
					case 4:
						info.setMemUse(list.get(4));
						break;
					case 5:
						info.setMemhare(list.get(5));
						break;
					case 6:
						info.setState(list.get(6));
						break;
					case 7:
						info.setCpuTime(list.get(7));
						break;
					case 8:
						info.setName(list.get(8));
						break;
					}
				}

				processInfos.add(info);
			}
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return processInfos;
	}
	

	public AllInfo(String args1) {
		// TODO Auto-generated constructor stub
		AllInfo.str = args1;
	}

	public AllInfo() {
		// TODO Auto-generated constructor stub
	}


	public void run() {

		Sigar sigar = new Sigar();
		AllInfo info = new AllInfo(str);
		List<ProcessInfo> processInfos = info.getProcessInfo();
		try {

			for (ProcessInfo processInfo : processInfos) {
//				 System.out.println(processInfo.getName());
				if (/*processInfo.getName().equals(str)*/processInfo.getPid().equals(str))// 待比较的是需要监控的进程的进程名，也可以通过PID直接监控
				{
					temp = 1;
					int i=0;
					while (true) {
						Thread.sleep(4000);// 在一段时间内，假如CPU跳动100次，而被监控程序跳动了8次，那么久可以认为此进程占用了CPU的8%
						cpu = (float) sigar.getProcCpu(processInfo.getPid()).getPercent() * 100;
						i ++;
						float s = cpu / Packe.getMapfordosip().size();
//						 System.out.println("输出CPU的占用率/出现ip的总数"+s);
						cpusever.add(s);
						Cpu_Servelet.setCpuvalue(cpu);
/*						System.out.println("输出"+Packe.getMapfordosip().size());
						System.out.println("Cpu的值:" + cpu);
//						System.out.println("没五秒输出一份");
*/						if (i>=5 ) {
//							System.out.println("这是CPU的终止");
							i=0;
						}
					}
				}
			}
			if (temp == 0) {
				System.out.println("此进程不存在！");
			}
		} catch (InterruptedException | SigarException e) {
			e.printStackTrace();
		}
	}

	public static float getCpu() {
		return cpu;
	}
	public static List<Float> getCpusever() {
		return cpusever;
	}

	public static void setCpusever(List<Float> cpusever) {
		AllInfo.cpusever = cpusever;
	}

}
