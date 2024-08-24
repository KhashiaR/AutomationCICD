package ABCAcademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class N3_Retry implements IRetryAnalyzer{

	
	int count = 0;
	int mzxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<mzxTry) {
			count++;
			return true;
		}
		
		return false;
	}

	
}
