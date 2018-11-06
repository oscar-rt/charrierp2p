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
package charrierp2p.data;

/**
 *
 * @author Oscar
 * 
 * Basic decimal to base 64 translation class. 
 */

public class Base64 {
    
    final static char[] alphanumeric64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                                          'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
                                          'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
                                          'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 
                                          'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
                                          'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
                                          'w', 'x', 'y', 'z', '0', '1', '2', '3',
                                          '4', '5', '6', '7', '8', '9', '+', '/'};
    
    public static String getBase64String(long value){
        
        long quotient = value/64;
        int remainder = (int) (value % 64);
        
        String base64String = "" + alphanumeric64[remainder];
        
        remainder = (int) (quotient % 64);
        base64String = alphanumeric64[remainder] + base64String;
        
        while(quotient != 0){
            
            quotient = quotient/64;
            remainder = (int) (quotient % 64);
            
            if(64 > quotient){
                quotient = 0;
            }
            
            base64String = alphanumeric64[remainder] + base64String;
        }
        
        return base64String;
    }
    
    public static long getBase10Long(String base64String){
        
        char[] anArray = base64String.toCharArray();
        
        long base10Long = 0;
        int index = 7;
        for(int i = 0; i < 8; i++){
            base10Long += (getArrayPos(anArray[index])) * Math.pow(64, i);
            index--;
        }
        
        return base10Long;
    }
    
    private static int getArrayPos(char x){
        
        for(int i = 0; i < alphanumeric64.length; i++){
            if(alphanumeric64[i] == x){
                return i;
            }
        }
        
        return -1;
    }
}
