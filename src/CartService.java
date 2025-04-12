
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class CartService {
    ConcurrentHashMap <String, String> hashMap = new ConcurrentHashMap <> ();

    public CartService() {
    }

    public String createCart(){
        Cart cart = new Cart();
        return cart.getID();
    }
}
