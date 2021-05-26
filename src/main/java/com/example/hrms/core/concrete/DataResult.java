package com.example.hrms.core.concrete;

public class DataResult<T>  extends Result{
	
	private T data;
	public DataResult(T data,boolean success,String message) {
		super(success,message); // super base sınıfın constructorlarını çalıştırmaya yarıyor. 
		this.data=data;
	}
	public DataResult(T data,boolean success) {
		super(success); // message yazmak istemiyorsan diye bir daha constroctor yazdık. 
		this.data=data;
	}
	
	public T getData() {
		return this.data;
	}
}
