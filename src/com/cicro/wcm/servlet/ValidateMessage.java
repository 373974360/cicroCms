package com.cicro.wcm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateMessage extends HttpServlet
{
	private static final long serialVersionUID = 3131099767169123925L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String no = request.getParameter("no");
		String code = request.getParameter("code");
		String serverCode = (String)request.getSession().getAttribute(no);
		if(serverCode.equals(code))
		{
			response.getWriter().print("1");
		}
		else
		{
			response.getWriter().print("0");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	  doGet(request, response);
	}
	
	public static void main(String[] args) {
		String code = "";
		for(int i = 0; i < 6; i++)
		{
			code += (int)(Math.random()*10);
		}
		System.out.println(code);
	}

}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.servlet.CreateImage
 * JD-Core Version:    0.6.2
 */