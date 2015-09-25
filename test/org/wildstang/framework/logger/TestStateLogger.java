package org.wildstang.framework.logger;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.core.DummyInputs;
import org.wildstang.framework.core.DummyOutputs;
import org.wildstang.framework.core.Inputs;
import org.wildstang.framework.core.Outputs;
import org.wildstang.framework.logger.IOSet;
import org.wildstang.framework.logger.StateGroup;
import org.wildstang.framework.logger.StateLogger;
import org.wildstang.framework.logger.StateTracker;

public class TestStateLogger extends BaseTest
{

   private static IOSet ioSet;
   private static StateGroup group;
   
   private StateLogger sLogger;
   private StateTracker mockTracker;
   
   private static final String NAME = "IMU.x";
   private static final String PARENT = "IMU";
   private static final double VALUE = 0.55;

   
   @BeforeClass
   public static void setupBeforeClass()
   {
      ioSet = new IOSet();
      // Create IOSet and StateGroup
      for (Inputs in : DummyInputs.values())
      {
         ioSet.addIOInfo(in.getName(), in.getType().toString(), "Input", in.getPort());
      }
      for (Outputs out : DummyOutputs.values())
      {
         ioSet.addIOInfo(out.getName(), out.getType().toString(), "Output", out.getPort());
      }
      
      group = new StateGroup(new Date());
      
      for (int i = 0; i < 5; i++)
      {
         group.addState(NAME, PARENT, VALUE);
      }

   }
   
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(false);
      mockTracker = createNiceMock(StateTracker.class);
      sLogger = new StateLogger(mockTracker);

//      Whitebox.setInternalState(StateLogger.class, getMockLogger());
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testStateLogger()
   {
      sLogger = new StateLogger(mockTracker);
      assertNotNull(sLogger);
   }

   @Test
   public void testSetWriter()
   {
      StringWriter sw = new StringWriter();
      sLogger.setWriter(sw);
      
      assertEquals(sw, sLogger.getOutput());
      
   }

   @Test
   public void testIsRunning()
   {
      sLogger.start();
      assertTrue(sLogger.isRunning());
   }

   @Test
   public void testStart()
   {
      assertFalse(sLogger.isRunning());
      
      sLogger.start();
      assertTrue(sLogger.isRunning());
   }

   @Test
   public void testStop()
   {
      assertFalse(sLogger.isRunning());
      
      sLogger.start();
      assertTrue(sLogger.isRunning());

      sLogger.stop();
      assertFalse(sLogger.isRunning());
   }

   
   // Tests
   // Run normal
   // Call while stopped (m_running == false)
   // Call when not tracking state
   @Test
   public void testRun()
   {
      sLogger.setWriter(new StringWriter());
      sLogger.start();
      assertTrue(sLogger.isRunning());
      ArrayList<StateGroup> grouplist = new ArrayList<>();
      grouplist.add(group);
      
      resetAll();
      expect(mockTracker.isTrackingState()).andReturn(true).anyTimes();
      expect(mockTracker.getIoSet()).andReturn(ioSet).anyTimes();
      expect(mockTracker.getStateList()).andReturn(grouplist).anyTimes();
      replayAll();
      
      Thread t = new Thread(new Runnable() {
         
         @Override
         public void run()
         {
            try
            {
               Thread.sleep(2500);
            }
            catch (InterruptedException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            
            sLogger.stop();
         }
      });
      t.start();
      sLogger.run();
      
   }

   // Call while stopped (m_running == false)
   @Test
   public void testRunNotRunning()
   {
      sLogger.setWriter(new StringWriter());
      ArrayList<StateGroup> grouplist = new ArrayList<>();
      grouplist.add(group);
      
      resetAll();
      expect(mockTracker.isTrackingState()).andReturn(true).anyTimes();
      expect(mockTracker.getIoSet()).andReturn(ioSet).anyTimes();
      expect(mockTracker.getStateList()).andReturn(grouplist).anyTimes();
      replayAll();
      
      Thread t = new Thread(new Runnable() {
         
         @Override
         public void run()
         {
            try
            {
               Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            
            sLogger.stop();
         }
      });
      t.start();
      sLogger.run();
      
   }

   // Call when not tracking state
   @Test
   public void testRunNotTracking()
   {
      sLogger.setWriter(new StringWriter());
      sLogger.start();
      assertTrue(sLogger.isRunning());
      ArrayList<StateGroup> grouplist = new ArrayList<>();
      grouplist.add(group);
      
      resetAll();
      expect(mockTracker.isTrackingState()).andReturn(false).anyTimes();
      expect(mockTracker.getIoSet()).andReturn(ioSet).anyTimes();
      expect(mockTracker.getStateList()).andReturn(grouplist).anyTimes();
      replayAll();
      
      Thread t = new Thread(new Runnable() {
         
         @Override
         public void run()
         {
            try
            {
               Thread.sleep(2500);
            }
            catch (InterruptedException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            
            sLogger.stop();
         }
      });
      t.start();
      sLogger.run();
      
   }

   // Test with null input
   // Test with valid input
   @Test
   public void testWriteInfoValid()
   {
      String s;
      StringWriter writer = new StringWriter();
      try
      {
         sLogger.writeInfo(writer, ioSet);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Test(expected=NullPointerException.class)
   public void testWriteInfoNullInfo()
   {
      String s;
      StringWriter writer = new StringWriter();
      try
      {
         sLogger.writeInfo(writer, null);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Test
   public void testWriteInfoNullWriter()
   {
      String s;
      StringWriter writer = new StringWriter();
      try
      {
         sLogger.writeInfo(null, ioSet);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   // Test with null input
   // Test with valid input
   @Test
   public void testWriteStateValid()
   {
      String s;
      StringWriter writer = new StringWriter();
      ArrayList<StateGroup> list = new ArrayList<>();
      list.add(group);
      list.add(group);
      
      try
      {
         sLogger.writeState(writer, list);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Test(expected=NullPointerException.class)
   public void testWriteStateNull()
   {
      String s;
      StringWriter writer = new StringWriter();

      try
      {
         sLogger.writeState(writer, null);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Test
   public void testWriteStateNullWriter()
   {
      String s;
      StringWriter writer = new StringWriter();
      ArrayList<StateGroup> list = new ArrayList<>();
      list.add(group);
      list.add(group);

      try
      {
         sLogger.writeState(null, list);
         sLogger.writeState(null, list);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   // Test with null input
   // Test with valid input
   @Test
   public void testWriteEndValid()
   {
      String s;
      StringWriter writer = new StringWriter();

      try
      {
         sLogger.writeEnd(writer);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Test
   public void testWriteEndNull()
   {
      String s = null;
      StringWriter writer = new StringWriter();

      try
      {
         sLogger.writeEnd(null);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      assertEquals("", s.toString());
   }

   // Test with null input
   // Test with valid input
   @Test
   public void testWriteStartValid()
   {
      String s;
      StringWriter writer = new StringWriter();

      try
      {
         sLogger.writeStart(writer);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Test
   public void testWriteStartNull()
   {
      String s = null;
      StringWriter writer = new StringWriter();

      try
      {
         sLogger.writeStart(null);
         s = writer.getBuffer().toString();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      assertEquals("", s.toString());
   }

}
