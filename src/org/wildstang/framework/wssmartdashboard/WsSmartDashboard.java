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
      try
      {
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
               ip = addr.getHostAddress();
            }
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

      Boolean toggle;
      if (!table.containsKey(key + NetworkTable.PATH_SEPARATOR + "changed"))
      {
         table.putString(key + NetworkTable.PATH_SEPARATOR + "~TYPE~", "BBBCamera");
         toggle = true;
      }
      else
      {
         // Key exists, toggle to update
         toggle = !table.getBoolean(key + NetworkTable.PATH_SEPARATOR
               + "changed", true);
      }

      String filename = key + ".jpg";
      Highgui.imwrite("/public/img/" + filename, img);
      String urlStr = "";

      urlStr = "http://" + ip + ":8888/img/" + filename;

      table.putString(key + NetworkTable.PATH_SEPARATOR + "url", urlStr);
      table.putBoolean(key + NetworkTable.PATH_SEPARATOR + "changed", toggle);
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
