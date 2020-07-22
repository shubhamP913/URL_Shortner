package io.shubham.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShortenUrlController {
	
	@RequestMapping(value="/",method=RequestMethod.GET)  //Request will go to index.html File
	public String loadIndex()
	{
		return "index";
	}
}
