package org.wildstang.framework.core;


import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.hardware.OutputConfig;
import org.wildstang.framework.io.outputs.OutputType;

public enum DummyOutputs implements Outputs
{
   TEST1("Test1",      DummyOutputType.SERVO,      new DummyOutputConfig(5.5), false),
   TEST2("Test2",      DummyOutputType.NULL,       new DummyOutputConfig(true), false),
   TEST3("Test3",      DummyOutputType.SERVO,      new DummyOutputConfig(5.5), true),
   TEST4("Test4",      DummyOutputType.NULL,       new DummyOutputConfig(false), true);


   private final String m_name;
   private final OutputType m_type;
   private OutputConfig m_config;
   private boolean m_trackingState;

   DummyOutputs(String p_name, OutputType p_type, OutputConfig p_config, boolean p_trackingState)
   {
      m_name = p_name;
      m_type = p_type;
      m_config = p_config;
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
   
   public OutputConfig getConfig()
   {
      return m_config;
   }

   public boolean isTrackingState()
   {
      return m_trackingState;
   }
   

}
