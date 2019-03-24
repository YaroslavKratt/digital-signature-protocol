package processor;


import hash.HashFile;
import signature.Signature;
import utills.Utills;
import writer.Writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.math.BigInteger;
import java.util.HashMap;

public class CommandArgumentProcessor {
    private HashMap<String, Processor> possibleCommands = new HashMap<>();
    private String flag;
    private Writer writer = new Writer();

    public CommandArgumentProcessor(String flag, String... files) {
        this.flag = flag;
        possibleCommands.put("-sign", () -> sign(files));
        possibleCommands.put("-check", () -> check(files));

    }


    private void check(String... files) {
        System.out.println("CHECKING...");
        try (BufferedReader reader = new BufferedReader(new FileReader("File2Sign.sig"))) {
            reader.readLine();
            reader.readLine();
            reader.readLine();

            BigInteger Y = parseBigIntegerFromString(reader.readLine());
            BigInteger K = parseBigIntegerFromString(reader.readLine());
            BigInteger S = parseBigIntegerFromString(reader.readLine());

            BigInteger hash = new HashFile(files[1]).readAndHashFile();
            Signature signature = new Signature(hash);
            System.out.println("Y " + Y.toString(16));
            System.out.println("K " + K.toString(16));
            System.out.println("S " + S.toString(16));
            System.out.println("HASH " + Utills.byteArrayToString(hash.toByteArray()));


            boolean verificationResult  = signature.verify(S,Y,K,hash);
            System.out.println(verificationResult);

            System.out.println(verificationResult ? "Підпис вірний" : "Підпис невірний");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private BigInteger parseBigIntegerFromString(String readLine) {
        System.out.println("read from file " + readLine);
        System.out.println("substr " + readLine.substring(4));

        return new BigInteger(readLine.substring(4),16);
    }

    private void sign(String... files) {
        System.out.println("SIGNING...");
        try {
            BigInteger fileHash = new HashFile(files[1]).readAndHashFile();
            writer.setH(fileHash.toString(16));

            Signature signature = new Signature(fileHash);
            signature.sign();
            System.out.println(signature.verify(signature.getSignature(),signature.getY(),signature.getK(), new HashFile(files[1]).readAndHashFile()));
            writer.setS(signature.getSignature().toString(16))
                    .setG(signature.getG().toString(16))
                    .setK(signature.getK().toString(16))
                    .setU(signature.getU().toString(16))
                    .setY(signature.getY().toString(16))
                    .setFileName(files[1])
                    .setZ(signature.getZ().toString(16))
                    .write();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process() {
        possibleCommands.get(flag).process();
    }


}
