package org.wildstang.framework.logger;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.logger.IOInfo;

public class TestIOInfo extends BaseTest
{
   IOInfo info;
   private static final String NAME = "Test Name";
   private static final String TYPE = "Analog";
   private static final String DIRECTION = "Input";
   private static final String PORT = "BB_10";
   
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testIOInfo()
   {
      info = new IOInfo(NAME, TYPE, DIRECTION, PORT);
      
      // TODO: Add tests with null parameters
      
      assertNotNull(info);
   }

   
   @Test
   public void testGetName()
   {
      info = new IOInfo(NAME, TYPE, DIRECTION, PORT);
      
      assertEquals(NAME, info.getName());
   }

   @Test
   public void testGetType()
   {
      info = new IOInfo(NAME, TYPE, DIRECTION, PORT);
      
      assertEquals(TYPE, info.getType());
   }

   @Test
   public void testGetPort()
   {
      info = new IOInfo(NAME, TYPE, DIRECTION, PORT);
      
      assertEquals(PORT, info.getPort());
   }

   @Test
   public void testGetDirection()
   {
      info = new IOInfo(NAME, TYPE, DIRECTION, PORT);
      
      assertEquals(DIRECTION, info.getDirection());
   }

}
