package org.wildstang.hardware.crio.inputs;

import org.wildstang.framework.io.inputs.InputType;

public enum WSInputType implements InputType
{
   SWITCH("Digital"),
   HALL_EFFECT("Hall Effect"),
   POT("Analog"),
   JS_BUTTON("Joystick Button"),
   JS_JOYSTICK("Joystick"),
   JS_DPAD("Joystick DPad"),
   LIDAR("LIDAR"),
   ENCODER("Encoder"),
   NULL("Null"),
   CAMERA("Camera"),
   ABSOLUTE_ENCODER("Absolute Encoder");	

   private String m_typeStr;
   
   WSInputType(String p_typeStr)
   {
      m_typeStr = p_typeStr;
   }

   @Override
   public String toString()
   {
      return m_typeStr;
   }
   
   
}
