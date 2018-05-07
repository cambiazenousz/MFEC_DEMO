package com.mfec.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;

@Component
public class CallDetail {
	private String DateStart;
	private String TimeStart;
	private String TimeStop;
	@Expose
	private String Msisdn;
	private String Promotion;
	@Expose
	private Long Duration;
	@Expose
	private String Rate;
	
	private static final Logger logger = LoggerFactory.getLogger(CallDetail.class);
	
	
	
	
	public static CallDetail setCallDetail(String calldetail) {
		SimpleDateFormat formatter = new SimpleDateFormat("y/M/d H:m:s");
		Date date_start;
		Date date_stop;
		String tempStrDate1;
		String tempStrDate2;
		//Long date_start_epoch;
		//Long date_stop_epoch;
		
		CallDetail myCalldetail = new CallDetail();
		
		
		//Mapping to CallDetail object
		String[]  callarray = calldetail.split("\\|"); //Split string array 
		myCalldetail.DateStart = callarray[0];
		myCalldetail.TimeStart = callarray[1];
		myCalldetail.TimeStop = callarray[2];
		myCalldetail.Msisdn = callarray[3];
		myCalldetail.Promotion  = callarray[4];
		tempStrDate1 = myCalldetail.DateStart+" "+myCalldetail.TimeStart;
		tempStrDate2 = myCalldetail.DateStart+" "+myCalldetail.TimeStop;
		//logger.info("Start Date String "+tempStrDate1);
		//logger.info("Stop Date String "+tempStrDate2);
		
		
		try {
			date_start  = formatter.parse(tempStrDate1);
	    } catch (ParseException e) {
			// TODO Auto-generated catch block
			date_start = null;
			e.printStackTrace();
		} // Convert string  Start Date to Date 
		
		
		try {
			date_stop  = formatter.parse(tempStrDate2);
	    } catch (ParseException e) {
			// TODO Auto-generated catch block
			date_stop = null;
			e.printStackTrace();
		} // Convert string  Start Date to Date 
		
		
		if (date_start != null && date_stop != null) {
			myCalldetail.Duration = (date_stop.getTime() - date_start.getTime())/1000; // Calculate the duration in seconds
		    myCalldetail.Rate = String.format("%.2f",Rating.calculate(myCalldetail.Promotion,myCalldetail.Duration)); //Calculate the rate with input duration
		}
		
		
		
		
		//date_stop  = formatter.parse(tempStrDate); // Convert string  Start Date to Date 
		return myCalldetail;
		
	}
	
	public String getMsisdn() {
		return this.Msisdn;
		
	}

	public Long getDuration() {
		return this.Duration;
		
	}
	
	public String getPromotion() {
		return this.Promotion;
		
	}
	
	public String getDatestart() {
		return this.DateStart;
		
	}
	
	public String getTimestop() {
		return this.TimeStop;
		
	}

	public String getTimestart() {
		return this.TimeStart;
	}

	public String getRate() {
		return this.Rate;
	}

}