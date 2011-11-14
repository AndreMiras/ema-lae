/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Formation;

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
}