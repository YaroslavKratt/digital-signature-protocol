import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        BigInteger bigIntegerFromFyle = FileReader.readBytesFromFile();
        Hasher hasher = new Hasher(bigIntegerFromFyle);
        System.out.println(hasher.hash());
       // BigInteger hash = new BigInteger(64, 15, new Random());
         //BigInteger hash = new BigInteger("14241010612313363537",10);

        System.out.println(hasher.getHash().toString(2));
        Signature signature = new Signature(hasher.getHash());
        signature.sign();
        System.out.println(signature.verify());
    }
}
