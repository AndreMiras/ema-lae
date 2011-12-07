/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Contract;
import exceptions.ContractException;
import exceptions.DaoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author logar
 */
public class ContractDao extends DaoHibernate<Contract, Integer> {

    public ContractDao()
    {
        super(Contract.class);
    }

    public ContractDao(Class<Contract> type)
    {
        super(type);
    }


    //-1 returned if error.
    //Fails silently
    @Override
    public Integer create(Contract c) throws Error{
        Integer pk = null;
        if(c.isCorrect())
        try
        {
            pk = super.create(c);
        }
        catch (DaoException ex)
        {
            Logger.getLogger(ContractDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            return pk;
        }
        else throw new Error();
    }

    @Override
    public void update(Contract c) throws Error{
        if(c.isCorrect()) super.update(c);
        else throw new Error();
    }
}
