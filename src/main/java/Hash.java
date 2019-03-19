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
        //finished here
      //  BigInteger encrypted = new BigInteger(misty.cypher(), 2);
       // return encrypted.xor(m).xor(h);
       return 0;
    }

    private String fillTo64Bits(String str) {
        while (str.length() != 64) {
            str = "0" + str;
        }
        return str;
    }


}
