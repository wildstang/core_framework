package org.wildstang.framework.io.inputs;

import org.junit.Before;

public class TestNullDigitalInputWithLogging extends TestNullDigitalInput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
