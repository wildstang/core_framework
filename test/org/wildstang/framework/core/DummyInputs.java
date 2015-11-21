package org.wildstang.framework.core;

import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.hardware.InputConfig;
import org.wildstang.framework.io.inputs.InputType;

public enum DummyInputs implements Inputs
{
   TEST1("Test1", DummyInputType.POT, new DummyInputConfig(5.5), false),
   TEST2("Test2", DummyInputType.NULL, new DummyInputConfig(false), true),
   TEST3("Test3", DummyInputType.POT, new DummyInputConfig(5.5), true),
   TEST4("Test4", DummyInputType.NULL, new DummyInputConfig(true), true);
   
   private final String m_name;
   private final InputType m_type;
   private boolean m_trackingState;
   private boolean m_pullup;
   private InputConfig m_config;

   DummyInputs(String p_name, InputType p_type, InputConfig p_config, boolean p_trackingState)
   {
      m_name = p_name;
      m_type = p_type;
      m_trackingState = p_trackingState;
      m_config = p_config;
   }
   
   @Override
   public String getName()
   {
      return m_name;
   }
   
   @Override
   public InputType getType()
   {
      return m_type;
   }

   public boolean getPullup()
   {
      return m_pullup;
   }

   public boolean isTrackingState()
   {
      return m_trackingState;
   }

   @Override
   public InputConfig getConfig()
   {
      return m_config;
   }
   
   
}
