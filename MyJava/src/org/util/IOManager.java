package org.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class IOManager {
	public static void testReadFile(){
        try {
        	long start = System.currentTimeMillis();
			String data = IOManager.readFile("bin/ceria#m2_House2Inside.map");
			long end = System.currentTimeMillis();
			
			System.out.println(data);
			System.out.println(data.length());
			System.out.println("Execution time was "+(end-start)+" ms.");
		} catch (IOException e) {
			System.out.println(e);
		}
    }
	
	public static String readFile(String fileName) throws IOException{
		String output="";
		try{
			File templMap = new File(fileName);
			InputStream in = new FileInputStream(templMap);
		
		    byte[] buf = new byte[1024];
		    int len;
		    while ((len = in.read(buf)) > 0) {
		        output+=new String(buf).substring(0, len);
		    }
		    System.out.println(output);
		    System.out.println(output.length());
		    in.close();
		}catch(IOException e){
			throw new IOException(e);
		}
		return output;
	}
	
	public void copyFile(File filesource, String fileName) throws Exception{
		File templMap = new File("data/" + fileName + ".map"); 
		
		InputStream in = new FileInputStream(filesource);
	    OutputStream out = new FileOutputStream(templMap);
	
	    // Copy data from template file to new file
	    byte[] buf = new byte[1024];
	    int len;
	    String str="";
	    while ((len = in.read(buf)) > 0) {
	    	
	        out.write(buf, 0, len);
	        str+=new String(buf);
	    }
	    
	    
	    in.close();
	    out.close();
	}

}
