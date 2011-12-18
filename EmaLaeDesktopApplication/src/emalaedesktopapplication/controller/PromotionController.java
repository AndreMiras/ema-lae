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

import database.entity.Promotion;
import emalaedesktopapplication.EmaLaeDesktopView;
import emalaedesktopapplication.forms.ViewPromotion;

public class PromotionController {

    private EmaLaeDesktopView mainWindow;
    private ViewPromotion viewPromotion;
    private Promotion promotion;

    public PromotionController(EmaLaeDesktopView mainWindow, Promotion promotion) {
        this.mainWindow = mainWindow;
        this.viewPromotion = new ViewPromotion();
        populateView();
    }

    public ViewPromotion getView()
    {
        return viewPromotion;
    }

    private void populateView()
    {
        viewPromotion.setPromotion(promotion);
    }

}
