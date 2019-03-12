import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.pow;

public class Hasher {

    private BigInteger message;
    private BigInteger hash;

    Hasher(BigInteger message) {
        this.message = message;
    }

    public BigInteger getMessage() {
        return message;
    }


    public Hasher addPadding() {
        message = message.shiftLeft(1).or(BigInteger.ONE);

        while (message.bitLength() % pow(64, 3) != 0) {
            message = message.shiftLeft(1);
        }

        return this;
    }

    public List<String> splitOnStringsWithLength(int stringLength) {
        List<String> splited64BitBlocks;

        Iterable<String> result = Splitter.fixedLength(stringLength).split(message.toString(2));
        splited64BitBlocks = Arrays.asList(Iterables.toArray(result, String.class));

        return splited64BitBlocks;
    }

    Hasher hash() {
        BigInteger H = BigInteger.ZERO;
        List<String> M = this.addPadding().splitOnStringsWithLength(64);

        for (String m : M) {
            H = round(new BigInteger(m, 2), H);
        }
        this.hash = H;
        System.out.println(hash.toString(16));
        return this;
    }

    BigInteger round(BigInteger m, BigInteger h) {
        String messageForMISTY = m.xor(h).toString(2);
        MISTY misty = new MISTY(fillTo64Bits(messageForMISTY), fillTo64Bits(h.toString(2)));
        BigInteger encrypted = new BigInteger(misty.cypher(), 2);
        return encrypted.xor(m).xor(h);
    }

    private String fillTo64Bits(String str) {
        while (str.length() != 64) {
            str = "0" + str;
        }
        return str;
    }

    public BigInteger getHash() {
        return hash;
    }
}
