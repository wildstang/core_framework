package org.wildstang.newfw.hardware.crio.inputs;


import edu.wpi.first.wpilibj.AnalogInput;


/**
 *
 */
public class WsAnalogInput extends org.wildstang.framework.io.inputs.AnalogInput
{

   AnalogInput input;

   // By giving the input number in the constructor we can make this generic
   // for all digital inputs
   public WsAnalogInput(String p_name, int channel, double p_default)
   {
      super(p_name, p_default);

      input = new AnalogInput(channel);
   }

   public double readRawValue()
   {
      return input.getAverageVoltage();
   }


   public void notifyConfigChange()
   {
      // Nothing to update here, since the config value only affect the
      // start state.
   }
}
