package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> 
{
	private double expectedLoadPercentage;
	
	public CurrentLoadPercentageMatcher(double expectedLoadPercentage)
	{
		this.expectedLoadPercentage = expectedLoadPercentage;
	}

	public void describeTo(Description description) 
	{
		description.appendText("a server with load percentage of").appendValue(expectedLoadPercentage);
	}
	
	@Override
	public void describeMismatchSafely(Server item, Description description)
	{
		description.appendText("a server with load percentage of").appendValue(item);
	}

	@Override
	protected boolean matchesSafely(Server item) 
	{
		return item.getCurrentLoadPercentage() == expectedLoadPercentage || Math.abs(item.getCurrentLoadPercentage() - expectedLoadPercentage) < 0.01d;
	}
}
