package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class RestconfClientManagerTest {

    private static final String BASE_URL = "https://172.20.0.108/restconf/data/ietf-interfaces:interfaces";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "cisco123";

    @Test
    public void testRestconfOperations() throws Exception {
        RestconfClientManager clientManager = new RestconfClientManager(USERNAME, PASSWORD);

        // 1. GET config
        String getResponse = clientManager.getConfig(BASE_URL);
        assertNotNull(getResponse);
        System.out.println("RESTCONF GET Operation Performed Successfully");
        System.out.println("GET Response: " + getResponse);

        // Wait for 7 seconds
        Thread.sleep(7000);

        // 2. POST config
        String postRequestBody = "<interface xmlns=\"urn:ietf:params:xml:ns:yang:ietf-interfaces\">\n" +
                "    <name>Loopback11</name>\n" +
                "    <type xmlns:ianaift=\"urn:ietf:params:xml:ns:yang:iana-if-type\">ianaift:softwareLoopback</type>\n" +
                "    <enabled>true</enabled>\n" +
                "    <ipv4 xmlns=\"urn:ietf:params:xml:ns:yang:ietf-ip\">\n" +
                "        <address>\n" +
                "            <ip>12.0.0.5</ip>\n" +
                "            <netmask>255.0.0.0</netmask>\n" +
                "        </address>\n" +
                "    </ipv4>\n" +
                "</interface>";

        String postResponse = clientManager.postConfig(BASE_URL, postRequestBody);
        assertNotNull(postResponse);
        System.out.println("RESTCONF POST Operation Performed Successfully");
        System.out.println("POST Response: " + postResponse);

        // Wait for 7 seconds
        Thread.sleep(7000);

        // 3. PUT config
        String putUrl = BASE_URL + "/interface=Loopback11";
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

        String putResponse = clientManager.putConfig(putUrl, putRequestBody);
        assertNotNull(putResponse);
        System.out.println("RESTCONF PUT Operation Performed Successfully");
        System.out.println("PUT Response: " + putResponse);

        // DELETE operation is commented out as requested
        /*
        // Wait for 7 seconds
        Thread.sleep(7000);

        // 4. DELETE config
        String deleteUrl = BASE_URL + "/interface=Loopback11";
        String deleteResponse = clientManager.deleteConfig(deleteUrl);
        assertNotNull(deleteResponse);
        System.out.println("RESTCONF DELETE Operation Performed Successfully");
        System.out.println("DELETE Response: " + deleteResponse);
        */

        clientManager.close();
    }
}