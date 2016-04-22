package org.wildstang.hardware.crio.inputs;

import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.hardware.JoystickConstants;

import edu.wpi.first.wpilibj.Joystick;

public class WsDPadButton extends DigitalInput
{

   Joystick m_joystick;
   int m_buttonIndex;
   
   public WsDPadButton(String p_name, int p_port, int p_buttonIndex)
   {
      super(p_name, true, 3);
      m_joystick = new Joystick(p_port);
      m_buttonIndex = p_buttonIndex;
   }
   
   @Override
   protected boolean readRawValue()
   {
      double value;
      boolean boolValue = false;

      switch (m_buttonIndex)
      {
         case JoystickConstants.DPAD_X_LEFT:
            value = m_joystick.getPOV();
            boolValue = (value >= 225 && value < 315);
            break;
         case JoystickConstants.DPAD_X_RIGHT:
            value = m_joystick.getPOV();
            boolValue = (value >= 45 && value < 135);
            break;
         case JoystickConstants.DPAD_Y_UP:
            value = m_joystick.getPOV();
            boolValue = (value >= 315 && value < 45);
            break;
         case JoystickConstants.DPAD_Y_DOWN:
            value = m_joystick.getPOV();;
            boolValue = (value >= 135 && value < 225);
            break;
      }

      return boolValue;
   }

}
