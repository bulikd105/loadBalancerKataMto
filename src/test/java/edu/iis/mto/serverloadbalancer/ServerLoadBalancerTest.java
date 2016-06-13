package edu.iis.mto.serverloadbalancer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest 
{
	@Test
	public void itCompiles() 
	{
		assertThat(true, equalTo(true));
	}
	
	@Test
	public void balancingServerWithNoVms_serverStaysEmpty()
	{
		Server theServer = a(server().withCapacity(1));
		
		balance(aListOfServersWith(theServer),anEmptyListOfVms());
		
		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}
	
	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm()
	{
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		
		balance(aListOfServersWith(theServer),aListOfVmsWith(theVm));
		
		assertThat(theServer, hasLoadPercentageOf(100.0d));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}
	


	private Matcher<? super Server> hasLoadPercentageOf(double expectedLoadPercentage) 
	{
		return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
	}
	
	private void balance(Server[] servers, Vm[] vms) 
	{
		new ServerLoadBalancer().balance(servers,vms);
	}
	
	private Vm[] anEmptyListOfVms() 
	{
		return new Vm[0];
	}
	
	private Vm[] aListOfVmsWith(Vm vm) 
	{
		return new Vm[]{vm};
	}

	private Server[] aListOfServersWith(Server server) 
	{
		return new Server[]{server};
	}
	
	private Server a(ServerBuilder builder) 
	{
		return builder.build();
	}

	private ServerBuilder server()
	{
		return new ServerBuilder();
	}
	
	private Vm a(VmBuilder builder)
	{
		return builder.build();
	}
	
	private VmBuilder vm()
	{
		return new VmBuilder();
	}
}
