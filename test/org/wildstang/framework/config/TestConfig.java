package org.wildstang.framework.config;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.wildstang.framework.BaseTest;
import org.wildstang.framework.config.Config;


public class TestConfig extends BaseTest
{

   Config config;
   
   private static final String DEFAULT_STRING_VALUE = "Default";
   
   private static final String EMPTY_LINE = "";
   private static final String NULL_LINE = null;
   
   private static final String DOUBLE_KEY = "drive.maxSpeed";
   private static final String DOUBLE_VALUE = "0.90";
   private static final double DOUBLE_VALUE_DOUBLE = 0.90;
   private static final String DOUBLE_LINE = DOUBLE_KEY + "=" + DOUBLE_VALUE;

   private static final String INT_KEY = "drive.maxAccel";
   private static final String INT_VALUE = "100";
   private static final int INT_VALUE_INT = 100;
   private static final String INT_LINE = INT_KEY + "=" + INT_VALUE;
   
   private static final String BOOLEAN_VALUE_FALSE_KEY = "drive.reverseThrottle";
   private static final String BOOLEAN_VALUE_TRUE_KEY = "drive.reverseDirection";
   private static final String BOOLEAN_VALUE_TRUE_VALUE = "true";
   private static final String BOOLEAN_VALUE_FALSE_VALUE = "false";
   private static final boolean BOOLEAN_VALUE_TRUE_BOOLEAN = true;
   private static final boolean BOOLEAN_VALUE_FALSE_BOOLEAN = false;
   private static final String BOOLEAN_VALUE_TRUE_LINE = BOOLEAN_VALUE_TRUE_KEY + "=" + BOOLEAN_VALUE_TRUE_VALUE;
   private static final String BOOLEAN_VALUE_FALSE_LINE = BOOLEAN_VALUE_FALSE_KEY + "=" + BOOLEAN_VALUE_FALSE_VALUE;
   
   private static final String STRING_KEY = "drive.errorMsg";
   private static final String STRING_VALUE = "This is a String message";
   private static final String STRING_LINE = STRING_KEY + "=" + STRING_VALUE;

   private static final String AMBIGUOUS_KEY = "drive.ambiguous";
   private static final String AMBIGUOUS_VALUE = "0.99 times false reading";
   private static final String AMBIGUOUS_LINE = AMBIGUOUS_KEY + "=" + AMBIGUOUS_VALUE;

   private static final String PART_LINE_COMMENT = DOUBLE_LINE + " # this is a part line comment";
   private static final String FULL_LINE_COMMENT = "# This is a full line comment";

   private static final String BAD_KEY = "drive.factor";
   private static final String BAD_VALUE = "10#speed.scale.factor=10";
   private static final String BAD_LINE = BAD_KEY + "=" + BAD_VALUE;

   private static final String NAME_ONLY = "drive.factor";
   private static final String NO_VALUE = NAME_ONLY + "=";


   @Before
   public void setUp() throws Exception
   {
      super.setUp();
      config = new Config();
      
      Whitebox.setInternalState(Config.class, getMockLogger());
      setLoggingState(false);

   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testConfig()
   {
      Config config = new Config();
      assertNotNull(config);
   }

   
//-----------------------------------
   // Strip Comments
   // 1. Empty String - return empty
   // 2. Null string - throw NPE
   // 3. Whole line comment - return empty
   // 4. Part line comment - return first part of string
   // 5. No comments - return entire string
   
   @Test
   public void testStripCommentsEmptyString()
   {
      // 1. Empty String - return empty
      configureLogging();
      replayAll();
      
      String result = config.stripComments(EMPTY_LINE);
   
      assertEquals(result, "");
      
      verifyAll();
   }

   @Test(expected=NullPointerException.class)
   public void testStripCommentsNull()
   {
      // 2. Null string - throw NPE
      configureLogging();
      replayAll();
      
      String result = config.stripComments(NULL_LINE);
   }

   @Test
   public void testStripCommentsWholeLine()
   {
      // 3. Whole line comment - return empty
      configureLogging();
      replayAll();
      
      String result = config.stripComments(FULL_LINE_COMMENT);
   
      assertEquals(result, "");
      
      verifyAll();
   }

   @Test
   public void testStripCommentsPartLine()
   {
      // 4. Part line comment - return first part of string
      configureLogging();
      replayAll();
      
      String result = config.stripComments(PART_LINE_COMMENT);
   
      assertEquals(result, DOUBLE_LINE);
      
      verifyAll();
   }

   @Test
   public void testStripCommentsNone()
   {
      // 5. No comments - return entire string
      configureLogging();
      replayAll();
      
      String result = config.stripComments(DOUBLE_LINE);
   
      assertEquals(result, DOUBLE_LINE);
      
      verifyAll();
   }


//-----------------------------------
   // Key Value pair
   // 1. Empty string
   // 2. Null String
   // 3. Valid line (int)
   // 4. Name only
   // 5. Name with equals
   
   @Test
   public void testGetKeyValuePairEmptyString()
   {
      // 1. Empty string
      configureLogging();
      replayAll();
      
      String[] result = config.getKeyValuePair(EMPTY_LINE);
   
      assertEquals(1, result.length);
      assertEquals(result[0], "");
      
      verifyAll();
   }

   @Test(expected=NullPointerException.class)
   public void testGetKeyValuePairNull()
   {
      // 2. Null String
      configureLogging();
      replayAll();
      
      String[] result = config.getKeyValuePair(NULL_LINE);
   }

   @Test
   public void testGetKeyValuePairValid()
   {
      // 3. Valid line (int)
      configureLogging();
      replayAll();
      
      String[] result = config.getKeyValuePair(INT_LINE);
   
      assertEquals(2, result.length);
      assertEquals(INT_KEY, result[0]);
      assertEquals(INT_VALUE, result[1]);
      
      verifyAll();
   }

   @Test
   public void testGetKeyValueNameOnly()
   {
      // 4. Name only
      configureLogging();
      replayAll();
      
      String[] result = config.getKeyValuePair(NAME_ONLY);
   
      assertEquals(1, result.length);
      assertEquals(NAME_ONLY, result[0]);
      
      verifyAll();
   }

   @Test
   public void testGetKeyValueNameWithEquals()
   {
      // 5. Name with equals
      configureLogging();
      replayAll();
      
      String[] result = config.getKeyValuePair(NO_VALUE);
   
      assertEquals(1, result.length);
      assertEquals(NAME_ONLY, result[0]);
      
      verifyAll();
   }

   
//-----------------------------------
   // Parse Value
   // 1. Empty string
   // 2. Null string
   // 3. Integer value
   // 4. Double value
   // 5. Boolean value
   // 6. String value
   
   @Test
   public void testParseValueEmptyString()
   {
      // 1. Empty input
      configureLogging();
      replayAll();

      Object o = config.parseValue(EMPTY_LINE);
      
      assertNull(o);
      
      verifyAll();
   }

   @Test(expected=NullPointerException.class)
   public void testParseValueNull()
   {
      // 2. Null string
      configureLogging();
      replayAll();

      Object o = config.parseValue(EMPTY_LINE);
   }

   @Test
   public void testParseValue()
   {
      // 3. Integer value
      configureLogging();
      replayAll();

      Object o = config.parseValue(INT_VALUE);
      
      assertTrue(o instanceof Integer);
      assertEquals(INT_VALUE_INT, (int)(Integer)o);
      
      verifyAll();
   }

   @Test
   public void testParseValueDouble()
   {
      // 4. Double value
      configureLogging();
      replayAll();

      Object o = config.parseValue(DOUBLE_VALUE);
      
      assertTrue(o instanceof Double);
      assertEquals(DOUBLE_VALUE_DOUBLE, (double)(Double)o, 0.001);
      
      verifyAll();
   }

   @Test
   public void testParseValueBoolean()
   {
      // 5. Boolean value
      configureLogging();
      replayAll();

      Object o = config.parseValue(BOOLEAN_VALUE_TRUE_VALUE);
      
      assertTrue(o instanceof Boolean);
      assertTrue((Boolean)o);
      
      o = config.parseValue(BOOLEAN_VALUE_FALSE_VALUE);
      
      assertTrue(o instanceof Boolean);
      assertFalse((Boolean)o);

      verifyAll();
   }

   @Test
   public void testParseValueString()
   {
      // 6. String value
      configureLogging();
      replayAll();

      Object o = config.parseValue(STRING_VALUE);
      
      assertTrue(o instanceof String);
      assertEquals(STRING_VALUE, o);
      
      verifyAll();
   }


//-----------------------------------
   // Parse Double
   // 1. Empty input
   // 2. Null input
   // 3. Valid input
   // 4. Int input
   // 5. invalid input (non-numerical value)
   
   @Test
   public void testParseDoubleEmptyString()
   {
      // 1. Empty input
      configureLogging();
      replayAll();

      Double d = config.parseDouble(EMPTY_LINE);
      
      assertNull(d);
      
      verifyAll();
   }

   @Test(expected=NullPointerException.class)
   public void testParseDoubleNull()
   {
      // 2. Null input
      configureLogging();
      replayAll();

      Double d = config.parseDouble(NULL_LINE);
      
   }

   @Test
   public void testParseDoubleValid()
   {
      // 3. Valid input
      configureLogging();
      replayAll();
   
      Double d = config.parseDouble(DOUBLE_VALUE);
      
      assertEquals((Double)DOUBLE_VALUE_DOUBLE, d);
      
      verifyAll();
   }

   @Test
   public void testParseDoubleInt()
   {
      // 4. Int input
      configureLogging();
      replayAll();

      Double d = config.parseDouble(INT_VALUE);
      
      assertNull(d);
      
      verifyAll();
   }

   @Test
   public void testParseDoubleInvalid()
   {
      // 5. invalid input (non-numerical value)
      configureLogging();
      replayAll();
   
      Double d = config.parseDouble(STRING_VALUE);
      
      assertNull(d);
      
      verifyAll();
   }

   
   
//-----------------------------------
   // Parse Int
   // 1. Empty input
   // 2. Null input
   // 3. Valid input
   // 4. Double input
   // 5. invalid input (non-numerical value)
   
   @Test
   public void testParseIntEmptyString()
   {
      // 1. Empty input
      configureLogging();
      replayAll();

      Integer i = config.parseInt(EMPTY_LINE);
      
      assertNull(i);
      
      verifyAll();
   }

   @Test(expected=NullPointerException.class)
   public void testParseIntNull()
   {
      // 2. Null input
      configureLogging();
      replayAll();

      Integer i = config.parseInt(NULL_LINE);
      
   }

   @Test
   public void testParseIntValid()
   {
      // 3. Valid input
      configureLogging();
      replayAll();
   
      Integer i = config.parseInt(INT_VALUE);
      
      assertEquals((Integer)INT_VALUE_INT, i);
      
      verifyAll();
   }

   @Test
   public void testParseIntDoubleValue()
   {
      // 4. Int input
      configureLogging();
      replayAll();

      Integer i = config.parseInt(DOUBLE_VALUE);
      
      assertNull(i);
      
      verifyAll();
   }

   @Test
   public void testParseIntInvalid()
   {
      // 5. invalid input (non-numerical value)
      configureLogging();
      replayAll();
   
      Integer i = config.parseInt(STRING_VALUE);
      
      assertNull(i);
      
      verifyAll();
   }


//-----------------------------------
   // Parse Boolean
   // 1. Empty input
   // 2. Null input
   // 3. Valid input
   // 4. Double input
   // 5. invalid input (non-numerical value)
   
   @Test
   public void testParseBooleanEmptyString()
   {
      // 1. Empty input
      configureLogging();
      replayAll();

      Boolean b = config.parseBoolean(EMPTY_LINE);
      
      assertNull(b);
      
      verifyAll();
   }

   @Test(expected=NullPointerException.class)
   public void testParseBooleanNull()
   {
      // 2. Null input
      configureLogging();
      replayAll();

      Boolean b = config.parseBoolean(NULL_LINE);
      
   }

   @Test
   public void testParseBooleanValid()
   {
      // 3. Valid input
      configureLogging();
      replayAll();
   
      Boolean b = config.parseBoolean(BOOLEAN_VALUE_TRUE_VALUE);
      
      assertEquals((Boolean)BOOLEAN_VALUE_TRUE_BOOLEAN, b);
      
      b = config.parseBoolean(BOOLEAN_VALUE_FALSE_VALUE);
      
      assertEquals((Boolean)BOOLEAN_VALUE_FALSE_BOOLEAN, b);

      verifyAll();
   }

   @Test
   public void testParseBooleanDoubleValue()
   {
      // 4. Boolean input
      configureLogging();
      replayAll();

      Boolean b = config.parseBoolean(DOUBLE_VALUE);
      
      assertNull(b);
      
      verifyAll();
   }

   @Test
   public void testParseBooleanInvalid()
   {
      // 5. invalid input (non-numerical value)
      configureLogging();
      replayAll();
   
      Boolean b = config.parseBoolean(STRING_VALUE);
      
      assertNull(b);
      
      verifyAll();
   }
   
//-----------------------------------
   // Load internal
   // 1. Null Reader
   // 2. Empty input
   // 3. Valid input
   // 4. Invalid input

   // Helper
   private BufferedReader createConfigReader(boolean empty, boolean invalid)
   {
      BufferedReader reader = null;
      StringBuilder buf = new StringBuilder();

      if (empty)
      {
         buf.append("");
      }
      else if (invalid)
      {
         buf.append(DOUBLE_LINE);
         buf.append("  // regular C-style comments");
         buf.append("\n");
      }
      else
      {
         buf.append(FULL_LINE_COMMENT);
         buf.append("\n");
         buf.append(DOUBLE_LINE);
         buf.append("\n");
         buf.append(INT_LINE);
         buf.append("\n");
         buf.append(BOOLEAN_VALUE_FALSE_LINE);
         buf.append("\n");
         buf.append(STRING_LINE);
         buf.append("\n");
         buf.append(PART_LINE_COMMENT);
         buf.append("\n");
         buf.append(BAD_LINE);
         buf.append("\n");
         buf.append(AMBIGUOUS_LINE);
         buf.append("\n");
      }

      StringReader strReader = new StringReader(buf.toString());
      reader = new BufferedReader(strReader);
      
      return reader;
   }
   
   @Test(expected=NullPointerException.class)
   public void testLoadNull()
   {
      // 1. Null Reader
      // Should thrown NPE
      configureLogging();
      replayAll();

      config.load(null);
      
   }

   @Test
   public void testLoadEmpty()
   {
      // 2. Empty input
      // Config should remain empty
      
      configureLogging();
      replayAll();

      config.load(createConfigReader(true, false));
      
      assertEquals(0, config.size());
   }

   @Test
   public void testLoadValid()
   {
      // 3. Valid input
      // Should contain 6 values
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      assertEquals(6, config.size());
      
      assertEquals(DOUBLE_VALUE_DOUBLE, (double)config.getValue(DOUBLE_KEY), 0.001);
      assertEquals(INT_VALUE_INT, (int)config.getValue(INT_KEY));
      assertFalse((boolean)config.getValue(BOOLEAN_VALUE_FALSE_KEY));
      assertEquals(STRING_VALUE, config.getValue(STRING_KEY));
      assertEquals(10, (int)config.getValue(BAD_KEY));
      assertEquals(AMBIGUOUS_VALUE, config.getValue(AMBIGUOUS_KEY));

   }

   @Test
   public void testLoadInvalid()
   {
      // 4. Invalid input
      // Single line with the double value with C-style comments
      // Should return as a String value with the double key
      // Should contain 6 values
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, true));
      
      assertEquals(1, config.size());

      assertTrue(config.getValue(DOUBLE_KEY) instanceof String);
   }

//-----------------------------------
   
   // getValue(key)
   // 1. Call with null key
   // 2. Call with empty key
   // 3. call with valid key
   // 4. call with key not in config
   @Test
   public void testGetValueString()
   {
      fail("Not yet implemented"); // TODO
   }

//-----------------------------------

   // getValue(key, default)
   // getValue(key)
   // 1. Call with null key
   // 2. Call with empty key
   // 3. call with valid key
   // 4. call with key not in config
   @Test
   public void testGetValueStringObject()
   {
      fail("Not yet implemented"); // TODO
   }

   
//-----------------------------------
   // Test getDouble
   // 1. Test null key
   // 2. Test empty key
   // 3. Test with key with valid Double value
   // 4. Test with key with valid String value
   // 5. Key doesn't exist
   
   @Test(expected=NullPointerException.class)
   public void testGetDoubleNull()
   {
      // 1. Test null key
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      config.getDouble(NULL_LINE, 5.5);
   }

   @Test
   public void testGetDoubleEmpty()
   {
      // 2. Test empty key
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      double d = config.getDouble(EMPTY_LINE, 5.5);
      assertEquals(5.5, d, 0.001);
   }

   @Test
   public void testGetDoubleValid()
   {
      // 3. Test with key with valid Double value
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      double d = config.getDouble(DOUBLE_KEY, 5.5);
      assertEquals(DOUBLE_VALUE_DOUBLE, d, 0.001);
   }

   @Test(expected=NumberFormatException.class)
   public void testGetDoubleNotDouble()
   {
      // 4. Test with key with valid String value
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      double d = config.getDouble(STRING_KEY, 5.5);
   }

   @Test
   public void testGetDoubleNoKey()
   {
      // 5. Key doesn't exist
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      double d = config.getDouble("NoValue", 5.5);
      assertEquals(5.5, d, 0.001);
   }

//-----------------------------------
   // Test getInt
   // 1. Test null key
   // 2. Test empty key
   // 3. Test with key with valid Int value
   // 4. Test with key with valid String value
   // 5. Key doesn't exist
   
   @Test(expected=NullPointerException.class)
   public void testGetIntNull()
   {
      // 1. Test null key
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      config.getInt(NULL_LINE, 5);
   }

   @Test
   public void testGetIntEmpty()
   {
      // 2. Test empty key
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      int i = config.getInt(EMPTY_LINE, 5);
      assertEquals(5, i);
   }

   @Test
   public void testGetIntValid()
   {
      // 3. Test with key with valid Int value
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      int i = config.getInt(INT_KEY, 5);
      assertEquals(INT_VALUE_INT, i);
   }

   @Test(expected=NumberFormatException.class)
   public void testGetIntNotInt()
   {
      // 4. Test with key with valid String value
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      int i = config.getInt(STRING_KEY, 5);
   }

   @Test
   public void testGetIntNoKey()
   {
      // 5. Key doesn't exist
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      int i= config.getInt("NoValue", 5);
      assertEquals(5, i);
   }

//-----------------------------------
   // Test getBoolean
   // 1. Test null key
   // 2. Test empty key
   // 3. Test with key with valid Boolean value
   // 4. Test with key with valid String value
   // 5. Key doesn't exist
   
   @Test(expected=NullPointerException.class)
   public void testGetBooleanNull()
   {
      // 1. Test null key
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      config.getBoolean(NULL_LINE, true);
   }

   @Test
   public void testGetBooleanEmpty()
   {
      // 2. Test empty key
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      boolean b = config.getBoolean(EMPTY_LINE, true);
      assertTrue(b);
   }

   @Test
   public void testGetBooleanValid()
   {
      // 3. Test with key with valid Boolean value
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      boolean b = config.getBoolean(BOOLEAN_VALUE_FALSE_KEY, true);
      assertFalse(b);
   }

   @Test(expected=NumberFormatException.class)
   public void testGetBooleanNotBoolean()
   {
      // 4. Test with key with valid String value
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      boolean o = config.getBoolean(STRING_KEY, false);
   }

   @Test
   public void testGetBooleanNoKey()
   {
      // 5. Key doesn't exist
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      boolean b = config.getBoolean("NoValue", true);
      assertTrue(b);
   }

//-----------------------------------
   // Test getString
   // 1. Test null key
   // 2. Test empty key
   // 3. Test with key with valid String value
   // 4. Test with key with valid Double value
   // 5. Key doesn't exist
   
   @Test(expected=NullPointerException.class)
   public void testGetStringNull()
   {
      // 1. Test null key
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      config.getString(NULL_LINE, "");
   }

   @Test
   public void testGetStringEmpty()
   {
      // 2. Test empty key
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      String s = config.getString(EMPTY_LINE, DEFAULT_STRING_VALUE);
      assertEquals(DEFAULT_STRING_VALUE, s);
   }

   @Test
   public void testGetStringValid()
   {
      // 3. Test with key with valid String value
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      String s = config.getString(STRING_KEY, DEFAULT_STRING_VALUE);
      assertEquals(STRING_VALUE, s);
   }

   @Test(expected=NumberFormatException.class)
   public void testGetStringNotString()
   {
      // 4. Test with key with valid String value
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      String o = config.getString(DOUBLE_KEY, DEFAULT_STRING_VALUE);
   }

   @Test
   public void testGetStringNoKey()
   {
      // 5. Key doesn't exist
      configureLogging();
      replayAll();

      config.load(createConfigReader(false, false));
      
      String s = config.getString("NoValue", DEFAULT_STRING_VALUE);
      assertEquals(DEFAULT_STRING_VALUE, s);
   }

}
