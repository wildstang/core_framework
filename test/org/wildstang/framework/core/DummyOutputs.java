package org.wildstang.framework.core;


import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.io.outputs.OutputType;

public enum DummyOutputs implements Outputs
{
   TEST1("Test1",      DummyOutputType.SERVO,       "", 5.5, false),
   TEST2("Test2",      DummyOutputType.NULL,       "", true, false),
   TEST3("Test3",      DummyOutputType.SERVO,       "", 5.5, true),
   TEST4("Test4",      DummyOutputType.NULL,       "", true, true);


   private final String m_name;
   private final OutputType m_type;
   private final String m_port;
   private Object m_default;
   private Object m_module;
   private boolean m_trackingState;

   DummyOutputs(String p_name, OutputType p_type, String p_port, boolean p_trackingState)
   {
      m_name = p_name;
      m_type = p_type;
      m_port = p_port;
      m_trackingState = p_trackingState;
   }
   
   DummyOutputs(String p_name, OutputType p_type, String p_port, boolean p_default, boolean p_trackingState)
   {
      m_name = p_name;
      m_type = p_type;
      m_port = p_port;
      m_default = p_default;
      m_trackingState = p_trackingState;
   }
   
   DummyOutputs(String p_name, OutputType p_type, String p_port, double p_default, boolean p_trackingState)
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
   public OutputType getType()
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

   public boolean isTrackingState()
   {
      return m_trackingState;
   }
   

}
