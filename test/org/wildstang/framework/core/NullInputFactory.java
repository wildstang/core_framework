package org.wildstang.framework.core;

import org.wildstang.framework.hardware.InputFactory;
import org.wildstang.framework.io.Input;
import org.wildstang.framework.io.inputs.NullAnalogInput;
import org.wildstang.framework.io.inputs.NullDigitalInput;

public class NullInputFactory implements InputFactory
{

   public NullInputFactory()
   {
      
   }
   @Override
   public void init()
   {
   }

   @Override
   public Input createInput(Inputs p_input)
   {
      Input in;
      switch ((DummyInputType)p_input.getType())
      {
         case POT:
            in = new NullAnalogInput(p_input.getName(), (double)p_input.getDefault());
            break;
         case NULL:
         default:
            in = new NullDigitalInput(p_input.getName(), (boolean)p_input.getDefault());
            break;
      }
      
      return in;
   }

}
