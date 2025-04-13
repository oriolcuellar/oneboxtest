import java.math.BigInteger;
import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private BigInteger id;
    private ConcurrentHashMap<String, Product> productsHM = new ConcurrentHashMap<>();
    private Instant lastUpdated;

    public Cart(){
        this.id=IdGenerator.generate();
        this.lastUpdated=Instant.now();
    }
    public BigInteger getID() {
        return this.id;
    }

    public int addProduct(String description, int quantity){
        this.lastUpdated=Instant.now();
        if(productsHM.containsKey(description)){
            Product product =productsHM.get(description);
            int actualQuantity=product.addQuantity(quantity);
            productsHM.put(description, product);
            return actualQuantity;
        }
        else{
            Product product = new Product(description, quantity);
            productsHM.put(description, product);
            return quantity;
        }
    }
    public boolean isEmpty(){
        return productsHM.isEmpty();
    }
    public ArrayList<Product> getProducts(){
        this.lastUpdated=Instant.now();//I understand that see the products is an activity
        return new ArrayList<>(productsHM.values());
    }

    public Instant getLastUpdated(){
        return this.lastUpdated;
    }
}
