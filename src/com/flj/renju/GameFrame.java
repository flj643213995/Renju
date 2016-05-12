package com.flj.renju;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * 界面
 * @author Administrator
 *
 */
public class GameFrame {

	private JFrame frame;

	public GameFrame() {
		
		frame = new JFrame("樊灵洁的五子棋");
		
		frame.setSize(600, 600);

		frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		
		frame.getContentPane().add(new GamePanel(), BorderLayout.CENTER);
		
		
		
		frame.setVisible(true);
		
	}

}
