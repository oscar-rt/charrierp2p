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
package charrierp2p.messaging.protocols;

import charrierp2p.data.User;
import charrierp2p.messaging.msg.EventStateMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar
 */
public class InitProtocol{
    
    public boolean completed;
    public boolean failed;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private User newUser;
    private User serverUser;
    
    public InitProtocol(User user, boolean server, ObjectInputStream inputStream, ObjectOutputStream outputStream){
        
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        completed = false;
        failed = false;
        
        if(server){
            runServerHandshake();
        }
        else{
            newUser = user;
            runClientHandshake();
        }
    }

    private void runServerHandshake() {
        try {
            
            
            EventStateMessage firstContact = (EventStateMessage) inputStream.readObject();
            if("INIT_001".equals(firstContact.getEventString())
            && firstContact.getEventInteger() == 1){
                firstContact.update("INIT_001_STAMPED", 2, newUser);
                outputStream.writeObject(firstContact);
                firstContact = (EventStateMessage) inputStream.readObject();
                    if("INIT_001_STAMPED_CONFIRMED".equals(firstContact.getEventString())
                    && firstContact.getEventInteger() == 3){
                        newUser = firstContact.getUserData();
                        completed = true;
                    }
                    else{
                        failed = true;
                    }
            }
            else{
                failed = true;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(InitProtocol.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InitProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void runClientHandshake() {
        try {
            EventStateMessage firstContact = new EventStateMessage("INIT_001", 1, newUser);
            outputStream.writeObject(firstContact);
            firstContact = (EventStateMessage) inputStream.readObject();
            firstContact.update(firstContact.getEventString() + "_CONFIRMED",firstContact.getEventInteger() + 1, newUser);
            
            outputStream.writeObject(firstContact);
            
            this.completed = true;
            
        } catch (IOException ex) {
            Logger.getLogger(InitProtocol.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InitProtocol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User getNewUser(){
        if(completed && !failed){
            return newUser;
        }
        return null;
    }
    
    public User getServerUser(){
        if(completed && !failed){
            return serverUser;
        }
        return null;
    }

}
