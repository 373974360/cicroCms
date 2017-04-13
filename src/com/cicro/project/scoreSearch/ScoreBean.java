package com.cicro.project.scoreSearch;

/**
 * Created with IntelliJ IDEA.
 * User: like
 * Date: 2016/3/21
 * Time: 14:05
 * Description:
 * Version: v1.0
 */
public class ScoreBean {

    private static final long serialVersionUID = 1L;
    private int id = 0;
    private int examId = 0;
    private String sfzh = "";
    private String xm = "";
    private String zkzh = "";
    private String excelData = "";
    private String importDate= "";
    private String status = "";

    private String examName = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getZkzh() {
		return zkzh;
	}

	public void setZkzh(String zkzh) {
		this.zkzh = zkzh;
	}

	public String getExcelData() {
		return excelData;
	}

	public void setExcelData(String excelData) {
		this.excelData = excelData;
	}
	
	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}
	
}
