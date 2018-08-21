package com.eray.sleuthtest.customerservice.sleuthcustomerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SleuthCustomerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthCustomerserviceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate webTemplate;

	@RequestMapping(value="/customer/{cid}", method= RequestMethod.GET)
	public @ResponseBody
	String getCustomer(@PathVariable Integer cid) {
        System.out.println("*** CID *** " + cid);
        ResponseEntity<String> contact = webTemplate.getForEntity("http://localhost:8081/fastpass/customer/"+cid+"/contactdetails", String.class);
        System.out.println("Contact: " + contact);
        ResponseEntity<String> vehicle = webTemplate.getForEntity("http://localhost:8082/fastpass/customer/"+cid+"/vehicledetails", String.class);
        System.out.println("Vehicle: " + vehicle);

		return contact.getBody() + " | " + vehicle.getBody();
        //return contact.getBody();

	}

}
