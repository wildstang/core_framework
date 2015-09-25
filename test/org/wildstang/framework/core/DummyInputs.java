package org.wildstang.framework.core;

import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.io.inputs.InputType;

public enum DummyInputs implements Inputs
{
   TEST1("Test1", DummyInputType.POT, "", 5.5, false),
   TEST2("Test2", DummyInputType.NULL, "", false, false, true),
   TEST3("Test3", DummyInputType.POT, "", 5.5, true),
   TEST4("Test4", DummyInputType.NULL, "", false, true, true);
   
   private final String m_name;
   private final InputType m_type;
   private final String m_port;
   private Object m_default;
   private Object m_module;
   private boolean m_trackingState;
   private boolean m_pullup;

   DummyInputs(String p_name, InputType p_type, String p_port, boolean p_default, boolean p_trackingState, boolean p_pullup)
   {
      m_name = p_name;
      m_type = p_type;
      m_port = p_port;
      m_default = p_default;
      m_trackingState = p_trackingState;
      m_pullup = p_pullup;
   }
   
   DummyInputs(String p_name, InputType p_type, String p_port, double p_default, boolean p_trackingState)
   {
      m_name = p_name;
      m_type = p_type;
      m_port = p_port;
      m_default = p_default;
      m_trackingState = p_trackingState;
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
   
   @Override
   public Object getPort()
   {
      return m_port;
   }

   @Override
   public Object getDefault()
   {
      return m_default;
   }

   @Override
   public Object getModule()
   {
      return m_module;
   }
   
   
   
   public boolean getPullup()
   {
      return m_pullup;
   }

   public boolean isTrackingState()
   {
      return m_trackingState;
   }
}
