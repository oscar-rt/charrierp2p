package charrierp2p;

import charrierp2p.managers.ClientManager;
import charrierp2p.setup.Setup;
import charrierp2p.managers.ServerManager;
/**
 *
 * @author Oscar
 */

public class Entrypoint {

    /*Application type-version
    *Change to compile a different type and version of the application
    *GUI = GUI app, CONSOLE = Console app
    *String written in form  
    */
    final static String A_TYPE = "Console";
    final static String A_VERSION = "DEV";
    
    public static void main(String[] args){
        
        Setup appSetup = new Setup(A_TYPE, A_VERSION);
        
        if(appSetup.appVariables.IS_SERVER){
            new ServerManager(appSetup).start();
        }
        else{
            new ClientManager(appSetup).start();
        }
    }
    
}
