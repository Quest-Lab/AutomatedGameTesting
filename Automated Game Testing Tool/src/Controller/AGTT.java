
/*This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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
