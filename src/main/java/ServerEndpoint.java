import javax.websocket.*;
import java.io.IOException;

@javax.websocket.server.ServerEndpoint("/macaddress")
public class ServerEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText("connected");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            MacAddressUtil macAddressUtil = new MacAddressUtil();
            session.getBasicRemote().sendText(macAddressUtil.checkMacAddress(message).toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable e) { e.printStackTrace(); }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Closed");
    }
}
