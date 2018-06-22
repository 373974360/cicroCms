<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="com.cicro.project.weinanzjzf.ZJZFBean" pageEncoding="utf-8"%>
<%@page import="com.cicro.project.weinanzjzf.ZJZFManager,com.cicro.util.FormatUtil,com.cicro.util.OutExcel,com.cicro.util.jconfig.JconfigUtilContainer,com.cicro.wcm.services.appeal.count.CountUtil,java.io.File"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.cicro.project.dz_dxsjx.DxsjxInfoManager" %>
<%@ page import="com.cicro.project.dz_dxsjx.DxsjxInfoBean" %>
<%
	String[] path = getFileUrl();
	String[] head = getExcelHeadName();	
	Map<String,String> m = new HashMap<String,String>();
	m.put("start_num", "0");	
	m.put("page_size", "10000");
	m.put("orderby", "id desc");
	List<DxsjxInfoBean> l = DxsjxInfoManager.getDxsjxList(m);
	String[][] data = new String[l.size()][head.length];
	int i=0;
	try{					
		for(DxsjxInfoBean dxsjxBean : l)
		{
			data[i][0] = dxsjxBean.getXm();
			data[i][1] = dxsjxBean.getXb();
			data[i][2] = dxsjxBean.getMz();
			data[i][3] = dxsjxBean.getJg();
			data[i][4] = dxsjxBean.getZzmm();
			data[i][5] = dxsjxBean.getSfzhm();
			data[i][6] = dxsjxBean.getXxmc();
			data[i][7] = dxsjxBean.getSxzy();
			data[i][8] = dxsjxBean.getSznj();
			data[i][9] = dxsjxBean.getLxdh();
			data[i][10] = dxsjxBean.getXxtxdz();
			data[i][11] = dxsjxBean.getJtlxrjdh();
			data[i][12] = dxsjxBean.getJtxxzz();
			i++;
		}		
		
		OutExcel oe=new OutExcel("大学生见习报名信息表");
		oe.doOut(path[0],head,data);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	//return path[1];
	//out.println("<script>window.location.href='"+path[1]+"'</script>");
	out.println("<script>document.write('<a href=\""+path[1]+"\" target=\"_blank\">下载文件</a>')</script>");
%>
<%!
//删除今天以前的文件夹  并 创建今天的文件夹和xls文件
	public static String[] getFileUrl(){
		//删除今天以前的文件夹
		String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path");
		String path = FormatUtil.formatPath(root_path + "/project/dz_dxsjx/export");
		//System.out.println("path===" + path);
		CountUtil.deleteFile(path);
		
		//创建今天的文件夹和xls文件
		String nowDate = CountUtil.getNowDayDate();
		String fileTemp2 = FormatUtil.formatPath(path+File.separator+nowDate);
		//System.out.println("fileTemp2===" + fileTemp2);
		File file2 = new File(fileTemp2);
		if(!file2.exists()){
			file2.mkdir();
		}
		String nowTime = CountUtil.getNowDayDateTime();
		String xls = nowTime + CountUtil.getEnglish(1)+".xls";
		String xlsFile = fileTemp2+File.separator+xls;
		String urlFile = "/manager/project/dz_dxsjx/export/"+nowDate+File.separator+xls;
		//System.out.println("xlsFile===" + xlsFile);
		String[] str = {xlsFile,urlFile};
		
		return str;
	}

	public static String[] getExcelHeadName()
	{
		String[] head = "姓名,性别,民族,籍贯,政治面貌,身份证号码,学校名称,所学专业,所在年级,联系电话,学校通信地址,家庭联系人及电话,家庭详细住址".split(",");
		
		return head;
	}
%>
