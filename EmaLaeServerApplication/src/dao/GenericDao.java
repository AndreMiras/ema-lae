package dao;

/**
 *
 * @author andre
 */
public class GenericDao<T> extends DaoHibernate<T, Integer>
{
    public GenericDao(Class<T> type)
    {
        super(type);
    }
}
