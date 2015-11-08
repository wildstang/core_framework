package org.wildstang.hardware.crio.inputs.config;

import org.wildstang.framework.hardware.InputConfig;

public class WsJSButtonInputConfig implements InputConfig
{
   private int m_port = 0;
   private int m_button = 0;
   
   public WsJSButtonInputConfig(int p_port, int p_button)
   {
      m_port = p_port;
      m_button = p_button;
   }

   public int getPort()
   {
      return m_port;
   }

   public int getButton()
   {
      return m_button;
   }

   
   
}
