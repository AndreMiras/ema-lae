/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication;

import emalaedesktopapplication.utils.StackTraceUtil;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class Dialogs {

    public static void stackTraceDialog(
            Component parentComponent, Throwable aThrowable)
    {
        String strackTrace =
                StackTraceUtil.getStackTrace(aThrowable);
        JOptionPane.showMessageDialog(parentComponent,
                new javax.swing.JScrollPane(
                new javax.swing.JTextArea(
                strackTrace, 20, 40)));
    }
}
