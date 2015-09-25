package org.wildstang.framework.config;

import org.junit.Before;


public class TestConfigWithLogging extends TestConfig
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
