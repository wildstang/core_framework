package org.wildstang.framework.logger;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.logger.StateGroup;

public class TestStateGroup extends BaseTest
{
   private static final String NAME = "IMU.x";
   private static final String PARENT = "IMU";
   private static final double VALUE = 0.55;

   StateGroup group;
   Date timestamp;
   
   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      timestamp = new Date(); // set and keep for each test
      group = new StateGroup(timestamp);
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testStateGroup()
   {
      group = new StateGroup(timestamp);
      assertNotNull(group);
   }

   @Test
   public void testAddState()
   {
      group.addState(NAME, PARENT, VALUE);
      assertEquals(1, group.getStateList().size());
   }

   @Test
   public void testGetTimestamp()
   {
      assertEquals(timestamp, group.getTimestamp());
   }

   @Test
   public void testGetStateList()
   {
      assertNotNull(group.getStateList());
   }

}
