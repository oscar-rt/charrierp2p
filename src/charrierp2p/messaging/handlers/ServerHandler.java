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
package charrierp2p.messaging.handlers;

import charrierp2p.data.User;
import charrierp2p.managers.ServerManager;
import charrierp2p.messaging.MessageHandler;
import charrierp2p.messaging.AppMessage;
import charrierp2p.setup.Setup;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author Oscar
 */
public class ServerHandler extends MessageHandler{
    
    HashMap <User, ObjectOutputStream> outputStreams;
    ServerManager manager;
    
    public ServerHandler(Setup appSetup, ServerManager manager){
        super(appSetup);
        outputStreams = new HashMap<>();
        this.manager = manager;
    }

    @Override
    public void sendMessageFromConsole(String message){
        
    }

    public void sendServerMessage(String message) {
        
    }
    
    public void routeMessage(AppMessage message){
        
    }
    
    public void addOutputStream(User user, ObjectOutputStream output){
        outputStreams.put(user, output);
    }
    
    public void removeOutputStream(User user){
        outputStreams.remove(user);
    }
}
