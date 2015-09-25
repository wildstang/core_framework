package org.wildstang.framework.io.outputs;

import org.junit.Before;

public class TestNullDigitalOutputWithLogging extends TestNullDigitalOutput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
