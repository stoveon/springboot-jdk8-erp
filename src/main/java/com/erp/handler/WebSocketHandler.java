package com.erp.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    /**
     * <pre>
     *     소켓 세션 id를 저장해 두기 위한 맵 형식의 공간
     * </pre>
     */
    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();

    /**
     * <pre>
     *     사용자가 웹소켓 서버에 접속하게 되면 동작하는 메소드
     *     - 이때 WebSocketSession 값이 생성
     *     - session을 CLIENTS 변수에 put (키 값은 세션의 고유값)
     * </pre>
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CLIENTS.put(session.getId(), session);
    }

    /**
     * <pre>
     *     웹소캣 서버접속이 끝났을 때 동작하는 메소드
     *     - 세션 id 제거
     * </pre>
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        CLIENTS.remove(session.getId());
    }

    /**
     * <pre>
     *     사용자의 메시지를 받게되면 동작하는 메소드
     *     - 본인 이외의 사용자에게 메세지를 보낼 수 있음.
     * </pre>
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String id = session.getId();  //메시지를 보낸 아이디
        CLIENTS.entrySet().forEach(arg -> {
            if (!arg.getKey().equals(id)) {  //같은 아이디가 아니면 메시지를 전달합니다.
                try {
                    arg.getValue().sendMessage(message);
                } catch (IOException e) {
                    log.error("{} | {}", e, e.getMessage());
                }
            }
        });
    }

}
