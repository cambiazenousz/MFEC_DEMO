package com.mfec.demo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CallDetailController { //Rest Service Interface
	
	
	 @RequestMapping(value = "/Checkrate/{msisdn}", method = RequestMethod.GET)
	 public ArrayList<CallDetail> Checkrate(@PathVariable("msisdn") String msisdn)
	 {
	    ArrayList<CallDetail> filter_msisdn;
	    filter_msisdn=DemoApplication.findCallDetail(msisdn);
	    return (filter_msisdn);
	 }
	
	 @RequestMapping(value = "/Checkrate/", method = RequestMethod.GET)
	 public ArrayList<CallDetail> CheckrateAll(){
		 ArrayList<CallDetail> filter_msisdn;
		 filter_msisdn=DemoApplication.findCallDetail("ALL");
		 return (filter_msisdn);
	 }
	 
}