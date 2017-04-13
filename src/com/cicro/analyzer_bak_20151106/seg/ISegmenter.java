package com.cicro.analyzer_bak_20151106.seg;

import com.cicro.analyzer.Context;

public abstract interface ISegmenter
{
  public abstract void nextLexeme(char[] paramArrayOfChar, Context paramContext);

  public abstract void reset();
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.seg.ISegmenter
 * JD-Core Version:    0.6.2
 */