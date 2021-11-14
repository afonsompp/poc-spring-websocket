package br.com.websocket.client;

import br.com.websocket.client.entry.message.ResponseMessage;
import br.com.websocket.client.entry.message.WebSocketSessionHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {
	
	private final WebSocketSessionHandler handler;
	private final Executor executor;
	
	public ClientApplication(WebSocketSessionHandler handler, Executor executor) {
		this.handler = handler;
		this.executor = executor;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
	
	@Override public void run(String... args) throws Exception {
		WebSocketClient client = new StandardWebSocketClient();
		
		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		
		ListenableFuture<StompSession> sessionFuture = stompClient.connect("ws://localhost:8080/chat", handler);
		StompSession session = sessionFuture.get();
		
		CompletableFuture.runAsync(() -> {
			while (true) {
				session.send("/app/chat", ResponseMessage.getSampleMessage());
			}
		}, executor);
		
	}
}
