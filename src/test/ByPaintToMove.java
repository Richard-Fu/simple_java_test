package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ByPaintToMove{

	JFrame f = new JFrame("move");
	MoveTest m = new MoveTest();
	//坐标
	int x=0;
	int y=0;
	int l_x;
	int l_y;
	//位移量
	int x_move = 27;
	int y_move = 27;
	//File file = new File("icon/icon.png");
	BufferedImage icon;// = ImageIO.read(file);
	public void init() throws Exception
	{
		icon = ImageIO.read(new File("icon/icon.png"));
		ImageIcon i = new ImageIcon("src/jingame/icon/5.gif");
		JLabel l = new JLabel(i);
		JButton b=new JButton("asdasd");
		//test设位置
		l.setBounds(30, 45, 40, 40);
		b.setBounds(30, 50, 20, 20);
		f.setLayout(null);
		f.add(l);
		f.add(b);
		//f.add(m);
		f.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_UP)
				{
					l_y=y;l_x=x;
					if(y>=1)
					y--;
					System.out.println("x: "+x+" y: "+y
							+" l_x: "+l_x+" l_y :"+l_y);
					m.repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					l_y=y;l_x=x;
					y++;
					System.out.println("x: "+x+" y: "+y
							+" l_x: "+l_x+" l_y :"+l_y);
					m.repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT)
				{
					l_x=x;l_y=y;
					if(x>=1)
					x--;
					System.out.println("x: "+x+" y: "+y
							+" l_x: "+l_x+" l_y :"+l_y);
					m.repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					l_x=x;l_y=y;
					x++;
					System.out.println("x: "+x+" y: "+y
							+" l_x: "+l_x+" l_y :"+l_y);
					m.repaint();
				}
			}
		});
		f.setPreferredSize(new Dimension(250,250));
		f.pack();
		f.setVisible(true);
		
	}
	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		new ByPaintToMove().init();
	}

	class MoveTest extends JPanel
	{
		public void paint(Graphics g)
		{
			//先清空   如果是魔塔那怎么清空  还是怪物那些都是首先就画好的?
			//还是把上一个坐标画成背景？
			//通过目前x y 值 和上一次x y 值 比较就知道按了哪个键，所以就重画哪个
			g.setColor(new Color(255,0,0));
			g.fillRect(l_x*x_move,l_y*x_move,x_move,x_move);
			//clearRect和fillRect其实原理一样  fillRect效果更好 能通过setColor指定颜色
			//而clearRect好像不能修改颜色.
			g.drawImage(icon, x*x_move, y*x_move, null);
			//感觉这样很重   如果是移动图片呢？
			//
		}
	}
}
