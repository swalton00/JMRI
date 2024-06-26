package jmri.util.prefs;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for InitializationException class.
 *
 * @author Paul Bender Copyright (C) 2016
 **/

public class InitializationExceptionTest {

   @Test
   public void testCtor(){
      Assert.assertNotNull("InitializationException constructor",new InitializationException("test exception",null));
   }

   @BeforeEach
   public void setUp() {
        JUnitUtil.setUp();
        JUnitUtil.initDefaultUserMessagePreferences();
   }

   @AfterEach
   public void tearDown(){
        JUnitUtil.tearDown();
   }

}
