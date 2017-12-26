package jingame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import jingame.FightPanel.Background;

public class StoryPanel extends JPanel{
	//剧情框在最下方  可以是对话 
	//一个头像框  一个文字框   NPC里面搞个数据结构存对话内容？还是一人说一句？
	BufferedImage background;
	private final int TABLE_WIDTH = 790;
	private final int TABLE_HEIGHT = 250;
	private JLabel head;//35 25 140 140
	private JLabel content;//210 50 560 160
	private JLabel name;//60 180 150 60
	private Npc npc;
	private Hero man;
	//存商店里面固定说的几句话
	private String msg1 = "少侠，我家的店可是什么都有！";
	private String hp = "1  增加生命值:";
	private String money = "消耗金币:";
	private String attack = "2  增加攻击力:";
	private String defence = "3  增加防御力:";
	private String ex = "4  增加经验值:";
	private String exit = "按 '5'退出商店哦!";
	//存Door里面固定几句话
	private String msg2 = "小子，你是哪里人，想到哪里去?";
	private String y_k = "1 需要黄色英雄帖:";
	private String b_k = "需要蓝色英雄帖:";
	private String r_k = "需要红色英雄帖:";
	private String fight = "3 敢挡我路？灭了他!";
	private String bride = "2 大爷行行好,我给你金币:";
	private String exit1 = "4 我还是一会再来吧!";
	//用来当剧情指针的  默认为0 遍历剧情长度
	public static int i =0;
	//用来存商店符号的
	public static char ch = '0';
	public static Run run;
	//把BufferedImage转过来
	ImageIcon npc_head;
	ImageIcon man_head;
	//初始化GUI
	public StoryPanel()
	{
		run = new Run();
		//实际上重写paint的JPanel
		Background back = new Background();
		head = new JLabel();
		content = new JLabel();
		name = new JLabel();
		//背景图
		try {
			background = ImageIO.read(new File("src/jingame/icon/story.png"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		head.setBounds(35, 25, 140, 140);
		content.setBounds(210, 50, 560, 160);
		name.setBounds(60, 180, 150, 60);
		back.setBounds(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
		JLayeredPane layerPanel = new JLayeredPane();
		layerPanel.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		layerPanel.add(back,new Integer(1));
		layerPanel.add(head,new Integer(2));
		layerPanel.add(content,new Integer(2));
		layerPanel.add(name,new Integer(2));
		this.add(layerPanel);
		setVisible(true);
	}
	//背景JPanel
	class Background extends JPanel
	{
		public void paint(Graphics g)
		{
		//super.paint(g);
		g.drawImage(background, 0, 0, null);
		}
	}
	//传一个Npc 和一个Hero  如果是奇遇呢？肯定加数据啊
	public void showStory(Npc npc, Hero man)
	{
		//把Game里面的判断调成正在剧情中
		Game.isStory = true;
		//更新
		this.npc = npc;
		this.man = man;
		npc_head = new ImageIcon(npc.head);
		man_head = new ImageIcon(man.head);
		head.setIcon(null);
		head.setText(null);
		content.setText(null);
		name.setText(null);
		System.out.println("剧情");
		ch ='0';
		i=0;
		run.start();
	}
	class Run extends Thread
	{
		
		public void run()
		{
						
						//如果是在商店里面，那就1,2,3,4的按键响应
						//原理也是一样 生命值 攻击力 防御力 经验值
			
			System.out.println("剧情1");
			System.out.println("剧情2");
				//如果在剧情(也就是剧情  商 店)模式中
				if(Game.isStory)
				{
					//System.out.println("剧情3");
					//如果是真在剧情中  先把头像给显示出来
					if(npc.isStory)
					//true 是hero
					{
						if(npc.storylist[0].isWhoTalk())
						{	
						head.setIcon(man_head);
						name.setText(man.getName());
						}
						else
						{
						head.setIcon(npc_head);
						name.setText(npc.getName());
						}
					}
					//如果在商店中,门  也先显示头像
					else
						{	head.setIcon(npc_head);
							name.setText(npc.getName());
						}
				}
			//测试该线程是否在运行
				ActionListener taskPerformer = evt ->
		        {
		        	//System.out.println(i);
		        	//System.out.println(i);
					//System.out.println("剧情4");
						
						
						//如果在剧情中  按q刷新
		        	if(npc.isStory)
		        	{	//遍历storylist   更新GUI数据
		        		if(i<npc.getStorylist().length)
						{
						    	
								if(npc.storylist[i].isWhoTalk())
								{	
									head.setIcon(man_head);
									name.setText(man.getName());
								}

								else
									{
									head.setIcon(npc_head);
									name.setText(npc.getName());
									}
								content.setText(npc.storylist[i].getContent());
						}
						else
						{
							Game.isStory = false;
							Game.story.setBounds(0,0,0,0);
						}
		        	}
		        	//如果在商店中  按 1 2 3 4 5选择
		        	if(npc.isStore)
		        	{
		        		//先刷新商店目录
		        		head.setIcon(npc_head);
						name.setText(npc.getName());
		        		content.setText("<html>"+msg1 + "<br>" 
		        				+hp + npc.getNumList()[0].getNum()
		        				+money + npc.getNumList()[0].getMoney()
		        				+"<br>" + attack +npc.getNumList()[1].getNum()
		        				+money + npc.getNumList()[1].getMoney()
		        				+"<br>" + defence +npc.getNumList()[2].getNum()
		        				+money + npc.getNumList()[2].getMoney()
		        				+"<br>" + ex +npc.getNumList()[3].getNum()
		        				+money + npc.getNumList()[3].getMoney()
		        				+"<br>" + exit+"</html>");
		        		System.out.println(ch);
		        		switch(ch)
		        		{
		        		//如果是1  判断是否有钱  更新Hero
		        		case '1':
		        			//有钱  减钱  没钱不动
		        			if(man.getMoney()>=npc.getNumList()[0].getMoney())
		        			{
		        				man.setMoney(man.getMoney()-npc.getNumList()[0].getMoney());
		        				man.setHp(man.getHp()+npc.getNumList()[0].getNum());
		        			}
		        			//c 复位
		        			ch = '0';
		        			break;
		        		case '2':
		        			//有钱  减钱  没钱不动
		        			if(man.getMoney()>=npc.getNumList()[1].getMoney())
		        			{
		        				man.setMoney(man.getMoney()-npc.getNumList()[1].getMoney());
		        				man.setAttack(man.getAttack()+npc.getNumList()[1].getNum());
		        			}
		        			ch = '0';
		        			break;
		        		case '3':
		        			//有钱  减钱  没钱不动
		        			if(man.getMoney()>=npc.getNumList()[2].getMoney())
		        			{
		        				man.setMoney(man.getMoney()-npc.getNumList()[2].getMoney());
		        				man.setDefence(man.getDefence()+npc.getNumList()[2].getNum());
		        			}
		        			ch = '0';
		        			break;
		        		case '4':
		        			//有钱  减钱  没钱不动
		        			if(man.getMoney()>=npc.getNumList()[3].getMoney())
		        			{
		        				man.setMoney(man.getMoney()-npc.getNumList()[3].getMoney());
		        				man.setEx(man.getEx()+npc.getNumList()[3].getNum());
		        			}
		        			ch = '0';
		        			break;
		        		case '5':
		        			//退出商店
		        			ch = '0';
		        			Game.isStory = false;
							Game.story.setBounds(0,0,0,0);
		        		}
		        		
		        	}
		        	//如果在门 中
		        	if(npc.isDoor)
		        	{
		        		head.setIcon(npc_head);
						name.setText(npc.getName());
		        		content.setText("<html>"+msg2 + "<br>" 
		        				+y_k + npc.getNum2List()[0]
		        				+b_k + npc.getNum2List()[1]
		        				+r_k + npc.getNum2List()[2]
		        				+"<br>" +bride +npc.getNum2List()[3]
		        				+"<br>" +fight
		        				+"<br>" + exit1+"</html>");
		        		switch(ch)
		        		{
		        		//如果是1  判断是否门票  更新Hero
		        		case 'w':
		        			//有门票  减门票  没门票不动
		        			//判断三个门票数  可能有要多张门票的情况
		        			if(man.getY_keys()>=npc.getNum2List()[0]
		        			&& man.getB_keys()>=npc.getNum2List()[1]
		        			&& man.getR_keys()>=npc.getNum2List()[2])
		        			{
		        				//减门票
		        				man.setY_keys(man.getY_keys()-npc.getNum2List()[0]);
		        				man.setB_keys(man.getB_keys()-npc.getNum2List()[1]);
		        				man.setR_keys(man.getR_keys()-npc.getNum2List()[2]);
		        				Game.isStory = false;
								Game.story.setBounds(0,0,0,0);
								Game.position.getF()
		        				.things[Game.position.getXy()[0]][Game.position.getXy()[2]] 
		        						= new Floor();
								Game.position.getF()
		        				.flag[Game.position.getXy()[0]][Game.position.getXy()[2]] 
		        						= 1;
		        				
		        			}
		        			//c 复位
		        			ch = '0';
		        			break;
		        		case '2':
		        			//有钱  减钱  没钱不动    这是贿赂
		        			if(man.getMoney()>=npc.getNum2List()[3])
		        			{
		        				man.setMoney(man.getMoney()-npc.getNum2List()[3]);
		        				//当前位置的门 调成 地板
		        				Game.position.getF()
		        				.things[Game.position.getXy()[0]][Game.position.getXy()[2]] 
		        						= new Floor();
		        				Game.position.getF()
		        				.flag[Game.position.getXy()[0]][Game.position.getXy()[2]] 
		        						= 1;
		        				Game.isStory = false;
								Game.story.setBounds(0,0,0,0);
		        			}
		        			ch = '0';
		        			break;
		        		case '3':
		        			//打架   调用里面的怪物属性
		        			npc.m.doJob(man, Game.position.getXy()[0]
		        					, Game.position.getXy()[2]);
		        			Game.isStory = false;
							Game.story.setBounds(0,0,0,0);
		        			ch = '0';
		        			break;
		        		case '4':
		        			//退出
		        			ch = '0';
		        			//主角退回上一步位置
		        			Game.position.getXy()[0]=Game.position.getXy()[1];
		        			Game.position.getXy()[2]=Game.position.getXy()[3];
		        			//及时更新画面 
		        			Game.refreshField();
		        			Game.isStory = false;
							Game.story.setBounds(0,0,0,0);
		        			break;
		        		}
		        	}
		        };
		        Timer timer = new Timer(1000,taskPerformer);
		        timer.start();
				
	}
	}
}


















