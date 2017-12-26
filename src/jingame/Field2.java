package jingame;

import java.awt.Graphics;
import java.io.IOException;

public class Field2 extends GameField{

	
	int[][] xy = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
		
	public Field2(Hero man) throws IOException {
		super(man);
		
	}
	//设置楼层信息  不能放在构造函数里面  因为有些楼层还没有构造  然后就死循环 null指针了
		public void init()
		{
			floors = "皇子坡";
			//设置楼梯位置
			setUp_x(12);
			setUp_y(12);
			setDown_x(5);
			setDown_y(5);
			//background = ImageIO.read(new File("src/jingame/icon/test.png"));
			/*things[5][6] = new Wall();
			things[7][5] = new Wall();
			things[7][4] = new Wall();
			things[7][3] = new Wall();*/
			things[7][2] = new Thief();
			things[8][2] = new Thief();
			things[9][2] = new Thief();
			things[10][2] = new Thief();
			things[11][2] = new Thief();
			things[getUp_x()][getUp_y()] = new UpStair();
			things[getDown_x()][getDown_y()] = new DownStair();
			//设置上下楼梯位置	
			//设置down_f为野猪林
			setDown_f(Game.fieldList.get(2));
		}
}
