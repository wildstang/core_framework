package org.wildstang.framework.io.inputs;

import org.wildstang.framework.io.inputs.DiscreteInput;

public class RandomDiscreteInput extends DiscreteInput
{
   private boolean m_hold;
   
   
   public RandomDiscreteInput(String p_name)
   {
      super(p_name);
   }
   
   
   public RandomDiscreteInput(String p_name, int p_default)
   {
      super(p_name, p_default);
   }
   
   
   public void holdValue(boolean p_hold)
   {
      m_hold = p_hold;
   }
   
   @Override
   protected int readRawValue()
   {
      if (!m_hold)
      {
         return (int)Math.random();
      }
      else
      {
         return getValue();
      }
   }

}
