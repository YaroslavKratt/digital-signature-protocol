import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {

        BigInteger bigIntegerFromFyle = new HashFile().readBytesFromFile();
      /*  System.out.println(hash.getHash().toString(2));
        Signature signature = new Signature(hash.getHash());
        signature.sign();
        System.out.println(signature.verify());*/
    }
}
