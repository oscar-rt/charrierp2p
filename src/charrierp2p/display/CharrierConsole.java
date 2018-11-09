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
package charrierp2p.display;
import charrierp2p.data.Time;
import charrierp2p.messaging.AppMessage;
import charrierp2p.messaging.MessageHandler;
import charrierp2p.setup.AppVariables;
import charrierp2p.setup.Setup;
import java.util.Scanner;

/**
 *
 * @author Oscar
 */

public class CharrierConsole extends Thread implements DisplayType{
    
    Setup appSetup;
    boolean running;
    MessageHandler handler;
    
    public CharrierConsole(Setup appSetup, MessageHandler handler){
        this.appSetup = appSetup;
        this.handler = handler;
        running = true;
    }
    
    @Override
    public synchronized void display(AppMessage message) {
        if(message.getConsoleDisplay() != null){
            System.out.println(message.getConsoleDisplay());
        }
    }
    
    @Override
    public void run(){
        AppVariables appVariables = appSetup.appVariables;
        Scanner input = new Scanner(System.in);
        
        String mngr = appSetup.appVariables.IS_SERVER ? "server" : "client";
        handler.sendLocalMessage("Console " + mngr + " started @ " + Time.current64Time(), null);
        
        while(running){
            handler.sendMessageFromConsole(input.nextLine());
        }
    }

    @Override
    public synchronized void finish() {
        running = false;
    }
}
