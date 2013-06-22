package at.ispend.lab.cassandra.datasvc;

import java.util.ArrayList;
import java.util.List;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.beans.HSuperColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import at.ispend.lab.cassandra.model.Purchase;


public class PurchaseDataService extends AbstractDataService {
    
    final String PURCHASES = "Purchases";

    /**
     * @param p The purchase to add
     */
    void insert(Purchase p) {
        
        Cluster cluster = HFactory.getOrCreateCluster("PictorCluster", "localhost:9160");
        Keyspace keyspace = HFactory.createKeyspace(KEYSPACE_PICTOR, cluster);
        
        Mutator<String> mutator = HFactory.createMutator(keyspace, stringSerializer);
        
        List subcols = new ArrayList();
        subcols.add(HFactory.createStringColumn("vendor", p.getVendor()));
        subcols.add(HFactory.createStringColumn("product", p.getProduct()));
        subcols.add(HFactory.createStringColumn("price", String.valueOf(p.getPrice())));
        
        
        HSuperColumn<String,String,String> superColumn = HFactory.createSuperColumn(p.getPurchaseId(), subcols, stringSerializer, stringSerializer, stringSerializer);
        
        mutator.insert(p.getHandle()+":"+now(), PURCHASES, superColumn);
        
        cluster.getConnectionManager().shutdown();
        
        
    }
    
    
}
