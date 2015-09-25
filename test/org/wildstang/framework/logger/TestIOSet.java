package org.wildstang.framework.logger;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.logger.IOInfo;
import org.wildstang.framework.logger.IOSet;

public class TestIOSet extends BaseTest
{
   private IOSet set;
   
   private static final String NAME = "Test Name";
   private static final String TYPE = "Analog";
   private static final String DIRECTION = "Input";
   private static final String PORT = "BB_10";

   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      set = new IOSet();
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testIOSet()
   {
      set = new IOSet();
      
      assertNotNull(set);
   }

   @Test
   public void testAddIOInfo()
   {
      set.addIOInfo(NAME, TYPE, DIRECTION, PORT);
      
      assertEquals(1, set.getInfoList().size());

      set.addIOInfo(NAME, TYPE, DIRECTION, PORT);
      
      assertEquals(2, set.getInfoList().size());
   }

   @Test
   public void testGetInfoList()
   {
      List<IOInfo> list = set.getInfoList();
      assertNotNull(set.getInfoList());
      assertEquals(0, set.getInfoList().size());
   }

}
