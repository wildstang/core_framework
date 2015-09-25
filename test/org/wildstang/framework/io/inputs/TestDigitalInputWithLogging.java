package org.wildstang.framework.io.inputs;

import org.junit.Before;

public class TestDigitalInputWithLogging extends TestDigitalInput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
