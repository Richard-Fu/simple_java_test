package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall implements Thing{

	private transient BufferedImage wall;
	//有陷阱 墙  就扣血
	private int sub_hp;
	public Wall(BufferedImage head,int sub_hp)
	{
		
			wall = head;
			this.sub_hp = sub_hp;
	}
	//墙阻挡它  所以field x y = l_x l_y  所以在原地不动
	public void doJob(Hero man,int i, int j) {
		Game.position.getXy()[0]=Game.position.getXy()[1];
		Game.position.getXy()[2]=Game.position.getXy()[3];
		//现在是定义的碰到wall 减一滴血  所以 还可以设置陷阱  爽！
		man.setHp(man.getHp()-sub_hp);
	}
	public BufferedImage getWall() {
		return wall;
	}
	public void setWall(BufferedImage wall) {
		this.wall = wall;
	}
	@Override
	public BufferedImage getImage() {
		// TODO 自动生成的方法存根
		return wall;
	}

}
