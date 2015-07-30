package Interface;

import java.io.IOException;
import java.util.ArrayList;

import Controller.AGTT;
import Controller.RecievingThread;

public class Console {

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		AGTT obj = new AGTT();
		//RecievingThread r = new RecievingThread();
	//	r.sta r t();
		obj.generateTestCases();     
		ArrayList<String> cases = obj.getAllTestCases();
		obj.SetTestCases(cases);
		obj.prepareTestcases(31);
	}
	
}
 