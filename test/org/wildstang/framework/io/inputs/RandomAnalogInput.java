package org.wildstang.framework.io.inputs;

import org.wildstang.framework.io.inputs.AnalogInput;

public class RandomAnalogInput extends AnalogInput
{
   private boolean m_hold;
   
   
   public RandomAnalogInput(String p_name)
   {
      super(p_name);
   }
   
   
   public RandomAnalogInput(String p_name, double p_default)
   {
      super(p_name, p_default);
   }
   
   
   public void holdValue(boolean p_hold)
   {
      m_hold = p_hold;
   }
   
   @Override
   protected double readRawValue()
   {
      if (!m_hold)
      {
         return Math.random();
      }
      else
      {
         return getValue();
      }
   }

}
