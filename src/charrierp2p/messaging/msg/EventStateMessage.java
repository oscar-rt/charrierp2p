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
package charrierp2p.messaging.msg;

import charrierp2p.data.User;
import charrierp2p.messaging.AppMessage;

/**
 *
 * @author Oscar
 */

public class EventStateMessage extends AppMessage{
    

    String eventString;
    int eventInteger;
    User userData;
    
    public EventStateMessage(){
        super();
        this.messageType = AppMessage.MessageTypes.EVENTSTATE;
    }
    
    //init constructor
    public EventStateMessage(String eventString, int eventInteger, User userData){
        super();
        this.messageType = AppMessage.MessageTypes.EVENTSTATE;
        this.eventString = eventString;
        this.eventInteger = eventInteger;
        this.userData = userData;
    }

    @Override
    public String getConsoleDisplay() {
        return null;
    }
    
    public void update(String eventString, int eventInteger, User userData){
        this.eventString = eventString;
        this.eventInteger = eventInteger;
        this.userData = userData;
    }
    
    public String getEventString(){
        return eventString;
    }
    
    public int getEventInteger(){
        return eventInteger;
    }
    
    public User getUserData(){
        return userData;
    }
    
}
