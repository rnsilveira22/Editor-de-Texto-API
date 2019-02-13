package com.digitro.dao;

public class DaoException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException(String msg, Throwable e) {		
		super(msg,e);
		e.printStackTrace();
	}
}
