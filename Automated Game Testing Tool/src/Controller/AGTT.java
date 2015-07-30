package Controller;

import java.io.IOException;
import java.util.ArrayList;

import de.ksquared.test.system.keyboard.KeyboardHookTest;
import Driver.RunTestCases;
import Driver.TestCase;
import Driver.TestCaseGeneration;

public class AGTT {

	TestCaseGeneration testCasegeneration;
	RunTestCases runtestcase;
	KeyboardHookTest keyboardTest ;
	TestCase testCase;
	public AGTT(){
		testCasegeneration = new TestCaseGeneration();
		runtestcase = new RunTestCases();
		keyboardTest = new KeyboardHookTest();
		testCase = new TestCase();
	}
	public void generateTestCases(){
		testCasegeneration.generateTestCases();
	}
	public ArrayList<String> getAllTestCases(){
		return testCasegeneration.getAllTestCases();
	}
	public void SetTestCases(ArrayList<String> Cases){
		runtestcase.SetTestcases(Cases);
	}
	public void prepareTestcases( int min){
		runtestcase.PrepareTestCase(min);
	}
	public ArrayList<String> KeysConversion() throws NumberFormatException, IOException
	{
		return keyboardTest.ConvertKeys();
	}
	public ArrayList<String> GetTransitions()
	{
		return testCasegeneration.getTransitions();
	}
	public void getReport(){
		 runtestcase.generateReport();
		
	}
}
