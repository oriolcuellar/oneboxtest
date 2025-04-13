package com.onebox.core;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

public class IdGenerator {
    public IdGenerator() {

    }
    public static BigInteger generate() {
        UUID uuid = UUID.randomUUID();
        long first=uuid.getMostSignificantBits();
        long second=uuid.getLeastSignificantBits();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(first);
        buffer.putLong(second);
        byte[] bytes = buffer.array();
        return new BigInteger(bytes);
    }
}
