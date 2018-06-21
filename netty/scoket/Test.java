package com.chow.scoket;

import java.net.URISyntaxException;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Test {
	
	static Socket socket;
	public static void main(String[] args) throws Exception {
	
	
		socket = IO.socket("http://localhost:9092");	
		socket.on(Socket.EVENT_CONNECT,new Emitter.Listener() {			
			public void call(Object... args) {
				// TODO Auto-generated method stub
				socket.emit("chatevent", "hi",new Ack() {
					
					public void call(Object... args) {
						// TODO Auto-generated method stub
						System.out.println(args[0]);
					}
				});
				
			}
		}).on("chatevent", new Emitter.Listener() {
		
			public void call(Object... args) {
				// TODO Auto-generated method stub
				System.out.println(args[0]);
				
			}
		});
		
     
		//socket.emit("chatevent", "hi");
		socket.connect();
      
		
	}

}
