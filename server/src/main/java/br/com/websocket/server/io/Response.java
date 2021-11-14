package br.com.websocket.server.io;

public class Response {
	
	private String name;
	private String message;
	
	public Response() {
	}
	
	public Response(String name, String message) {
		this.name = name;
		this.message = message;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMessage() {
		return message;
	}
}
