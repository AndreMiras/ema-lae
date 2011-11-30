/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Contract;
import exceptions.ContractException;

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
    @Override
    public Integer create(Contract c) throws Error{
        if(c.getApprentice().isApprentice()
                && c.getSupervisingTeacher().isSupervisingTeacher()
                && c.getInternshipSupervisor().isInternshipSupervisor())
            return super.create(c);
        else{
            throw new Error();
        }
    }
}
