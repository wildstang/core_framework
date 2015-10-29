package org.wildstang.framework.io;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.Output;
import org.wildstang.framework.io.OutputManager;

public class TestOutputManager extends BaseTest
{
   Output mockOutput;
   OutputManager manager;
   
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      mockOutput = createNiceMock(Output.class);
      manager = new OutputManager();
      manager.init();
      
      Whitebox.setInternalState(OutputManager.class, getMockLogger());
   }

   @After
   public void tearDown() throws Exception
   {
      manager.removeAll();
   }

   @Test
   public void testGetInstance()
   {
      configureLogging();
      replayAll();
      assertNotNull(manager);
   }

   @Test
   public void testUpdate()
   {
      // TESTS
      // 1. Call update, validate update was called once on the Output
      // 3. Call update with multiple Outputs added to manager

      // Setup - add the Output
      resetAll();
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestOutput").anyTimes();
      replayAll();

      manager.addOutput(mockOutput);

      // validate number of outputs
      assertEquals(1, manager.size());

      
      // 1. Call update, validate update was called once on the Output
      resetAll();
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestOutput").anyTimes();
      expect(mockOutput.isEnabled()).andReturn(true).anyTimes();
      mockOutput.update();
      expectLastCall().times(1);
      replayAll();
      
      manager.update();
      verifyAll();

      
//-----------------------------------------

      // 2. Call update with multiple Outputs added to manager
      resetAll();
      Output mockOutput2 = createNiceMock(Output.class);
      manager.addOutput(mockOutput2);
      
      configureLogging();
      expect(mockOutput2.getName()).andReturn("TestOutput2").anyTimes();
      expect(mockOutput.isEnabled()).andReturn(true).anyTimes();
      expect(mockOutput2.isEnabled()).andReturn(true).anyTimes();
      mockOutput.update();
      expectLastCall().times(1);
      mockOutput2.update();
      expectLastCall().times(1);
      replayAll();

      assertEquals(2, manager.size());
      
      manager.update();
      verifyAll();

   }

 //-----------------------------------------
   // TESTS
   // 1. Call update, validate update was called once on the Input
   // 2. Call update with multiple Inputs added to manager

   @Test
   public void testUpdateDisabled()
   {
      // Setup - add the Input
      resetAll();
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestInput").anyTimes();

      replayAll();
      manager.addOutput(mockOutput);

      // validate number of inputs
      assertEquals(1, manager.size());

      resetAll();
      
      // 1. Call update, validate update was called once on the Input
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestInput").anyTimes();
      expect(mockOutput.isEnabled()).andReturn(false).anyTimes();
      replayAll();
      
      manager.update();
      verifyAll();

   }      

   @Test
   public void testUpdateMultiplePartialDisable()
   {
      // 2. Call update with multiple Inputs added to manager
      Output mockOutput2 = createNiceMock(Output.class);
      resetAll();
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestInput").anyTimes();
      expect(mockOutput2.getName()).andReturn("TestInput2").anyTimes();
      expect(mockOutput.isEnabled()).andReturn(true).anyTimes();
      expect(mockOutput2.isEnabled()).andReturn(false).anyTimes();
      
      mockOutput.update();
      expectLastCall().times(1);
      replayAll();

      manager.addOutput(mockOutput);
      manager.addOutput(mockOutput2);

      assertEquals(2, manager.size());
      
      manager.update();
      verifyAll();

   }

   
   @Test
   public void testAddOutput()
   {
      // TESTS
      // 1. Call add with null output
      // 2. Call add with valid output
      // 3. Call add with output that already exists in manager


      // 1. Call add with null output
      resetAll();
      configureLogging();
      replayAll();

      boolean thrown = false;
      
      try     
      {
         manager.addOutput(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      
      assertTrue(thrown);
      
      // validate number of outputs
      assertEquals(0, manager.size());

//-----------------------------------------

      // 2. Call add with valid output
      resetAll();
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestInput").anyTimes();
      replay(mockOutput);

      manager.addOutput(mockOutput);
      
      // validate number of outputs
      assertEquals(1, manager.size());
      

//-----------------------------------------

      // 3. Call add with output that already exists in manager
      resetAll();
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestInput").anyTimes();
      replay(mockOutput);
      
      manager.addOutput(mockOutput);
      
      // validate number of outputs
      assertEquals(1, manager.size());


   }

   @Test
   public void testRemoveOutput()
   {
      // TESTS
      // 1. Call remove with null output
      // 2. Call remove with output that exists in the manager
      // 3. Call remove with output that does not exist in the manager
      
      // 1. Call remove with null output
      resetAll();
      configureLogging();
      replayAll();

      boolean thrown = false;

      try
      {
         manager.removeOutput(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      
      assertTrue(thrown);
      

//-----------------------------------------
      
      // 2. Call remove with output that exists in the manager
      resetAll();
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestInput").anyTimes();
      replay(mockOutput);

      manager.addOutput(mockOutput);
      
      // validate number of outputs
      assertEquals(1, manager.size());

      // reset the mock objects
      resetAll();

      // remove the test output
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestInput").anyTimes();
      replayAll();

      manager.removeOutput(mockOutput);
      verify();
      
      // validate number of outputs
      assertEquals(0, manager.size());


//-----------------------------------------

      // 3. Call remove with output that does not exist in the manager
      resetAll();
      configureLogging();
      replayAll();

      manager.removeOutput(mockOutput);
      verify();

      // validate number of outputs
      assertEquals(0, manager.size());
   }

   @Test
   public void testGetOutput()
   {
      // TESTS
      // 1. Call get with null name
      // 2. Call get with name that exists in the manager
      // 3. Call get with output that does not exist in the manager


      configureLogging();
      replayAll();

      // validate number of outputs
      assertEquals(0, manager.size());

      // 1. Call remove with null output
      boolean thrown = false;
      try
      {
         manager.getOutput(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      
      assertTrue(thrown);
      
      
//-----------------------------------------

      // Setup - add the output to the manager
      resetAll();
      configureLogging();
      expect(mockOutput.getName()).andReturn("TestOutput").anyTimes();
      
      replayAll();
      manager.addOutput(mockOutput);

      // validate number of outputs
      assertEquals(1, manager.size());

      
//-----------------------------------------

      // 2. Call get with name that exists in the manager
      Output result = manager.getOutput("TestOutput");
      verifyAll();
      
      assertTrue(result.equals(mockOutput));
      
      
//-----------------------------------------

      // 3. Call get with output that does not exist in the manager
      result = manager.getOutput("TestInpout");
      assertNull(result);
      
      // validate number of outputs
      assertEquals(1, manager.size());
   }

}
