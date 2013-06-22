package at.ispend.lab.cassandra.api;

import at.ispend.lab.cassandra.model.Purchase;


/**
 * API for purchasing
 * @author sih
 *
 */
public interface PurchaseApi {

    public void create(Purchase p);
    
    public Purchase update(Purchase p);
    
    public void delete(Purchase p);
    
    public Purchase get(String handle, String purchaseId);
    
    
}
