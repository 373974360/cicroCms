package com.cicro.wcm.dataCollection.services;

import com.cicro.wcm.dataCollection.bean.CollRuleBean;
import com.cicro.wcm.dataCollection.dao.CollectionDataDAO;
import com.cicro.wcm.dataCollection.util.FormatString;
import com.cicro.wcm.timer.TimerListener;
import com.cicro.wcm.timer.TimerTaskJobForDay;

import java.util.List;

public class CollTimerImpl
        implements TimerListener {
    static {
        TimerTaskJobForDay.registerPublishListener(new CollTimerImpl());
    }

    public void timingTask() {
        List<CollRuleBean> col_list = CollectionDataDAO.getAllCollRuleBeanList();
        if ((col_list != null) && (col_list.size() > 0)) {
            //System.out.println("------------Timer caollDate start....  ---------");
            for (CollRuleBean collBean : col_list) {
                String collinterval = collBean.getColl_interval();
                if ((FormatString.strIsNull(collinterval)) &&
                        (!"0".equals(collinterval)))
                    CollectionDataRPC.collTimer(collBean.getRule_id() + "");
            }
        }
    }
}
