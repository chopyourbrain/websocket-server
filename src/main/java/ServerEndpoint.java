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
            session.getBasicRemote().sendText(MacAddressUtil.checkMACAddress(message).toString());
        } catch (Exception ex) {
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
