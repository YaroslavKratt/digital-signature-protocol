import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class Hash {

    private byte[] message;
    private int hash;

    Hash(byte[] message) {
        this.message = message;
    }

    public byte[] getMessage() {
        return message;
    }







   public static long round(byte[] m, long  h) {
         long longMessage = ByteBuffer.wrap(m).getLong();
        long messageForMISTY = longMessage ^ h;
        MISTY misty = new MISTY(messageForMISTY,longMessage);

       System.out.println("B");

       return misty.cypher()^longMessage^h;
    }




}
