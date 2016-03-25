package org.wildstang.framework.io.inputs;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.inputs.AbstractInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.inputs.NullAnalogInput;

public class TestNullAnalogInput extends BaseTest
{
   private static final String TEST_INPUT_NAME = "TestInput";
   NullAnalogInput nullInput;

   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      
      Whitebox.setInternalState(AbstractInput.class, getMockLogger());
      Whitebox.setInternalState(NullAnalogInput.class, getMockLogger());
      Whitebox.setInternalState(DigitalInput.class, getMockLogger());

   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testReadRawValue()
   {
      nullInput = new NullAnalogInput(TEST_INPUT_NAME);
      
      resetAll();
      configureLogging();
      replayAll();

      assertEquals(0.0, nullInput.readRawValue(), 0.001);
   }

   @Test
   public void testNullAnalogInputString()
   {
      nullInput = new NullAnalogInput(TEST_INPUT_NAME);

      assertEquals(TEST_INPUT_NAME, nullInput.getName());
      assertEquals(0.0, nullInput.getValue(), 0.001);
   }

   @Test
   public void testNullAnalogInputStringDouble()
   {
      nullInput = new NullAnalogInput(TEST_INPUT_NAME, 5.5);

      assertEquals(TEST_INPUT_NAME, nullInput.getName());
      assertEquals(5.5, nullInput.getValue(), 0.001);
   }

}
