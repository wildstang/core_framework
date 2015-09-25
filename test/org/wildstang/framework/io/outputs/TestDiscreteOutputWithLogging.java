package org.wildstang.framework.io.outputs;

import org.junit.Before;

public class TestDiscreteOutputWithLogging extends TestDiscreteOutput
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
