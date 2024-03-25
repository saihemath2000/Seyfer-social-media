package com.seyfer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/hello")	
  public String homecontrollerhandler() {
	  return "Hello seyfer";
  }
}
