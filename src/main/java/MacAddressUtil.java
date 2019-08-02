import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.Logger;

public class MacAddressUtil {
    private static Logger log = Logger.getLogger(MacAddressUtil.class.getName());
    public Boolean checkMacAddress(String inputMac) {
        boolean f = false;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            while(nis.hasMoreElements())
            {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                byte[] mac = ni.getHardwareAddress();
                StringBuilder stringBuilder = new StringBuilder();
                for(int i=0; i < mac.length; i++) {
                    stringBuilder.append(String.format("%02X%s", mac[i], (i<mac.length - 1) ? ":"  : ""));
                }
                if (stringBuilder.toString().equals(inputMac))
                    f = true;
            }
        } catch (Exception ex) {
            log.info(ex.toString());
        }
        return f;
    }
}
