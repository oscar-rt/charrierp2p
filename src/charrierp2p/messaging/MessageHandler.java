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

package charrierp2p.messaging;

import charrierp2p.data.User;
import charrierp2p.display.DisplayType;
import charrierp2p.messaging.msg.ComMessage;
import charrierp2p.setup.AppVariables;
import charrierp2p.setup.Setup;
import java.util.HashMap;

/**
 *
 * @author Oscar
 */
public abstract class MessageHandler {
    
    public DisplayType displayType = null;
    public Setup appSetup;
    
    public MessageHandler(Setup appSetup){
        this.appSetup = appSetup;
    }
    
    public void setDisplay(DisplayType display){
        displayType = display;
    }
    
    public abstract void sendMessageFromConsole(String message);
    public abstract void displayComMessage(ComMessage comMessage);
    public abstract void sendLocalMessage(String message, User user);
}
