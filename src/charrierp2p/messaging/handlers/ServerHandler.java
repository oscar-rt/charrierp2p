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
import charrierp2p.messaging.msg.ComMessage;
import charrierp2p.messaging.msg.LocalMessage;
import charrierp2p.setup.Setup;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SealedObject;

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
        User consoleUser = this.appSetup.appVariables.user;
    }

    public void sendServerMessage(String message){
        
    }
    
    public synchronized void routeMessage(User user, SealedObject sealedMessage){
        AppMessage message = this.appSetup.appVariables.getSealedMessage(sealedMessage);
        
        if(message != null && message.messageType == AppMessage.MessageTypes.COMMUNICATION){
            ComMessage comMessage = (ComMessage) message;
        }
    }
    
    public synchronized void addOutputStream(User user, ObjectOutputStream output){
        outputStreams.put(user, output);
    }
    
    public synchronized void removeOutputStream(User user){
        if(user != null){
            outputStreams.remove(user);
        }
    }

    @Override
    public void sendLocalMessage(String message, User user) {
        if(user==null){
            displayType.display(new LocalMessage(message));
        }
        else{
            sendLocalMessageTo(new LocalMessage(message), user);
        }
    }
    
    private void sendLocalMessageTo(LocalMessage message, User user){
        try {
            outputStreams.get(user).writeObject(message);
        } catch (IOException ex) {
            sendLocalMessage("Could not send local message to ", null);
        }
    }
    
    public void sendComMessage(ComMessage message, User user){
        if(user == null){
            
            broadcastAsSealedObject(message);
            displayComMessage(message);
        }
        else{
            
        }
    }
    
    private void broadcastAsSealedObject(AppMessage message){
        Set<User> users = outputStreams.keySet();
        User[] userA = (User[]) users.toArray();
        User aUser;
        for(int i = 0; i < users.size(); i++){
            
        }
    }

    @Override
    public void displayComMessage(ComMessage comMessage) {
        displayType.display(comMessage);
    }
}
