/*******************************************************************************
DESCRIPTION:
		Grammar for Sybase's PowerBuilder PowerScript						
		see: http://manuals.sybase.com/onlinebooks/group-pb/pbg0900e/psref/@Generic__BookTextView/222
				
AUTHOR:
		Ivan.Brezina (ibre5041@ibrezina.net)
DATE:
		DEC 2011
NOTES:
		target language Java
		antlr version 3.4
*******************************************************************************/

package org.ibre5041.parsing;

import java.io.IOException;

import org.antlr.runtime.RecognitionException;
import org.ibre5041.parsing.window.Window;
import org.ibre5041.parsing.window.WindowFactory;

public class test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Window window = WindowFactory.getInstance().createWindow(args[0]);
			System.out.println(window.getAST().toStringTree());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
 	}
}
