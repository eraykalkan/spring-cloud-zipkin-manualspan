package com.eray.sleuthtest.dataservice1.sleuthdataservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.Random;

@SpringBootApplication
@RestController
public class SleuthDataservice1Application  {

	public static void main(String[] args) {
		SpringApplication.run(SleuthDataservice1Application.class, args);
	}

	@RequestMapping(value="/customer/{cid}/contactdetails", method= RequestMethod.GET)
	public @ResponseBody
	String getCustomerContactDetails(@PathVariable Integer cid) throws InterruptedException {

		//add arbitrary latency
		Random r = new Random();
		int multiplier = r.nextInt(5) * 1000;
		System.out.println("multiplier: " + multiplier);
		Thread.sleep(multiplier);


		Hashtable<Integer, String> customers = new Hashtable<Integer, String>();
		customers.put(100, "Beverly Goldberg");
		customers.put(101, "Dave Kim");
		customers.put(102, "Lainey Lewis");

		String result = customers.get(cid);

		return result;
	}
}
