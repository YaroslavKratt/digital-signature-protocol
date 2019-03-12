import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;

public class Signature {
    private static final BigInteger p = new BigInteger("AF5228967057FE1CB84B92511BE89A47", 16);
    private static final BigInteger a = new BigInteger("9E93A4096E5416CED0242228014B67B5", 16);
    private static final BigInteger q = new BigInteger("57A9144B382BFF0E5C25C9288DF44D23", 16);
    private static final String padding = "0000000011111111111111111111111111111111111111111111111100000000";
    private BigInteger x;
    private BigInteger y;
    private BigInteger signature;

    private BigInteger hash;
    private BigInteger u;
    private BigInteger z;
    private BigInteger k;


    Signature(BigInteger hash) {
        this.hash = new BigInteger(padding + hash.toString(2), 2);
        System.out.println("hash  " + this.hash.toString(2));

        System.out.println("hash length " + this.hash.toString(2).length());

        u = new BigInteger(128, new SecureRandom()).mod(p);

        do {
            x = new BigInteger(128, new SecureRandom());
        } while (x.compareTo(p) >= 0);
        System.out.println("X " + x.toString(16));

        if (x.compareTo(p) >= 0) {
            throw new RuntimeException();
        }
        y = a.modPow(x, p);

        System.out.println("Y " + y.toString(16));
        System.out.println("U " + u.toString(16));

        z = this.hash.multiply(a.modPow(u, p)).mod(p);
        System.out.println("Z " + z.toString(16));

    }

    HashMap<String, BigInteger> calculateKeys() {
        HashMap<String, BigInteger> keys = new HashMap<String, BigInteger>();

        BigInteger k = ((z.subtract(u)).multiply(x.modInverse(q))).mod(q);
        BigInteger g = ((u.multiply(x)).multiply((z.subtract(u)).modInverse(q))).mod(q);

        System.out.println("K " + k.toString(16));
        System.out.println("g " + g.toString(16));
        this.k = k;
        keys.put("k", k);
        keys.put("g", g);

        return keys;
    }

    public Signature sign() {
        HashMap<String, BigInteger> keys = calculateKeys();
        signature = a.modPow(keys.get("g"), p);



        signature= a.modPow(keys.get("g"), p);
        return this;
    }

    public BigInteger getSignature() {
        return signature;
    }

    boolean verify() {
        BigInteger testExpresionLeft = (signature.multiply(y)).modPow(k, p);
        BigInteger testExpresionRight = a.modPow(hash.multiply(signature.modPow(k, p)).mod(p), p);
        return testExpresionLeft.equals(testExpresionRight);
    }


}
