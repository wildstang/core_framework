package org.wildstang.framework.core;

import org.wildstang.framework.hardware.InputConfig;

public class DummyInputConfig implements InputConfig
{
   private double m_double;
   private boolean m_boolean;
   
   public DummyInputConfig(double p_double)
   {
      m_double = p_double;
   }

   public DummyInputConfig(boolean p_boolean)
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
