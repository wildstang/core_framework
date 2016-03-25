package org.wildstang.framework.io.inputs;

import org.junit.Before;

public class TestAnalogInputWithLogging extends TestAnalogInput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
