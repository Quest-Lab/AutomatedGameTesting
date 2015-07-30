package Interface;

import java.io.FileNotFoundException;

import de.ksquared.test.system.keyboard.KeyboardHookTest;

public class Scenario extends Thread {

	final KeyboardHookTest k = new KeyboardHookTest();
	public void run(){
		k.start();
		try {
			k.main();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
