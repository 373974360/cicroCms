package com.cicro.wcm.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface ISyncCatchRmi extends Remote
{
  public abstract void reloadCateh(String paramString)
    throws RemoteException;
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.rmi.ISyncCatchRmi
 * JD-Core Version:    0.6.2
 */