package com.mfec.demo;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Rating {
	
	
	private static final Logger logger = LoggerFactory.getLogger(Rating.class);
	
	public static Double calculate(String plan,Long duration) 
	{
		Double initprice=0.00;
		Double price=0.00;
		Double totalprice=0.00;
		Double durationinDouble=0.00;
		
		
		
		if (plan.equals("P1")) {
			
			if (duration >= 60) { 
				initprice = 3.00; // First step charge 3 bath to minute
				durationinDouble = (double)duration - 60;
				price =  durationinDouble * (1/60.00); // Second step charge 1 bath to minute
				totalprice = initprice + price;
				
			}
			else
			{
				if (duration > 0) {
					 totalprice = duration * (3/60.00);  // If the duration is less than one minute
				}
				else
				    totalprice = 0.00;
			}
		}
		
		else {
		   totalprice = -1.00; // Can't find the Promotion plan
		}
		
		logger.info("Duration "+duration.toString()+" Pricing "+totalprice.toString()+" Round up Price "+String.format("%.2f", totalprice));
		
		
		return totalprice;
}
}     
	
	