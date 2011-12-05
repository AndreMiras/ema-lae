/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Utils
{

    public static String getClassNameWithoutPackage(Class cls)
    {
        String clsname = cls.getName();
        System.out.println("Full class name =" + clsname);
        int mid = clsname.lastIndexOf('.') + 1;
        String finalClsName = clsname.substring(mid);
        return finalClsName;
    }
    
    public static Properties readProperties(String fileName){
            // Read properties file.
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName+".properties"));
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }

    public static void writeProperties(Properties properties, String fileName){
                // Write properties file.
        try {
            properties.store(new FileOutputStream(fileName+".properties"), null);
        }
        catch (IOException e)
        {
        }
    }
}
