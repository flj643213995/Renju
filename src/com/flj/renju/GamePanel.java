package com.flj.renju;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 面板
 * 
 * @author Administrator
 *
 */
public class GamePanel extends JPanel {

	// 存放棋子的集合
	private static ArrayList<Point> pointList;

	// 将棋子按照黑色1白色-1，，没有为0放进二维数组
	static int[][] basket = new int[19][19];

	/**
	 * 获得存放棋子的集合
	 * 
	 * @return
	 */
	public ArrayList<Point> getPointList() {
		return pointList;
	}

	// 鼠标的监听器
	class Listener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);

			// 点击一次将点存入棋子落点的集合pointList中
			pointList.add(new Point(e.getX(), e.getY()));

			repaint();
		}
	}

	Image image;

	public GamePanel() {
		pointList = new ArrayList<>();
		// 棋盘画布
		URL path = GamePanel.class.getResource("/res/6.png");
		image = new ImageIcon(path).getImage();
		addMouseListener(new Listener());

		// isWin();
	}

	/**
	 * 画棋盘的方法
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		drawMyLine(g);
		drawTianyuan(g);
		drawQiZi(g);

	}

	/**
	 * 画落子,并将棋子按照颜色不同存入basket中
	 * 
	 * @param g
	 */
	private void drawQiZi(Graphics g) {
		for (int i = 0; i < pointList.size(); i++) {

			int a;
			int b;
			int x;
			int y;
			if ((pointList.get(i).x - 20) % 30 >= 15) {
				a = ((pointList.get(i).x - 20) / 30 + 1) * 30 + 20;
				x = (pointList.get(i).x - 20) / 30 + 1;
			} else {
				a = ((pointList.get(i).x - 20) / 30) * 30 + 20;
				x = (pointList.get(i).x - 20) / 30;
			}
			if ((pointList.get(i).y - 20) % 30 >= 15) {
				b = ((pointList.get(i).y - 20) / 30 + 1) * 30 + 20;
				y = (pointList.get(i).y - 20) / 30 + 1;
			} else {
				b = ((pointList.get(i).y - 20) / 30) * 30 + 20;
				y = (pointList.get(i).y - 20) / 30;
			}

			if (i % 2 == 0) {
				g.setColor(Color.BLACK);
				g.fillOval(a - 13, b - 13, 26, 26);
				basket[x][y] = 1;

			} else {
				g.setColor(Color.WHITE);
				g.fillOval(a - 13, b - 13, 26, 26);
				basket[x][y] = -1;

			}

		}
	}

	/**
	 * 画棋盘线
	 * 
	 * @param g
	 */
	private void drawMyLine(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 20; i <= 560; i += 30) {
			g.drawLine(20, i, 560, i);
		}

		g.setColor(Color.BLACK);
		for (int i = 20; i <= 560; i += 30) {
			g.drawLine(i, 20, i, 560);
		}
	}

	/**
	 * 画天元
	 * 
	 * @param g
	 */
	private void drawTianyuan(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillOval(286, 286, 8, 8);

		g.setColor(Color.BLACK);
		g.fillOval(166, 166, 8, 8);

		g.setColor(Color.BLACK);
		g.fillOval(406, 166, 8, 8);

		g.setColor(Color.BLACK);
		g.fillOval(166, 406, 8, 8);

		g.setColor(Color.BLACK);
		g.fillOval(406, 406, 8, 8);
	}

	/**
	 * 判断胜负的方法
	 */
	public static void isWin(){
		boolean flag = false;
		while(!flag){
			
			while(pointList.size() != 0){
				
				//current 为最新加入到pointList中的点
				Point current = pointList.get(pointList.size()-1);
				
				//cx、cy对应basket中的x、y
				int cx = (current.x-20)/30-1;
				int cy = (current.y-20)/30-1;
				
				//获得当前点的颜色
				int value = basket[cx][cy];
				
				//情况1
				if(cx<4 & cy<4){
					
					int count = 1;
					
					if(basket[cx+1][cy+1] == value){
						count++;
						cx++;
						cy++;
						while(count == 5){
							if(value == 1){
								System.out.println("黑子胜！");
								flag = true;							
							}else{
								System.out.println("白子胜！");
								flag = true;
							}
							
						}
						
					}
					
					
//					if((basket[cx+1][cy+1] == value) & 
//						(basket[cx+2][cy+2] == value) & 
//						(basket[cx+3][cy+3] == value) & 
//						(basket[cx+4][cy+4] == value)){
//						
//						if(value == 1){
//							System.out.println("黑子胜！");
//							flag = true;							
//						}else{
//							System.out.println("白子胜！");
//							flag = true;
//						}
//						
//					}
				}
//				
//				
//				//情况2
//				else if(cx>14 & cy<4){
//					
//				}
//				
//				//情况3
//				else if(cx>14 & cy>14){
//					
//				}
//				
//				//情况4
//				else if(cx<4 & cy>14){
//					
//				}
//				//情况5
//				else if((cx>=4&cx<=14) & (cy<4)){
//					
//				}
//				//情况6
//				else if((cx>14) & (cy>=4&cy<=14)){
//					
//				}
//				//情况7
//				else if((cx>=4&cx<=14) & (cy>14)){
//					
//				}
//				//情况8
//				else if((cx<4) & (cy>=4&cy<=14)){
//					
//				}
//				//情况9
//				else {
//					
//				}
			}
			
			
		}
	}

}