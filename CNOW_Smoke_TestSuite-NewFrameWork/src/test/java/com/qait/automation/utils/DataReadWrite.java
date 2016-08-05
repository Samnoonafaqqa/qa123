package com.qait.automation.utils;


import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * This is the utility class for data read write
 *
 * @author QAIT
 *
 */
public class DataReadWrite {

    /**
     * construtor of this class
     */
    public DataReadWrite() {
    }
    static Properties prop = new Properties();
    /**
     * writeDataToFile
     *
     * @param Property
     * @param Data
     * @return true if written else return false
     */
    public static boolean writeDataToFile(String property,
			String data) {
		boolean result = false;
		try {
			InputStream inPropFile = new FileInputStream("./src/test/resources/testdata/courseData.properties");
			prop.load(inPropFile);
			inPropFile.close();
	  	    OutputStream outPropFile = new FileOutputStream("./src/test/resources/testdata/courseData.properties");
		    prop.setProperty(property, data);
		    prop.store(outPropFile, null);
		    outPropFile.close();
		 	result = true;
		  } catch (IOException e) {
			   e.printStackTrace();
		}
		return result;
    }

    /**
     * readDataFromFile
     *
     * @param Property
     * @return text
     */
     public static String getProperty(String Property) {
        try {
            Properties prop = ResourceLoader.loadProperties("./src/test/resources/testdata/courseData.properties");
            return prop.getProperty(Property);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * 
     * This method will get the properties pulled from a file located relative to the base dir
     * 
     * @param propFile complete or relative (to base dir) file location of the properties file 
     * @param Property property name for which value has to be fetched 
     * @return String value of the property
     */
    public static String getProperty(String propFile, String Property) {
        try {
            Properties prop = ResourceLoader.loadProperties(propFile);
            return prop.getProperty(Property);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }    

    /**
     * readXmlFromFile
     *
     * @param fileName
     * @return text
     */
    public static String readXmlFromFile(String fileName) {

        File file = new File(fileName);
        StringBuilder contents = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text).append(
                        System.getProperty("line.separator"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (contents.toString());
    }
}
