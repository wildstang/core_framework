package org.wildstang.newfw.hardware.crio.outputs;

import org.wildstang.framework.io.outputs.DiscreteOutput;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */

public class WsDoubleSolenoid extends DiscreteOutput
{

   DoubleSolenoid solenoid;

   public WsDoubleSolenoid(String name, int channel1, int channel2, int p_default)
   {
      super(name, p_default);

      solenoid = new DoubleSolenoid(0, channel1, channel2);
   }

   @Override
   protected void sendDataToOutput()
   {
      DoubleSolenoid.Value solValue;
      switch (getValue())
      {
         case DoubleSolenoid.Value.kForward_val:
            solValue = DoubleSolenoid.Value.kForward;
            break;
         case DoubleSolenoid.Value.kReverse_val:
            solValue = DoubleSolenoid.Value.kReverse;
            break;
         case DoubleSolenoid.Value.kOff_val:
         default:
            solValue = DoubleSolenoid.Value.kOff;
            break;
      }
      solenoid.set(solValue);
   }
   

}
