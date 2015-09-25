package org.wildstang.framework.hardware;

import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.io.Input;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.DigitalInput;

/**
 * Creates Input objects attached to a specified port, and of a specified type.
 * 
 * @author Steve
 *
 */
public interface InputFactory
{
   
   public void init();
   
   public Input createInput(Inputs p_input);
}
