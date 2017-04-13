package com.cicro.wcm.dao.search_bak_20151106;

import com.cicro.wcm.bean.search.IndexBeanInfo;
import java.util.List;
import java.util.Map;

public abstract interface IIndexInfoPicDao
{
  public abstract List getInfoListBySiteIdPages(Map paramMap);

  public abstract int getInfoListBySiteIdCount(Map paramMap);

  public abstract Map getInfoListBySiteIdCount(int paramInt);

  public abstract IndexBeanInfo getInfoById(int paramInt);

  public abstract List<Map> getInfosById(int paramInt);
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.search.IIndexInfoPicDao
 * JD-Core Version:    0.6.2
 */