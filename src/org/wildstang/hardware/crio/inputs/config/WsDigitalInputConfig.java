package org.wildstang.hardware.crio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

public class WsDigitalInputConfig implements InputConfig
{
   private int m_channel = 0;
   private boolean m_pullup = false;
   
   public WsDigitalInputConfig(int channel, boolean p_pullup)
   {
      m_channel = channel;
      m_pullup = p_pullup;
   }

   public int getChannel()
   {
      return m_channel;
   }

   public boolean getPullup()
   {
      return m_pullup;
   }
   
   

}
