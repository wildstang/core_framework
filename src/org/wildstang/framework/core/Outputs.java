package org.wildstang.framework.core;

import org.wildstang.framework.io.outputs.OutputType;

public interface Outputs
{

   public String getName();

   public OutputType getType();

   public Object getPort();
   
   public Object getModule();

   public Object getDefault();

   public boolean isTrackingState();
}