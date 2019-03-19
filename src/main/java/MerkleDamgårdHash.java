import java.nio.ByteBuffer;

class MerkleDamgårdHash {


    static long round(byte[] m, long h) {
        long longMessage = ByteBuffer.wrap(m).getLong();
        long messageForMISTY = longMessage ^ h;

        MISTY misty = new MISTY(messageForMISTY, longMessage);

        return misty.cypher() ^ longMessage ^ h;
    }


}
