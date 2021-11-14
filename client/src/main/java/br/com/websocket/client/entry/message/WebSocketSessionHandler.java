package br.com.websocket.client.entry.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Component
public class WebSocketSessionHandler implements StompSessionHandler {
	private static final Logger log = LoggerFactory.getLogger(WebSocketSessionHandler.class);
	
	static int count = 1;
	
	private StompSession stompSession;
	
	private Executor executor;
	
	public WebSocketSessionHandler(Executor executor) {this.executor = executor;}
	
	@Override public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		session.subscribe("/topic/messages", this);
		stompSession = session;
	}
	
	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
								Throwable exception) {
		log.info("Ocorreu um erro!");
	}
	
	@Override public void handleTransportError(StompSession session, Throwable exception) {
		log.info("Ocorreu um erro!");
	}
	
	@Override public Type getPayloadType(StompHeaders headers) {
		return EntryMessage.class;
	}
	
	@Override public void handleFrame(StompHeaders headers, Object payload) {
		
		CompletableFuture.runAsync(() -> {
			EntryMessage message = (EntryMessage) payload;
			
			log.info("message received number: {}", count++);
			
		}, executor);
		
	}
}
