package com.shmouradko.textparsing.scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by test on 10.11.2016.
 */
public class TextScanner {
    private static final String FILE_PATH = System.getProperty("user.dir")+"\\files\\data.txt";

    public static String scanFile() {
        String inputText = "";
        try(Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNext()){
                inputText=inputText+scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputText;
    }

}
