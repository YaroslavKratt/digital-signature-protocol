public class Utills {
    static void printBytes(byte[] array) {
         StringBuilder sb = new StringBuilder();
         for (byte b : array) {
             sb.append(String.format("%02X ", b));
         }
         System.out.println(sb.toString());
     }
}
