package org.wildstang.framework.io.inputs;

import org.junit.Before;

public class TestNullAnalogInputWithLogging extends TestNullAnalogInput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
