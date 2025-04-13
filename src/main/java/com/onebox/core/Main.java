package com.onebox.core;
import java.math.BigInteger;


public class Main {
    public static void main(String[] args) throws Exception{
        //Mirar el apartado de tests

        //Ejemplo basico
        CartService cs = new CartService();
        BigInteger id=cs.createCart();
        cs.addProductToCart("Azucar", 5, id);
        cs.addProductToCart("Miel", 2, id);
        System.out.println(cs.getCartInfo(id));
        cs.deleteCart(id);

        cs.finishExecutor();
    }
}
