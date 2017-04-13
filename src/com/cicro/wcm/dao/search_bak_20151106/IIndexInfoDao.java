package com.cicro.wcm.dao.search_bak_20151106;

import java.util.List;
import java.util.Map;

public abstract interface IIndexInfoDao
{
  public abstract List getInfoListBySiteIdPages(Map paramMap);

  public abstract int getInfoListBySiteIdCount(Map paramMap);

  public abstract Map getInfoById(int paramInt);

  public abstract Map getInfoById(Map paramMap);
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.search.IIndexInfoDao
 * JD-Core Version:    0.6.2
 */