package com.min.www.Exception;

import java.util.HashMap;
import java.util.Map;

public class MemberDuplicationException extends Exception {

	Map<String,Object> returnMap = new HashMap<>();
	
	public MemberDuplicationException(Map<String, Object> invalidCheckMap) {
		returnMap = invalidCheckMap;
		
	}
	
	public Map<String,Object> getReturnMap() {
		
		return returnMap;
	}
	

}
