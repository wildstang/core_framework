/**
 * 
 */
package org.wildstang.framework.io;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.Input;
import org.wildstang.framework.io.InputManager;
import org.wildstang.framework.io.inputs.AbstractInput;
import org.wildstang.framework.io.inputs.RandomAnalogInput;

/**
 * @author Steve
 *
 */
public class TestInputManager extends BaseTest
{

   Input mockInput;
   IInputManager manager;
   
   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(false);
      
      mockInput = createNiceMock(AbstractInput.class);
      manager = new InputManager();
      manager.init();
      
      Whitebox.setInternalState(InputManager.class, getMockLogger());
   }

   /**
    * @throws java.lang.Exception
    */
   @After
   public void tearDown() throws Exception
   {
      manager.removeAll();
   }

   
//-----------------------------------------
   // TESTS
   // 1. Call update, validate update was called once on the Input
   // 2. Call update with multiple Inputs added to manager

   @Test
   public void testUpdate()
   {
      // Setup - add the Input
      resetAll();
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();

      replayAll();
      manager.addInput(mockInput);

      // validate number of inputs
      assertEquals(1, manager.size());

      resetAll();
      
      // 1. Call update, validate update was called once on the Input
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      expect(mockInput.isEnabled()).andReturn(true).anyTimes();
      mockInput.update();
      expectLastCall().times(1);
      replayAll();
      
      manager.update();
      verifyAll();

   }      

   @Test
   public void testUpdateMultiple()
   {
      // 2. Call update with multiple Inputs added to manager
      Input mockInput2 = createNiceMock(Input.class);
      resetAll();
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      expect(mockInput2.getName()).andReturn("TestInput2").anyTimes();
      expect(mockInput.isEnabled()).andReturn(true).anyTimes();
      expect(mockInput2.isEnabled()).andReturn(true).anyTimes();
      
      mockInput.update();
      expectLastCall().times(1);
      mockInput2.update();
      expectLastCall().times(1);
      replayAll();

      manager.addInput(mockInput);
      manager.addInput(mockInput2);

      assertEquals(2, manager.size());
      
      manager.update();
      verifyAll();

   }

   
 //-----------------------------------------
   // TESTS
   // 1. Call update, validate update was not called once on the Input
   // 2. Call update with multiple Inputs added to manager, update only called on one

   @Test
   public void testUpdateDisabled()
   {
      // Setup - add the Input
      resetAll();
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();

      replayAll();
      manager.addInput(mockInput);

      // validate number of inputs
      assertEquals(1, manager.size());

      resetAll();
      
      // 1. Call update, validate update was called once on the Input
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      expect(mockInput.isEnabled()).andReturn(false).anyTimes();
      replayAll();
      
      manager.update();
      verifyAll();

   }      

   @Test
   public void testUpdateMultiplePartialDisable()
   {
      // 2. Call update with multiple Inputs added to manager
      Input mockInput2 = createNiceMock(Input.class);
      resetAll();
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      expect(mockInput2.getName()).andReturn("TestInput2").anyTimes();
      expect(mockInput.isEnabled()).andReturn(true).anyTimes();
      expect(mockInput2.isEnabled()).andReturn(false).anyTimes();
      
      mockInput.update();
      expectLastCall().times(1);
      replayAll();

      manager.addInput(mockInput);
      manager.addInput(mockInput2);

      assertEquals(2, manager.size());
      
      manager.update();
      verifyAll();

   }

   
 ///-----------------------------------------
   // TESTS
   // 1. Call add with null input
   // 2. Call add with valid input
   // 3. Call add with input that already exists in manager

   @Test
   public void testAddInputNull()
   {
      
      // 1. Call add with null input
      configureLogging();
      replayAll();
      boolean thrown = false;
      
      try     
      {
         manager.addInput(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      
      assertTrue(thrown);
      
      // validate number of inputs
      assertEquals(0, manager.size());
   }

   @Test
   public void testAddInputValid()
   {
      // 2. Call add with valid input
      resetAll();
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      replay(mockInput);

      manager.addInput(mockInput);
      
      // validate number of inputs
      assertEquals(1, manager.size());

   }

   @Test
   public void testAddInputExistingInput()
   {
      // 3. Call add with input that already exists in manager
      resetAll();
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      replay(mockInput);

      manager.addInput(mockInput);
      manager.addInput(mockInput);
      
      // validate number of inputs
      assertEquals(1, manager.size());
   }

 //-----------------------------------------
   // TESTS
   // 1. Call remove with null input
   // 2. Call remove with input that exists in the manager
   // 3. Call remove with input that does not exist in the manager

   @Test
   public void testRemoveInputNull()
   {
      // 1. Call remove with null input
      replayAll();
      boolean thrown = false;
      try
      {
         manager.removeInput(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      
      assertTrue(thrown);
   }      

      
   @Test
   public void testRemoveInputValid()
   {
      // 2. Call remove with input that exists in the manager
      // Add the input
      resetAll();
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      replay(mockInput);

      manager.addInput(mockInput);
      
      // validate number of inputs
      assertEquals(1, manager.size());

      // reset the mock objects
      resetAll();

      // remove the test input
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      replayAll();
      manager.removeInput(mockInput);
      verifyAll();
      
      // validate number of inputs
      assertEquals(0, manager.size());

      // reset the mock objects
      resetAll();
   }

   @Test
   public void testRemoveInputDoesntExist()
   {
      // 3. Call remove with input that does not exist in the manager
      replayAll();
      manager.removeInput(mockInput);
      verifyAll();

      // validate number of inputs
      assertEquals(0, manager.size());


   }

//-----------------------------------------
   // TESTS
   // 1. Call get with null name
   // 2. Call get with name that exists in the manager
   // 3. Call get with input that does not exist in the manager

   @Test
   public void testGetInputNull()
   {
      configureLogging();
      replayAll();

      // validate number of inputs
      assertEquals(0, manager.size());

      // 1. Call remove with null input
      boolean thrown = false;
      try
      {
         manager.getInput(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      
      assertTrue(thrown);
   }      

   @Test
   public void testGetInputValid()
   {
      // 2. Call get with name that exists in the manager
      resetAll();
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      
      replayAll();
      manager.addInput(mockInput);

      // validate number of inputs
      assertEquals(1, manager.size());

      Input result = manager.getInput("TestInput");
      verifyAll();
      
      assertTrue(result.equals(mockInput));
   }      
      

   @Test
   public void testGetInputDoesntExist()
   {
      // 3. Call get with input that does not exist in the manager
      resetAll();
      configureLogging();
      expect(mockInput.getName()).andReturn("TestInput").anyTimes();
      
      replayAll();
      manager.addInput(mockInput);

      // validate number of inputs
      assertEquals(1, manager.size());

      Input result = manager.getInput("TestInpout");
      assertNull(result);
      
      // validate number of inputs
      assertEquals(1, manager.size());

      
   }

}
