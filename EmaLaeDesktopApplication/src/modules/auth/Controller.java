/*
 * WARNING: this is only a prototype/POF
 * Process the client request (Payload), request the model and send the reply
 * Actually root to the relevant Views? method
 * TODO: the client session should be checked, to verify whether or not the
 * client is allowed to perform certain actions
 * Each controller should really implement an interface so the rich client
 * can also use it
 */

package modules.auth;

import dao.UserDao;
import exceptions.DaoException;
import database.entity.Users;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author amiras
 */
public class Controller {
    /**
     * @return true if the login was possible (and set the session), false otherwise
     * TODO: way to verify client session
     */
    private boolean login()
    {
        return true; // FIXME: POF only
    }

    // TODO: returning an Users as a POF but will later be a Profile returned
    private Users get_profile(String username)
    {
        UserDao userDao = new UserDao();
        HashMap querySet = new HashMap<String, String>();
        querySet.put("username", username);
        Users user;
        try
        {
            user = userDao.get(querySet);
        } catch (DaoException ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            user = null;
        }

        return user;
    }

    // TODO: returning an Users as a POF but will later be a Profile returned
    // this is a proto
    private Users get_my_profile()
    {
        // proto
        String current_profile = "firstname.lastname1"; // get_current_profile_from_request()
        return get_profile(current_profile);
    }

}
