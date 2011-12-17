/*
This file is part of ema-lae.

Ema-Lae is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Ema-Lae is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Jpcsp.  If not, see <http://www.gnu.org/licenses/>.
 */

package emalaedesktopapplication.controller;

import client.ControllerServiceClient;
import database.entity.Formation;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.ViewFormation;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormationController {

    private EmaLaeDesktopView mainWindow;
    private ViewFormation viewFormation;

    public FormationController(EmaLaeDesktopView mainWindow) {
        this.mainWindow = mainWindow;
        this.viewFormation = new ViewFormation();
        populateView();
    }

    public ViewFormation getView()
    {
        return viewFormation;
    }

    private void populateView()
    {
        Formation formation = new Formation();
        try
        {
            formation = ControllerServiceClient.getController().get(Formation.class, formation);
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
            formation = null;
        }

        viewFormation.setFormation(formation);
    }
}
