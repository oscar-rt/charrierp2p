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
import charrierp2p.messaging.MessageHandler;
import charrierp2p.messaging.handlers.ServerHandler;
import charrierp2p.setup.AppVariables;
import charrierp2p.setup.Setup;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
public class ServerManager extends Thread{
    
    Setup setupVariables;
    ServerHandler handler;
    DisplayType display;
    ConnectionsManager connections;
    
    public ServerManager(Setup setupVariables){
        this.setupVariables = setupVariables;
    }
    
    @Override
    public void run(){
        
        AppVariables appVariables = setupVariables.appVariables;
        ServerSocket serverConnection;
        
        try {
            serverConnection = new ServerSocket(appVariables.port);
        } catch (IOException ex) {
            serverConnection = null;
        }
        
        if(serverConnection != null){
            handler = new ServerHandler(setupVariables, this);
            display = Source.getDisplay(setupVariables, handler);
            handler.setDisplay(display);
            try {
                handler.sendLocalMessage("Server started locallly on " + InetAddress.getLocalHost().getHostAddress(), null);
            } catch (UnknownHostException ex) {
                Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            connections = new ConnectionsManager(setupVariables, handler, serverConnection);
            connections.start();
        }
    }
    
    public void finish(){
        display.finish();
        connections.finish();
    }
}
