package com.cicro.wcm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.cicro.wcm.bean.appeal.sq.SQBean;
import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkBean;
import com.cicro.wcm.services.appeal.sq.SQManager;
import com.cicro.wcm.services.zwgk.ysqgk.YsqgkInfoManager;

public class SendMessage extends HttpServlet
{
	private static final long serialVersionUID = 3131099767169123925L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String info = null;
		String no = request.getParameter("no");
		String type = request.getParameter("type");
		if(type != null && "1".equals(type))
		{
			String code = "";
			for(int i = 0; i < 6; i++)
			{
				code += (int)(Math.random()*10);
			}
			request.getSession().setMaxInactiveInterval(300);
			request.getSession().setAttribute(no, code);
			try{
				HttpClient httpclient = new HttpClient();
				PostMethod post = new PostMethod("http://api.ums86.com:8899/sms/Api/Send.do");//
				post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
				post.addParameter("SpCode", "218968");
				post.addParameter("LoginName", "wn_xxh");
				post.addParameter("Password", "wn1234");
				post.addParameter("MessageContent", "您的信件验证码为：" + code + "，感谢您对我们工作的支持！");
				post.addParameter("UserNumber", no);
				post.addParameter("SerialNumber", "");
				post.addParameter("ScheduleTime", "");
				post.addParameter("whitevalid", "1");
				post.addParameter("f", "1");
				httpclient.executeMethod(post);
				info = new String(post.getResponseBody(),"gbk");
				System.out.println(info);
				String result = info.substring(info.indexOf("=")+1, info.indexOf("&"));
				if(result != null && 0 == Integer.parseInt(result))
				{
					response.getWriter().print(code);
				}
				else
				{
					response.getWriter().print("0");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(type != null && "2".equals(type))
		{
			String code = "";
			for(int i = 0; i < 6; i++)
			{
				code += (int)(Math.random()*10);
			}
			request.getSession().setMaxInactiveInterval(300);
			request.getSession().setAttribute(no, code);
			try{
				HttpClient httpclient = new HttpClient();
				PostMethod post = new PostMethod("http://api.ums86.com:8899/sms/Api/Send.do");//
				post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
				post.addParameter("SpCode", "218968");
				post.addParameter("LoginName", "wn_xxh");
				post.addParameter("Password", "wn1234");
				post.addParameter("MessageContent", "您的依申请公开验证码为：" + code + "，感谢您对我们工作的支持！");
				post.addParameter("UserNumber", no);
				post.addParameter("SerialNumber", "");
				post.addParameter("ScheduleTime", "");
				post.addParameter("whitevalid", "1");
				post.addParameter("f", "1");
				httpclient.executeMethod(post);
				info = new String(post.getResponseBody(),"gbk");
				System.out.println(info);
				String result = info.substring(info.indexOf("=")+1, info.indexOf("&"));
				if(result != null && 0 == Integer.parseInt(result))
				{
					response.getWriter().print(code);
				}
				else
				{
					response.getWriter().print("0");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(type != null && "3".equals(type))
		{
			String ysq_code = request.getParameter("ysq_code");
			String query_code = request.getParameter("query_code");
			if(ysq_code != null && query_code != null)
			{
				try{
					HttpClient httpclient = new HttpClient();
					PostMethod post = new PostMethod("http://api.ums86.com:8899/sms/Api/Send.do");//
					post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
					post.addParameter("SpCode", "218968");
					post.addParameter("LoginName", "wn_xxh");
					post.addParameter("Password", "wn1234");
					post.addParameter("MessageContent", "您的依申请公开已提交成功，依申请公开编号："+ ysq_code + "，查询码：" + query_code + "，请等待回复处理，感谢您对我们工作的支持！");
					post.addParameter("UserNumber", no);
					post.addParameter("SerialNumber", "");
					post.addParameter("ScheduleTime", "");
					post.addParameter("whitevalid", "1");
					post.addParameter("f", "1");
					httpclient.executeMethod(post);
					info = new String(post.getResponseBody(),"gbk");
					System.out.println(info);
					String result = info.substring(info.indexOf("=")+1, info.indexOf("&"));
					if(result != null && 0 == Integer.parseInt(result))
					{
						response.getWriter().print("1");
					}
					else
					{
						response.getWriter().print("0");
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else if(type != null && "4".equals(type))
		{
			String ysq_id = request.getParameter("ysq_id");
			YsqgkBean bean = null;
			if(ysq_id != null && !"".equals(ysq_id))
			{
				bean = YsqgkInfoManager.getYsqgkBean(ysq_id);
			}
			if(bean != null)
			{
				try{
					HttpClient httpclient = new HttpClient();
					PostMethod post = new PostMethod("http://sx.ums86.com:8899/sms/Api/Send.do");//
					post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
					post.addParameter("SpCode", "218968");
					post.addParameter("LoginName", "wn_xxh");
					post.addParameter("Password", "wn1234");
					post.addParameter("MessageContent", "您提交的依申请公开已处理完毕，请根据依申请公开编号："+ bean.getYsq_code() + "及查询密码：" + bean.getQuery_code() + "进行查询，感谢您对我们工作的支持！");
					post.addParameter("UserNumber", no);
					post.addParameter("SerialNumber", "");
					post.addParameter("ScheduleTime", "");
					post.addParameter("whitevalid", "1");
					post.addParameter("f", "1");
					httpclient.executeMethod(post);
					info = new String(post.getResponseBody(),"gbk");
					System.out.println(info);
					String result = info.substring(info.indexOf("=")+1, info.indexOf("&"));
					if(result != null && 0 == Integer.parseInt(result))
					{
						response.getWriter().print("1");
					}
					else
					{
						response.getWriter().print("0");
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			String sq_id = request.getParameter("sq_id");
			SQBean bean = null;
			if(sq_id != null && !"".equals(sq_id))
			{
				bean = SQManager.getSqBean(Integer.parseInt(sq_id));
			}
			if(bean != null)
			{
				try{
					HttpClient httpclient = new HttpClient();
					PostMethod post = new PostMethod("http://sx.ums86.com:8899/sms/Api/Send.do");//
					post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
					post.addParameter("SpCode", "218968");
					post.addParameter("LoginName", "wn_xxh");
					post.addParameter("Password", "wn1234");
					post.addParameter("MessageContent", "您提交的市长信箱（咨询投诉建议），已处理完毕，请根据信件编号："+ bean.getSq_code() + "及查询密码：" + bean.getQuery_code() + "进行查询，感谢您对我们工作的支持！");
					post.addParameter("UserNumber", no);
					post.addParameter("SerialNumber", "");
					post.addParameter("ScheduleTime", "");
					post.addParameter("whitevalid", "1");
					post.addParameter("f", "1");
					httpclient.executeMethod(post);
					info = new String(post.getResponseBody(),"gbk");
					System.out.println(info);
					String result = info.substring(info.indexOf("=")+1, info.indexOf("&"));
					if(result != null && 0 == Integer.parseInt(result))
					{
						response.getWriter().print("1");
					}
					else
					{
						response.getWriter().print("0");
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
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