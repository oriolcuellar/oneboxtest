
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;



public class CartService {
    private ConcurrentHashMap <BigInteger, Cart> cartsHM = new ConcurrentHashMap <> ();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public CartService() {
        executor.scheduleAtFixedRate(()->deleteInactivity(), 0, 1, TimeUnit.MINUTES);
    }

    public void finishExecutor(){
        executor.shutdown();
    }

    public void deleteInactivity(){
        Instant now=Instant.now();
        for(Cart cart : cartsHM.values()){
            Instant last=cart.getLastUpdated();
            if(ChronoUnit.SECONDS.between(last, now)>10){
                BigInteger idCart=cart.getID();
                this.deleteCart(idCart);
            }
        }
    }
    public BigInteger createCart(){
        Cart cart = new Cart();
        cartsHM.put(cart.getID(), cart);
        return cart.getID();
    }
    public boolean existCart(BigInteger cartID){
        return cartsHM.containsKey(cartID);
    }
    public int addProductToCart(String productName, int quantity, BigInteger cartID)throws Exception{
        if (quantity<=0) {
            throw new Exception("Quantity must be > 0");
        }
        if(productName==null || productName.isEmpty()){
            throw new Exception("Description can't be empty");
        }
        if(!this.existCart(cartID)){
            return -1;
        }
        Cart cart = cartsHM.get(cartID);
        int realQuantity=cart.addProduct(productName, quantity);
        cartsHM.put(cartID, cart);
        return realQuantity;
    }
    public boolean deleteCart(BigInteger cartID){
        if(!this.existCart(cartID)){
            return false;
        }
        cartsHM.remove(cartID);
        return true;
    }
    public String getCartInfo(BigInteger cartID){
        if(!this.existCart(cartID)){
            return "This cart does not exist";
        }
        Cart cart = cartsHM.get(cartID);
        if(cart.isEmpty()){
            return "This cart is empty";
        }
        ArrayList<Product> products = cart.getProducts();
        String all="List of products\n\n";
        for (Product product : products) {
            int quantity = product.getQuantity();
            String productDesc=product.getDescription();
            String s=String.format("""
                        %s
                           Quantity: %d
                    """, productDesc, quantity);
            all+=s;
        }
        return all;
    }
}
