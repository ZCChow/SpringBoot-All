package com.chow.scoket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class CharteventListener implements DataListener<String> {

    SocketIOServer server;

    public void setServer(SocketIOServer server) {
        this.server = server;
    }

    public void onData(SocketIOClient client, String data,
            AckRequest ackSender) throws Exception {
        // chatevent为 事件的名称， data为发送的内容
        this.server.getBroadcastOperations().sendEvent("chatevent", client,data);
        client.sendEvent("chatevent", "receive,welcome");
        if (ackSender.isAckRequested()) {
        	ackSender.sendAckData("-----------");
		}
        
        System.out.println(data);
    }
}