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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    static int limit = 5;

    public InitProtocol(boolean server, ObjectInputStream inputStream, ObjectOutputStream outputStream){
        
        completed = false;
        failed = false;
        
        if(server){
            runServerHandshake();
        }
        else{
            runClientHandshake();
        }
    }

    private void runServerHandshake() {
        System.out.println("SERVER HELLOS!");
    }

    private void runClientHandshake() {
        System.out.println("CLIENT HELLOS!");
    }
    
    public User getNewUser(){
        return newUser;
    }

}
