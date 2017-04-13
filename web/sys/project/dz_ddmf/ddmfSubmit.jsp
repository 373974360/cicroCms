<%@page import="com.cicro.util.DateUtil"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cicro.util.FormatUtil,com.cicro.project.dz_ddmf.*"%>
<%
	String xm = FormatUtil.formatNullString(request.getParameter("xm"));
	String nl = FormatUtil.formatNullString(request.getParameter("nl"));
	String xb = FormatUtil.formatNullString(request.getParameter("xb"));
	String zy = FormatUtil.formatNullString(request.getParameter("zy"));
	String xl = FormatUtil.formatNullString(request.getParameter("xl"));
	String jg = FormatUtil.formatNullString(request.getParameter("jg"));
	String zz = FormatUtil.formatNullString(request.getParameter("zz"));
	String xjsj = FormatUtil.formatNullString(request.getParameter("xjsj"));
	String lxdh = FormatUtil.formatNullString(request.getParameter("lxdh"));
	String qq = FormatUtil.formatNullString(request.getParameter("qq"));
	String email = FormatUtil.formatNullString(request.getParameter("email"));
		
	String codeSession = (String)request.getSession().getAttribute("valiCode");
	String auth_code = request.getParameter("auth_code");
	if(!auth_code.equals(codeSession))
	{
		out.println("<script>");
		out.println("top.alert('验证码不正确')");
		out.println("top.changeCreateImage()");
		out.println("</script>");
		return;
	}
	
	DdmfBean ddmf = new DdmfBean();
	ddmf.setXm(xm);
	ddmf.setNl(nl);
	ddmf.setXb(xb);
	ddmf.setZy(zy);
	ddmf.setXl(xl);
	ddmf.setJg(jg);
	ddmf.setZz(zz);
	ddmf.setXjsj(xjsj);
	ddmf.setLxdh(lxdh);
	ddmf.setQq(qq);
	ddmf.setEmail(email);
	ddmf.setAddTime(DateUtil.getCurrentDateTime());
	ddmf.setStatus("0");
	
	if(DdmfManager.insertDdmf(ddmf,null))
	{
		out.println("<script>");
		out.println("top.alert('报名信息添加成功，感谢您的参与！')");		
		out.println("top.document.bm_form.reset()");	
		out.println("top.location.reload()");		
		out.println("</script>");
	}else
	{
		out.println("<script>");
		out.println("top.alert('报名信息添加失败，请重新提交')");	
		out.println("</script>");
	}
%>
