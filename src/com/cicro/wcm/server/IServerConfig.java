package com.cicro.wcm.server;

import java.util.Map;

public abstract interface IServerConfig
{
  public static final String TOMCAT_SERVER = "tomcat";
  public static final String WEBLOGIC_SERVER = "weblogic";
  public static final String WESPHERE_SERVER = "websphere";
  public static final String JETTY_SERVER = "jetty";
  public static final String JRUN_SERVER = "jrun";
  public static final String JBOSS_SERVER = "jboss";
  public static final String TONGWEB_SERVER = "tongweb";
  public static final String INFOWEB_SERVER = "infoweb";
  public static final String NO_ERROR = "NO_ERROR";

  public abstract String getType();

  public abstract String addSite(Map paramMap);

  public abstract String updateSite(Map paramMap);

  public abstract String delSite(Map paramMap);

  public abstract String addAlias(Map paramMap);

  public abstract String delAlias(Map paramMap);

  public abstract String reset(Map paramMap);
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.server.IServerConfig
 * JD-Core Version:    0.6.2
 */