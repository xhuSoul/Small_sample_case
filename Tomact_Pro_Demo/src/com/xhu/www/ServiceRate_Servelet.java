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
 * Servlet implementation class ServiceRate_Servelet
 */
public class ServiceRate_Servelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static double Servicerate;
    public ServiceRate_Servelet() {
        super();
    }

	public static Double getServicerate() {
		return Servicerate;
	}

	public static void setServicerate(Double servicerate) {
		Servicerate = servicerate;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Map<String, Double> data = new HashMap<>();
		data.put("Cpu",ServiceRate_Servelet.getServicerate());
		//System.out.println(data.get("Cpu")+ " asdsad ");
		JSONArray jsonArray = JSONArray.fromObject(data);
		response.getWriter().println(jsonArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
