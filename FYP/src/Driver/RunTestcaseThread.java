package Driver;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import de.ksquared.system.keyboard.KeyEvent;

public class RunTestcaseThread extends Thread {

	ArrayList<String> testcases = new ArrayList<>();
	PrintWriter writer;
	Robot runer = null;
	Boolean Enemey = false;
	int time;
	public RunTestcaseThread(ArrayList<String> cases, int min){
		this.testcases = cases;
		this.time = min;
	}
	public void setEnemeyFlag(Boolean flag){
		this.Enemey = flag;
	}
	public ArrayList<String> PrepareTestCase(int num){
		
			String[] tokens = testcases.get(num).split(",");
			ArrayList<String> readyTestCase = new ArrayList<>();
			int index = 0;
			for (String string : tokens) {
				if(index%2 == 1){
					readyTestCase.add(string);
				}
				index++;
			}
			return readyTestCase;
	}
	public void RunTest(ArrayList<String> testCase, int number){
		System.out.println(testCase);
		String NextState = "";
		int index = 0;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true)
		{
		/*if(index == testcase.size())
		{
			try {
				Thread.sleep(3000);
				int key = KeyEvent.VK_E;
				runer.setAutoDelay(500);
				runer.keyPress(key);
				runer.keyRelease(key);
				break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}*/
			int key = KeyEvent.VK_RIGHT;						
			if(NextState.equals(" jump") ){
				runer.setAutoDelay(500);
				runer.keyPress(key);				
				runer.keyRelease(key);
				if(Enemey == true)
				{
					key = KeyEvent.VK_SPACE;
					runer.setAutoDelay(500);
					runer.keyPress(key);
					runer.keyRelease(key);
					
					break;
				}
			}
			else if(NextState.equals(" Get Hit")){
				runer.setAutoDelay(500);
				runer.keyPress(key);				
				runer.keyRelease(key);
				if(Enemey == true)
				{
					key = KeyEvent.VK_DOWN;
					runer.setAutoDelay(500);
					runer.keyPress(key);
					runer.keyRelease(key);
					break;
				}
			}
			else{
				String trans = testCase.get(index);
				if(trans.equals(" runforward")){	key = KeyEvent.VK_RIGHT;	}
				if(trans.equals(" runbackward")){	key = KeyEvent.VK_LEFT;		}
				if((testCase.size()-1) > index )
				{
					NextState = testcases.get(index+1);
					runer.setAutoDelay(500);
					runer.keyPress(key);
					runer.keyRelease(key);
					if(Enemey == true)
					{
						int key1 = key;
						//runer.setAutoDelay(500);
						key = KeyEvent.VK_SPACE ;

						runer.keyPress(key);
						runer.delay(100);
						
						runer.keyPress(key1);
						runer.delay(500);
						runer.keyRelease(key1);
						runer.keyRelease(key);
						
					}
				}
			 	 else
			 	 {
			 		// NextState= "";
			 		 break;
			 	 }
				index++;
			}      
		}
		this.checkTestCase(testCase, number+1);
	}
	public String checkTestCase(ArrayList<String> test, int number){
		String result = "";
		BufferedReader reader = null;
		ArrayList<String> testcases = new ArrayList<>();
		try {
			reader= new BufferedReader(new FileReader("C:\\Users\\Osama Ehsan\\Documents\\Mario Side-Scroller1\\Mario Side-Scroller\\flags.txt"));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String line = null;
			
			writer.println("Test Case # "+number);
			while((line = reader.readLine())!= null)
			{
				for(String s : test){
					String[] tokens = line.split(",");
					if(tokens[0].equals(s)){
						if(tokens[1].equals("true")){
							writer.println( s + " transition Executed successfully !!");
						}
						else
						{
							writer.println( s + " transition not Executed successfully !!");
						}
					}
				}
			}
			
		}
		catch (Exception e) {		 
		}
		return result;
	}
	public void run(){
		try {
			runer = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter("Results.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		long start_time = System.currentTimeMillis();
		long end_time = start_time + time;
		int testCaseIndex = 0;
		while(System.currentTimeMillis() < end_time){
			System.out.println("time = "+ (end_time - System.currentTimeMillis()));
		//	System.out.println(testCaseIndex);
			ArrayList<String> testCase = this.PrepareTestCase(testCaseIndex);	
			this.RunTest(testCase, testCaseIndex);
			testCaseIndex++;
		}
		writer.close();
	}
}
