package br.com.websocket.server.io;

public class Message {
	
	private String from;
	private String message;
	
	public Message() {
	}
	
	public Message(String from, String message) {
		this.from = from;
		this.message = message;}
	
	public String getMessage() {
		return message;
	}
	
	public String getFrom() {
		return from;
	}
}
