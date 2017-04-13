package com.cicro.wcm.rmi;

import com.cicro.iresource.service.resourceService.dto.DataElementCollectionDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public abstract interface ICustomFormRMI extends Remote
{
  public abstract boolean addCustomForm(DataElementCollectionDTO paramDataElementCollectionDTO)
    throws RemoteException;

  public abstract boolean updateCustomForm(DataElementCollectionDTO paramDataElementCollectionDTO)
    throws RemoteException;

  public abstract boolean addCustomForm(String paramString)
    throws RemoteException;

  public abstract boolean updateCustomForm(String paramString)
    throws RemoteException;

  public abstract boolean deleteCustomForm(String paramString1, String paramString2)
    throws RemoteException;

  public abstract boolean addCustomInfo(String paramString)
    throws RemoteException;

  public abstract boolean updateCustomInfo(String paramString)
    throws RemoteException;

  public abstract boolean deleteCustomInfo(String paramString)
    throws RemoteException;

  public abstract boolean insertForm(Object paramObject, String paramString1, String paramString2)
    throws RemoteException;

  public abstract boolean updateForm(Object paramObject, String paramString1, String paramString2)
    throws RemoteException;

  public abstract boolean deleteForm(List<String> paramList, String paramString)
    throws RemoteException;
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.rmi.ICustomFormRMI
 * JD-Core Version:    0.6.2
 */