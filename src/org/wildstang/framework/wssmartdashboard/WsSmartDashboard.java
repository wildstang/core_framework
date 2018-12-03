package org.wildstang.framework.wssmartdashboard;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.opencv.core.Mat;
import org.wildstang.framework.core.Core;
import org.wildstang.framework.subsystems.WsMJPEGstreamer;

import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class WsSmartDashboard
{
   public static class TableKeyNotDefinedException extends Exception {
      public TableKeyNotDefinedException() {}
   }
   
   private static final NetworkTable table = NetworkTable.getTable("SmartDashboard");

   private static String ip;

   private static Core robot_core;

   public static void init(Core p_core)
   {
      ip = getIP();
      robot_core = p_core;
   }

   public static String getIP()
   {
      try
      {
         String ipAddr = null;
         String radioIpAddr = null;
         String usbIpAddr = null;
         Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
         while (interfaces.hasMoreElements())
         {
            NetworkInterface iface = interfaces.nextElement();
            // filters out 127.0.0.1 and inactive interfaces
            if (iface.isLoopback() || !iface.isUp()) continue;

            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while (addresses.hasMoreElements())
            {
               InetAddress addr = addresses.nextElement();

               ipAddr = addr.getHostAddress();
               if (ipAddr.startsWith("10.1.11"))
               {
                  radioIpAddr = ipAddr;
               }
               else if (ipAddr.equalsIgnoreCase("192.168.7.2"))
               {
                  usbIpAddr = ipAddr;
               }
            }
         }
         if (radioIpAddr != null)
         {
            return radioIpAddr;
         }
         else if (usbIpAddr != null)
         {
            return usbIpAddr;
         }
         else
         {
            return ipAddr;
         }
      }
      catch (SocketException e)
      {
         throw new RuntimeException(e);
      }
   }

   // public static void putImage(String key, Mat img)
   public static void putImage(String key, Mat img)
   {
      if (ip == null)
      {
         ip = getIP();
      }

      WsMJPEGstreamer mjpegStream = (WsMJPEGstreamer) robot_core.getSubsystemManager().getSubsystem("WsMJPEGstreamer");
      if (mjpegStream == null)
      {
         System.out.println("mjpegStream is null!");
         return;
      }

      // create new BBBCamera key by adding the
      // ~TYPE~ specifier
      table.putString(key + NetworkTable.PATH_SEPARATOR + "~TYPE~", "BBBCamera");
      String urlStr = "";

      String port = mjpegStream.getPort();

      table.putString(key + NetworkTable.PATH_SEPARATOR + "ip", ip);
      table.putString(key + NetworkTable.PATH_SEPARATOR + "port", port);

      ((WsMJPEGstreamer) robot_core.getSubsystemManager().getSubsystem("WsMJPEGstreamer")).send(img);

   }

   public static void putNumber(String key, double value)
   {
      table.putNumber(key, value);
   }

   public static void putBoolean(String key, boolean value)
   {
      table.putBoolean(key, value);
   }

   public static double getNumber(String key)
         throws TableKeyNotDefinedException
   {
      if (!table.containsKey(key)) {
         throw new TableKeyNotDefinedException();
      } else {
         return table.getNumber(key, 0.0);
      }
   }

   public static double getNumber(String key, double defaultValue)
   {
      return table.getNumber(key, defaultValue);
   }

   public static boolean getBoolean(String key)
         throws TableKeyNotDefinedException
   {
      if (!table.containsKey(key)) {
         throw new TableKeyNotDefinedException();
      } else {
         return table.getBoolean(key, false);
      }
   }

   public static boolean getBoolean(String key, boolean defaultValue)
   {
      return table.getBoolean(key, defaultValue);
   }
}
