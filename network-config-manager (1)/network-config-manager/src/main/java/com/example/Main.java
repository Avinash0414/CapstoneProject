package com.example;
public class Main {

    public static void main(String[] args) {
        try {
            String url = "https://172.20.0.108/restconf/data/ietf-interfaces:interfaces/interfaces"; // Replace with actual URL
            String username = "admin"; // Replace with actual username
            String password = "cisco123"; // Replace with actual password

            RestconfClientManager restconfClientManager = new RestconfClientManager(username, password);

            // Example of a GET request
            String responseGet = restconfClientManager.getConfig(url);
            System.out.println("GET Response: " + responseGet);

           
            
            // Example of a POST request to create a new interface
            String loopbackRequestBody = "<interface xmlns=\"urn:ietf:params:xml:ns:yang:ietf-interfaces\"\n" +
                    "           xmlns:if=\"urn:ietf:params:xml:ns:yang:ietf-interfaces\">\n" +
                    "    <name>Loopback11</name>\n" +
                    "    <type xmlns:ianaift=\"urn:ietf:params:xml:ns:yang:iana-if-type\">ianaift:softwareLoopback</type>\n" +
                    "    <enabled>true</enabled>\n" +
                    "    <ipv4 xmlns=\"urn:ietf:params:xml:ns:yang:ietf-ip\">\n" +
                    "        <address>\n" +
                    "            <ip>12.0.0.2</ip>\n" +
                    "            <netmask>255.0.0.0</netmask>\n" +
                    "        </address>\n" +
                    "    </ipv4>\n" +
                    "</interface>";

            String responsePost = restconfClientManager.postConfig(url, loopbackRequestBody);
            System.out.println("POST Response for Loopback11 creation: " + responsePost);
            
            String putUrl = url + "/interface=Loopback11";
            String putRequestBody = "<interface xmlns=\"urn:ietf:params:xml:ns:yang:ietf-interfaces\">\n" +
                    "    <name>Loopback11</name>\n" +
                    "    <type xmlns:ianaift=\"urn:ietf:params:xml:ns:yang:iana-if-type\">ianaift:softwareLoopback</type>\n" +
                    "    <enabled>true</enabled>\n" +
                    "    <ipv4 xmlns=\"urn:ietf:params:xml:ns:yang:ietf-ip\">\n" +
                    "        <address>\n" +
                    "            <ip>12.0.0.4</ip>\n" +
                    "            <netmask>255.0.0.0</netmask>\n" +
                    "        </address>\n" +
                    "    </ipv4>\n" +
                    "</interface>";

            String putResponse = restconfClientManager.putConfig(putUrl, putRequestBody);
            System.out.println("PUT Response for updating Loopback11: " + putResponse);

            // DELETE request to remove Loopback11
  /*          String deleteUrl = url + "/interface=Loopback11";
            String deleteResponse = restconfClientManager.deleteConfig(deleteUrl);
            System.out.println("DELETE Response for removing Loopback11: " + deleteResponse);*/

            restconfClientManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
