package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServerWithNoVms_serverStaysEmpty()
	{
		Server theServer = a(sever().withCapacity(1));
		
		balance(aListOfServersWith(theServer), anEmptyListOfVms());
		
		asserThat(theServer, hasLoadPercentageOf(0.0d));
	}

}
