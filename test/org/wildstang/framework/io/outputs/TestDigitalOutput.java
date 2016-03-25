package org.wildstang.framework.io.outputs;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.outputs.AbstractOutput;
import org.wildstang.framework.io.outputs.DigitalOutput;
import org.wildstang.framework.io.outputs.NullDigitalOutput;
import org.wildstang.framework.logger.StateTracker;

public class TestDigitalOutput extends BaseTest
{
   private static final String TEST_OUTPUT_NAME = "TestOutput";

   DigitalOutput output;
   StateTracker mockStateTracker;
   
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      
      Whitebox.setInternalState(AbstractOutput.class, getMockLogger());
      Whitebox.setInternalState(NullDigitalOutput.class, getMockLogger());
      Whitebox.setInternalState(DigitalOutput.class, getMockLogger());
      Whitebox.setInternalState(StateTracker.class, getMockLogger());
      mockStateTracker = createNiceMock(StateTracker.class);
   }

   @After
   public void tearDown() throws Exception
   {
      mockStateTracker.reset();
   }

   @Test
   public void testDigitalOutputString()
   {
      output = new NullDigitalOutput(TEST_OUTPUT_NAME);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
   }

   @Test
   public void testDigitalOutputStringDouble()
   {
      output = new NullDigitalOutput(TEST_OUTPUT_NAME, true);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
      assertTrue(output.getValue());
   }
   
   @Test
   public void testSetValue()
   {
      output = new NullDigitalOutput(TEST_OUTPUT_NAME, true);
      assertNotNull(output);
      // Check value is correct after creation
      assertTrue(output.getValue());

      // Change value and verify value is set
      output.setValue(false);
      assertFalse(output.getValue());
   }
   
   @Test
   public void testGetValue()
   {
      output = new NullDigitalOutput(TEST_OUTPUT_NAME, true);

      assertTrue(output.getValue());
   }

   
   @Test
   public void testLogCurrentState()
   {
      resetAll();
      configureLogging();
      replayAll();
      
      output = new NullDigitalOutput(TEST_OUTPUT_NAME);
      output.setStateTracker(mockStateTracker);
      mockStateTracker.beginCycle(new Date());
      output.update();
   }
   
   

}
