<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="com.cicro.project.dz_dxsjx.DxsjxBean" pageEncoding="utf-8"%>
<%@page import="com.cicro.project.dz_dxsjx.DxsjxManager,com.cicro.util.FormatUtil"%>
<%
	String xxmc  = FormatUtil.formatNullString(request.getParameter("xxmc"));
	String sxzy  = FormatUtil.formatNullString(request.getParameter("sxzy"));
	String sznj  = FormatUtil.formatNullString(request.getParameter("sznj"));
	String xm  = FormatUtil.formatNullString(request.getParameter("xm"));
	String xb  = FormatUtil.formatNullString(request.getParameter("xb"));
	String mz  = FormatUtil.formatNullString(request.getParameter("mz"));
	String jg  = FormatUtil.formatNullString(request.getParameter("jg"));
	String zzmm  = FormatUtil.formatNullString(request.getParameter("zzmm"));
	String jkzk  = FormatUtil.formatNullString(request.getParameter("jkzk"));
	String nl  = FormatUtil.formatNullString(request.getParameter("nl"));
	String qq  = FormatUtil.formatNullString(request.getParameter("qq"));
	String brtc  = FormatUtil.formatNullString(request.getParameter("brtc"));
	String yzbm  = FormatUtil.formatNullString(request.getParameter("yzbm"));
	String sfzhm  = FormatUtil.formatNullString(request.getParameter("sfzhm"));
	String lxdh  = FormatUtil.formatNullString(request.getParameter("lxdh"));
	String xxtxdz  = FormatUtil.formatNullString(request.getParameter("xxtxdz"));
	String jtlxrjdh  = FormatUtil.formatNullString(request.getParameter("jtlxrjdh"));
	String jtxxzz  = FormatUtil.formatNullString(request.getParameter("jtxxzz"));
	String hjqk  = FormatUtil.formatNullString(request.getParameter("hjqk"));
	String grzjsq  = FormatUtil.formatNullString(request.getParameter("grzjsq"));
	String zp  = FormatUtil.formatNullString(request.getParameter("zp"));
	String tjb  = FormatUtil.formatNullString(request.getParameter("tjb"));

	String codeSession = (String)request.getSession().getAttribute("valiCode");

	String auth_code = request.getParameter("auth_code");
	System.out.println("******************************************************"+codeSession + "------------------------------------------------" +auth_code);
	if(!auth_code.equals(codeSession))
	{
		out.println("<script>");
		out.println("top.alert('验证码不正确')");
		out.println("top.changeCreateImage()");
		out.println("</script>");
		return;
	}
	
	DxsjxBean dxsjxBean = new DxsjxBean();
	dxsjxBean.setXxmc(xxmc);
	dxsjxBean.setSxzy(sxzy);
	dxsjxBean.setSznj(sznj);
	dxsjxBean.setXm(xm);
	dxsjxBean.setXb(xb);
	dxsjxBean.setMz(mz);
	dxsjxBean.setJg(jg);
	dxsjxBean.setZzmm(zzmm);
	dxsjxBean.setJkzk(jkzk);
	dxsjxBean.setNl(nl);
	dxsjxBean.setQq(qq);
	dxsjxBean.setBrtc(brtc);
	dxsjxBean.setYzbm(yzbm);
	dxsjxBean.setSfzhm(sfzhm);
	dxsjxBean.setLxdh(lxdh);
	dxsjxBean.setXxtxdz(xxtxdz);
	dxsjxBean.setJtlxrjdh(jtlxrjdh);
	dxsjxBean.setJtxxzz(jtxxzz);
	dxsjxBean.setHjqk(hjqk);
	dxsjxBean.setGrzjsq(grzjsq);
	dxsjxBean.setZp(zp);
	dxsjxBean.setTjb(tjb);
	dxsjxBean.setStatus(0);

	if(DxsjxManager.insertDxsjx(dxsjxBean))
	{
		out.println("<script>");
		out.println("top.alert('报名成功，感谢您的参与')");		
		out.println("top.location.reload()");		
		out.println("</script>");
	}else
	{
		out.println("<script>");
		out.println("top.alert('报名失败，请重新提交')");
		out.println("</script>");
	}
%>
