package com.google.code.rfc3263;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sip.PeerUnavailableException;
import javax.sip.SipFactory;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Integration Test for sip.edu Members
 */
@RunWith(Parameterized.class)
public class SipEduIT {
	private AddressFactory addressFactory;
	private Locator locator;
	private final String domain;
	
	@Parameters
	public static List<? extends Object> getParameters() {
		List<String[]> parameters = new ArrayList<String[]>();
		
		parameters.add(new String[] { "colostate.edu" });
		parameters.add(new String[] { "columbia.edu" });
		parameters.add(new String[] { "indiana.edu" });
		parameters.add(new String[] { "harvard.edu" });
		parameters.add(new String[] { "uni-mainz.de" });
		parameters.add(new String[] { "mit.edu" });
		parameters.add(new String[] { "noaa.gov" });
		parameters.add(new String[] { "sfu.ca" });
		parameters.add(new String[] { "ethz.ch" });
		parameters.add(new String[] { "uc3m.es" });
		parameters.add(new String[] { "uaf.edu" });
		parameters.add(new String[] { "ucla.edu" });
		parameters.add(new String[] { "ucsd.edu" });
		parameters.add(new String[] { "hawaii.edu" });
		parameters.add(new String[] { "upenn.edu" });
		parameters.add(new String[] { "valencia.edu" });
		parameters.add(new String[] { "whoi.edu" });
		parameters.add(new String[] { "yale.edu" });
		parameters.add(new String[] { "inria.fr" });
		parameters.add(new String[] { "iptel.org" });
		
		return parameters;
	}
	
	@BeforeClass
	public static void configureLogging() {
		BasicConfigurator.configure();
	}
	
	@Before
	public void setUp() throws PeerUnavailableException {
		addressFactory = SipFactory.getInstance().createAddressFactory();
		locator = new Locator(Arrays.asList("TCP", "UDP", "SCTP", "TLS", "TLS-SCTP"));
	}
	
	public SipEduIT(String domain) {
		this.domain = domain;
	}
	
	@Test
	public void testSimple() throws ParseException, IOException {
		SipURI uri = addressFactory.createSipURI(null, domain);
		locator.locate(uri);
	}
}
