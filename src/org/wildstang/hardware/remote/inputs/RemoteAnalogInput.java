package org.wildstang.hardware.remote.inputs;

import org.wildstang.framework.io.inputs.AnalogInput;

public class RemoteAnalogInput extends AnalogInput
{
   private int m_index;
   private RemoteInputGroup m_group;
   
   public RemoteAnalogInput(String p_name, RemoteInputGroup p_group)
   {
      super(p_name);
      m_group = p_group;
      m_index = m_group.registerInput(p_name);
   }

   @Override
   protected double readRawValue()
   {
      return (double)m_group.getValue(m_index);
   }

}
