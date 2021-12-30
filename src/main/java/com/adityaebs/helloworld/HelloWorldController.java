package com.adityaebs.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldDTO helloWorldBean() {
		return new HelloWorldDTO("Hello World");
	}
	
	///hello-world/path-variable/aditya
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldDTO helloWorldPathVariable(@PathVariable String name) {
		//throw new RuntimeException("Something went wrong");
		return new HelloWorldDTO(String.format("Hello World, %s", name));
	}
}
