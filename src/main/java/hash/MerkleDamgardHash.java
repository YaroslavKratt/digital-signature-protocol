package hash;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import hash.encryption.MISTY;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class MerkleDamgardHash {


    static long round(byte[] m, long h) {
        long longMessage = ByteBuffer.wrap(m).getLong();
        long messageForMISTY = longMessage ^ h;
        MISTY misty = new MISTY(messageForMISTY, longMessage);
        return misty.cypher() ^ longMessage ^ h;
    }


}
