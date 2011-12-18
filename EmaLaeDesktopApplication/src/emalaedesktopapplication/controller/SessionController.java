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

import database.entity.CourseSession;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.ViewSession;

public class SessionController {

    private EmaLaeDesktopView mainWindow;
    private ViewSession viewSession;
    private CourseSession session;

    public SessionController(EmaLaeDesktopView mainWindow, CourseSession session) {
        this.mainWindow = mainWindow;
        this.viewSession = new ViewSession();
        populateView();
    }

    public ViewSession getView()
    {
        return viewSession;
    }

    private void populateView()
    {
        viewSession.setSession(session);
    }

}
