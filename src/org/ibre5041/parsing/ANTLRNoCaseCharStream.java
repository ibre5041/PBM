/*
 * ANTLRNoCaseFileStream.java
 *
 * Created on January 25, 2008, 2:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.ibre5041.parsing;

//import java.io.*;

/**
*
* @author Christian P. Lerch
* @version 1.0.0
* @since 1.0
*/
public class ANTLRNoCaseCharStream  extends ANTLRStringStream  {
	public boolean toUpperCase = false;  // default: Stream is case-sensitive
	
    public ANTLRNoCaseCharStream(String input) {
        super();
        this.data = input.toCharArray();
        this.n = input.length();
    }

    /** This is the preferred constructor as no data is actually copied */
    public ANTLRNoCaseCharStream(char[] data, int numberOfActualCharsInArray) {
      super();
      this.data = data;
      this.n = numberOfActualCharsInArray;
    }
    
    @Override
    public int LA(int i) {
        int c = super.LA(i);
        return toUpperCase ? Character.toUpperCase(c) : c;
      }
}