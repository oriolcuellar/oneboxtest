import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        CartService cartService = new CartService();
        BigInteger cartID = cartService.createCart();
        System.out.println(cartID);
        int cant=cartService.addProductToCart("Azucar", 2, cartID);
        System.out.println(cant);
        BigInteger cartID2 = cartService.createCart();
        System.out.println(cartID2);
        cant=cartService.addProductToCart("Azucar2", 3, cartID);
        System.out.println(cant);
        cartService.deleteCart(cartID);
        cant=cartService.addProductToCart("Azucar", 2, cartID);
        System.out.println(cant);
        cant=cartService.addProductToCart("Azucar", 2, cartID2);
        cant=cartService.addProductToCart("Agua", 7, cartID2);
        cant=cartService.addProductToCart("maiz", 2, cartID2);
        System.out.println(cant);


    }
}