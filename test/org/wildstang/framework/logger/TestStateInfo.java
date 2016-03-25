package org.wildstang.framework.logger;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.logger.StateInfo;

public class TestStateInfo extends BaseTest
{

   private StateInfo info;
   
   private static final String NAME = "IMU.x";
   private static final String PARENT = "IMU";
   private static final double VALUE = 0.55;

   
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
   public void testStateInfo()
   {
      info = new StateInfo(NAME, PARENT, VALUE);
      
      // TODO:  Add more tests for null/invalid parameters
      
      assertNotNull(info);
   }

   @Test
   public void testGetName()
   {
      info = new StateInfo(NAME, PARENT, VALUE);
      
      assertEquals(NAME, info.getName());
   }

   @Test
   public void testGetParent()
   {
      info = new StateInfo(NAME, PARENT, VALUE);
      
      assertEquals(PARENT, info.getParent());
   }

   @Test
   public void testGetValue()
   {
      info = new StateInfo(NAME, PARENT, VALUE);
      
      assertEquals(VALUE, info.getValue());
   }

}
