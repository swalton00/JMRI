package jmri.jmrix.sprog;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for SprogConnectionTypeList.
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class SprogConnectionTypeListTest {

   @Test
   public void ConstructorTest(){
       SprogConnectionTypeList ct = new SprogConnectionTypeList();
       Assert.assertNotNull(ct);
   }

   @Test
   public void ManfacturerString(){
       SprogConnectionTypeList ct = new SprogConnectionTypeList();
       Assert.assertEquals("Manufacturers",new String[]{"SPROG DCC"}, ct.getManufacturers());
   }

   @Test
   public void ProtocolClassList(){
       SprogConnectionTypeList ct = new SprogConnectionTypeList();
       Assert.assertEquals("Protocol Class List", new String[]{
            "jmri.jmrix.sprog.sprog.ConnectionConfig",
            "jmri.jmrix.sprog.sprogCS.ConnectionConfig",
            "jmri.jmrix.sprog.sprognano.ConnectionConfig",
            "jmri.jmrix.sprog.pi.pisprogone.ConnectionConfig",
            "jmri.jmrix.sprog.pi.pisprogonecs.ConnectionConfig",
            "jmri.jmrix.sprog.pi.pisprognano.ConnectionConfig",
            "jmri.jmrix.sprog.simulator.ConnectionConfig",
            "jmri.jmrix.sprog.SprogCSStreamConnectionConfig"},
            ct.getAvailableProtocolClasses());
   }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

}
