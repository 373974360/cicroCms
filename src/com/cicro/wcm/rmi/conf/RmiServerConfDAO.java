package com.cicro.wcm.rmi.conf;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.db.DBManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RmiServerConfDAO {

    public static List<RmiServerConfBean> getAllRmiServerConf() {
        return DBManager.queryFList("getAllRmiServerConf", "");
    }

}