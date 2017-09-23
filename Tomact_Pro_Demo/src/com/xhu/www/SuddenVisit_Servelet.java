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
 * Servlet implementation class SuddenVisit_Servelet
 */
public class SuddenVisit_Servelet extends HttpServlet {

	private static String WebAttack;
	private static String NetAttack;

	public static String getWebAttack() {
		return WebAttack;
	}

	public static void setWebAttack(String webAttack) {
		WebAttack = webAttack;
	}

	public static String getNetAttack() {
		return NetAttack;
	}

	public static void setNetAttack(String netAttack) {
		NetAttack = netAttack;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static String viewddosforcacl;

	private static final long serialVersionUID = 1L;

	public SuddenVisit_Servelet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> data = new HashMap<>();

		if (viewddosforcacl != null) {
			data.put("SuddenVisit", viewddosforcacl);
			System.out.println("shuchu" + data.get("SuddenVisit"));
			System.err.println(viewddosforcacl);
		}

		/*WebAttack = CaptureCore.getWebresult();
		NetAttack = CaptureCore.getNetresult();*/

		data.put("WebAttack", WebAttack);
		data.put("NetAttack", NetAttack);
		if(WebAttack!=null){
			//System.out.println(WebAttack);
		
		if (WebAttack.equals("À¶É«Ô¤¾¯")) {
			data.put("Web_data_value", String.valueOf((int) ((Math.random() * 25)) + 25));
		}else if(WebAttack.equals("Õý³£")){
			data.put("Web_data_value", String.valueOf((int) ((Math.random() * 25)) + 1));
		}else if(WebAttack.equals("»ÆÉ«Ô¤¾¯")){
			data.put("Web_data_value", String.valueOf((int) ((Math.random() * 25)) + 50));
		}else if(WebAttack.equals("ºìÉ«Ô¤¾¯")){
			data.put("Web_data_value", String.valueOf((int) ((Math.random() * 25)) + 75));
		}
			
		}
			
			
		if(NetAttack!=null){	
		if (NetAttack.equals("À¶É«Ô¤¾¯")) {
			data.put("Net_data_value", String.valueOf((int) ((Math.random() * 25)) + 25));
		}else if(NetAttack.equals("Õý³£")){
			data.put("Net_data_value", String.valueOf((int) ((Math.random() * 25)) + 1));
		}else if(NetAttack.equals("»ÆÉ«Ô¤¾¯")){
			data.put("Net_data_value", String.valueOf((int) ((Math.random() * 25)) + 50));
		}else if(NetAttack.equals("ºìÉ«Ô¤¾¯")){
			data.put("Net_data_value", String.valueOf((int) ((Math.random() * 25)) + 75));
		}
		}
		JSONArray jsonArray = JSONArray.fromObject(data);

		// System.out.println(jsonArray.toString());
		response.getWriter().println(jsonArray);

	}

	public static String getViewddosforcacl() {
		return viewddosforcacl;
	}

	public static void setViewddosforcacl(String viewddosforcacl) {
		SuddenVisit_Servelet.viewddosforcacl = viewddosforcacl;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
