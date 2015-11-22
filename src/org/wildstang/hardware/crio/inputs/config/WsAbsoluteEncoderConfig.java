package org.wildstang.hardware.crio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

public class WsAbsoluteEncoderConfig implements InputConfig
{
   private int m_channel = 0;
   private int m_maxVoltage = 0;
   
   public WsAbsoluteEncoderConfig(int channel, int p_maxVoltage)
   {
      m_channel = channel;
      m_maxVoltage = p_maxVoltage;
   }

   public int getChannel()
   {
      return m_channel;
   }

   public int getMaxVoltage()
   {
      return m_maxVoltage;
   }
   
   
}
