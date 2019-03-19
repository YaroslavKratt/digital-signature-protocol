import org.apache.calcite.util.BitString;
import org.apache.commons.lang.math.IntRange;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class HashFile {
    private static final String FILE_PATH = "D:\\Study\\cryptology\\4_course\\2_period\\LAB2\\some_text.txt";
    public static final int AMOUNT_OF_BYTES = 8;

    public BigInteger readBytesFromFile() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(FILE_PATH, "r");
        byte[] eightBytes = new byte[8];
        int freeBytes = 0;
        int index = 0;
        long byteFlowLength = randomAccessFile.length();
        long hash = 0;
        for (int j = 0; j < byteFlowLength; j++) {
            index = j%8;
            freeBytes = 8 - (j % 8 + 1);

            if (j % AMOUNT_OF_BYTES == 0) {
                if(j!=0) {
                    hash = Hash.round(eightBytes, hash);
                    System.out.println("HASH:" );
                    printBytes(ByteBuffer.allocate(8).putLong(hash).array());
                    System.out.println();
                }
                Arrays.fill(eightBytes, (byte) 0);
            }
            eightBytes[j % 8] = randomAccessFile.readByte();


            if ((j + 1) % AMOUNT_OF_BYTES == 0 || (j == randomAccessFile.length() - 1)) {
                printBytes(eightBytes);
            }


        }

        System.out.println("number of bytes missing up to 8: " + freeBytes);
        System.out.println("index of array  when the flow ended: " + index);
        addPaddingOnIndex(index +1, eightBytes);
        hash = Hash.round(eightBytes, hash);
        System.out.println("HASH:" );
       printBytes(ByteBuffer.allocate(8).putLong(hash).array());
        System.out.println();

        return new BigInteger(ByteBuffer.allocate(8).putLong(hash).array());
    }



   static void printBytes(byte[] array) {
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
