package org.ibre5041.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.ibre5041.parsing.window.WindowTypeEnum;

public class DirList {
	private static final Set<String> suffixes;
	
	static {
		//suffixes = Arrays.asList(".sra", ".srd", ".srf", ".srj", ".srm", ".srq", ".srs", ".sru", ".srw");
		suffixes = WindowTypeEnum.getSuffixes();
	}

	public static List<File> listDirectory(String dir)
	{
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		List<File> retval = new ArrayList<File>();

		for (File file : listOfFiles)
			for (String suffix : suffixes)
				if (file.getPath().endsWith(suffix) == true) {
					retval.add(file);
					break;
				}					
													
		    return retval;
	}	
}
