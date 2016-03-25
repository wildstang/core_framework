package org.wildstang.framework.io.inputs;

import org.junit.Before;

public class TestDiscreteInputWithLogging extends TestDiscreteInput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
