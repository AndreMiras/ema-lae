/*
 * EmaLaeDesktopApplication.java
 */

package emalaedesktopapplication;

import emalaedesktopapplication.controller.MainWindowController;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class EmaLaeDesktopApplication extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     * Create the associated MainWindowController and bind it to the view.
     */
    @Override protected void startup() {
        EmaLaeDesktopView emaLaeDesktopView;
        emaLaeDesktopView = new EmaLaeDesktopView(this);
        show(emaLaeDesktopView);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of EmaLaeDesktopApplication
     */
    public static EmaLaeDesktopApplication getApplication() {
        return Application.getInstance(EmaLaeDesktopApplication.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(EmaLaeDesktopApplication.class, args);
    }
}
