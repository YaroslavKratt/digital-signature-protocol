import org.apache.calcite.util.BitString;
import org.apache.commons.lang.math.IntRange;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class HashFile {
    private static final String FILE_PATH = "C:\\Users\\Yaroslav_Kratt\\LabTasks\\2_period\\CRYPTO_LAB2\\some_text.txt";
    public static final int AMOUNT_OF_BYTES = 8;

    public BigInteger readBytesFromFile() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(FILE_PATH, "r");
        byte[] eightBytes = new byte[8];
        int freeBytes = 0;
        long byteFlowLength = randomAccessFile.length();

        for (int j = 0; j < byteFlowLength; j++) {
            fillArrayByNullIfNumberModEightIsZero(j, eightBytes);
            eightBytes[j % 8] = randomAccessFile.readByte();


            if ((j + 1) % AMOUNT_OF_BYTES == 0 || (j == randomAccessFile.length() - 1)) {
                printBytes(eightBytes);
            }

            freeBytes = 8 - (j % 8 + 1);
            if (j == (byteFlowLength  - 1)  && freeBytes > 0) {
                System.out.println("index of array  when the flow ended: " + j%8);
                addPaddingOnIndex(j % 8 +1, eightBytes);
            }
        }

        System.out.println("number of bytes missing up to 8: " + freeBytes);
        printBytes(eightBytes);
        System.out.println();

        return BigInteger.ONE;
    }

    void fillArrayByNullIfNumberModEightIsZero(int number, byte[] array) {
        if (number % AMOUNT_OF_BYTES == 0) {
            Arrays.fill(array, (byte) 0);
        }


    }

    void printBytes(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02X ", b));
        }
        System.out.println(sb.toString());
    }

    byte[] addPaddingOnIndex(int index, byte[] array) {
        array[index] = (byte) 0x80;

        return array;

    }
}
