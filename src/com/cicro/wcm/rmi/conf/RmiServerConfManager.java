package com.cicro.wcm.rmi.conf;

import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RmiServerConfManager {

    public static List<RmiServerConfBean> getAllRmiServerConf() {
        return RmiServerConfDAO.getAllRmiServerConf();
    }

}