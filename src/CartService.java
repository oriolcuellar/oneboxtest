
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class CartService {
    private ConcurrentHashMap <BigInteger, Cart> cartsHM = new ConcurrentHashMap <> ();

    public CartService() {
    }

    public BigInteger createCart(){
        Cart cart = new Cart();
        cartsHM.put(cart.getID(), cart);
        return cart.getID();
    }
    public boolean existCart(BigInteger cartID){
        return cartsHM.containsKey(cartID);
    }
    public int addProductToCart(String productName, int quantity, BigInteger cartID){
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
