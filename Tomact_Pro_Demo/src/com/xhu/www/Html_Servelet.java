package com.xhu.www;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jnetcapforcapture.Packe;
import layoutrule.NetRuleCala;
import layoutrule.WebRuleCala;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class Demo
 */
public class Html_Servelet extends HttpServlet  {
	private static int ipsum;
	// 计数接收到的包的数量
	private static int countacceptpack;
	// 发送包的数量


	// 计数http层响应的报文数、
	private static int httpresponsecount;
	// 计数http层响应的请求报文的数量
	private static int httprequestconut;

	// 各个包类型数量的统计
	private static int udpsum;
	private static int tcpsum;

	// 出现的ip,以及接收到的包的数量
	private static Map<String, Integer> mapfordosip = new HashMap<String, Integer>();

	private static final long serialVersionUID = 1L;

	public Html_Servelet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Map<String, Integer> data = new HashMap<>();
		
		data.put("TCP_blocks_numbers", Packe.getTcpsum());
		data.put("HTTP_blocks_numbers", Packe.getHttpsum());
		data.put("UDP_blocks_numbers", Packe.getUdpsum());
		data.put("ANRC_numbers",(int)WebRuleCala.getAnrcAve(Packe.getHttprequestconut(), Packe.getMapfordosip().size()));
		data.put("abnormalIP_numbers", +NetRuleCala.getUnnomalMap().size());
		data.put("AllAccept_blocks_numbers", Packe.getIpsum());
		data.put("AllUserIP_numbers",Packe.getMapfordosip().size() );
		JSONArray jsonArray = JSONArray.fromObject(data);
		response.getWriter().println(jsonArray);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static int getIpsum() {
		return ipsum;
	}

	public static void setIpsum(int ipsum) {
		Html_Servelet.ipsum = ipsum;
	}

	public static int getCountacceptpack() {
		return countacceptpack;
	}

	public static void setCountacceptpack(int countacceptpack) {
		Html_Servelet.countacceptpack = countacceptpack;
	}


	public static int getHttpresponsecount() {
		return httpresponsecount;
	}

	public static void setHttpresponsecount(int httpresponsecount) {
		Html_Servelet.httpresponsecount = httpresponsecount;
	}

	public static int getHttprequestconut() {
		return httprequestconut;
	}

	public static void setHttprequestconut(int httprequestconut) {
		Html_Servelet.httprequestconut = httprequestconut;
	}

	public static int getUdpsum() {
		return udpsum;
	}

	public static void setUdpsum(int udpsum) {
		Html_Servelet.udpsum = udpsum;
	}

	public static int getTcpsum() {
		return tcpsum;
	}

	public static void setTcpsum(int tcpsum) {
		Html_Servelet.tcpsum = tcpsum;
	}

	public static Map<String, Integer> getMapfordosip() {
		return mapfordosip;
	}

	public static void setMapfordosip(Map<String, Integer> mapfordosip) {
		Html_Servelet.mapfordosip = mapfordosip;
	}

}
