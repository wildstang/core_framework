package org.wildstang.framework.config;

import org.junit.Before;

public class TestConfigManagerWithLogging extends TestConfigManager
{

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      setLoggingState(true);
   }
}
