package com.qait.automation.getpageobjects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class reads the PageObjectRepository text files. Uses buffer reader.
 *
 * @author prashantshukla
 *
 */
public class ObjectFileReader {

    static String tier="";
    static String filepath = "src/test/resources/PageObjectRepository/QA/";

    public static String[] getELementFromFile(String pageName,
            String elementName) {
        //setTier();
        BufferedReader br = null;
        String returnElement = "";
        try {
            br = new BufferedReader(
                    new FileReader(filepath + tier + pageName + ".txt"));
            String line = br.readLine();

            while (line != null) {
                if (line.split(":", 3)[0].equalsIgnoreCase(elementName)) {
                    returnElement = line;
                    break;
                }
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnElement.split(":", 3);
        // TODO: Handle Arrayoutofbounds and Filenotfound exceptions gracefully.
    }

    public static String getPageTitleFromFile(String pageName) {
        //setTier();  As all pageObjectRepo reside in QA folder
        BufferedReader br = null;
        String returnElement = "";
        try {
            br = new BufferedReader(
                    new FileReader(filepath + tier + pageName + ".txt"));
            String line = br.readLine();

            while (line != null) {
                if (line.split(":", 3)[0].equalsIgnoreCase("pagetitle")
                        || line.split(":", 3)[0].equalsIgnoreCase("title")) {
                    returnElement = line;
                    break;
                }
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnElement.split(":", 3)[1].trim();
    }
}
