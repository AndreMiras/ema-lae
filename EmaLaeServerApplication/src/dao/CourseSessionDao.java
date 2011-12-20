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

import database.entity.CourseSession;
import exceptions.DaoException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseSessionDao extends DaoHibernate<CourseSession, Integer> {
    public CourseSessionDao()
    {
        super(CourseSession.class);
    }

    public CourseSessionDao(Class<CourseSession> type)
    {
        super(type);
    }

    @Override
    //Fails silently
    public Integer create(CourseSession cs){
        Integer pk = null;
        try
        {
            pk = super.create(cs);
        }
        catch (DaoException ex)
        {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return pk;
        }
    }
}
