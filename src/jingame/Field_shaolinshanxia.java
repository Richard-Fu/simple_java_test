
package jingame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Field_shaolinshanxia extends GameField{

	BufferedImage pot;
	BufferedImage carrot;
	//BufferedImage floor;
	//1画桶子  2画r_pig 3 画g_pig  4 s_monk 5画carrot
	int[][] xy = {{0,0,0,0,0,1,1,1,0,0,1,0,0,0,0,0},
				  {0,0,0,0,0,1,0,1,2,2,1,5,1,1,0,0},
				  {0,0,0,0,1,0,0,1,0,3,1,0,0,4,0,0},
				  {0,0,1,1,5,0,5,1,1,4,1,0,1,1,1,0},
				  {0,0,0,4,1,1,3,2,1,2,1,2,1,0,0,0},
				  {0,0,1,1,0,0,4,2,5,1,1,4,1,1,0,0},
				  {0,0,0,0,0,0,2,0,2,0,3,0,3,1,0,0},
				  {0,0,1,1,0,2,0,0,0,0,1,0,3,1,0,0},
				  {0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,0},
				  {0,5,0,1,1,1,1,1,1,0,1,1,1,1,1,0},
				  {0,1,0,1,4,1,0,0,2,0,0,4,1,0,0,0},
				  {0,1,4,0,0,1,0,1,5,1,1,2,1,0,0,0},
				  {0,0,0,0,4,1,4,0,0,0,1,0,1,1,1,0},
				  {0,1,1,0,0,0,1,3,5,4,1,0,1,0,0,0},
				  {0,0,0,1,0,0,1,3,0,0,1,0,0,4,0,0},
				  {0,3,4,0,0,0,5,1,4,1,1,4,1,1,1,0},
				  {0,4,4,2,0,0,1,0,0,0,1,0,1,0,0,0},
				  {0,2,4,3,0,0,1,0,0,0,1,1,1,0,0,0}};
		
	public Field_shaolinshanxia(Hero man) throws IOException {
		super(man);
		
	}
	//设置楼层信息  不能放在构造函数里面  因为有些楼层还没有构造  然后就死循环 null指针了
		public void init()
		{
			try {
				pot = ImageIO.read(new File("src/jingame/icon/pot.gif"));
				carrot = ImageIO.read(new File("src/jingame/icon/carrot.gif"));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			floors = "少林山下";
			for(int i=0;i<18;i++)
			{   
				for(int j=0;j<16;j++)
				{
					if(xy[i][j]==1)
						things[j][i]= new Wall(pot,0);
					if(xy[i][j]==5)
						things[j][i]= new Wall(carrot,0);
					if(xy[i][j]==4)
						things[j][i] = new S_monk();
					if(xy[i][j]==3)
						things[j][i] = new G_pig();
					if(xy[i][j]==2)
						things[j][i] = new R_pig();
				}
			}
			//设置地板
			//设置楼梯位置
			setLeft_x(8);
			setLeft_y(17);
			things[getLeft_x()][getLeft_y()] = new LeftStair();
			
			//设置上下楼梯位置	
			//设置down_f为野猪林
			setLeft_f(Game.fieldList.get(2));
		}
}