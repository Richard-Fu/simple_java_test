package jingame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import jingame.FightPanel.Background;

public class BootyPanel extends JPanel{
	//战利品显示框  显示在中间
	//有两种情况  一种是得到金币 得到经验
	//另一种是获得装备   还有就是升级
	BufferedImage background;
	private final int TABLE_WIDTH = 790;
	private final int TABLE_HEIGHT = 150;
	
	//
	JLabel label;
	String money ="获得金币 : ";
	String ex = "获得经验 : ";
	String thing ="获得装备 : ";
	String defeat = "击败";
	public BootyPanel() throws IOException
	{
		//实际上重写paint的JPanel
		Background back = new Background();
		
		background = ImageIO.read(new File("src/jingame/icon/booty.png"));
		label = new JLabel();
		label.setBounds(70, 35, 650, 90);
		back.setBounds(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
		JLayeredPane layerPanel = new JLayeredPane();
		layerPanel.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		layerPanel.add(back,new Integer(1));
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
	public void showBooty(Thing thing, Hero h)
	{
		Game.booty.setBounds(480,315,TABLE_WIDTH,TABLE_HEIGHT);
		//首先判断thing的类型  
		//如果是怪物
		//System.out.println(thing.getClass()+"    "+Monster.class);
		if(thing instanceof Monster)
		{
			System.out.println("怪物");
			Monster m = (Monster)thing;
			h.setMoney(h.getMoney()+m.money);
			h.setEx(h.getEx()+m.ex);
			label.setText(defeat + m.getName() + "," + money +m.money+" , "+ex+m.ex);
		}
		//显示出来
		Game.isBooty = true;
		//if(thing.getClass() == Monster.class)
			
	}
	public void showLvUp(Hero h)
	{
		Game.booty.setBounds(480,315,TABLE_WIDTH,TABLE_HEIGHT);
		label.setText("恭喜 "+h.getName()+"升级! ,"+"获得生命值:100,攻击力:5,防御力:5"
				+ "再接再厉哦!  ");
		//显示出来
		Game.isBooty = true;
	}
	public void showGetEquip(Equip e, Hero h)
	{
		Game.booty.setBounds(480,315,TABLE_WIDTH,TABLE_HEIGHT);
		label.setText("恭喜 "+h.getName()+"获得装备:"+e.getName()
		+"增加攻击:"+e.getAttack()+"增加防御:"+e.getDefence()
		+"每次攻击吸血:"+e.getGet_hp()+"每次偷钱:"+e.getGet_money());
		//显示出来
		Game.isBooty = true;
	}
}
