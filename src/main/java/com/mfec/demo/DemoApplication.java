package com.mfec.demo;

import java.io.File;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class DemoApplication implements ApplicationRunner  {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	private static ArrayList<CallDetail> objcallDetail_list = new ArrayList<CallDetail>();
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
	}
   
	
	// @Autowired
	@Override
	    public void run(ApplicationArguments args) throws Exception {
	        /*logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
	        logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
	        logger.info("OptionNames: {}", args.getOptionNames());
            */
		    Integer counter=0;
		    ArrayList<String> mycallDetail;
		    objcallDetail_list = new ArrayList<CallDetail>();
		    CallDetail objcallDetail;
	        
		    for (String name : args.getOptionNames()){
	            logger.info("arg-" + name + "=" + args.getOptionValues(name));
	        }

	        if (args.getSourceArgs().length >= 1) { //Check the number of arguments 
	        	logger.info("Running with File argument "+ args.getSourceArgs()[0].toString());  //Show the naming of file
	        	String inputfile = args.getSourceArgs()[0].toString();
	        	File f = new File(inputfile);
	        	if (f.exists()) {
	        		logger.info("Found the input File "+inputfile);
                    mycallDetail = FileLoader.load(inputfile); // Load the file content into array of string
                    while (counter<mycallDetail.size()) {
                       	objcallDetail = CallDetail.setCallDetail(mycallDetail.get(counter).toString()); // Create CallDetail object with rating data
                    	objcallDetail_list.add(objcallDetail); // Add 
                       	counter++;
                    }
                    
                    if (!objcallDetail_list.isEmpty()) {
                    	logger.info("Start to write json output file "+inputfile);
                    	JsonLog.componseLog(objcallDetail_list,inputfile); // Compose the output in JSON file format
                    	logger.info("Finish to write output "+inputfile+".out.json");
                    }
                }
	       }
	   }
	
	   public static ArrayList<CallDetail> findCallDetail(String msisdn) { //Method to show the rated call detail from REST request
		   Integer counter=0;
		   ArrayList<CallDetail> filter_calldetail= new ArrayList<CallDetail>(); 
		   while (counter< objcallDetail_list.size()) { 
			   
			   if (msisdn.equals("ALL")) {// Show all MSISDNS
				   filter_calldetail.add(objcallDetail_list.get(counter));
			   }
			   else {// Show only specific MSISDN
			   if (objcallDetail_list.get(counter).getMsisdn().equals(msisdn))
				   filter_calldetail.add(objcallDetail_list.get(counter));
		       }
			   counter++;	   
		   }
		   return filter_calldetail;
	   }


}
