import org.apache.calcite.util.BitString;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {
    private  static  final  String FILE_PATH = "C:\\Users\\Yaroslav_Kratt\\LabTasks\\2_period\\CRYPTO_LAB2\\some_text.txt";
    public static BigInteger readBytesFromFile() {
        File file = new File(FILE_PATH);
        byte[] byteArray = new byte[0];
        String binaryString;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);

            byteArray = new byte[fileInputStream.available()];
            fileInputStream.read(byteArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //binaryString =  BitString.createFromBytes(byteArray).toString();
        StringBuilder sb = new StringBuilder();
        for (byte b : byteArray) {
            sb.append(String.format("%02X ", b));
        }
        System.out.println(sb.toString());
        return new BigInteger(byteArray);
    }






}
