package org.wildstang.framework.io.inputs;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.inputs.AbstractInput;
import org.wildstang.framework.io.inputs.DigitalInput;
import org.wildstang.framework.io.inputs.NullDigitalInput;

public class TestNullDigitalInput extends BaseTest
{

   private static final String TEST_INPUT_NAME = "TestInput";
   NullDigitalInput nullInput;

   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      
      Whitebox.setInternalState(AbstractInput.class, getMockLogger());
      Whitebox.setInternalState(NullDigitalInput.class, getMockLogger());
      Whitebox.setInternalState(DigitalInput.class, getMockLogger());

   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testReadRawValue()
   {
      nullInput = new NullDigitalInput(TEST_INPUT_NAME);
      
      resetAll();
      configureLogging();
      replayAll();

      assertFalse(nullInput.readRawValue());
   }

   @Test
   public void testNullDigitalInputString()
   {
      nullInput = new NullDigitalInput(TEST_INPUT_NAME);

      assertEquals(TEST_INPUT_NAME, nullInput.getName());
      assertFalse(nullInput.getValue());
   }

   @Test
   public void testNullDigitalInputStringDouble()
   {
      nullInput = new NullDigitalInput(TEST_INPUT_NAME, true);

      assertEquals(TEST_INPUT_NAME, nullInput.getName());
      assertTrue(nullInput.getValue());
   }

}
