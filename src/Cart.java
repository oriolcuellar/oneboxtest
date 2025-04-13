import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private BigInteger id;
    private ConcurrentHashMap<String, Product> productsHM = new ConcurrentHashMap<>();

    public Cart(){
        this.id=IdGenerator.generate();
    }
    public BigInteger getID() {
        return this.id;
    }

    public int addProduct(String description, int quantity){
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
        return new ArrayList<>(productsHM.values());
    }
}
