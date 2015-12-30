package org.wildstang.hardware.remote.inputs;

import org.wildstang.framework.io.inputs.DiscreteInput;

public class RemoteDiscreteInput extends DiscreteInput
{
   private int m_index;
   private RemoteInputGroup m_group;
   
   public RemoteDiscreteInput(String p_name, RemoteInputGroup p_group)
   {
      super(p_name);
      m_group = p_group;
      m_index = m_group.registerInput(p_name);
   }

   @Override
   protected int readRawValue()
   {
      return (int)m_group.getValue(m_index);
   }

}
