package org.skunion.smallru8.BungeeDynamicSync.controller.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadFile {

	public static String READ_FILE(File path) {
		String ret = "";
		
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(path),"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String tmp = "";
			while((tmp = br.readLine())!=null) {
				ret+=tmp+"\n";
			}
			br.close();
			isr.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
