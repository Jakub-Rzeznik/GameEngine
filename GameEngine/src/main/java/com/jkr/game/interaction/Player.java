package com.jkr.game.interaction;

public interface Player<S extends Comparable<S>, T>{

	public  S getDiscriminator();
	
	public Response<T> askForResponse(String ask);
	
	public void showMessage(String msg);
	
	public interface Response<T> {
		public T getCargo();
	}
}
