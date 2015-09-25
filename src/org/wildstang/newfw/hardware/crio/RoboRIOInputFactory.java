package org.wildstang.newfw.hardware.crio;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.hardware.InputFactory;
import org.wildstang.framework.io.Input;
import org.wildstang.newfw.hardware.crio.inputs.WSInputType;
import org.wildstang.newfw.hardware.crio.inputs.WsAnalogInput;
import org.wildstang.newfw.hardware.crio.inputs.WsDigitalInput;
import org.wildstang.newfw.hardware.crio.inputs.WsHallEffectInput;
import org.wildstang.newfw.hardware.crio.inputs.WsLIDAR;
import org.wildstang.yearly.robot.WSInputs;

import edu.wpi.first.wpilibj.I2C.Port;

public class RoboRIOInputFactory implements InputFactory
{

   private static Logger s_log = Logger.getLogger(RoboRIOInputFactory.class.getName());
   private static final String s_className = "RoboRIOInputFactory";
   private boolean s_initialised = false;

   public RoboRIOInputFactory()
   {

   }


   public void init()
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "init");

      if (!s_initialised)
      {
         s_initialised = true;
      }

      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "init");
   }


   @Override
   public Input createInput(Inputs p_input)
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "createAnalogInput");
      
      Input in = null;
      
      if (s_log.isLoggable(Level.FINE))
      {
         s_log.fine("Creating analog input: Name = " + p_input.getName() + ", type = " + p_input.getType() + ", port = " + p_input.getPort() + ", default = " + p_input.getDefault());
      }
      
      WSInputs wsInput = (WSInputs)p_input;


      switch ((WSInputType)p_input.getType())
      {
         case POT:
            in = new WsAnalogInput(p_input.getName(), (int)p_input.getPort(), (double)p_input.getDefault());
            break;
         case SWITCH: 
            in = new WsDigitalInput(p_input.getName(), (int)p_input.getPort(), (boolean)p_input.getDefault());
            break;
         case HALL_EFFECT:
            // Port is the address, module is the port - such as I2C.kMXP
            in = new WsHallEffectInput(p_input.getName(), (Port)p_input.getModule(), (int)p_input.getPort());
            break;
         case LIDAR:
            // Port is the address, module is the port - such as I2C.kMXP
            in = new WsLIDAR(p_input.getName(), (Port)p_input.getModule(), (int)p_input.getPort());
            break;
         case NULL:
         default:
//            in = new NullAnalogInput(p_name);
            break;
      }
      
      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "createAnalogInput");

      return in;
   }
   
   
}
