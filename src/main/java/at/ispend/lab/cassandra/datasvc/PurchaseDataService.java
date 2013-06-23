package at.ispend.lab.cassandra.datasvc;

import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.beans.HSuperColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SuperColumnQuery;
import at.ispend.lab.cassandra.model.Purchase;

public class PurchaseDataService extends AbstractDataService {

    final String PURCHASES = "Purchases";
    final String COMPOSITE_PURCHASES = "CompositePurchases";


    /**
     * @param p The purchase to add
     */
    void insert(Purchase p) {

        
        Keyspace keyspace = HFactory.createKeyspace(KEYSPACE_PICTOR, cluster);

        Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);

        List subcols = new ArrayList();
        subcols.add(HFactory.createStringColumn("vendor", p.getVendor()));
        subcols.add(HFactory.createStringColumn("product", p.getProduct()));
        subcols.add(HFactory.createStringColumn("price",
                String.valueOf(p.getPrice())));

        HSuperColumn<String, String, String> superColumn = HFactory
                .createSuperColumn(p.getPurchaseId(), subcols, stringSerializer,
                        stringSerializer, stringSerializer);

        mutator.insert(p.getHandle() + ":" + now(), PURCHASES, superColumn);


    }

    Purchase get(String handle, String purchaseDate, String purchaseId) {
        Cluster cluster = HFactory.getOrCreateCluster("PictorCluster",
                "localhost:9160");
        Keyspace keyspace = HFactory.createKeyspace(KEYSPACE_PICTOR, cluster);

        // key, supercolumn name, col name, value types
        SuperColumnQuery<String, String, String, String> query = HFactory
                .createSuperColumnQuery(keyspace, stringSerializer,
                        stringSerializer, stringSerializer, stringSerializer);

        query.setColumnFamily(PURCHASES);
        query.setKey(handle+":"+purchaseDate);
        query.setSuperName(purchaseId);

        QueryResult<HSuperColumn<String, String, String>> result = query.execute();
        
        Purchase p = new Purchase();
        
        if (result != null && result.get() != null) {
            List<HColumn<String,String>> resultColumns = result.get().getColumns();
            for (HColumn<String, String> col : resultColumns) {
                p.setPurchaseId(purchaseId);
                p.setHandle(handle);
                if (col.getName().equals("product")) {
                    p.setProduct(col.getValue());
                }
                
                else if (col.getName().equals("price")) {
                    p.setPrice(Integer.valueOf(col.getValue()));
                }
                
                else if (col.getName().equals("vendor")) {
                    p.setVendor(col.getValue());
                }
                
                else if (col.getName().equals("handle")) {
                    p.setHandle(col.getValue());
                }
                
            }
        }

        
        return p;
    }
    
    
    /**
     * @param p
     */
    void insertComposite(Purchase p) {
        Cluster cluster = HFactory.getOrCreateCluster("PictorCluster",
                "localhost:9160");
        Keyspace keyspace = HFactory.createKeyspace(KEYSPACE_PICTOR, cluster);
        Mutator<String> mutator = HFactory.createMutator(keyspace, StringSerializer.get());
        
        String key = p.getHandle() + ":" + now();
        
        // assemble the insertions
        mutator.addInsertion(key, COMPOSITE_PURCHASES, p.getColumn());
        mutator.execute();
    }

}
