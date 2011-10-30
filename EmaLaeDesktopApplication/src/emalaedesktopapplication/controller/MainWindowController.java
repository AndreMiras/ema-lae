/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emalaedesktopapplication.controller;

import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.LoginScreenPanel;
import javax.swing.JPanel;

/**
 *
 * @author andre
 */
public class MainWindowController {

    private EmaLaeDesktopView view;
    private LoginScreenPanel loginScreenPanel;

    public MainWindowController(EmaLaeDesktopView view) {
        this.view = view;
        loginScreenPanel = new LoginScreenPanel();
        setMiddleContent(loginScreenPanel);
    }

    private void setMiddleContent(JPanel panel)
    {
        view.setMiddleContentPanel(panel);
    }

}
