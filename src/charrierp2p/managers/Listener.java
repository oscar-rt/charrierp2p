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

import charrierp2p.messaging.handlers.ClientHandler;
import charrierp2p.messaging.protocols.InitProtocol;
import charrierp2p.setup.Setup;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SealedObject;

/**
 *
 * @author Oscar
 */
public class Listener extends Thread{
    
    Setup setupVariables;
    ClientHandler handler;
    Socket serverConnection;
    boolean running;

    
    
    public Listener( Setup setupVariables, ClientHandler handler, Socket serverConnection){
        
        this.setupVariables = setupVariables;
        this.handler = handler;
        this.serverConnection = serverConnection;
        running = true;
    }
    
    @Override 
    public void run(){
        
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        
        try{
            outputStream = new ObjectOutputStream(serverConnection.getOutputStream());
            inputStream = new ObjectInputStream(serverConnection.getInputStream());
        } catch (IOException ex) {
            running = false;
        }
        
        if(running){
            InitProtocol initConnection = new InitProtocol(setupVariables.appVariables.user, false, inputStream, outputStream);
            if(initConnection.completed && !initConnection.failed){
                handler.setServerOutputStream(outputStream);
                running = true;
            }
            else{
                running = false;
            }
        }
        
        while(running){
            try {
                handler.interpretMessage((SealedObject) inputStream.readObject());
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public synchronized void finish(){
        if(running){
        running = false;
            
        }
    }
}
