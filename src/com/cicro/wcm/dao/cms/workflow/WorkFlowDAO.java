package com.cicro.wcm.dao.cms.workflow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cicro.wcm.bean.cms.workflow.WorkFlowBean;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.db.DBManager;

/**
 *  工作流管理数据处理类.
 * <p>Title: CicroDate</p>
 * <p>Description: 工作流角色管理数据处理类</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Cicro</p>
 * @author zhuliang
 * @version 1.0
 * * 
 */

public class WorkFlowDAO {
	/**
     * 得到所有工作流列表
     * @param 
     * @return List
     * */
	@SuppressWarnings("unchecked")
	public static List getAllWorkFlowList()
	{
		return DBManager.queryFList("getAllWorkFlowList", "");
	}
	
	/**
     * 根据ID返回工作流对象
     * @param int wf_id
     * @return WorkFlowBean
     * */
	public static WorkFlowBean getWorkFlowBean(int wf_id)
	{
		return (WorkFlowBean)DBManager.queryFObj("getWorkFlowBean", wf_id+"");
	}
	
	/**
     * 插入工作流信息
     * @param WorkFlowBean wfb
     * @param SettingLogsBean stl
     * @return boolean
     * */
	public static boolean insertWorkFlow(WorkFlowBean wfb,SettingLogsBean stl){
		int id = PublicTableDAO.getIDByTableName(PublicTableDAO.WORKFLOW_TABLE_NAME);
		wfb.setWf_id(id);
		if(DBManager.insert("insert_workflow", wfb))
		{				
			PublicTableDAO.insertSettingLogs("添加","工作流",id+"",stl);
			return true;
		}else
			return false; 
	}
	
	/**
     * 修改工作流信息
     * @param WorkFlowBean wfb
     * @param SettingLogsBean stl
     * @return boolean
     * */
	public static boolean updateWorkFlow(WorkFlowBean wfb,SettingLogsBean stl){
		if(DBManager.update("update_workflow", wfb))
		{				
			PublicTableDAO.insertSettingLogs("修改","工作流",wfb.getWf_id()+"",stl);
			return true;
		}else
			return false; 
	}
	
	/**
     * 删除工作流信息
     * @param String wf_ids
     * @param SettingLogsBean stl
     * @return boolean
     * */
	public static boolean deleteWorkFlow(String wf_ids,SettingLogsBean stl){
		Map<String, String> m = new HashMap<String, String>();
		m.put("wf_ids", wf_ids);
		if(DBManager.insert("delete_workflow", m))
		{				
			PublicTableDAO.insertSettingLogs("删除","工作流",wf_ids+"",stl);
			return true;
		}else
			return false; 
	}
}
