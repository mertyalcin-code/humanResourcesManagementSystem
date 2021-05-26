package com.example.hrms.core.concrete;

public class ErrorDataResult<T> extends DataResult<T>{
	
	public ErrorDataResult(T data,String message) {
		super(data, false, message);		
	}
	public ErrorDataResult(T data) {//sadece data 
		super(data, false);		//hata da ne data yollayabilirsin. şablon felan olabilir. patlamamak için
	}
	public ErrorDataResult(String message) { //sadece mesaj
		super(null, false, message);		
	}
	public ErrorDataResult() { //sadece false
		super(null, false);		
	}
	
}
