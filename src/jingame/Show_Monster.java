/*package jingame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import jingame.BootyPanel.Background;

public class Show_Monster extends JPanel{
	//显示本层的怪物信息
	//战利品显示框  显示在中间
		//有两种情况  一种是得到金币 得到经验
		//另一种是获得装备   还有就是升级
		BufferedImage background;
		private final int TABLE_WIDTH = 790;
		private final int TABLE_HEIGHT = 920;
		
		//11个label  其中10个显示当前层怪物  1个来作为标题
		JLabel title;
		JLabel label1;
		JLabel label2;
		JLabel label3;
		JLabel label4;
		JLabel label5;
		JLabel label6;
		JLabel label7;
		JLabel label8;
		JLabel label9;
		JLabel label10;
		String name = "名称: ";
		String hp ="生命值: ";
		String attack = "攻击力: ";
		String defence = "防御力: ";
		public Show_Monster() throws IOException
		{
			//实际上重写paint的JPanel
			Background back = new Background();
			
			background = ImageIO.read(new File("src/jingame/icon/booty.png"));
			//各种label的定位
			
			back.setBounds(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
			//组件添加的层级类
			JLayeredPane layerPanel = new JLayeredPane();
			layerPanel.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
			//底层
			layerPanel.add(back,new Integer(1));
			//上层
			layerPanel.add(label,new Integer(2));
			this.add(layerPanel);
		}
		class Background extends JPanel
		{
			public void paint(Graphics g)
			{
			//super.paint(g);
			g.drawImage(background, 0, 0, null);
			}
		}
		//显示当前层的怪物信息
		public void showMonster()
		{
			
		}
}*/
