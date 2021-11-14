package br.com.websocket.server.io;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public Response greeting(Message message) {
		return new Response("Server", "Hello!");
	}
}
