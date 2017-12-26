package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UpStair implements Thing{
	private transient BufferedImage upStair;
	public UpStair()
	{
		try {
			upStair = ImageIO.read(new File("src/jingame/icon/stair.png"));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void doJob(Hero man,int i, int j) {
		
		GameField f;
		//获得当前楼层的up楼层
		f = Game.position.getF().getUp_f();
		//首先x y 等于上层 的down 的位置 我感觉要+1不然就无限跳转了
		Game.position.getXy()[0]=f.getDown_x()+1;
		Game.position.getXy()[2]=f.getDown_y();
		//当前楼层设置成不可视
		//up设置成可视
		Game.position.getF().setVisible(false);
		f.setVisible(true);
		//然后设置当前楼层  为当前的up
		Game.position.setF(f);
		Game.position.getXy()[4]=(Game.fieldList.indexOf(f));
	}
	@Override
	public BufferedImage getImage() {
		// TODO 自动生成的方法存根
		return upStair;
	}
}
