package org.wildstang.hardware.crio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

public class WsDigitalInputConfig implements InputConfig
{
   private int m_channel = 0;
   
   public WsDigitalInputConfig(int channel)
   {
      m_channel = channel;
   }

   public int getChannel()
   {
      return m_channel;
   }
   

}
