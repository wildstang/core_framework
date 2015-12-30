package org.wildstang.hardware.remote.inputs;

import java.util.HashMap;

public class RemoteInputGroup
{
   private int m_numInputs = 0;
   private HashMap<String, Integer> m_inputIndexMap = new HashMap<String, Integer>();
   
   private Object[] m_values;
   
   public int registerInput(String p_name)
   {
      m_inputIndexMap.put(p_name, m_numInputs);

      return m_numInputs++;
   }
   
   
   public Object getValue(int p_index)
   {
      return m_values[p_index];
   }
   
   
   
}
