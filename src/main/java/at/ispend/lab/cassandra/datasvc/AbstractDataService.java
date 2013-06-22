package at.ispend.lab.cassandra.datasvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.factory.HFactory;


/**
 * @author sid
 *
 */
public abstract class AbstractDataService {
    
    protected Cluster cluster;
    
    
    static final String KEYSPACE_PICTOR = "pictor";

    static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    static final StringSerializer stringSerializer = StringSerializer.get();
    
    static String now() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        df.setTimeZone(tz);
        return df.format(new Date());
    }
    
    public final void init() {
        cluster = HFactory.getOrCreateCluster("PictorCluster",
                "localhost:9160");        
    }
    
    public final void release() {
        cluster.getConnectionManager().shutdown();
    }
    
    
    
    public static void main(String[] args) {
        System.out.println(now());
    }
    
}
