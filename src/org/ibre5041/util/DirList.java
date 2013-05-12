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
		List<File> retval = new ArrayList<File>();
		File folder = new File(dir);
		if(folder.isFile())
		{
			retval.add(folder);
			return retval;
		}

		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles)
		{
			if (file.isFile())
				for (String suffix : suffixes)
				{
					if( file.getPath().endsWith(suffix) == true)
					{
						retval.add(file);
						break;
					}
				}

			if (file.isDirectory())
				retval.addAll(listDirectory(file.getPath()));
		}

		return retval;
	}

}
