package org.zerock.sample;

import org.springframework.stereotype.Component;

@Component
public class Restaurant {
	
	private Chef chef = new Chef();
	
	void sample() {
		chef.eat();
	}

}
