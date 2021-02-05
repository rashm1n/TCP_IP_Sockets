import java.net.*;
import java.util.Enumeration;

public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces(); // list of NetworkInterface objects
            if (interfaceList == null) {
                System.out.println("no interfaces found");
            } else {
                while(interfaceList.hasMoreElements()) {
                    NetworkInterface ni = interfaceList.nextElement();
                    System.out.println("Interface " + ni.getName() + ":");
                    Enumeration<InetAddress> adrrs= ni.getInetAddresses();

                    if (!adrrs.hasMoreElements()) {
                        System.out.println("\t(No addresses for this interface)");
                    }

                    while (adrrs.hasMoreElements()) {
                        InetAddress address =  adrrs.nextElement();
                        System.out.print("\tAddress " + ((address instanceof Inet4Address ? "(v4)" : (address instanceof Inet6Address ? "(v6)" : "(?)"))));
                        System.out.println(": " + address.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }
    }
}
