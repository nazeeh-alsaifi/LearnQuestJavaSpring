package com.student;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FirstAscentDAO {
	private static Map<Long, String> ascent = new HashMap<Long, String>();
	{
		ascent.put(1L, "1950");
		ascent.put(2L, "1936");
		ascent.put(3L, "1958");
		   
	}
	public String get(Long key){
		return ascent.get(key);
	}
}
