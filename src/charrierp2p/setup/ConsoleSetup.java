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
package charrierp2p.setup;

import java.util.Scanner;

/**
 *
 * @author Oscar
 */
public class ConsoleSetup {
    
    //Static input object to be used throughout the console setup
    private static Scanner input;
    
    static AppVariables getUserPreferences(){
        
        input = new Scanner(System.in);
        
        System.out.println("Welcome to Charrier P2P Setup, would you like to start a Server or a Client? [Server/Client]");
        
        String answer = input.nextLine();
        
        while(!answer.equals("Server") && !answer.equals("Client")){
            printNoOptionTryAgain(answer, "[Server/Client]");
            answer = input.nextLine();
        }
        
        //now that we have determined if we want to make a server or a client we hand off the rest of the process to another function 
        
        boolean server = answer.equals("Server") ? true : false;
        AppVariables appVariables = new AppVariables(server);
        
        if(server){
            getPortIPForServer(appVariables);
        }
        else{
            getPortIPForClient(appVariables);
        }
        
        //clear reference to input object so it can be freed
        input = null;
        System.out.println("\n");
        return appVariables;
    }

    private static void getPortIPForServer(AppVariables appVariables) {
        
        //SET PORT
        System.out.print("Enter the port that the server will use (e.g. 4747): ");
        
        String answer = input.nextLine();
        
        while(!checkPort(answer)){
            System.out.print("Please try again and enter a valid port number: ");
            answer = input.nextLine();
        }
        
        appVariables.port = Integer.parseInt(answer);
        
        //GET USER PROFILE 
    }
    
    private static void getPortIPForClient(AppVariables appVariables) {

        //SET IP
        System.out.print("Enter the ip that the client will connect to (e.g. 127.0.0.1): ");
        
        String answer = input.nextLine();
        
        while(!checkIP(answer)){
            System.out.print("Please try again and enter a valid ip address: ");
            answer = input.nextLine();
        }
        
        appVariables.ipAddress = answer;
        
        //SET PORT
        System.out.print("Enter the port that the client will connect to (e.g. 4747): ");
        
        answer = input.nextLine();
        
        while(!checkPort(answer)){
            System.out.print("Please try again and enter a valid port number: ");
            answer = input.nextLine();
        }
        
        appVariables.port = Integer.parseInt(answer);
    }
        
    private static void printNoOptionTryAgain(String option, String availableOptions){    
            System.out.println("No option " + option + ", please try again and choose a valid option. " + availableOptions);
    }
    
    private static boolean checkIP(String answer) {
        return answer.matches("\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b");
    }
    
    private static boolean checkPort(String value) {  
     try {  
         int port = Integer.parseInt(value);
         if(port <= 65535){
             return true;
         }
         return false;  
      } 
     catch (NumberFormatException e) {  
         return false;  
      }  
    }


}
