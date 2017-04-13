package com.cicro.wcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cicro.wcm.services.cms.category.CategoryTreeUtil;

public class Category extends HttpServlet {

	// Process the HTTP Get request

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String site_id = request.getParameter("site_id");
		String user_id = request.getParameter("user_id");
		String pid = request.getParameter("pid");
		String jsonStr = CategoryTreeUtil.getInfoCategoryTreeByUserIDSync(site_id,Integer.parseInt(user_id), Integer.parseInt(pid));
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/plain;charset=utf-8");
		
		response.getWriter().print(jsonStr);
	}

}
