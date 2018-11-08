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

import charrierp2p.data.User;
import charrierp2p.messaging.handlers.ServerHandler;
import charrierp2p.setup.Setup;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
public class Connection extends Thread{
    
    User user;
    Setup setupVariables;
    ServerHandler handler;
    public boolean running;
    Socket connection;
    
    public Connection(Socket connection, Setup setupVariables, ServerHandler handler){
        this.connection = connection;
        this.setupVariables = setupVariables;
        this.handler = handler;
        this.running = true;
    }
    
    @Override 
    public void run(){
        
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        try{
            inputStream = new ObjectInputStream(connection.getInputStream());
            outputStream = new ObjectOutputStream(connection.getOutputStream());
        } catch (IOException ex) {
           
        }
        
        while(running){
        //listen to input
        
        }
        
        
    }
    
    public synchronized void finish(){
        running = false;
    }
}