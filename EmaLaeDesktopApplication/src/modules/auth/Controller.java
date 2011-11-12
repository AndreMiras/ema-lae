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
import database.entity.User;
import java.util.HashMap;
import server.core.Payload;

/**
 *
 * @author amiras
 */
public class Controller {


    /**
     * Returns Serialized response
     */
    public String processRequest(Payload payload)
    {
        return "TODO";
    }


    /**
     * @return true if the login was possible (and set the session), false otherwise
     * TODO: way to verify client session
     */
    private boolean login()
    {
        return true; // FIXME: POF only
    }

    // TODO: returning an User as a POF but will later be a Profile returned
    private User get_profile(String username)
    {
        UserDao userDao = new UserDao();
        HashMap querySet = new HashMap<String, String>();
        querySet.put("username", username);
        User user = userDao.get(querySet);

        return user;
    }

    // TODO: returning an User as a POF but will later be a Profile returned
    // this is a proto
    private User get_my_profile()
    {
        // proto
        String current_profile = "firstname.lastname1"; // get_current_profile_from_request()
        return get_profile(current_profile);
    }

}
