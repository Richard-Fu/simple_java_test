//2017.12.5
//这个是整个游戏的主框架
package jingame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JFrame{

	//首先是设定这个大小
	private final int WIDTH = 1290;
	private final int HEIGHT = 960;
	/*//当前坐标
	private int x=0;
	private int y=0;
	//上一次移动坐标
	private int l_x=0;
	private int l_y=0;*/
	//位置信息类    所以都公用它  且都对它刷新
	public static Position position = new Position();
	//信息区
	private Info info;
	//地图区
	//拿个辅助数组来存当前是哪个GameField   1为当前层  0为不是当前层      换成xy[4]是多少就是几层
	//存全部的GameField
	public static ArrayList<GameField> fieldList = new ArrayList<>();
	//战斗场面
	public static FightPanel fight;
	public static boolean isFight = false;
	//战利品场面
	public static BootyPanel booty;
	public static boolean isBooty = false;
	//剧情画面
	public static StoryPanel story;
	public static boolean isStory = false;
	//存档读档面板
	/*public static Save save;
	public static Load load;*/
	//地图
	private GameField enter;
	private GameField zero;
	private GameField one;
	private GameField two;
	private GameField three;
	private GameField shao_down;
	//Hero
	private static Hero man;
	//Hero头像
	private static BufferedImage m_head;
	
	//读写到文件里面
	/*private ObjectOutputStream keep;
	private ObjectInputStream load;*/
	RandomAccessFile randomfile;
	private File file;
	//判断是否按了存档按钮  如果没按 就读取上次的对象
	//如果按了 更新
	//private boolean isChange = false;
	//存档的中间变量 存hero信息  和坐标
	private Hero h;
	private int[] temp = new int[5];
	//存档保存的楼层
	private int floor;
	public Game()
	{
		super("金庸群侠传");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		file = new File("src/jingame/file/load1.txt");
		ImageIcon icon=new ImageIcon("src/jingame/icon/title.png");
        this.setIconImage(icon.getImage());
		//创建一个随机任意读取文件的RandomAccessfile
		//并设置成可读写
		try {
			randomfile=new RandomAccessFile(file,"rw");
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		/*try {
			//获取对象输出流
			keep = new ObjectOutputStream(new FileOutputStream(file));
			load = new ObjectInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
	}
	public void init() throws IOException
	{
		//初始化英雄
		m_head = ImageIO.read(new File("src/jingame/icon/front.gif"));
		man = new Hero(1,50,15,2
				,0,0,0
				,0,0,m_head);
		
		fight = new FightPanel();
		booty = new BootyPanel();
		story = new StoryPanel();
		/*save = new Save();
		load = new Load();*/
		//默认不显示
		//fight.setVisible(false);
		info = new Info();
		enter = new Entrance(man);
		fieldList.add(enter);
		zero = new Field0(man);
		fieldList.add(zero);
		one = new Field1(man);
		fieldList.add(one);
		two = new Field2(man);
		fieldList.add(two);
		three = new Field3(man);
		fieldList.add(three);
		shao_down = new Field_shaolinshanxia(man);
		fieldList.add(shao_down);
		//最开始是第一层
		for(GameField f:fieldList)
		{
			f.init();
			//让其他层先不显示
			f.setVisible(false);
			addField(f);
		}
		//首先让one 显示    因为JPanel好像默认显示  如果不这么处理  可能造成一些显示错误  
 		/*one.setVisible(true);
		one.setVisible(false);
		one.setVisible(true);*/
		
		//首先显示首页  交代剧情   
		enter.setVisible(true);
		position.setF(enter);
		//坐标在xy[0] = 7; xy[2] = 17;
		position.getXy()[0] = 7;
		position.getXy()[1] = 7;
		position.getXy()[2] = 17;
		position.getXy()[3] = 17;
		//position.setFloor(0);
		/*one.setVisible(false);
		two.setVisible(true);*/
		//测试数量 ArrayList从0号位置开始存
		/*System.out.println(fieldList.get(0));
		System.out.println(fieldList.get(1));*/
		setLayout(null);
		//设置各组件的位置及大小
		/*enter.setBounds(480,0,800,900);
		zero.setBounds(480,0,800,900);
		one.setBounds(480,0,800,900);
		two.setBounds(480,0,800,900);
		three.setBounds(480,0,800,900);*/
		info.setBounds(0,0,480,900);
		fight.setBounds(480,0,0,0);
		booty.setBounds(480,315,0,0);
		story.setBounds(480,0,0,0);
		
		/*save.setBounds(420,280,760,410);
		load.setBounds(420,280,760,410);*/
		//把楼层加入JFrame
		/*add(enter);
		add(zero);
		add(one);
		add(two);
		add(three);*/
		//加info
		add(info);
		//加fight
		add(fight);
		add(booty);
		add(story);
		/*add(save);
		add(load);*/
		/*add(one, BorderLayout.EAST);
		add(info, BorderLayout.WEST);*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//让窗口中间显示？
		//setLocationRelativeTo(null);
		pack();
		setResizable(false);
		setVisible(true);
		/*new showFight().start();*/
		//定时更新  info的数据
		ActionListener taskPerformer = evt ->
        {
        	//更新
        	refreshInfoByHero();
        	//System.out.println(man);
        	//更新当前位置  也就是地图位置
    		info.getFloors().setText(position.getF().getFloors());
        	if(isFight)
        		{
        		//while(isFight)
        			fight.repaint();
        		}
        	if(isBooty)
        	{
        		booty.repaint();
        	}
        	if(isStory)
        	{
        		story.repaint();
        	}
        };
        Timer timer = new Timer(500,taskPerformer);
        timer.start();
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
			
				//只有在剧情中 才能按这些
				if(isStory)
				{
					if(e.getKeyCode()==KeyEvent.VK_Q)
				
				{
					StoryPanel.i++;
					//如果此时剧情结束  停止那个线程
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD1)
				{
					
					StoryPanel.ch = '1';
					//如果此时剧情结束  停止那个线程
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD2)
				{
					StoryPanel.ch = '2';
					//如果此时剧情结束  停止那个线程
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD3)
				{
					StoryPanel.ch = '3';
					//如果此时剧情结束  停止那个线程
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD4)
				{
					StoryPanel.ch = '4';
					//如果此时剧情结束  停止那个线程
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD5)
				{
					StoryPanel.ch = '5';
					//如果此时剧情结束  停止那个线程
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				}
				//只有不在战斗中  不在剧情中，不在商店中 ,才能移动
				//才能存档
				
				if(!isFight && !isStory)
				{
					if(e.getKeyCode()==KeyEvent.VK_UP)
				{
					position.getXy()[3]=position.getXy()[2];
					position.getXy()[1]=position.getXy()[0];
					if(position.getXy()[2]>=1)
						position.getXy()[2]--;
					System.out.println("x: "+position.getXy()[0]
							+" y: "+position.getXy()[2]
							+" l_x: "+position.getXy()[1]
							+" l_y :"+position.getXy()[3]);
					
					//遍历isField看哪个重画
					//先传更新好的值过去
					refreshField();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					position.getXy()[3]=position.getXy()[2];
					position.getXy()[1]=position.getXy()[0];
					if(position.getXy()[2]<GameField.HEIGHT-1)
						position.getXy()[2]++;
					System.out.println("x: "+position.getXy()[0]
							+" y: "+position.getXy()[2]
							+" l_x: "+position.getXy()[1]
							+" l_y :"+position.getXy()[3]);
					
					refreshField();
					//fight.setBounds(480,0,790,170);
					//fight.repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT)
				{
					position.getXy()[3]=position.getXy()[2];
					position.getXy()[1]=position.getXy()[0];
					if(position.getXy()[0]>=1)
						position.getXy()[0]--;
					System.out.println("x: "+position.getXy()[0]
							+" y: "+position.getXy()[2]
							+" l_x: "+position.getXy()[1]
							+" l_y :"+position.getXy()[3]);
					
					//遍历isField看哪个重画
					refreshField();
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					position.getXy()[3]=position.getXy()[2];
					position.getXy()[1]=position.getXy()[0];
					if(position.getXy()[0]<GameField.WIDTH-1)
						position.getXy()[0]++;
					System.out.println("x: "+position.getXy()[0]
							+" y: "+position.getXy()[2]
							+" l_x: "+position.getXy()[1]
							+" l_y :"+position.getXy()[3]);
					//遍历isField看哪个重画
					refreshField();
				}
				//存档按钮
				if(e.getKeyCode()==KeyEvent.VK_E)
				{
					/*save.setVisible(true);*/
					keepFile();
					/*isChange = true;*/
				}
				//取档按钮   之后可以改成几个按钮  有几个存档
				if(e.getKeyCode()==KeyEvent.VK_R)
				{
					/*load.setVisible(true);*/
					loadFile();
					refreshFile();
					//isChange = false;
				}
			}
				//
			}
		});
	}
	//找当前Field 并更新画面
	public static void refreshField()
	{
		//通过移动关闭战利品框
		isBooty = false;
		booty.setBounds(480,315,0,0);
		GameField f;
		//获得当前层
		f = position.getF();
		//按照f的规则更新
		f.rule();
		//应该是把找到当前Field  再传x y l_x l_y
		//值过去   再把值传回来
		//one.rule(xy);
	}
	/*public static void main(String[] args) throws IOException
	{
		new Game().init();
	}*/
	//重写paint?    为的是缩放游戏时   全都一起重画  
	public void paint(Graphics g)
	{
		info.repaint();
		//fight.repaint();
		//one.repaint();
		GameField f;
		//获得当前层
		f = position.getF();
		//按照f的规则更新
		f.rule();
	}
	//通过Hero的更新  更新Info信息
	//写个timer  及时更新数据?
	private void refreshInfoByHero()
	{
		//更新man里面的num  用于存档
		man.refreshNum();
		info.getLv().setText(intToString(man.getLv()));
		info.getHp().setText(intToString(man.getHp()));
		info.getAttack().setText(intToString(man.getAttack()));
		info.getDefence().setText(intToString(man.getDefence()));
		info.getMoney().setText(intToString(man.getMoney()));
		info.getEx().setText(intToString(man.getEx()));
		info.getY_keys().setText(intToString(man.getY_keys()));
		info.getB_keys().setText(intToString(man.getB_keys()));
		info.getR_keys().setText(intToString(man.getR_keys()));
		//有装备 加当前装备属性和更新info
		if(man.getHelmet()!=null)
		{
			info.getHelmet().setIcon(new ImageIcon(man.getHelmet().head));
		}
		else
			info.getHelmet().setIcon(null);
		if(man.getArmour()!=null)
		{
			info.getArmour().setIcon(new ImageIcon(man.getArmour().head));
			
		}
		else
			info.getArmour().setIcon(null);
		if(man.getShoe()!=null)
		{
			info.getShoe().setIcon(new ImageIcon(man.getShoe().head));
		}
		else
			info.getShoe().setIcon(null);
		if(man.getWeapon()!=null)
		{
			info.getWeapon().setIcon(new ImageIcon(man.getWeapon().head));
		}
		else
			info.getWeapon().setIcon(null);
		//如果经验超过100  生命值加100 攻击加5 防御加5 经验清零
		if(man.getEx()>=100)
		{
			man.setEx(man.getEx()-100);
			man.setHp(man.getHp()+100);
			man.setAttack(man.getAttack()+5);
			man.setDefence(man.getDefence()+5);
			man.setLv(man.getLv()+1);
			booty.showLvUp(man);
		}
		//如果有装备就加当前装备的属性
		
	}
	//int 转 string
	public static String intToString(int num)
	{
		return Integer.toString(num);
	}
	//存档操作   
	public  void keepFile()
	{
		/*hero要传  hp attack defence money ex lv y,b,r_keys
		还有就是装备信息一共有四种装备 就留4个int位置
		13int

		楼层位置和坐标信息是一个int[5]数组

		楼层内容信息是一个int[16][18]数组*/
			
			try {
				
				randomfile.seek(0);
				//英雄信息
				for(int i=0;i<15;i++)
				{
					randomfile.writeInt(man.getNum()[i]);
					System.out.println(man.getNum()[i]);
				}
				for(int i=0;i<5;i++)
				randomfile.writeInt(position.getXy()[i]);
				for(GameField f :fieldList)
				for(int i=0;i<16;i++)
					for(int j=0;j<18;j++)
						randomfile.writeInt(f.flag[i][j]);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		/*//把hero position 还有每层的thing都存进去
		//用对象输出流
		try {
			System.out.println(man);
			keep.writeObject(man);
			System.out.println("man:"+man);
			//System.out.println(position.getFloor());
			//keep.writeObject(position.getFloor());
			keep.writeObject(position.getXy());
			System.out.println(position.getXy()[0]+":"+
					position.getXy()[1]+":"+
					position.getXy()[2]+":"+
					position.getXy()[3]+":"+
					position.getXy()[4]);
			//遍历送每层thing
			for(GameField f :fieldList)
			{
				keep.writeObject(f.flag);
			}
			keep.writeObject(null);
			keep.flush();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
	}
	//如果没有按存档按钮  就再写一次覆盖文件  为了让读文件指针归位
	private void un_keepFile()
	{
		/*try {
			keep.writeObject(h);
			keep.writeObject(temp);
			//遍历送每层thing
			for(GameField f :fieldList)
			{
				keep.writeObject(f.f_flag);
			}
			keep.writeObject(null);
			keep.flush();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}*/
	}
	//读档操作
	public  void loadFile()
	{
		try {
			randomfile.seek(0);
			//英雄信息
			for(int i=0;i<15;i++)
			man.getNum()[i] = randomfile.readInt();
			for(int i=0;i<5;i++)
			position.getXy()[i] = randomfile.readInt();
			for(GameField f :fieldList)
			for(int i=0;i<16;i++)
				for(int j=0;j<18;j++)
					f.flag[i][j] = randomfile.readInt();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		/*Object o ;
		int i=0;
		//如果按了存档按钮  读取最新的存档
		//什么都不干
			try {
				load = new ObjectInputStream(new FileInputStream(file));
				System.out.println("新load");
			} catch (FileNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		if(!isChange)
		{
			//重写文件一次
			un_keepFile();
			System.out.println("旧load");
		}
			try {
			while((o = load.readObject())!=null)
			{
				if(o.getClass() == Hero.class)
					{	
						//为了让man与之前的对象一样
					    //所以是一一赋值
						h = (Hero)o;
						System.out.println("h:"+h);
						//man= (Hero)o;
					}
				//坐标 楼层信息
				if(o.getClass() == int[].class)
					{
					//这里竟然也是传对象  诶  麻烦
					temp = (int[])o;
					//System.out.println("asd:"+temp);
					}
				
				if(o.getClass() == int[][].class)
				{
					//遍历赋值
					fieldList.get(i).flag =(int[][])o; 
					i++;
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		//更新坐标
		//依然用值传递  不用对象传递  避免传对象导致使用同一个对象
		for(int e=0;e<5;e++)
			{
				position.getXy()[e] = temp[e];
			}
		System.out.println(position.getXy()[0]+":"+
				position.getXy()[1]+":"+
				position.getXy()[2]+":"+
				position.getXy()[3]+":"+
				position.getXy()[4]);
		System.out.println(temp+"  "+position.getXy());
		position.setXy(temp);
		System.out.println(temp[0]+":"+temp[1]+":"+temp[2]);
		//把每层flag  给f_flag  只是传值  而不是传对象
		for(GameField f :fieldList)
		{
			for(int q=0;q<GameField.WIDTH;q++)
			{
				for(int j=0;j<GameField.HEIGHT;j++)
				{
					f.f_flag[q][j] = f.flag[q][j];
					//System.out.println(f.flag[q][j]);
				}
			}
		}
		//如果没有更新存档  读取上次的(也就是不走读取操作)
				man.setHp(h.getHp());
				man.setEx(h.getEx());
				man.setAttack(h.getAttack());
				man.setDefence(h.getDefence());
				man.setLv(h.getLv());
				man.setGet_hp(h.getGet_hp());
				man.setGet_money(h.getGet_money());
				man.setY_keys(h.getY_keys());
				man.setB_keys(h.getB_keys());
				man.setR_keys(h.getR_keys());
				man.setGet_money(h.get_money);
				man.setHelmet(h.getHelmet());
				
					try {
						load.close();
						System.out.println("关load");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}*/
				
	}
	
	/*//搞个线程更新数据  
		class showFight extends Thread
		{
			public void run()
			{
				//每隔一秒更新一次
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				fight.repaint();
				System.out.println("123");
			}
		}*/
	//更新读档之后的   数据信息
	public void refreshFile()
	{
		//更新状态
		//更新地板  
		for(GameField f :fieldList)
		{	
			//如果存档前没变
			//读档操作在存档之后  发生了变化的话
			f.init();
			//先初始化当前楼层
			int a=0;
			for(int i=0;i<GameField.WIDTH;i++)
			{
				for(int j=0;j<GameField.HEIGHT;j++)
				{
					//如果有改变
					if(f.flag[i][j]==1)
					{	
						System.out.println("萨达");
						//当前楼层初始化 且只初始化一次
						//if(a==0)
							//f.init();
						f.things[i][j] = new Floor();
						//a++;
					}
				}
			}
			//更新hero
			man.setLv(man.getNum()[0]);
			man.setHp(man.getNum()[1]);
			man.setAttack(man.getNum()[2]);
			man.setDefence(man.getNum()[3]);
			man.setMoney(man.getNum()[4]);
			man.setEx(man.getNum()[5]);
			man.setY_keys(man.getNum()[6]);
			man.setB_keys(man.getNum()[7]);
			man.setR_keys(man.getNum()[8]);
			switch(man.getNum()[9])
			{
			case 0:man.setHelmet((new Equip("铠甲", "helmet", 0, new Pig().getHead()
					, 10, 5, 5, 5)));
			default:man.setHelmet(null);
			}
			switch(man.getNum()[10])
			{
			/*case 0:man.setArmour((new Equip("铠甲", "helmet", new Pig().getHead()
					, 10, 5, 5, 5)));*/
			default:man.setArmour(null);
			}
			switch(man.getNum()[11])
			{
			case 1:man.setWeapon(new Equip("铠甲", "Armour", 1,Field3.img
					, 10, 5, 5, 5));
			default:man.setWeapon(null);
			}
			switch(man.getNum()[12])
			{
			/*case 0:man.setShoe((new Equip("铠甲", "helmet", new Pig().getHead()
					, 10, 5, 5, 5)));*/
			default:man.setShoe(null);
			}
			man.setGet_hp(man.getNum()[13]);
			man.setGet_money(man.getNum()[14]);
			
		}
		//更新楼层
		position.getF().setVisible(false);
		System.out.println(floor);
		position.setF(fieldList.get(position.getXy()[4]));
		//System.out.println(position.getFloor());
		position.getF().setVisible(true);
		refreshField();
		repaint();
	}
	//添加楼层到Game
	private void addField(GameField f)
	{
		f.setBounds(480,0,800,900);
		add(f);
	}
}
