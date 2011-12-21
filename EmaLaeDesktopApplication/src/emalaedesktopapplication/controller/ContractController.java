
package emalaedesktopapplication.controller;

import client.ControllerServiceClient;
import database.entity.UserProfile;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.ContractViewPanel;
import emalaedesktopapplication.forms.ViewProfile;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ContractController {

    private EmaLaeDesktopView mainWindow;
    private ContractViewPanel contractViewPanel;

    public ContractController(EmaLaeDesktopView mainWindow) {
        this.mainWindow = mainWindow;
        this.contractViewPanel = new ContractViewPanel();
        populateView();
    }

    public ContractViewPanel getView()
    {
        return contractViewPanel;
    }

    private void setMiddleContentPanel()
    {
        mainWindow.setMiddleContentPanel(
        this.getView());
    }

    private void populateView()
    {
        UserProfile userProfile;
        try
        {
            userProfile = ControllerServiceClient.getController().getUserProfile();
        } catch (RemoteException ex)
        {
            Logger.getLogger(ContractController.class.getName()).log(Level.SEVERE, null, ex);
            userProfile = null;
        }
        if (userProfile == null)
        {
            mainWindow.loginRequiredErrorMessage();
        }
        else
        {
            contractViewPanel.setContract(userProfile.getContract());
            setMiddleContentPanel();
        }
    }

}
