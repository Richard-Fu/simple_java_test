package jingame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Field1 extends GameField{

	BufferedImage wall;
	BufferedImage tree;
	BufferedImage door;
	//地图  为1画wall 2 画树 3画黑猪 4 画绿 5 画红
		/*int[][] xy = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};*/
		int[][] xy = {{0,0,0,0,0,2,2,2,2,0,0,0,0,2,0,0},
				  	  {0,0,0,0,0,2,0,0,2,0,0,0,0,2,0,3},
			          {0,0,0,0,0,2,0,0,2,2,2,2,0,2,0,0},
			          {2,2,2,2,3,2,0,0,3,0,0,2,0,2,0,2},
			          {0,0,2,0,4,0,0,0,2,0,0,0,0,2,0,0},
			          {0,0,2,2,0,3,2,0,2,0,0,2,3,2,0,2},
			          {0,3,2,2,0,0,2,0,3,0,0,0,0,2,0,0},
			          {3,3,2,2,0,0,2,0,2,2,2,2,0,2,2,0},
			          {5,5,2,2,5,3,2,0,2,0,2,2,0,2,0,0},
			          {4,4,2,2,0,0,0,0,2,0,2,2,0,2,0,0},
			          {3,3,2,2,2,2,2,2,0,0,2,2,0,2,0,0},
			          {3,3,2,2,0,0,0,2,0,0,2,2,0,2,0,0},
			          {0,0,2,2,0,0,0,2,0,0,0,0,0,2,0,0},
			          {0,0,0,0,0,0,0,2,0,0,0,0,2,0,0,0},
			          {0,0,0,0,0,0,0,2,0,0,0,2,0,0,0,0},
			          {0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0},
			          {0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0},
			          {0,0,0,0,0,0,0,2,0,3,0,0,3,0,0,0}};
	
	
	public Field1(Hero man) throws IOException {
		
		super(man);
	}
	
	//设置楼层信息  不能放在构造函数里面  因为有些楼层还没有构造  然后就死循环 null指针了
	public void init()
	{
		
		try {
			wall = ImageIO.read(new File("src/jingame/icon/wall.gif"));
			tree = ImageIO.read(new File("src/jingame/icon/tree.gif"));
			door = ImageIO.read(new File("src/jingame/icon/2.gif"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//地图名字
				floors = "野猪林";
				//设置楼梯位置
				setUp_x(10);
				setUp_y(15);
				setRight_x(14);
				setRight_y(0);
				setLeft_x(0);
				setLeft_y(5);
				int[] test = {0,1,0,20};
				//background = ImageIO.read(new File("src/jingame/icon/test.png"));
				//地图上除开地板 其他的thing
				for(int i=0;i<18;i++)
				{   
					for(int j=0;j<16;j++)
					{
						if(xy[i][j]==2)
							things[j][i]= new Wall(tree,0);
						if(xy[i][j]==3)
							things[j][i] = new Pig();
						if(xy[i][j]==4)
							things[j][i] = new G_pig();
						if(xy[i][j]==5)
							things[j][i] = new R_pig();
					}
				}
				
				
				things[up_x][up_y] = new UpStair();
				things[left_x][left_y] = new LeftStair();
				things[right_x][right_y] = new RightStair();
				
				things[1][2] = new Npc1Test();
				things[1][12] = new Store1Test();
				things[right_x][right_y+3] = new Door1Test("看门狗", false, false, false, true
						, null , null,test,door ,new Pig());
				
		//设置up_f为 皇子坡
				setUp_f(Game.fieldList.get(3));
		//设置right_f 为少林山下
				setRight_f(Game.fieldList.get(5));
		//设置left_f为绿树林
				setLeft_f(Game.fieldList.get(4));
	}
	//重写rule
	//不用重写rule吧
	//重写paint
}
