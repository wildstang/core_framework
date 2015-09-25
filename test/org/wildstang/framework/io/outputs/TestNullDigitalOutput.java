package org.wildstang.framework.io.outputs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.outputs.AbstractOutput;
import org.wildstang.framework.io.outputs.DigitalOutput;
import org.wildstang.framework.io.outputs.NullDigitalOutput;

public class TestNullDigitalOutput extends BaseTest
{
   private static final String TEST_OUTPUT_NAME = "TestOutput";

   DigitalOutput output;


   @Before
   public void setUp() throws Exception
   {
      super.setUp();

      Whitebox.setInternalState(AbstractOutput.class, getMockLogger());
      Whitebox.setInternalState(NullDigitalOutput.class, getMockLogger());
      Whitebox.setInternalState(DigitalOutput.class, getMockLogger());
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testSendDataToOutput()
   {
      output = new NullDigitalOutput(TEST_OUTPUT_NAME, true);

      resetAll();
      configureLogging();
      replayAll();
      
      output.sendDataToOutput();
   }

   @Test
   public void testNullDigitalOutputString()
   {
      output = new NullDigitalOutput(TEST_OUTPUT_NAME);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
   }

   @Test
   public void testNullDigitalOutputStringBoolean()
   {
      output = new NullDigitalOutput(TEST_OUTPUT_NAME, true);
      assertNotNull(output);
      assertEquals(TEST_OUTPUT_NAME, output.getName());
      assertTrue(output.getValue());
   }

}
