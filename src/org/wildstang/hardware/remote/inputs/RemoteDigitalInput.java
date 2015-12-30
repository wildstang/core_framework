package org.wildstang.hardware.remote.inputs;

import org.wildstang.framework.io.inputs.DigitalInput;

public class RemoteDigitalInput extends DigitalInput
{
   private int m_index;
   private RemoteInputGroup m_group;
   
   public RemoteDigitalInput(String p_name, RemoteInputGroup p_group)
   {
      super(p_name);
      m_group = p_group;
      m_index = m_group.registerInput(p_name);
   }

   @Override
   protected boolean readRawValue()
   {
      return (boolean)m_group.getValue(m_index);
   }


}
