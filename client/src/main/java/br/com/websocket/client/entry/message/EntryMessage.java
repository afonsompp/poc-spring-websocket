package br.com.websocket.client.entry.message;

public class EntryMessage {
	
	private String from;
	private String message;
	
	public EntryMessage() {
	}
	
	public EntryMessage(String from, String message) {
		this.from = from;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getFrom() {
		return from;
	}
}
