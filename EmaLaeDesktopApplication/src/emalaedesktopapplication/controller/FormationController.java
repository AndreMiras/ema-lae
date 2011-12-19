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

import database.entity.Formation;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.ViewFormation;

public class FormationController {

    private EmaLaeDesktopView mainWindow;
    private ViewFormation viewFormation;
    private Formation formation;

    public FormationController(EmaLaeDesktopView mainWindow, Formation formation) {
        this.mainWindow = mainWindow;
        this.viewFormation = new ViewFormation();
        this.formation = formation;
        populateView();
    }

    public ViewFormation getView()
    {
        return viewFormation;
    }

    private void populateView()
    {
        viewFormation.setFormation(formation);
    }
}
