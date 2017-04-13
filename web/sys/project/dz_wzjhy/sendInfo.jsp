<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.cicro.util.*"%>
<%@page import="com.yinhai.policyfileins.*,com.yinhai.policyfileins.ws.impl.*"%>
<%
	PolicyFileUtilService_Service service = new PolicyFileUtilService_Service();
	PolicyFileUtilService portType = service.getPolicyFileUtilServiceImplPort();
	String xml = "";
	String action_type = FormatUtil.formatNullString(request.getParameter("action_type"));
	if("delete".equals(action_type))
	{
		String id = FormatUtil.formatNullString(request.getParameter("id"));
		String tempA[] = id.split(",");
		for(int i=0;i<tempA.length;i++)
		{
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			xml += "<webquery>";
			xml += "<qhead>";
			xml += "<msgsender>xawt00001</msgsender>";
			xml += "<funcode>zcwj000001</funcode>";
			xml += "<queryid>111</queryid></qhead>";
			xml += "<svccont>";
			xml += "<input>";
			xml += "<kbs208><![CDATA[3]]></kbs208>";
			xml += "<kbs210><![CDATA["+tempA[i]+"]]></kbs210>";
			xml += "</input>";
			xml += "</svccont>";
			xml += "</webquery>";
			System.out.println(xml);
			System.out.println(portType.yhPolicyFileUtil(xml));
		}
	}else
	{
		String id = FormatUtil.formatNullString(request.getParameter("id"));
		String title = FormatUtil.formatNullString(request.getParameter("title"));
		String doc_no = FormatUtil.formatNullString(request.getParameter("doc_no"));
		String gk_input_dept = FormatUtil.formatNullString(request.getParameter("gk_input_dept"));
		String generate_dtime = FormatUtil.formatNullString(request.getParameter("generate_dtime"));
		String gk_content = FormatUtil.formatNullString(request.getParameter("gk_content"));
		String user_realname = FormatUtil.formatNullString(request.getParameter("user_realname"));
	
		if("update".equals(action_type))
			action_type = "2";
		else
			action_type = "1";
		
	    xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml += "<webquery>";
		xml += "<qhead>";
		xml += "<msgsender>xawt00001</msgsender>";
		xml += "<funcode>zcwj000001</funcode>";
		xml += "<queryid>111</queryid></qhead>";
		xml += "<svccont>";
		xml += "<input>";
		xml += "<kbs202><![CDATA["+doc_no+"]]></kbs202>";
		xml += "<kbs203><![CDATA["+gk_input_dept+"]]></kbs203>";
		xml += "<kbs204><![CDATA["+generate_dtime+"]]></kbs204>";
		xml += "<kbs205><![CDATA["+action_type+"]]></kbs205>";
		xml += "<kbs206><![CDATA["+title+"]]></kbs206>";
		xml += "<kbs207><![CDATA["+gk_content+"]]></kbs207>";
		xml += "<kbs208><![CDATA[0]]></kbs208>";
		xml += "<kbs209><![CDATA[]]></kbs209>";
		xml += "<kbs210><![CDATA["+id+"]]></kbs210>";
		xml += "<kbs211><![CDATA["+user_realname+"]]></kbs211>";
		xml += "<kbs212><![CDATA["+DateUtil.getCurrentDateTime()+"]]></kbs212>";
		xml += "<kbs213><![CDATA[]]></kbs213>";
		xml += "</input>";
		xml += "</svccont>";
		xml += "</webquery>";
		System.out.println(xml);
		System.out.println(portType.yhPolicyFileUtil(xml));
	}	
	
%>