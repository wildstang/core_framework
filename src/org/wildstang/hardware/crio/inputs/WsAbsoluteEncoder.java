package org.wildstang.hardware.crio.inputs;


public class WsAbsoluteEncoder extends WsAnalogInput
{
   int m_maxVoltage = 5;
   
   public WsAbsoluteEncoder(String p_name, int channel, int p_maxVoltage)
   {
      super(p_name, channel);
      m_maxVoltage = p_maxVoltage;
   }

   public double readRawValue()
   {
      double rawValue = super.readRawValue();
      
//      double position = (rawValue / m_maxVoltage) * 360;

      // TODO: Only for testing with a multi-turn pot.. remove this and replace with above line later!
      double position = (double)((int)((rawValue / m_maxVoltage) * 360 * 5) % 360);
      
      return position;
   }

}
