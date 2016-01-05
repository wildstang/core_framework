package org.wildstang.hardware.remote.inputs;

import java.io.Serializable;
import java.util.ArrayList;

public class RemoteInputPacket implements Serializable
{
   private static final long serialVersionUID = 1L;
   
   private ArrayList m_inputs;

   public RemoteInputPacket()
   {
      
   }
   
   public void addItem(Object item)
   {
      m_inputs.add(item);
   }
   
   public Object[] getInputData()
   {
      return m_inputs.toArray();
   }
   
}
