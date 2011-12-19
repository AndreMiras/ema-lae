/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emalaedesktopapplication.controller;

import client.ControllerServiceClient;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.admin.AdminEditPanel;
import exceptions.PermissionException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class AdminEditController<T>
{

    private EmaLaeDesktopView mainWindow;
    private AdminEditPanel<T> view;
    Class<T> type;

    public AdminEditController(
            EmaLaeDesktopView mainWindow, AdminEditPanel<T> view, Class<T> type)
    {
        this.mainWindow = mainWindow;
        this.view = view;
        this.type = type;

        view.addButtonsListener(new ButtonsListener());
    }

    class ButtonsListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand();

            if (actionCommand.equals("Save"))
            {
                T editedObj = view.save();
                try
                {
                    // hit the database back with the edited user
                    ControllerServiceClient.getController().createOrUpdate(
                            type, editedObj);
                } catch (PermissionException ex)
                {
                    Logger.getLogger(AdminEditController.class.getName()).log(
                            Level.SEVERE, null, ex);
                    int n = JOptionPane.showConfirmDialog(
                        view,
                        "You're not allowed to modify this.\n" +
                        "Do you want more details?",
                        "Permission Error",
                        JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION)
                    {
                        String strackTrace = "TODO";// ex.getStackTrace();
                        JOptionPane.showMessageDialog(view,
                                new javax.swing.JScrollPane(
                                new javax.swing.JLabel(strackTrace)));
                    }
                } catch (RemoteException ex)
                {
                   JOptionPane.showMessageDialog(view, ex);
                    Logger.getLogger(AdminEditController.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            } else if (actionCommand.equals("Delete"))
            {
                T obj = view.getObj();
                try
                {
                    // TODO: go back to the previous screen
                    ControllerServiceClient.getController().delete(type, obj);
                } catch (RemoteException ex)
                {
                    Logger.getLogger(AdminEditController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (actionCommand.equals("Cancel"))
            {
                // TODO: go back to the previous screen
                System.out.println("TODO:back to previous screen");
            }
        }
    }
}
