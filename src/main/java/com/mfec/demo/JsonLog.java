package com.mfec.demo;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class JsonLog {
	
	
   private static final Logger logger = LoggerFactory.getLogger(JsonLog.class);
	
	public static void componseLog(ArrayList<CallDetail> objCallDetail_list,String fname)  {
	
		FileWriter filewriter = null;
		
		logger.info("Number of Objects "+objCallDetail_list.size());
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); //Prepare well format for JSON
		String mystr;
		
		
		mystr=gson.toJson(objCallDetail_list); //Convert object list to JSON
		try  {
			fname = fname + ".out.json"; //Prepare the file output name
			filewriter = new FileWriter(fname);
			filewriter.write(mystr); //Write the JSON File
		}
		catch (IOException e) {
            logger.info("Found error when writing json log file " +e.getMessage());     
        }

         finally {
        try {
            filewriter.close();
        } catch (IOException e) {
        	logger.info("Found error when closing json log file " +e.getMessage());
        }
    }
		
	}
	
}