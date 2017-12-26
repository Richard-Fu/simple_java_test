package jingame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Field0 extends GameField{

	BufferedImage wall;
	//地图  为1画wall
	int[][] xy = {{0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1},
				  {0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1},
			      {1,1,1,1,0,1,0,1,0,0,0,0,0,0,0,1},
			      {0,0,0,1,0,1,0,1,0,1,0,1,1,0,0,1},
			      {0,0,0,1,0,1,0,1,0,1,0,1,1,1,0,1},
			      {0,0,0,0,0,1,1,1,0,1,1,1,1,1,0,1},
			      {0,1,1,1,1,1,0,0,0,0,0,0,0,1,0,1},
			      {0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1},
			      {0,1,0,0,1,0,1,0,0,0,0,1,0,1,0,1},
			      {0,0,0,1,1,0,1,0,0,0,0,1,0,1,0,1},
			      {1,1,1,1,1,0,1,0,0,0,0,1,0,1,0,1},
			      {0,0,0,0,0,0,1,1,1,1,1,1,0,1,0,1},
			      {0,0,1,1,1,1,1,1,0,0,0,1,0,1,0,1},
			      {0,0,1,0,0,0,0,1,0,0,0,1,0,1,0,1},
			      {0,0,1,0,0,0,0,1,0,1,0,1,0,1,0,1},
			      {0,0,1,0,0,0,0,1,0,1,0,1,0,1,0,1},
			      {0,0,1,1,1,1,1,1,0,1,0,0,0,1,0,1},
			      {0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1}};
	
	public Field0(Hero man) throws IOException {
		
		super(man);
	}
	
	//设置楼层信息  不能放在构造函数里面  因为有些楼层还没有构造  然后就死循环 null指针了
	public void init()
	{
		
		try {
			wall = ImageIO.read(new File("src/jingame/icon/wall.gif"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//地图名字
				floors = "梦境通道";
				//设置上下楼梯位置
				setUp_x(15);
				setUp_y(17);
				//background = ImageIO.read(new File("src/jingame/icon/test.png"));
				//地图上wall初始化
				for(int i=0;i<18;i++)
				{   
					for(int j=0;j<16;j++)
					{
						if(xy[i][j]==1)
							things[j][i]= new Wall(wall,0);
					}
				}
				
				things[up_x][up_y] = new UpStair();
				/*for(int i=0;i<18;i++)
				{   System.out.print("{");
					for(int j=0;j<16;j++)
						System.out.print("0,");
					System.out.print("},");
					System.out.println("");
				}*/
		//设置up_f为 野猪林
				setUp_f(Game.fieldList.get(2));
		
	}
	//重写rule
	//不用重写rule吧
	//重写paint
}
