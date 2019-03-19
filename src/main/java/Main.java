import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {

        BigInteger bigIntegerFromFyle = new HashFile().readBytesFromFile();
        Signature signature = new Signature(bigIntegerFromFyle);
        System.out.println("SIGNATURE: ");
        HashFile.printBytes(signature.sign().getSignature().toByteArray());
      /*  System.out.println(hash.getHash().toString(2));
        Signature signature = new Signature(hash.getHash());
        signature.sign();
        System.out.println(signature.verify());*/
    }
}
