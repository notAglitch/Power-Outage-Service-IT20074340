package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Outage;

import javax.servlet.annotation.WebServlet;

@WebServlet("/customerAPI")
public class OutageApi {

	private static final long serialVersionUID = 1L;
    
	Outage itemObj = new Outage(); 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = itemObj.insertOutage(
				request.getParameter("cusID"), 
				request.getParameter("cusName"), 
				request.getParameter("outArea"), 
				request.getParameter("outDate"), 
				request.getParameter("outTime"), 
				request.getParameter("outDesc"));
		     
				response.getWriter().write(output); 
	}

	// Convert request parameters to a Map
	private static Map<String, String> getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 

	 String[] p = param.split("="); 
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		 String output = itemObj.updateOutage(paras.get("hidItemIDSave").toString(), 
		 paras.get("cusID").toString(), 
		paras.get("cusName").toString(), 
		paras.get("outArea").toString(), 
		paras.get("outDate").toString(),
		paras.get("outTime").toString(),
		paras.get("outDate").toString());
		
		response.getWriter().write(output); 
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<?, ?> paras = getParasMap(request); 
		 String output = itemObj.deleteOutage(paras.get("outageID").toString()); 
		response.getWriter().write(output); 
	}

}
