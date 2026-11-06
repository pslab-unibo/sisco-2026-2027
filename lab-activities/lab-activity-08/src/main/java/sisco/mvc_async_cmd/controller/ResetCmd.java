package sisco.mvc_async_cmd.controller;

import sisco.mvc_async_cmd.model.Counter;

public class ResetCmd implements Cmd {

	@Override
	public void execute(Counter c) {
		c.reset();
	}

}
