import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class HashFile {
    public static final int AMOUNT_OF_BYTES = 8;
    private static final String FILE_PATH = "D:\\Study\\cryptology\\4_course\\2_period\\LAB2\\some_text.txt";

    public BigInteger readAndHash() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(FILE_PATH, "r");
        byte[] eightBytes = new byte[8];
        int index = 0;
        long hash = 0;
        long byteFlowLength = randomAccessFile.length();

        for (int j = 0; j < byteFlowLength; j++) {
            index = j % 8;

            if (j % AMOUNT_OF_BYTES == 0) {
                if (j != 0) {
                    hash = MerkleDamgårdHash.round(eightBytes, hash);
                }
                Arrays.fill(eightBytes, (byte) 0);
            }
            eightBytes[j % 8] = randomAccessFile.readByte();

        }

        addPaddingOnIndex(index + 1, eightBytes);
        hash = MerkleDamgårdHash.round(eightBytes, hash);

        return new BigInteger(ByteBuffer.allocate(8).putLong(hash).array());
    }


    private byte[] addPaddingOnIndex(int index, byte[] array) {
        array[index] = (byte) 0x80;

        return array;

    }
}
