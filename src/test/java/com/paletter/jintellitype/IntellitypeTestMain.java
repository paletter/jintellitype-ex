package com.paletter.jintellitype;

import javax.swing.JFrame;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitype64;

public class IntellitypeTestMain {

	public static JFrame window = new JFrame();
	
	public static void main(String[] args) {
		
		window.setBounds(100, 100, 600, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
		
		JIntellitype64.getInstance().registerHotKey(89, JIntellitype.MOD_ALT, (int)'P');
		JIntellitype64.getInstance().addHotKeyListener(new HotkeyListener() {
			
			@Override
			public void onHotKey(int arg0) {
				
				window.setVisible(true);
				
				System.out.println("HAHAHA");
				if(arg0 == 89) {
					System.out.println("Go");
				}
			}
		});
	}
}
