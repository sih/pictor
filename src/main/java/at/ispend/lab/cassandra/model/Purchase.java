package at.ispend.lab.cassandra.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.prettyprint.cassandra.serializers.CompositeSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;

import com.google.gson.Gson;

/**
 * @author sid
 *
 */
public class Purchase {

    private String handle;
    private String purchaseId;
    private String vendor;
    private String product;
    private int price;
    private String purchaseDate;
    private String longitude;
    private String latitude;
    private boolean secret;
    
    static SimpleDateFormat iso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    
    
    /**
     * Default the purchase date to now 
     */
    public Purchase() {
       this.purchaseDate = iso8601.format(new Date()); 
    }
    
    /**
     * @return the handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @param handle the handle to set
     */
    public void setHandle(String handle) {
        this.handle = handle;
    }

    /**
     * @return the purchaseId
     */
    public String getPurchaseId() {
        return purchaseId;
    }
    
    /**
     * @param purchaseId the purchaseId to set
     */
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }
    
    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }
    
    /**
     * @param vendor the vendor to set
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    
    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }
    
    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }
    
    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    /**
     * @return the purchaseDate
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }
    
    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }
    
    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }
    
    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    
    /**
     * @return the secret
     */
    public boolean isSecret() {
        return secret;
    }
    
    /**
     * @param secret the secret to set
     */
    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((handle == null) ? 0 : handle.hashCode());
        result = prime * result + ((purchaseId == null) ? 0 : purchaseId.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Purchase other = (Purchase) obj;
        if (handle == null) {
            if (other.handle != null)
                return false;
        }
        else if (!handle.equals(other.handle))
            return false;
        if (purchaseId == null) {
            if (other.purchaseId != null)
                return false;
        }
        else if (!purchaseId.equals(other.purchaseId))
            return false;
        return true;
    }
    
    
    /**
     * @return A JSON representation of this purchase
     */
    public String toJSON() {
        Gson g = new Gson();
        return g.toJson(this);
    }
    

    /**
     * Creates an HColumn with a column name composite of the form:
     *   ['vendor']:['product]:['purchase_id'])
     * and a value of ['timezone']
     * @return
     */
    public HColumn<Composite,String> getColumn() {

      Composite composite = new Composite();
      composite.addComponent(getVendor(), StringSerializer.get());
      composite.addComponent(getProduct(), StringSerializer.get());
      composite.addComponent(getPurchaseId(), StringSerializer.get());
      
      HColumn<Composite,String> col =
        HFactory.createColumn(composite, String.valueOf(getPrice()) , new CompositeSerializer(), StringSerializer.get());
      return col;
    }    

    
}
