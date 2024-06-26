package jmri.jmrix.ecos.configurexml;

import org.jdom2.Element;

/**
 * Provides load and store functionality for configuring EcosReporterManagers.
 * <p>
 * Uses the store method from the abstract base class, but provides a load
 * method here.
 *
 * @author Kevin Dickerson Copyright: Copyright (c) 2012
 */
public class EcosReporterManagerXml extends jmri.managers.configurexml.AbstractReporterManagerConfigXML {

    public EcosReporterManagerXml() {
        super();
    }

    @Override
    public void setStoreElementClass(Element reporters) {
        reporters.setAttribute("class", "jmri.jmrix.ecos.configurexml.EcosReporterManagerXml");
    }

    @Override
    public boolean load(Element shared, Element perNode) {
        // load individual Reporters
        return loadReporters(shared);
    }

//    private final static Logger log = LoggerFactory.getLogger(EcosReporterManagerXml.class);

}
