package com.jkr.game.interaction;

public interface ResponseParser<T> {
	
	public T parse(String userResponse);

}
