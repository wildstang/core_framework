package org.wildstang.framework.io.outputs;

import org.junit.Before;

public class TestDigitalOutputWithLogging extends TestDigitalOutput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
