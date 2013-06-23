package at.ispend.lab.cassandra.datasvc;

import junit.framework.TestCase;

import org.junit.Test;

import at.ispend.lab.cassandra.model.Purchase;


public class PurchaseDataServiceTest extends TestCase {
    
    private static final String PURCHASE_ID_1 = "abc123";
    private static final String PURCHASE_ID_2 = "def456";

    private PurchaseDataService pds;

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override protected void setUp() throws Exception {
        super.setUp();
        pds = new PurchaseDataService();
        pds.init();
    }
    
    

    @Test public void testInsertNonNullPurchase() {
        
        Purchase p = new Purchase();
        p.setPurchaseId(PURCHASE_ID_1);
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
    
    @Test public void testInsertCompositeNonNullPurchase() {
        
        Purchase p = new Purchase();
        p.setPurchaseId(PURCHASE_ID_1);
        p.setHandle("sid");
        p.setVendor("Waterstones Teddington");
        p.setProduct("The Killing Joke");
        p.setPrice(1350);
        
        try {
            pds.insertComposite(p);
        }
        catch(Exception e) {
            fail("Failed with an exception "+e.getMessage());
        }
        
    }    

    
    @Test public void testGetPurchaseExists() {
        
        try {
            Purchase v = pds.get("sid", "2013-06-22", PURCHASE_ID_1);
            assertNotNull(v);
            
            Purchase p = new Purchase();
            p.setPurchaseId(PURCHASE_ID_1);
            p.setHandle("sid");
            p.setVendor("Waterstones Teddington");
            p.setProduct("The Killing Joke");
            p.setPrice(1350);
            
            assertEquals(v, p);
            assertEquals(p.getPurchaseId(),v.getPurchaseId());
            assertEquals(p.getPrice(),v.getPrice());
            assertEquals(p.getProduct(),v.getProduct());
            assertEquals(p.getVendor(),v.getVendor());
            assertEquals(p.getHandle(),v.getHandle());
            assertEquals(p.getPrice(),v.getPrice());
            
            
            
        }
        catch(Exception e) {
            fail("Failed with an exception "+e.getMessage());
        }
        
    }
    
}
