package at.ispend.lab.cassandra.datasvc;

import junit.framework.TestCase;

import org.junit.Test;

import at.ispend.lab.cassandra.model.Purchase;


public class PurchaseDataServiceTest extends TestCase {
    

    private PurchaseDataService pds;

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override protected void setUp() throws Exception {
        super.setUp();
        pds = new PurchaseDataService();
    }

    @Test public void testInsertNonNullPurchase() {
        
        Purchase p = new Purchase();
        p.setPurchaseId("v"+String.valueOf(System.currentTimeMillis()));
        p.setHandle("sid");
        p.setVendor("Waterstones Teddington");
        p.setProduct("The Killing Joke");
        p.setPrice(1350);
        
        try {
            pds.insert(p);
        }
        catch(Exception e) {
            fail("Failed with an exception "+e.getMessage());
        }
        
    }

}
