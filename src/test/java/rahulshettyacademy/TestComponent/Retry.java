package rahulshettyacademy.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		int count=0;
		int maxretry=1;
		
		if(count<maxretry)
		{
			++count;
			return true;
		}
		
		return false;
	}

}
