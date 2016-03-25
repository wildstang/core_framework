package org.wildstang.framework.core;

import org.wildstang.framework.hardware.OutputFactory;
import org.wildstang.framework.io.Output;
import org.wildstang.framework.io.outputs.NullAnalogOutput;
import org.wildstang.framework.io.outputs.NullDigitalOutput;

public class NullOutputFactory implements OutputFactory
{

   public NullOutputFactory()
   {
      
   }
   
   @Override
   public void init()
   {
   }

   @Override
   public Output createOutput(Outputs p_output)
   {
      Output out;
      switch ((DummyOutputType)p_output.getType())
      {
         case SERVO:
            out = new NullAnalogOutput(p_output.getName(), (double)((DummyOutputConfig)p_output.getConfig()).getDouble());
            break;
         case NULL:
         default:
            out = new NullDigitalOutput(p_output.getName(), (boolean)((DummyOutputConfig)p_output.getConfig()).getBoolean());
            break;
      }
      
      return out;
   }

}
