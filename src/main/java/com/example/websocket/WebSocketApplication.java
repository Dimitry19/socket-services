package com.example.websocket;

import com.example.websocket.listeners.WebSocketEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/*https://stackoverflow.com/questions/45357194/simple-convertandsendtouser-where-do-i-get-a-username*/

@SpringBootApplication
public class WebSocketApplication implements CommandLineRunner{
	private Map<String, Integer> progress = new HashMap<>();

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Resource(name="listener")
	WebSocketEventListener listener;

	public static void main(String[] args) {
		SpringApplication.run(WebSocketApplication.class, args);

	}

	/**
	 * Generate random numbers publish with WebSocket protocol each 3 seconds.
	 * @return a command line runner.
	 */
	@Bean
	public  CommandLineRunner websocketDemo() {
		return (args) -> {
			while (true) {
				try {
					Thread.sleep(3*1000); // Each 3 sec.
					progress.put("num1", randomWithRange(0, 100));
					progress.put("num2", randomWithRange(0, 100));




					listener.getSessionIds().forEach(s->{
						SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create();
						accessor.setHeader(SimpMessageHeaderAccessor.SESSION_ID_HEADER, s);
						accessor.setLeaveMutable(true);
						messagingTemplate.convertAndSendToUser(s,"/notification/item", "test"+randomWithRange(0, 100) + "sessionId=" + s,accessor.getMessageHeaders());
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	/**
	 * Get a random integer value in a min / max range.
	 * @param min min range value
	 * @param max max range value
	 * @return A random integer value
	 */
	private int randomWithRange(int min, int max)
	{
		int range = Math.abs(max - min) + 1;
		return (int)(Math.random() * range) + (min <= max ? min : max);
	}

	@Override
	public void run(String... args) throws Exception {
		websocketDemo();
	}
}
