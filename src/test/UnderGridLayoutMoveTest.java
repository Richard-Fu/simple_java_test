//2017.12.3
//测试图片在GridLayout里面能否移动    感觉不行   还是画吧   模仿五子棋
package test;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UnderGridLayoutMoveTest{

	//GUI
	JFrame f = new JFrame("测试移动");
	JPanel p = new JPanel();
	//按钮
	JButton up = new JButton("up");
	JButton down = new JButton("down");
	JButton left = new JButton("left");
	JButton right = new JButton("right");
	//GridLayout
	GridLayout layout = new GridLayout(5,5,50,50);
	//坐标值
	int x;
	int y;
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
