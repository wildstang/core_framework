package org.wildstang.framework.io.outputs;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.outputs.AbstractOutput;
import org.wildstang.framework.io.outputs.DiscreteOutput;
import org.wildstang.framework.io.outputs.NullDiscreteOutput;
import org.wildstang.framework.logger.StateTracker;

public class TestDiscreteOutput extends BaseTest
{
   private static final String TEST_OUTPUT_NAME = "TestOutput";

   DiscreteOutput output;
   StateTracker mockStateTracker;
   
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      
      Whitebox.setInternalState(AbstractOutput.class, getMockLogger());
      Whitebox.setInternalState(NullDiscreteOutput.class, getMockLogger());
      Whitebox.setInternalState(DiscreteOutput.class, getMockLogger());
      Whitebox.setInternalState(StateTracker.class, getMockLogger());
      mockStateTracker = createNiceMock(StateTracker.class);
   }

   @After
   public void tearDown() throws Exception
   {
      mockStateTracker.reset();
   }

   @Test
   public void testDiscreteOutputString()
   {
      output = new NullDiscreteOutput(TEST_OUTPUT_NAME);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
   }

   @Test
   public void testDiscreteOutputStringDouble()
   {
      output = new NullDiscreteOutput(TEST_OUTPUT_NAME, 5);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
      assertEquals(5, output.getValue());
   }
   
   @Test
   public void testSetValue()
   {
      output = new NullDiscreteOutput(TEST_OUTPUT_NAME, 5);
      assertNotNull(output);
      // Check value is correct after creation
      assertEquals(5, output.getValue());

      // Change value and verify value is set
      output.setValue(3);
      assertEquals(3, output.getValue());
   }
   
   @Test
   public void testGetValue()
   {
      output = new NullDiscreteOutput(TEST_OUTPUT_NAME, 7);

      assertEquals(7, output.getValue());
   }

   
   @Test
   public void testLogCurrentState()
   {
      resetAll();
      configureLogging();
      replayAll();
      
      output = new NullDiscreteOutput(TEST_OUTPUT_NAME);
      output.setStateTracker(mockStateTracker);
      mockStateTracker.beginCycle(new Date());
      output.update();
   }
   
   

}
