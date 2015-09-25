package org.wildstang.framework.io.outputs;

import org.junit.Before;

public class TestNullAnalogOutputWithLogging extends TestNullAnalogOutput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
