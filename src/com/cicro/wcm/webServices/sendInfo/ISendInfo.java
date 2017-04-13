package com.cicro.wcm.webServices.sendInfo;

public abstract interface ISendInfo
{
  public abstract String getReceiveConfigForJSON();

  public abstract String getReceiveConfigForXML();

  public abstract boolean sendInfo(String paramString1, String paramString2);

  public abstract boolean recordAdoptStatus(String paramString);

  public abstract boolean sendInfoFormThirdParty(String paramString1, String paramString2, String paramString3);
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.webServices.sendInfo.ISendInfo
 * JD-Core Version:    0.6.2
 */