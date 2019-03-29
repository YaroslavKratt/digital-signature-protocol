package com.cryptology.raccoon.hash;

import com.cryptology.raccoon.utills.Utills;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class HashFile {
    private  String filePath;
    public static final int AMOUNT_OF_BYTES = 8;
    private int freeBytes = 0;
    private int index = 0;
    private long bytePosition =0;
    private byte[] eightBytes = new byte[8];

    public byte[] getHash() {
        return ByteBuffer.allocate(8).putLong(hash).array();
    }

    private long hash = 0;

    public HashFile(String filePath) {
        this.filePath = filePath;
    }
    public BigInteger readAndHashFile() throws IOException {

        RandomAccessFile aFile = new RandomAccessFile
                (filePath, "r");

        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        while(inChannel.read(buffer) > 0)
        {


        hash = hashArrayOfBytes(hash, buffer.array(), inChannel.size() );
            if (freeBytes != 0) {
                addPaddingOnIndex(index + 1, eightBytes);
                hash = MerkleDamgardHash.round(eightBytes, hash);

            } else {
                hash = MerkleDamgardHash.round(eightBytes, hash);

                Arrays.fill(eightBytes, (byte) 0);
                addPaddingOnIndex(0, eightBytes);
                hash = MerkleDamgardHash.round(eightBytes, hash);
            }
        }

        Utills.printBytes(ByteBuffer.allocate(8).putLong(hash).array());
        return new BigInteger(ByteBuffer.allocate(8).putLong(hash).array());
    }

    private long hashArrayOfBytes(long hash,  byte bufferedBytes[], long fileLength){

        for (long j = bytePosition; j < fileLength; j++) {
            index = (int) (bytePosition % 8);
            freeBytes = (int) (8 - (j % 8 + 1));

            if (j % AMOUNT_OF_BYTES == 0) {
                if (j != 0) {
                    hash = MerkleDamgardHash.round(eightBytes, hash);
                }
                Arrays.fill(eightBytes, (byte) 0);
            }
            eightBytes[(int) (j % 8)] = bufferedBytes[(int) (j%bufferedBytes.length)];
            bytePosition++;
        }


        return hash;
    }


    byte[] addPaddingOnIndex(int index, byte[] array) {
        array[index] = (byte) 0x80;

        return array;

    }
}
