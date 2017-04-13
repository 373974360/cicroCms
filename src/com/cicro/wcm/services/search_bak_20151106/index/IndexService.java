package com.cicro.wcm.services.search_bak_20151106.index;

import java.util.Map;

public abstract interface IndexService
{
  public abstract boolean appendALlDocument(Map paramMap);

  public abstract boolean deleteALlDocument(Map paramMap);

  public abstract boolean appendSingleDocument(Map paramMap);

  public abstract boolean deleteSingleDocument(Map paramMap);
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.IndexService
 * JD-Core Version:    0.6.2
 */