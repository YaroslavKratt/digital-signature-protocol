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
        String strForPadding = message.toString(2);
        System.out.println("message length " + strForPadding.length());
        strForPadding = strForPadding + "1";
        while (strForPadding.length() % pow(64, 3) != 0) {
            strForPadding = strForPadding + "0";
        }
        message = new BigInteger(strForPadding);
        return this;
    }

    private List<String> splitOnStringsWithLength(int stringLength) {
        List<String> spittedStringList;

        Iterable<String> result = Splitter.fixedLength(stringLength).split(message.toString(2));
        spittedStringList = Arrays.asList(Iterables.toArray(result, String.class));

        return spittedStringList;
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
