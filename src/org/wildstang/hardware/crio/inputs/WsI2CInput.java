package org.wildstang.hardware.crio.inputs;

import org.wildstang.framework.io.inputs.I2CInput;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class WsI2CInput extends I2CInput
{

   private I2C i2c;
//   MessageHandler messageSender;

   public WsI2CInput(String name, Port port, int p_address)
   {
      super(name);

      i2c = new I2C(port, p_address);
   }

   @Override
   protected byte[] readRawValue()
   {
      // synchronized (messageReceiver)
      // {
      byte[] data = new byte[1];
      if (data != null)
      {
         i2c.readOnly(data, 1);//transaction(data, data.length, null, 0);
         // messageSender.setSendData(data, data.length);
         // messageSender.notify();
      }
      // }

      // Reset so we only send when we have new data
      // setValue(null);
      return data;
   }

//   private static class MessageHandler implements Runnable
//   {
//      // Offload to a thread avoid blocking main thread with I2C sends.
//
//      static byte[] rcvBytes;
//      byte[] sendData;
//      int sendSize = 0;
//      boolean running = true;
//      boolean dataToSend = false;
//      private I2C i2c;
//
//      public MessageHandler(I2C p_i2c)
//      {
//         // Get ourselves an i2c instance to send out some data.
//         i2c = p_i2c;
//      }
//
//      @Override
//      public void run()
//      {
//         while (running)
//         {
//            synchronized (this)
//            {
//               try
//               {
//                  // blocking sleep until someone calls notify.
//                  this.wait();
//                  // Need at least a byte and someone has to have called
//                  // setSendData.
//                  if (sendSize >= 0 && dataToSend)
//                  {
//                     // set receive size to 0 to avoid sending an i2c read
//                     // request.
//                     i2c.writeBulk(sendData);// transaction(sendData, sendSize,
//                                             // rcvBytes, 0);
//                     dataToSend = false;
//                  }
//               }
//               catch (InterruptedException e)
//               {
//               }
//            }
//         }
//      }
//
//      public void setSendData(byte[] data, int size)
//      {
//         sendData = data;
//         sendSize = size;
//         dataToSend = true;
//      }
//
//      public void stop()
//      {
//         running = false;
//      }
//   }
}
