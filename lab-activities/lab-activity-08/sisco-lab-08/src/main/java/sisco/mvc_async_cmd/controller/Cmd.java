package sisco.mvc_async_cmd.controller;

import sisco.mvc_async_cmd.model.Counter;

public interface Cmd {
	
	void execute(Counter c);
}
