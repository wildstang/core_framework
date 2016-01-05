package org.wildstang.hardware.remote.inputs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
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
   
   
   class RemoteInputListener implements Runnable
   {
      Socket m_socket;
      private boolean m_running = false;
      private ObjectInputStream m_inputStream;
      
      public RemoteInputListener(String ipAddress, int port)
      {
         try
         {
            m_socket = new Socket(ipAddress, port);
            m_inputStream = new ObjectInputStream(m_socket.getInputStream());
         }
         catch (UnknownHostException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         catch (IOException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      public void run()
      {
         while (m_running)
         {
            try
            {
               RemoteInputPacket packet = (RemoteInputPacket)m_inputStream.readObject();
               m_values = packet.getInputData();
            }
            catch (ClassNotFoundException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            catch (IOException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      
      public void start()
      {
         synchronized (this)
         {
            m_running = true;
         }
      }
      
      public void stop()
      {
         synchronized (this)
         {
            m_running = false;
         }
      }
   }
   
}
