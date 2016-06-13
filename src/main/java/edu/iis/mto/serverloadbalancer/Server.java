package edu.iis.mto.serverloadbalancer;

public class Server 
{
	private double currentLoadPercentage;
	
	public Server(int capacity)
	{
		
	}
	
	public double getCurrentLoadPercentage()
	{
		return currentLoadPercentage;
	}

	public void setCurrentLoadPercentage(double liczba)
	{
		this.currentLoadPercentage = liczba;
	}
	
	public boolean contains(Vm theVm) 
	{
		return true;
	}
}
