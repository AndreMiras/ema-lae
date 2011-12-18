/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.CourseSession;
import database.entity.Formation;
import exceptions.DaoException;
import java.util.Set;
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
    //Fails silently
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


    /**
     * Explicitely updates the many-to-one "parentFormation" field.
     * This is mainly used when performing a formation.setChildrenFormations
     * or a formation.setSessions
     * followed by a formationDao.save(formation)
     * @param f
     */
    @Override
    public void update(Formation f)
    {
        Formation beforeUpdateFormation = read(f.getFormationId());
        Set<Formation> beforeUpdateChildrenFormations =
                beforeUpdateFormation.getChildrenFormations();
        Set<CourseSession> beforeUpdateSessions =
                beforeUpdateFormation.getSessions();

        for (Formation beforeUpdateChildFormation: beforeUpdateChildrenFormations)
        {
            /*
             * if the old child isn't part of the new children set,
             * updates it by making it orphan
             */
            if(!f.containsChild(beforeUpdateChildFormation))
            {
                beforeUpdateChildFormation.setParentFormation(null);
                super.update(beforeUpdateChildFormation);
            }
        }
        
        GenericDao<CourseSession> sessionGenericDao =
                new GenericDao<CourseSession>(CourseSession.class);

        for (CourseSession beforeUpdateSession: beforeUpdateSessions)
        {
            /*
             * if the old child isn't part of the new children set,
             * updates it by making it orphan
             */
            if(!f.containsSession(beforeUpdateSession))
            {
                beforeUpdateSession.setFormation(null);
                sessionGenericDao.update(beforeUpdateSession);
            }
        }
        super.update(f);
    }
}