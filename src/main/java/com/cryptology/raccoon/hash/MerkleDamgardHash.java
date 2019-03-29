package com.cryptology.raccoon.hash;

import com.cryptology.raccoon.encryption.MISTY;

import java.nio.ByteBuffer;

public class MerkleDamgardHash {


    static long round(byte[] m, long h) {
        long longMessage = ByteBuffer.wrap(m).getLong();
        long messageForMISTY = longMessage ^ h;
        MISTY misty = new MISTY(messageForMISTY, longMessage);
        return misty.cypher() ^ longMessage ^ h;
    }


}
