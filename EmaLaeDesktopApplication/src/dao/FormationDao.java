/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Formation;
import exceptions.DaoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author logar
 */
public class FormationDao extends DaoHibernate<Formation, Integer> {

    public FormationDao()
    {
        super(Formation.class);
    }

    public FormationDao(Class<Formation> type)
    {
        super(type);
    }

    @Override
    public Integer create(Formation f){
        Integer pk = null;
        try {
            pk = super.create(f);
        } catch (DaoException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return pk;
        }
    }
}