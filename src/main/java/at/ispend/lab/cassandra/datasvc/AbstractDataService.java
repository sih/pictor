package at.ispend.lab.cassandra.datasvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import me.prettyprint.cassandra.serializers.StringSerializer;


/**
 * @author sid
 *
 */
public abstract class AbstractDataService {
    
    static final String KEYSPACE_PICTOR = "pictor";

    static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    
    static final StringSerializer stringSerializer = StringSerializer.get();
    
    static String now() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        df.setTimeZone(tz);
        return df.format(new Date());
    }
    
    
}
