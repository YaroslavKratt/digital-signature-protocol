package com.cryptology.raccoon.processor;


import com.cryptology.raccoon.hash.HashFile;
import com.cryptology.raccoon.signature.Signature;
import com.cryptology.raccoon.writer.Writer;

import java.awt.*;
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
        if (files.length!=3||isNullOrEmpty(files[1])||isNullOrEmpty(files[2])) {
            System.out.println("Don`t joke with me! Give me files! First file for check, second - file with com.cryptology.raccoon.hash.signature");
            return;
        }
        System.out.println("CHECKING...");
        try (BufferedReader reader = new BufferedReader(new FileReader(files[2]))) {
            reader.readLine();
            reader.readLine();
            reader.readLine();

            BigInteger Y = parseBigIntegerFromString(reader.readLine());
            BigInteger K = parseBigIntegerFromString(reader.readLine());
            BigInteger S = parseBigIntegerFromString(reader.readLine());

            BigInteger hash = new HashFile(files[1]).readAndHashFile();
            Signature signature = new Signature(hash);


            boolean verificationResult = signature.verify(S, Y, K, hash);
            System.out.println(verificationResult);

            System.out.println(verificationResult ? "Підпис вірний" : "Підпис невірний");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private BigInteger parseBigIntegerFromString(String readLine) {
        return new BigInteger(readLine.substring(4), 16);
    }

    private void sign(String... files) {
        if (files.length!=2||isNullOrEmpty(files[1])) {
            System.out.println("Don`t joke with me! Give me file to sign");
            return;
        }
        int[][] array = Writer.getArray("          SIGNING...",  new Font(Font.MONOSPACED, Font.PLAIN, 10));
        Writer.draw(array);
        try {
            BigInteger fileHash = new HashFile(files[1]).readAndHashFile();
            writer.setH(fileHash.toString(16));

            Signature signature = new Signature(fileHash);
            signature.sign();
            writer.setS(signature.getSignature().toString(16))
                    .setG(signature.getG().toString(16))
                    .setK(signature.getK().toString(16))
                    .setU(signature.getU().toString(16))
                    .setY(signature.getY().toString(16))
                    .setFileName(files[1])
                    .setZ(signature.getZ().toString(16))
                    .write();
            array = Writer.getArray("          SIGNED",  new Font(Font.MONOSPACED, Font.PLAIN, 10));
            Writer.draw(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process() {
        possibleCommands.get(flag).process();
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.equals("");
    }


}
