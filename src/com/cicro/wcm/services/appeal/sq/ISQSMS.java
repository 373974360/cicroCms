package com.cicro.wcm.services.appeal.sq;

import com.cicro.wcm.bean.appeal.model.ModelBean;
import com.cicro.wcm.bean.appeal.sq.SQBean;

public abstract interface ISQSMS
{
  public abstract void sendSMSToClientForAdd(SQBean paramSQBean, ModelBean paramModelBean);

  public abstract void sendSMSToAdminForAdd(SQBean paramSQBean, ModelBean paramModelBean);

  public abstract void sendSMSToClientForResult(SQBean paramSQBean, ModelBean paramModelBean);

  public abstract void sendSMSToAdminForResult(SQBean paramSQBean, ModelBean paramModelBean);

  public abstract void sendSMSForSupervise(SQBean paramSQBean, ModelBean paramModelBean);

  public abstract void sendSMSForTrans(SQBean paramSQBean, ModelBean paramModelBean);

  public abstract void sendSMSForPublish(SQBean paramSQBean, ModelBean paramModelBean);
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.sq.ISQSMS
 * JD-Core Version:    0.6.2
 */