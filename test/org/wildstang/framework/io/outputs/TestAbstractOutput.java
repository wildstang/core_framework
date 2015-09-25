package org.wildstang.framework.io.outputs;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.outputs.AbstractOutput;
import org.wildstang.framework.io.outputs.AnalogOutput;
import org.wildstang.framework.io.outputs.NullAnalogOutput;
import org.wildstang.framework.logger.StateTracker;


public class TestAbstractOutput extends BaseTest
{
   private static final String TEST_OUTPUT_NAME = "TestOutput";

   AbstractOutput output;

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      output = createMockBuilder(AbstractOutput.class).addMockedMethod("sendDataToOutput").createMock();

      Whitebox.setInternalState(AbstractOutput.class, getMockLogger());
      Whitebox.setInternalState(NullAnalogOutput.class, getMockLogger());
      Whitebox.setInternalState(AnalogOutput.class, getMockLogger());
      Whitebox.setInternalState(StateTracker.class, getMockLogger());
   }

   @After
   public void tearDown() throws Exception
   {
   }

//   @Test
//   public void testHashCode()
//   {
//      fail("Not yet implemented"); // TODO
//   }

   @Test
   public void testAbstractOutput()
   {
      output = new NullAnalogOutput(TEST_OUTPUT_NAME);
      assertNotNull(output);

      boolean thrown = false;
      try
      {
         output = new NullAnalogOutput(null);
      }
      catch (NullPointerException npe)
      {
         thrown = true;
      }
      
      assertTrue(thrown);
   }

   @Test
   public void testUpdate()
   {
      // TESTS
      // 1. Call update, validate that sendDataToOutput is called
      //  - test with logging
      doTestUpdate(true);

      //  - test without logging
      doTestUpdate(false);
}

   private void doTestUpdate(boolean loggingOn)
   {
      resetAll();
      configureLogging();
      output.sendDataToOutput();
      expectLastCall().once();
//      output.logCurrentState();
//      expectLastCall().once();
      replayAll();

      output.update();
      
      verifyAll();
   }

   @Test
   public void testGetName()
   {
      output = new NullAnalogOutput(TEST_OUTPUT_NAME);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
   }

//   @Test
//   public void testEqualsObject()
//   {
//      fail("Not yet implemented"); // TODO
//   }

}
