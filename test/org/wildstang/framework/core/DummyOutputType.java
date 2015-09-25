package org.wildstang.framework.core;

import org.wildstang.framework.io.outputs.OutputType;

public enum DummyOutputType implements OutputType
{

   DIGITAL_OUTPUT("Digital"),
   SERVO("Servo"),
   NULL("Null");

   private String m_typeStr;
   
   DummyOutputType(String p_typeStr)
   {
      m_typeStr = p_typeStr;
   }

   @Override
   public String toString()
   {
      return m_typeStr;
   }
}
