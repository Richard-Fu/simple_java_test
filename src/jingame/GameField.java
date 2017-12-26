//2017.12.5
//这个类是  游戏区的父类  也就是每一张图都继承它
package jingame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.Transient;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameField extends JPanel{
	//游戏区分为18*16
	//一个格子50*50
	protected String floors;
	//明天再想吧
	public final static int WIDTH = 16;
	public final static int HEIGHT = 18;
	public final static int LENGTH = 50;
	//信息框界面大小
	public final static int TABLE_WIDTH = WIDTH*LENGTH;
	public final static int TABLE_HEIGHT = HEIGHT*LENGTH;
	/*//背景只要画一次
	private boolean isDraw = false;*/
	//背景图
	private static transient BufferedImage background;
	private static transient BufferedImage front;
	private static transient BufferedImage frontmove;
	private static transient BufferedImage left;
	private static transient BufferedImage right;
	private static transient BufferedImage backmove;
	private static transient BufferedImage back;
	private static transient BufferedImage a;
	private static transient BufferedImage b;
	private static transient BufferedImage c;
	/*//当前坐标
	private int x=480;
	private int y=0;
	//上一次移动坐标
	private int l_x;
	private int l_y;*/
	//改成int[4] 方便传值
	//xy[0]=x xy[1]=l_x xy[2]=y  xy[3]=l_y
	
	
	protected Thing[][] things = new Thing[WIDTH][HEIGHT];
	//存things的状态   便于存档   这些默认都是0  所以只要改变了就是1  就变成floor了
	protected int[][] flag = new int[WIDTH][HEIGHT];
	//存flag状态  因为flag是随things随时更新  这样就造成存档错误   所以要一个在按存档的时候才更新的数组
	protected int[][] f_flag = new int[WIDTH][HEIGHT];
	protected Hero man;
	//上下左右楼梯的位置
	protected int up_x;
	protected int up_y;
	protected int down_x;
	protected int down_y;
	protected int left_x;
	protected int left_y;
	protected int right_x;
	protected int right_y;
	
	//上下左右的楼层
	protected GameField up_f;
	protected GameField down_f;
	protected GameField left_f;
	protected GameField right_f;
	
	public void rule()
	{
		//写规则
		//man.setHp(Integer.toString(Integer.valueOf(man.getHp())+1));
		this.repaint();
	}
	public int getUp_x() {
		return up_x;
	}
	public void setUp_x(int up_x) {
		this.up_x = up_x;
	}
	public int getUp_y() {
		return up_y;
	}
	public void setUp_y(int up_y) {
		this.up_y = up_y;
	}
	public int getDown_x() {
		return down_x;
	}
	public void setDown_x(int down_x) {
		this.down_x = down_x;
	}
	public int getDown_y() {
		return down_y;
	}
	public void setDown_y(int down_y) {
		this.down_y = down_y;
	}
	public String getFloors() {
		return floors;
	}
	public void setFloors(String floors) {
		this.floors = floors;
	}
	public int getLeft_x() {
		return left_x;
	}
	public void setLeft_x(int left_x) {
		this.left_x = left_x;
	}
	public int getLeft_y() {
		return left_y;
	}
	public void setLeft_y(int left_y) {
		this.left_y = left_y;
	}
	public int getRight_x() {
		return right_x;
	}
	public void setRight_x(int right_x) {
		this.right_x = right_x;
	}
	public int getRight_y() {
		return right_y;
	}
	public void setRight_y(int right_y) {
		this.right_y = right_y;
	}
	public GameField getUp_f() {
		return up_f;
	}
	public void setUp_f(GameField up_f) {
		this.up_f = up_f;
	}
	public GameField getDown_f() {
		return down_f;
	}
	public void setDown_f(GameField down_f) {
		this.down_f = down_f;
	}
	public GameField getLeft_f() {
		return left_f;
	}
	public void setLeft_f(GameField left_f) {
		this.left_f = left_f;
	}
	public GameField getRight_f() {
		return right_f;
	}
	public void setRight_f(GameField right_f) {
		this.right_f = right_f;
	}
	public GameField(Hero man) throws IOException
	{
		this.man = man;
		//初始化  全是地板
		for(int i = 0; i<WIDTH; i++)
		{
			for(int j=0; j<HEIGHT; j++)
			{
				things[i][j] = new Floor();
			}
		}
		background = ImageIO.read(new File("src/jingame/icon/test.png"));
		front = ImageIO.read(new File("src/jingame/icon/front.gif"));
		frontmove = ImageIO.read(new File("src/jingame/icon/frontmove.gif"));
		back = ImageIO.read(new File("src/jingame/icon/back.gif"));
		left = ImageIO.read(new File("src/jingame/icon/left.gif"));
		right = ImageIO.read(new File("src/jingame/icon/right.gif"));
		backmove = ImageIO.read(new File("src/jingame/icon/backmove.gif"));
		a = ImageIO.read(new File("src/jingame/icon/1.png"));
		b = ImageIO.read(new File("src/jingame/icon/2.png"));
		c = ImageIO.read(new File("src/jingame/icon/3.png"));
		//设置该JPanel大小
		setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
	}
	//初始化楼层信息
	public void init() {}
	//重写JPanel的paint  画出当前地图背景
	public void paint(Graphics g)
	{
		/*System.out.println("ggx: "+x+" y: "+y
				+" l_x: "+l_x+" l_y :"+l_y);*/
		//super.paint(g);
		
		/*if(!isDraw)
		{*/
			//g.drawImage(background, 0, 0, null);
			/*System.out.println("ggx: "+x+" y: "+y
					+" l_x: "+l_x+" l_y :"+l_y);*/
			//g.drawImage(front, 0, 0, 50, 50,  null);
		/*	//isDraw = true;
			System.out.println("11");
		}*/
		
		//先清空   如果是魔塔那怎么清空  还是怪物那些都是首先就画好的?
		//还是把上一个坐标画成背景？
		//通过目前x y 值 和上一次x y 值 比较就知道按了哪个键，所以就重画哪个
		/*g.setColor(new Color(255,0,0));
		g.fillRect(l_x*LENGTH,l_y*LENGTH,LENGTH,LENGTH);
		//clearRect和fillRect其实原理一样  fillRect效果更好 能通过setColor指定颜色
		//而clearRect好像不能修改颜色.
		g.setColor(new Color(0,255,0));
		g.fillRect(x*LENGTH, y*LENGTH,LENGTH,LENGTH);*/
		//遍历每一个格子    再次更新属性及 position
		for(int i=0;i<WIDTH;i++)
		{
			for(int j=0;j<HEIGHT;j++)
				{
				//man 走到该位置  触发事件
				if(i==Game.position.getXy()[0]&&j==Game.position.getXy()[2])
					//把当前位置也传进去 当时候可以及时更新当前位置的信息
				things[i][j].doJob(man,i,j);
				//并画出该对象  对应的图片
				g.drawImage(things[i][j].getImage()
						, i*LENGTH, j*LENGTH, 50, 50, null);
				}
		}
		switch(Game.position.getXy()[0] - Game.position.getXy()[1]
				+(Game.position.getXy()[2]-Game.position.getXy()[3])*2)
		{
		//证明向右走了
		case 1:/*g.drawImage(a, position.getXy()[0]*LENGTH
				, position.getXy()[2]*LENGTH
				, 50, 50, null);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		g.drawImage(b, position.getXy()[0]*LENGTH
				, position.getXy()[2]*LENGTH
				, 50, 50, null);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		g.drawImage(c, position.getXy()[0]*LENGTH
				, position.getXy()[2]*LENGTH
				, 50, 50, null);*/
			g.drawImage(right, Game.position.getXy()[0]*LENGTH
					, Game.position.getXy()[2]*LENGTH
					, 50, 50, null);break;
		case 2://向下走
			//g.drawImage(frontmove, x*LENGTH, y*LENGTH, 50, 50,  null);
			/*try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}*/
			g.drawImage(front, Game.position.getXy()[0]*LENGTH
					, Game.position.getXy()[2]*LENGTH, 50, 50,  null);
			break;
		case -1://向左走
			g.drawImage(left, Game.position.getXy()[0]*LENGTH
					, Game.position.getXy()[2]*LENGTH, 50, 50, null);
			break;
		case -2://向上走
			//g.drawImage(backmove, x*LENGTH, y*LENGTH, 50, 50,  null);
			/*try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}*/
			g.drawImage(back, Game.position.getXy()[0]*LENGTH
					, Game.position.getXy()[2]*LENGTH, 50, 50,  null);
			break;
			//0  画正身
		//case 0 :g.drawImage(front, 0, 0, 50, 50,  null);
		default://默认在当前画正身
			g.drawImage(front, Game.position.getXy()[0]*LENGTH
					, Game.position.getXy()[2]*LENGTH, 50, 50,  null);
		break;
		}
	}
	
}
