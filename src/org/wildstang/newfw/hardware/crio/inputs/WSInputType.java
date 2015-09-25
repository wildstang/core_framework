package org.wildstang.newfw.hardware.crio.inputs;

import org.wildstang.framework.io.inputs.InputType;

public enum WSInputType implements InputType
{
   SWITCH("Digital"),
   HALL_EFFECT("Hall Effect"),
   POT("Analog"),
   LIDAR("LIDAR"),
   NULL("Null");

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
