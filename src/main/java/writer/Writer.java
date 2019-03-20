package writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Writer {
    private String H;
    private String Y;
    private String K;
    private String S;
    private String U;
    private String Z;
    private String G;
    private String fileName;
    private final String file2SignAdd = "File2Sign.sig.add";
    private final String file2Sign = "File2Sign.sig";
    private String separator = "------------------------------";


    public void write() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file2Sign));
             BufferedWriter writer1 = new BufferedWriter(new FileWriter(file2SignAdd))) {
            writer.write(separator);

            writer.append('\n');
            writer.write(fileName);
            writer.append('\n');
            writer.write("H = " + H);
            writer.append('\n');

            writer.write("Y = " + Y);
            writer.append('\n');
            writer.write("K = " + K);
            writer.append('\n');
            writer.write("S = " + S);
            writer.append('\n');

            writer.write(separator);
            writer.close();

            writer1.write(separator);

            writer1.append('\n');
            writer1.write(fileName);
            writer1.append('\n');

            writer1.write("U = " + U);
            writer1.append('\n');
            writer1.write("Z = " + Z);
            writer1.append('\n');
            writer1.write("G = " + G);
            writer1.append('\n');

            writer1.write(separator);
            writer1.close();
            System.out.println("Signed. Check " + file2SignAdd);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Writer setH(String h) {
        H = h;
        return this;
    }

    public Writer setY(String y) {
        Y = y;
        return this;
    }

    public Writer setK(String k) {
        K = k;
        return this;
    }

    public Writer setS(String s) {
        S = s;
        return this;
    }

    public Writer setU(String u) {
        U = u;
        return this;
    }

    public Writer setZ(String z) {
        Z = z;
        return this;
    }

    public Writer setG(String g) {
        G = g;
        return this;
    }

    public Writer setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
