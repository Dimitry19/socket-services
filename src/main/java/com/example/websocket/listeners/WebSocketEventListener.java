package com.example.websocket.listeners;


import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component("listener")
public class WebSocketEventListener {

	private Set<String> sessionIds= new HashSet<>();

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		StompHeaderAccessor stompAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String sessionId = stompAccessor.getSessionId();
		//sessionIds.clear();
		sessionIds.add(sessionId);

		@SuppressWarnings("rawtypes")
		GenericMessage connectHeader = (GenericMessage) stompAccessor.getHeader(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER);
		// to the server
		@SuppressWarnings("unchecked")
		Map<String, List<String>> nativeHeaders = (Map<String, List<String>>) connectHeader.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
		if (nativeHeaders != null) {
			if(nativeHeaders.get("user")!=null){
				String user = nativeHeaders.get("user").get(0);


			}
			//TODO
		}
	}
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor stompAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String sessionId = stompAccessor.getSessionId();
		sessionIds.remove(sessionId);

	}

	public Set<String> getSessionIds() {
		return sessionIds;
	}
}
