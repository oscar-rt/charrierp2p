/*
 * The MIT License
 *
 * Copyright 2018 Oscar.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package charrierp2p.managers;

import charrierp2p.display.DisplayType;
import charrierp2p.display.Source;
import charrierp2p.messaging.handlers.ClientHandler;
import charrierp2p.setup.AppVariables;
import charrierp2p.setup.Setup;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Oscar
 */
public class ClientManager extends Thread{
   
    Setup setupVariables;
    ClientHandler handler;
    DisplayType display;
    Listener listener;
    
    public ClientManager(Setup setupVariables){
        this.setupVariables = setupVariables;
    }
    
    @Override
    public void run(){
        
        AppVariables appVariables = setupVariables.appVariables;
        Socket serverConnection;
        
        try {
            serverConnection = new Socket(appVariables.ipAddress, appVariables.port);
        } catch (IOException ex) {
            ex.printStackTrace();
            serverConnection = null;
        }
        
        if(serverConnection != null){
            
            handler = new ClientHandler(setupVariables, this);
            display = Source.getDisplay(setupVariables, handler);
            handler.setDisplay(display);
            listener = new Listener(setupVariables, handler, serverConnection);
        }
    }
    
    public void finish(){
        
    }
}
