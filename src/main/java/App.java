import java.io.IOException;
import java.math.BigInteger;

public class App {
    public static void main(String[] args) throws IOException {

        BigInteger bigIntegerFromFyle = new HashFile().readAndHash();
        Signature signature = new Signature(bigIntegerFromFyle);
        System.out.println("SIGNATURE: ");
        Utills.printBytes(signature.sign().getSignature().toByteArray());

    }
}
