package com.mfec.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileLoader {
	//-- File f = new File(filename);
	
	private static final Logger logger = LoggerFactory.getLogger(FileLoader.class);
	
	public static ArrayList<String> mycallDetail = new ArrayList<String>();
	
    public static ArrayList<String> load(String fname) {
	
	
	BufferedReader buffer_r = null;
	FileReader fname_r = null;

	
	try {
	fname_r = new FileReader(fname);
    buffer_r = new BufferedReader(fname_r);

	String sCurrentLine;

	logger.info("FileLoader Started");
	
	while ((sCurrentLine = buffer_r.readLine()) != null) {
		//System.out.println(sCurrentLine);
		mycallDetail.add(sCurrentLine); 
	}
	
	//return(mycallDetail);
	
	}
	catch (IOException e) {

		e.printStackTrace();

	} finally {

		try {

			if (buffer_r != null)
				buffer_r.close();

			if (fname_r != null)
				fname_r.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

	}
	return mycallDetail;
    }
	
}