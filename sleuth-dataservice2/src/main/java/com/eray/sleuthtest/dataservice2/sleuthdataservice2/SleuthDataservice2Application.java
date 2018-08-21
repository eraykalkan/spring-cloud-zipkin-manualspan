package com.eray.sleuthtest.dataservice2.sleuthdataservice2;

import brave.Span;
import brave.SpanCustomizer;
import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;

@SpringBootApplication
@RestController
public class SleuthDataservice2Application {

	public static void main(String[] args) {
		SpringApplication.run(SleuthDataservice2Application.class, args);
	}

	@Autowired
	private Tracer tracer;

	@Autowired
	private SpanCustomizer spanCustomizer;

	@RequestMapping(value="/customer/{cid}/vehicledetails", method= RequestMethod.GET)
	public @ResponseBody
	String getCustomerVehicleDetails(@PathVariable Integer cid) throws InterruptedException{

		String result;

		Span s=this.tracer.nextSpan().name("lookupvehicle").start();


		try {

			//bu altta yazanlar, ana span'e ekliyor. kendi custom spanimize eklemek icin en alttaki satir.
			/*this.spanCustomizer.tag("customerid",cid.toString());
			this.tracer.currentSpan().tag("customeridTracer",cid.toString());*/
			s.tag("customerid",cid.toString());
			s.annotate("Database Query Started"); // bu da logEvent yerine gecen method

			Thread.sleep(500);
			Hashtable<Integer, String> vehicles = new Hashtable<Integer, String>();
			vehicles.put(100, "Lincoln Continental; Plate SNUG30");
			vehicles.put(101, "Chevrolet Camaro; Plate R7TYR43");
			vehicles.put(102, "Volkswagen Beetle; Plate 6CVI3E2");

			result = vehicles.get(cid);

			s.annotate("Database query completed");
		}finally {
			s.finish();
		}


		return result;
	}
}
