package com.cryptology.raccoon.writer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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

    public static void draw(int[][] array) {
        for (int[] a : array) {
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i] == 0 ? " " : "#");
            }
            System.out.println();
        }
    }

    public static int[][] getArray(String s, Font font) {
        FontMetrics metrics = new JLabel().getFontMetrics(font);
        int width = metrics.stringWidth(s);
        int height = metrics.getMaxAscent();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setFont(font);
        g2d.setColor(Color.black);
        g2d.drawString(s, 0, height);
        g2d.dispose();
        int[][] array = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[i][j] = (bi.getRGB(j, i) != 0 ? 1 : 0);
            }
        }
        return array;
    }


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
