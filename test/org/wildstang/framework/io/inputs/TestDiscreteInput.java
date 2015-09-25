package org.wildstang.framework.io.inputs;


import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.InputListener;
import org.wildstang.framework.io.inputs.AbstractInput;
import org.wildstang.framework.logger.StateTracker;


public class TestDiscreteInput extends BaseTest
{
   private static final String TEST_INPUT_NAME = "TestInput";
   RandomDiscreteInput randomInput;
   InputListener mockListener;
   StateTracker mockStateTracker;
   
   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      mockListener = createMock(InputListener.class);
      mockStateTracker = createNiceMock(StateTracker.class);

      Whitebox.setInternalState(AbstractInput.class, getMockLogger());
      Whitebox.setInternalState(RandomDiscreteInput.class, getMockLogger());
      Whitebox.setInternalState(DiscreteInput.class, getMockLogger());
      Whitebox.setInternalState(StateTracker.class, getMockLogger());

   }

   /**
    * @throws java.lang.Exception
    */
   @After
   public void tearDown() throws Exception
   {
      mockStateTracker.reset();
   }


   @Test
   public void testReadDataFromInput()
   {
      // TESTS
      // 1. Test that when value hasn't changed, read value is same as default value
      // 2. Test that when not holding the value hasValueChanged returns true

      // 1. Test that when value hasn't changed, read value is same as default value
      testRead(true);
      assertEquals(5, randomInput.getValue());
      
//-----------------------------------------

      // 2. Test that when not holding the value hasValueChanged returns true
      testRead(false);
   }

   private void testRead(boolean hold)
   {
      resetAll();
      configureLogging();
      replayAll();

      randomInput = new RandomDiscreteInput(TEST_INPUT_NAME, 5);
      randomInput.setStateTracker(mockStateTracker);
      randomInput.holdValue(hold);
      
      randomInput.readDataFromInput();
      
      // If we're holding, the value should not have changed
      assertTrue(randomInput.hasValueChanged() != hold);
   }

   @Test
   public void testAnalogInputString()
   {
      randomInput = new RandomDiscreteInput(TEST_INPUT_NAME);
      randomInput.setStateTracker(mockStateTracker);

      assertEquals(TEST_INPUT_NAME, randomInput.getName());
      assertEquals(0, randomInput.getValue());
   }

   @Test
   public void testAnalogInputStringDouble()
   {
      randomInput = new RandomDiscreteInput(TEST_INPUT_NAME, 5);
      randomInput.setStateTracker(mockStateTracker);

      assertEquals(TEST_INPUT_NAME, randomInput.getName());
      assertEquals(5, randomInput.getValue());
   }

   @Test
   public void testLogCurrentState()
   {
      resetAll();
      configureLogging();
      replayAll();
      
      // TODO: Remove this and try to get the mock working
      randomInput = new RandomDiscreteInput(TEST_INPUT_NAME);
      randomInput.setStateTracker(mockStateTracker);

      mockStateTracker.beginCycle(new Date());
      randomInput.update();
   }
   
   
}
