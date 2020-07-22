package io.shubham.resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.hash.Hashing;

@RestController
public class UrlShortenRestController {
	
	@Autowired
	private RedisTemplate<String,Url> redisTemplate;
	
	@RequestMapping(value="/shortenurl",method=RequestMethod.POST)
	public ResponseEntity<Object> getShortenUrl(@RequestBody Url shortenUrl) throws MalformedURLException
	{
		String id = Hashing.murmur3_32().hashString(shortenUrl.getFull_url(), Charset.defaultCharset()).toString();
		setShortUrl(id,shortenUrl);
		return new ResponseEntity<Object>(shortenUrl,HttpStatus.OK);
	}
	
	private void setShortUrl(String id, Url shortenUrl) throws MalformedURLException {
		shortenUrl.setShort_url("http://localhost:8080/s/"+id);
		shortenUrl.setCreated(LocalDateTime.now());
		redisTemplate.opsForValue().set(id, shortenUrl, 240,TimeUnit.HOURS);
	}
	@RequestMapping(value="/s/{id}",method=RequestMethod.GET)
	public void getFullUrl(HttpServletResponse response,@PathVariable("id") String id)throws IOException
	{
		response.sendRedirect(redisTemplate.opsForValue().get(id).getFull_url());
	}
}
	
