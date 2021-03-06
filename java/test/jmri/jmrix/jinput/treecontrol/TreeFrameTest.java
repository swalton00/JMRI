package jmri.jmrix.jinput.treecontrol;

import java.awt.GraphicsEnvironment;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;

/**
 * Test simple functioning of TreeFrame
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class TreeFrameTest extends jmri.util.JmriJFrameTestBase {


    @BeforeEach
    @Override
    public void setUp() {
        JUnitUtil.setUp();
        if(!GraphicsEnvironment.isHeadless()){ 
           frame = new TreeFrame();
        }
    }

    @AfterEach
    @Override
    public void tearDown() {        
        JUnitUtil.clearShutDownManager(); // put in place because AbstractMRTrafficController implementing subclass was not terminated properly
        super.tearDown();    
    }
}
