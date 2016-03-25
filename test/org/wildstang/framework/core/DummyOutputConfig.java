package org.wildstang.framework.core;

import org.wildstang.framework.hardware.OutputConfig;

public class DummyOutputConfig implements OutputConfig
{
   
   private double m_double;
   private boolean m_boolean;
   
   public DummyOutputConfig(double p_double)
   {
      m_double = p_double;
   }

   public DummyOutputConfig(boolean p_boolean)
   {
      m_boolean = p_boolean;
   }

   public double getDouble()
   {
      return m_double;
   }

   public boolean getBoolean()
   {
      return m_boolean;
   }
}
