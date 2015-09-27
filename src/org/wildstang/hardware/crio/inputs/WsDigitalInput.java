package org.wildstang.hardware.crio.inputs;


import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class WsDigitalInput extends org.wildstang.framework.io.inputs.DigitalInput
{

   DigitalInput input;

   // By giving the input number in the constructor we can make this generic
   // for all digital inputs
   public WsDigitalInput(String p_name, int channel, boolean p_default)
   {
      super(p_name, p_default);

      this.input = new DigitalInput(channel);
   }

   public boolean readRawValue()
   {
      return input.get();
   }
}
