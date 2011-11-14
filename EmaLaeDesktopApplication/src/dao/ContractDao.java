/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import database.entity.Contract;

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
}
