package org.wildstang.framework.io.outputs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.outputs.AbstractOutput;
import org.wildstang.framework.io.outputs.AnalogOutput;
import org.wildstang.framework.io.outputs.NullAnalogOutput;

public class TestNullAnalogOutput extends BaseTest
{
   private static final String TEST_OUTPUT_NAME = "TestOutput";

   AnalogOutput output;
 

   @Before
   public void setUp() throws Exception
   {
      super.setUp();

      Whitebox.setInternalState(AbstractOutput.class, getMockLogger());
      Whitebox.setInternalState(NullAnalogOutput.class, getMockLogger());
      Whitebox.setInternalState(AnalogOutput.class, getMockLogger());
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testSendDataToOutput()
   {
      doTestSendDataInternal(true);
      
      doTestSendDataInternal(false);
      
   }

   private void doTestSendDataInternal(boolean loggingOn)
   {
      output = new NullAnalogOutput(TEST_OUTPUT_NAME, 5.5);

      resetAll();
      configureLogging();
      replayAll();
      
      output.sendDataToOutput();
   }

   @Test
   public void testNullAnalogOutputString()
   {
      output = new NullAnalogOutput(TEST_OUTPUT_NAME);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
   }

   @Test
   public void testNullAnalogOutputStringBoolean()
   {
      output = new NullAnalogOutput(TEST_OUTPUT_NAME, 5.5);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
      assertEquals(5.5, output.getValue(), 0.001);
   }

}
