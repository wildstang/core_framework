package org.wildstang.framework.wssmartdashboard;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class WsSmartDashboard
{
   private static final NetworkTable table = NetworkTable.getTable("SmartDashboard");

   private static String ip;

   public static void init()
   {
      ip = getIP();
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

      int count = ((int) table.getNumber(key + NetworkTable.PATH_SEPARATOR
            + "count", -1));

      if (count < 0)
      {
         // count key does not exist, create new BBBCamera key by adding the
         // ~TYPE~ specifier
         table.putString(key + NetworkTable.PATH_SEPARATOR + "~TYPE~", "BBBCamera");
         count = 0;
      }
      else
      {
         // Key exists increment count
         count++;
         count = count % 5;
      }

      String filename = key + count + ".jpg";
      Highgui.imwrite("/public/img/" + filename, img);
      String urlStr = "";

      urlStr = "http://" + ip + ":8888/img/" + filename;

      table.putString(key + NetworkTable.PATH_SEPARATOR + "url", urlStr);
      table.putNumber(key + NetworkTable.PATH_SEPARATOR + "count", (double) count);
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
      return table.getNumber(key);
   }

   public static double getNumber(String key, double defaultValue)
   {
      return table.getNumber(key, defaultValue);
   }

   public static boolean getBoolean(String key)
         throws TableKeyNotDefinedException
   {
      return table.getBoolean(key);
   }

   public static boolean getBoolean(String key, boolean defaultValue)
   {
      return table.getBoolean(key, defaultValue);
   }
}
