package com.onebox.core;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CartServiceTest {
    @Test
    public void createCart() {
        CartService cs = new CartService();
        BigInteger id=cs.createCart();
        assertEquals(true, cs.existCart(id));
        cs.finishExecutor();

    }

    @Test
    public void addProductCorrect() throws Exception {
        CartService cs = new CartService();
        BigInteger id=cs.createCart();
        cs.addProductToCart("Azucar", 5, id);
        cs.finishExecutor();
    }

    @Test
    public void addProductIncorrectProduct() throws Exception {
        CartService cs = new CartService();
        BigInteger id=cs.createCart();
        assertThrows(Exception.class, () -> {
            cs.addProductToCart("Azucar", -5, id);
        });
        cs.finishExecutor();
    }

    @Test
    public void addProductCartNotExists() throws Exception {
        CartService cs = new CartService();
        BigInteger id=cs.createCart();
        BigInteger val=BigInteger.valueOf(1234);
        assertThrows(Exception.class, () -> {
            cs.addProductToCart("Azucar", 5, val);
        });
        cs.finishExecutor();
    }

    @Test
    public void deleteCart() throws Exception {
        CartService cs = new CartService();
        BigInteger id=cs.createCart();
        cs.addProductToCart("Azucar", 5, id);
        cs.deleteCart(id);
        assertEquals(false, cs.existCart(id));
        cs.finishExecutor();
    }

    @Test
    public void deleteCartNoExists() throws Exception {
        CartService cs = new CartService();
        BigInteger id=cs.createCart();
        BigInteger val=BigInteger.valueOf(12345);
        boolean ret=cs.deleteCart(val);
        assertEquals(false, ret);
        cs.finishExecutor();
    }

    @Test
    public void getCartInfo() throws Exception {
        CartService cs = new CartService();
        BigInteger id=cs.createCart();
        cs.addProductToCart("Azucar", 5, id);
        cs.addProductToCart("Manzana", 2, id);
        String info = cs.getCartInfo(id);
        String realInfo="List of products\n" +
                "\n" +
                "    Azucar\n" +
                "       Quantity: 5\n" +
                "    Manzana\n" +
                "       Quantity: 2\n";
        assertEquals(realInfo,info );
        cs.finishExecutor();
    }

    @Test
    public void deleteInactivity() throws Exception {
        CartService cs = new CartService();
        BigInteger id=cs.createCart();
        cs.addProductToCart("Azucar", 5, id);
        assertEquals(true, cs.existCart(id));
        Thread.sleep(11000);//11 seconds
        assertEquals(false, cs.existCart(id));
        cs.finishExecutor();
    }

}


