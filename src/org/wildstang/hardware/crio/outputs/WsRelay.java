package org.wildstang.hardware.crio.outputs;

import org.wildstang.framework.io.outputs.DiscreteOutput;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 *
 */
public class WsRelay extends DiscreteOutput
{
   private Relay relay;
   
   public static final int RELAY_OFF = 0;
   public static final int RELAY_ON = 1;
   public static final int RELAY_FORWARD = 2;
   public static final int RELAY_REVERSE = 3;

   public WsRelay(String p_name, int channel, Direction direction)
   {
      // TODO: Need to handle direction in the output factory
      super(p_name);
      relay = new Relay(channel, direction);
   }

   public void sendDataToOutput()
   {
      Relay.Value value;
      
      switch (getValue())
      {
         case RELAY_ON:
            value = Relay.Value.kOn;
         case RELAY_FORWARD:
            value = Relay.Value.kForward;
         case RELAY_REVERSE:
            value = Relay.Value.kReverse;
         case RELAY_OFF:
         default:
            value = Relay.Value.kOff;
      }
      
      relay.set((Value) value);
   }

   public void notifyConfigChange()
   {
   }
}
