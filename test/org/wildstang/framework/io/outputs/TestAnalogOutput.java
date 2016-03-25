package org.wildstang.framework.io.outputs;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.powermock.api.easymock.*;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.outputs.AbstractOutput;
import org.wildstang.framework.io.outputs.AnalogOutput;
import org.wildstang.framework.io.outputs.NullAnalogOutput;
import org.wildstang.framework.logger.StateTracker;

public class TestAnalogOutput extends BaseTest
{
   private static final String TEST_OUTPUT_NAME = "TestOutput";

   AnalogOutput output;
   StateTracker mockStateTracker;

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      output = createMockBuilder(AnalogOutput.class).addMockedMethod("sendDataToOutput").createMock();
      mockStateTracker = createNiceMock(StateTracker.class);

//      PowerMock.mockStatic(StateManager.class);
//      mockStateManager = createMock(StateManager.class);
//      mockStateManager = createMockBuilder(StateManager.class).addMockedMethods("getInstance").createNiceMock();
      
      Whitebox.setInternalState(AbstractOutput.class, getMockLogger());
      Whitebox.setInternalState(NullAnalogOutput.class, getMockLogger());
      Whitebox.setInternalState(AnalogOutput.class, getMockLogger());
      Whitebox.setInternalState(StateTracker.class, getMockLogger());
   }

   @After
   public void tearDown() throws Exception
   {
      mockStateTracker.reset();
   }

   @Test
   public void testAnalogOutputString()
   {
      output = new NullAnalogOutput(TEST_OUTPUT_NAME);
      output.setStateTracker(mockStateTracker);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
   }

   @Test
   public void testAnalogOutputStringDouble()
   {
      output = new NullAnalogOutput(TEST_OUTPUT_NAME, 5.5);
      output.setStateTracker(mockStateTracker);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
      assertEquals(5.5, output.getValue(), 0.001);
   }
   
   @Test
   public void testSetValue()
   {
      output = new NullAnalogOutput(TEST_OUTPUT_NAME, 5.5);
      output.setStateTracker(mockStateTracker);
      assertNotNull(output);
      // Check value is correct after creation
      assertEquals(5.5, output.getValue(), 0.001);

      // Change value and verify value is set
      output.setValue(7.6);
      assertEquals(7.6, output.getValue(), 0.001);
   }
   
   @Test
   public void testGetValue()
   {
      output = new NullAnalogOutput(TEST_OUTPUT_NAME, 5.5);
      output.setStateTracker(mockStateTracker);

      assertEquals(5.5, output.getValue(), 0.001);
   }

   @Test
   public void testLogCurrentState()
   {
      resetAll();
      configureLogging();
      replayAll();
      
      // TODO: Remove this and try to get the mock working
      output = new NullAnalogOutput(TEST_OUTPUT_NAME);
      output.setStateTracker(mockStateTracker);

      mockStateTracker.beginCycle(new Date());
      output.update();
   }
   

}
