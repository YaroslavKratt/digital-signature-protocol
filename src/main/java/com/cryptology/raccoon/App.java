package com.cryptology.raccoon;

import com.cryptology.raccoon.processor.CommandArgumentProcessor;
import com.cryptology.raccoon.writer.Writer;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("FULL SCREEN, PLEASE");
        if(args.length<2)
        {
            System.out.println("O_o");
            System.out.println("Not enough command arguments");
            return;

        }
        Font font = new Font(Font.SERIF, Font.PLAIN, 10);
        int[][] array = Writer.getArray("HUYAK HUYAK I V PRODAKSHN", font);
        Writer.draw(array);

        TimeUnit.SECONDS.sleep(2);
        array = Writer.getArray("           PRESENTS", font);
        Writer.draw(array);
        CommandArgumentProcessor commandArgumentProcessor = new CommandArgumentProcessor(args[0], args);
        commandArgumentProcessor.process();

    }

}
