/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.utils;


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
}
