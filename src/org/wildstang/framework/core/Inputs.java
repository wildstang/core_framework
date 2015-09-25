package org.wildstang.framework.core;

import org.wildstang.framework.io.inputs.InputType;

public interface Inputs
{

   public String getName();

   public InputType getType();

   public Object getPort();

   public Object getDefault();

   public Object getModule();

   public boolean getPullup();
   
   public boolean isTrackingState();
}