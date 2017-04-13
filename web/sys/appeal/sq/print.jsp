<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.cicro.wcm.services.appeal.sq.SQManager,com.cicro.wcm.bean.appeal.sq.SQBean"%>
<%@page import="com.cicro.wcm.template.velocity.*,com.cicro.wcm.template.velocity.impl.*,com.cicro.util.FormatUtil"%>
<%@page import="com.cicro.wcm.services.member.*,com.cicro.wcm.bean.member.*"%>
<%	
	String sq_id = FormatUtil.formatNullString(request.getParameter("sq_id"));
	String model_id = FormatUtil.formatNullString(request.getParameter("model_id"));
	
	if(sq_id==null || sq_id=="")
	{			
		return;
	}
	
	VelocityAppealContextImp vc = new VelocityAppealContextImp(request);
	vc.setModelID(model_id,"print");
	out.println(vc.parseTemplate());

%>