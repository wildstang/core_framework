/**
 * 
 */
package org.wildstang.framework.io.inputs;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.InputListener;
import org.wildstang.framework.io.inputs.AbstractInput;
import org.wildstang.framework.io.inputs.AnalogInput;
import org.wildstang.framework.io.inputs.NullAnalogInput;
import org.wildstang.framework.logger.StateTracker;

/**
 * @author Steve
 *
 */
public class TestAbstractInput extends BaseTest
{
   private static final String TEST_INPUT_NAME = "TestInput";
   RandomAnalogInput randomInput;
   InputListener mockListener;
   StateTracker mockStateTracker;
   
   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      randomInput = new RandomAnalogInput(TEST_INPUT_NAME);
      mockListener = createMock(InputListener.class);
      mockStateTracker = createNiceMock(StateTracker.class);
      randomInput.setStateTracker(mockStateTracker);
      
      
      Whitebox.setInternalState(AbstractInput.class, getMockLogger());
      Whitebox.setInternalState(RandomAnalogInput.class, getMockLogger());
      Whitebox.setInternalState(AnalogInput.class, getMockLogger());

   }

   /**
    * @throws java.lang.Exception
    */
   @After
   public void tearDown() throws Exception
   {
      randomInput.removeAllListeners();
      mockStateTracker.reset();

   }

//   @Test
//   public void testHashCode()
//   {
//      fail("Not yet implemented"); // TODO
//   }

 //-----------------------------------------
   // TESTS
   // 1. No listeners added, confirm an empty list is returned - not a null list
   // 2. Add two listeners, get listener list, check list that both listeners are in the list
   
   @Test
   public void testGetInputListenersEmpty()
   {
      // 1. No listeners added, confirm an empty list is returned - not a null list
      configureLogging();
      replayAll();

      List<InputListener> tempList = randomInput.getInputListeners();
      assertNotNull(tempList);
      
      assertTrue(tempList.isEmpty());
      
      verifyAll();
   }      
      

   @Test
   public void testGetInputListenersMultiple()
   {
      // 2. Add two listeners, get listener list, check list that both listeners are in the list

      InputListener mockListener2 = createMock(InputListener.class);

      resetAll();
      configureLogging();
      replayAll();
      randomInput.addInputListener(mockListener);
      randomInput.addInputListener(mockListener2);
      List<InputListener> tempList = randomInput.getInputListeners();
      
      assertEquals(2, tempList.size());
      assertTrue(tempList.contains(mockListener));
      assertTrue(tempList.contains(mockListener2));

      verifyAll();

      // Test again without logging
      resetAll();
      randomInput.removeAllListeners();
      
      configureLogging();
      replayAll();
      randomInput.addInputListener(mockListener);
      randomInput.addInputListener(mockListener2);
      tempList = randomInput.getInputListeners();
      
      assertEquals(2, tempList.size());
      assertTrue(tempList.contains(mockListener));
      assertTrue(tempList.contains(mockListener2));

      verifyAll();
   }

 //-----------------------------------------
   // TESTS
   // 1. Call with null listener - throws NPE
   // 2. Call with valid listener
   // 3. Call with listener that already exists in list - should not add a second time
      // - verify by checking number of calls to notify on listener
   @Test
   public void testAddInputListener()
   {
      randomInput.holdValue(false);    // make sure new value is generated to call inputUpdate
      
      // 1. Call with null listener - throws NPE
      configureLogging();
      replayAll();
      boolean thrown = false;
      
      try     
      {
         randomInput.addInputListener(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      
      assertTrue(thrown);
   }      


   @Test
   public void testAddInputListenerValid()
   {
      // 2. Call with valid listener
      randomInput.holdValue(false);    // make sure new value is generated to call inputUpdate
      testAddValidInputListener();
   }

   @Test
   public void testAddInputListenerExisting()
   {
      // 3. Call with listener that already exists in list - should not add a second time
      // the function called checks for only ONE call to inputUpdate()
      randomInput.holdValue(false);    // make sure new value is generated to call inputUpdate
      testAddValidInputListener();
      testAddValidInputListener();
   }

   private void testAddValidInputListener()
   {
      resetAll();
      
      configureLogging();

      mockListener.inputUpdate(randomInput);
      expectLastCall().once();
      replayAll();
      
      mockStateTracker.beginCycle(new Date());
      // Test with different input value - update called
      randomInput.addInputListener(mockListener);
      randomInput.update();
      mockStateTracker.endCycle();


      verifyAll();
   }

   @Test
   public void testRemoveInputListener()
   {
      InputListener mockListener2 = createMock(InputListener.class);
      configureLogging();
      replayAll();
      
      randomInput.addInputListener(mockListener);
      randomInput.addInputListener(mockListener2);
      
      assertEquals(2, randomInput.getInputListeners().size());
      
      randomInput.removeInputListener(mockListener2);
      
      assertEquals(1, randomInput.getInputListeners().size());
      
      assertTrue(randomInput.getInputListeners().contains(mockListener));

      resetAll();
      configureLogging();
      replayAll();

      randomInput.removeInputListener(mockListener);
      
      assertEquals(0, randomInput.getInputListeners().size());
   }

   @Test(expected=NullPointerException.class)
   public void testRemoveInputListenerNull()
   {
      InputListener mockListener2 = createMock(InputListener.class);
      configureLogging();
      replayAll();
      
      randomInput.addInputListener(mockListener);
      randomInput.addInputListener(mockListener2);
      
      assertEquals(2, randomInput.getInputListeners().size());
      
      randomInput.removeInputListener(null);
      
      assertEquals(1, randomInput.getInputListeners().size());
      
      assertTrue(randomInput.getInputListeners().contains(mockListener));

      resetAll();
      configureLogging();
      replayAll();

      randomInput.removeInputListener(mockListener);
      
      assertEquals(0, randomInput.getInputListeners().size());
   }

   @Test
   public void testRemoveAllListener()
   {
      InputListener mockListener2 = createMock(InputListener.class);
      configureLogging();
      replayAll();
      
      randomInput.addInputListener(mockListener);
      randomInput.addInputListener(mockListener2);
      
      assertEquals(2, randomInput.getInputListeners().size());
      
      randomInput.removeAllListeners();
      
      assertEquals(0, randomInput.getInputListeners().size());
   }

   @Test
   public void testUpdate()
   {
      // 2. Call with valid listener
      // Test with same value - update not called
      testUpdateInternal(true);

      // Test with new/updated value - update will be called
      testUpdateInternal(false);

   }

   private void testUpdateInternal(boolean holdValue)
   {
      resetAll();
      
      configureLogging();
      // If we are holding the value constant, update is not called, so inputUpdate will not be called
      if (!holdValue)
      {
         mockListener.inputUpdate(randomInput);
         expectLastCall().once();
      }
      replayAll();

      mockStateTracker.beginCycle(new Date());
      randomInput.addInputListener(mockListener);
      randomInput.holdValue(holdValue);
      randomInput.update();
      mockStateTracker.endCycle();

      verifyAll();
   }

   @Test
   public void testAbstractInput()
   {
      NullAnalogInput input = new NullAnalogInput(TEST_INPUT_NAME);
      assertNotNull(input);

      boolean thrown = false;
      try
      {
         input = new NullAnalogInput(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      
      assertTrue(thrown);
   }

   @Test
   public void testGetName()
   {
      assertTrue(randomInput.getName().equals(TEST_INPUT_NAME));
   }

//   @Test
//   public void testEqualsObject()
//   {
//      fail("Not yet implemented"); // TODO
//   }

}
