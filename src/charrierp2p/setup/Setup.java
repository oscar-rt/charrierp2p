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

/**
 *
 * @author Oscar
 */
public class Setup {
    
    public final boolean IS_GUI;
    public final String A_VERSION;
    public final AppVariables appVariables;
    
    public Setup(String A_VTYPE, String A_VERSION){
        
        IS_GUI = A_VTYPE.equals("GUI") ? true : false;
        this.A_VERSION = A_VERSION;
        
        if(IS_GUI){
            //prompt user with GUI !!
            appVariables = null;
        }
        else{
            //prompt user through console !!
            appVariables = ConsoleSetup.getUserPreferences();
        }
    }
}
