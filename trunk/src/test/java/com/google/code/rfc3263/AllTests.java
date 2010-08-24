package com.google.code.rfc3263;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import javax.sip.SipFactory;
import javax.sip.address.AddressFactory;
import javax.sip.address.Hop;
import javax.sip.address.SipURI;

import org.junit.Test;

public class AllTests {
	@Test
	public void testAll() throws Exception {
		SipFactory factory = SipFactory.getInstance();
		AddressFactory addressFactory = factory.createAddressFactory();
		
		Set<SipURI> uris = new HashSet<SipURI>();
		
		for (String host : Arrays.asList("example.org", "127.0.0.1", "[::1]")) {
			for (int port : Arrays.asList(-1, 1234)) {
				for (boolean secure : Arrays.asList(true, false)) {
					for (String transport : Arrays.asList("udp", "tcp", "tls", null)) {
						for (String maddr : Arrays.asList("example.net", "127.0.0.2", "[::2]", null)) {
							SipURI uri = addressFactory.createSipURI(null, host);
							if (transport != null) {
								uri.setTransportParam(transport);
							} else {
								uri.removeParameter("transport");
							}
							uri.setSecure(secure);
							uri.setPort(port);
							if (maddr != null) {
								uri.setMAddrParam(maddr);
							} else {
								uri.removeParameter("maddr");
							}
							uris.add(uri);
						}
					}
				}
			}
		}
		// The uris set now contains all the possible combinations of
		// URIs that we will route.
		for (SipURI uri : uris) {
			Locator locator = new Locator(Arrays.asList("TCP", "UDP"));
			Queue<Hop> hops = locator.locate(uri);
			System.out.println(uri);
			System.out.println(hops);
		}
	}
}