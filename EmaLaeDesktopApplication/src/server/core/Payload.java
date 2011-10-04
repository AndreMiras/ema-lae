/*
 * Request content:
 *  - target module
 *  - action
 *  - data
 */

package server.core;

/**
 *
 * @author amiras
 */
public class Payload {

    private String module;
    private String action;
    private String data;

    public Payload(String module, String action, String data) {
        this.module = module;
        this.action = action;
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public String getData() {
        return data;
    }

    public String getModule() {
        return module;
    }

    

}
