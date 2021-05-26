package com.example.hrms.core.concrete;

public class SuccessDataResult<T> extends DataResult<T>{

	public SuccessDataResult(T data,String message) {

		super(data, true, message);
	}
	public SuccessDataResult(T data) {//sadece data 
		super(data, true);		
	}
	public SuccessDataResult(String message) { //sadece mesaj
		super(null, true, message);		
	}
	public SuccessDataResult() { //sadece true
		super(null, true);		
	}
	
}
