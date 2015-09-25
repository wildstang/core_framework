package org.wildstang.framework.core;

import org.wildstang.framework.io.inputs.InputType;

public enum DummyInputType implements InputType
{

   POT("Pot"),
   SWITCH("Switch"),
   NULL("Null");

   private String m_typeStr;
   
   DummyInputType(String p_typeStr)
   {
      m_typeStr = p_typeStr;
   }

   @Override
   public String toString()
   {
      return m_typeStr;
   }
}
