package hash;

import utills.Utills;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class HashFile {
    private  String filePath;
    public static final int AMOUNT_OF_BYTES = 8;

    public byte[] getHash() {
        return ByteBuffer.allocate(8).putLong(hash).array();
    }

    private long hash = 0;

    public HashFile(String filePath) {
        this.filePath = filePath;
    }
    public BigInteger readAndHashFile() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
        byte[] eightBytes = new byte[8];
        int freeBytes = 0;
        int index = 0;
        long byteFlowLength = randomAccessFile.length();
        for (int j = 0; j < byteFlowLength; j++) {
            index = j % 8;
            freeBytes = 8 - (j % 8 + 1);

            if (j % AMOUNT_OF_BYTES == 0) {
                if (j != 0) {
                    hash = MerkleDamgardHash.round(eightBytes, hash);
                }
                Arrays.fill(eightBytes, (byte) 0);
            }
            eightBytes[j % 8] = randomAccessFile.readByte();
        }

        if (freeBytes != 0) {
            addPaddingOnIndex(index + 1, eightBytes);
            hash = MerkleDamgardHash.round(eightBytes, hash);

        } else {
            hash = MerkleDamgardHash.round(eightBytes, hash);

            Arrays.fill(eightBytes, (byte) 0);
            addPaddingOnIndex(0, eightBytes);
            hash = MerkleDamgardHash.round(eightBytes, hash);
        }
        System.out.println("HASH:");
        Utills.printBytes(ByteBuffer.allocate(8).putLong(hash).array());
        System.out.println();

        return new BigInteger(ByteBuffer.allocate(8).putLong(hash).array());
    }


    byte[] addPaddingOnIndex(int index, byte[] array) {
        array[index] = (byte) 0x80;

        return array;

    }
}
