package org.wildstang.framework.io.inputs;

import org.wildstang.framework.io.inputs.DigitalInput;

public class RandomDigitalInput extends DigitalInput
{
   private boolean m_hold;
   
   
   public RandomDigitalInput(String p_name)
   {
      super(p_name);
   }
   
   
   public RandomDigitalInput(String p_name, boolean p_default)
   {
      super(p_name, p_default);
   }
   
   
   public void holdValue(boolean p_hold)
   {
      m_hold = p_hold;
   }
   
   @Override
   protected boolean readRawValue()
   {
      if (!m_hold)
      {
         return !getValue();
      }
      else
      {
         return getValue();
      }
   }

}
