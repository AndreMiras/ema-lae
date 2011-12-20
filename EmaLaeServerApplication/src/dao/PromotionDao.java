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

package dao;

import database.entity.Formation;
import database.entity.Promotion;
import exceptions.DaoException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PromotionDao extends DaoHibernate<Promotion, Integer> {

    public PromotionDao()
    {
        super(Promotion.class);
    }

    public PromotionDao(Class<Promotion> type)
    {
        super(type);
    }

    @Override
    //Fails silently
    public Integer create(Promotion p){
        Integer pk = null;
        try {
            pk = super.create(p);
        } catch (DaoException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return pk;
        }
    }

    /**
     * Explicitely updates the many-to-one "formation" field.
     * This is mainly used when performing a formation.setFormations
     * followed by a promotionDao.save(formation)
     * @param p
     */
    
    @Override
    public void update(Promotion p)
    {
        GenericDao<Formation> formationGenericDao =
                new GenericDao<Formation>(Formation.class);
        Promotion beforeUpdatePromotion = read(p.getPromotionId());
        Set<Formation> beforeUpdateFormations =
                beforeUpdatePromotion.getFormations();

        for (Formation beforeUpdatedFormation: beforeUpdateFormations)
        {
            /*
             * if the old formation isn't part of the new formations set,
             * updates it by making it orphan
             */
            if(!p.containsFormation(beforeUpdatedFormation))
            {
                beforeUpdatedFormation.setPromotion(null);
                formationGenericDao.update(beforeUpdatedFormation);
            }
        }
        super.update(p);
    }
}
