package org.wildstang.hardware.crio;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.hardware.OutputFactory;
import org.wildstang.framework.io.Output;
import org.wildstang.hardware.crio.outputs.WSOutputType;
import org.wildstang.hardware.crio.outputs.WsDigitalOutput;
import org.wildstang.hardware.crio.outputs.WsDoubleSolenoid;
import org.wildstang.hardware.crio.outputs.WsRelay;
import org.wildstang.hardware.crio.outputs.WsServo;
import org.wildstang.hardware.crio.outputs.WsSolenoid;
import org.wildstang.hardware.crio.outputs.WsTalon;
import org.wildstang.hardware.crio.outputs.WsVictor;

import edu.wpi.first.wpilibj.Relay.Direction;

public class RoboRIOOutputFactory implements OutputFactory
{

   private static Logger s_log = Logger.getLogger(RoboRIOOutputFactory.class.getName());
   private static final String s_className = "RoboRIOOutputFactory";

   private boolean s_initialised = false;

   public RoboRIOOutputFactory()
   {

   }

   @Override
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
   public Output createOutput(Outputs p_output)
   {
      if (s_log.isLoggable(Level.FINER)) s_log.entering(s_className, "createDigitalInput");

      Output out = null;
      
      if (s_log.isLoggable(Level.FINE))
      {
         s_log.fine("Creating digital output: Name = " + p_output.getName() + ", type = " + p_output.getType() + ", port = " + p_output.getPort()+ ", default = " + p_output.getDefault());
      }
      
      switch ((WSOutputType)p_output.getType())
      {
         case DIGITAL_OUTPUT:
            out = new WsDigitalOutput(p_output.getName(), (int)p_output.getPort(), (boolean)p_output.getDefault());
            break;
         case SERVO:
            out = new WsServo(p_output.getName(), (int)p_output.getPort(), (double)p_output.getDefault());
            break;
         case RELAY:
            out = new WsRelay(p_output.getName(), (int)p_output.getPort(), (Direction)p_output.getDefault());
            break;
         case VICTOR:
            out = new WsVictor(p_output.getName(), (int)p_output.getPort(), (double)p_output.getDefault());
            break;
         case TALON:
            out = new WsTalon(p_output.getName(), (int)p_output.getPort(), (double)p_output.getDefault());
            break;
         case SOLENOID_SINGLE:
            out = new WsSolenoid(p_output.getName(), (int)p_output.getPort());
            break;
         case SOLENOID_DOUBLE:
            out = new WsDoubleSolenoid(p_output.getName(), (int)p_output.getModule(), (int)p_output.getPort(), (int)p_output.getDefault());
            break;
         case NULL:
         default:
//            out = new NullDigitalOutput(p_name);
      }
      
      if (s_log.isLoggable(Level.FINER)) s_log.exiting(s_className, "createDigitalInput");

      return out;
   }

}
