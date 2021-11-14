package br.com.websocket.client.entry.message;

public class ResponseMessage {
	
	private String from;
	private String message;
	
	public ResponseMessage() {
	}
	
	public ResponseMessage(String from, String message) {
		this.from = from;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getFrom() {
		return from;
	}
	
	public static ResponseMessage getSampleMessage() {
		return new ResponseMessage("client", "Response");
	}
}
