
package emalaedesktopapplication.controller;

import client.ControllerServiceClient;
import database.entity.UserProfile;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.ViewProfile;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserProfileController {

    private EmaLaeDesktopView mainWindow;
    private ViewProfile viewProfile;

    public UserProfileController(EmaLaeDesktopView mainWindow) {
        this.mainWindow = mainWindow;
        this.viewProfile = new ViewProfile();
        populateView();
    }

    public ViewProfile getView()
    {
        return viewProfile;
    }

    private void populateView()
    {
        UserProfile userProfile;
        try
        {
            userProfile = ControllerServiceClient.getController().getUserProfile();
        } catch (RemoteException ex)
        {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            userProfile = null;
        }

        viewProfile.setUserProfile(userProfile);
    }

}
