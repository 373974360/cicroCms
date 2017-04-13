package com.cicro.wcm.services.page;

import com.cicro.util.DateUtil;
import com.cicro.wcm.bean.page.PageBean;
import com.cicro.wcm.dao.page.PageDAO;
import com.cicro.wcm.timer.TimerListener;
import com.cicro.wcm.timer.TimerTaskJob;
import java.io.IOException;
import java.util.List;

public class PageTimerImpl
  implements TimerListener
{
  static
  {
    TimerTaskJob.registerPublishListener(new PageTimerImpl());
  }

  public void timingTask()
  {
    List<PageBean> pb_list = PageDAO.getTimerPageList(DateUtil.getCurrentDateTime());
    if ((pb_list != null) && (pb_list.size() > 0))
    {
    	System.out.println("******************总共有"+ pb_list.size() + "个页面要生成************************");
    	int i = 1;
      for (PageBean pb : pb_list)
        try
        {
          PageManager.createHtmlPage(pb);
          System.out.println("******************第"+ i + "个页面生成成功************************");
          i++;
        }
        catch (IOException e) {
        	System.out.println("******************页面生成失败************************");
          e.printStackTrace();
        }
    }
    else
    {
    	System.out.println(DateUtil.getCurrentDateTime() + "******************没有页面要生成************************");
    }
  }
}