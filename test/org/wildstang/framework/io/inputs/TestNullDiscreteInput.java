package org.wildstang.framework.io.inputs;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.io.inputs.AbstractInput;

public class TestNullDiscreteInput extends BaseTest
{

   private static final String TEST_INPUT_NAME = "TestInput";
   NullDiscreteInput nullInput;

   /**
    * @throws java.lang.Exception
    */
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      
      Whitebox.setInternalState(AbstractInput.class, getMockLogger());
      Whitebox.setInternalState(NullDiscreteInput.class, getMockLogger());
      Whitebox.setInternalState(DiscreteInput.class, getMockLogger());

   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testReadRawValue()
   {
      nullInput = new NullDiscreteInput(TEST_INPUT_NAME);
      
      resetAll();
      configureLogging();
      replayAll();

      assertEquals(0, nullInput.readRawValue());
   }

   @Test
   public void testNullDiscreteInputString()
   {
      nullInput = new NullDiscreteInput(TEST_INPUT_NAME);

      assertEquals(TEST_INPUT_NAME, nullInput.getName());
      assertEquals(0, nullInput.getValue());
   }

   @Test
   public void testNullDiscreteInputStringInt()
   {
      nullInput = new NullDiscreteInput(TEST_INPUT_NAME, 7);

      assertEquals(TEST_INPUT_NAME, nullInput.getName());
      assertEquals(7, nullInput.getValue());
   }

}
