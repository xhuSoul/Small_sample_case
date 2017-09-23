package com.xhu.www;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class Cpu_Servelet
 */
public class Cpu_Servelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static float getcpu = 0;
	private static  Map<String, Float> data = new HashMap<>();
	private static float cpuvalue;


	public Cpu_Servelet() {
		super();
	}

	public static Map<String, Float> getData() {
		return data;
	}

	public static void setData(Map<String, Float> data) {
		Cpu_Servelet.data = data;
	}

	public static float getGetcpu() {
		return getcpu;
	}

	public static void setGetcpu(float getcpu) {
		Cpu_Servelet.getcpu = getcpu;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		data.put("Cpu",Cpu_Servelet.getCpuvalue() );
		JSONArray jsonArray = JSONArray.fromObject(data);
		// System.out.println(AllInfo.getCpu());
		response.getWriter().println(jsonArray);

	}

	public static float getCpuvalue() {
		return cpuvalue;
	}

	public static void setCpuvalue(float cpuvalue) {
		Cpu_Servelet.cpuvalue = cpuvalue;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
