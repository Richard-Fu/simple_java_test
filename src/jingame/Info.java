package jingame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Info extends JPanel{
	//信息框界面大小
	private final int TABLE_WIDTH = 480;
	private final int TABLE_HEIGHT = 900;
	
	//测试JFrame
	//JFrame f = new JFrame();
	//背景图
	BufferedImage background;
	
	//存JLabel列表  
	ArrayList<JLabel> labelList = new ArrayList<>();
	//用户头像
	JLabel head;
	//JLabel l;
	//用户等级
	JLabel lv;
	//HP
	JLabel hp;
	JLabel attack;
	JLabel defence;
	JLabel money;
	JLabel ex;
	
	//装备栏
	//头盔
	JLabel helmet;
	//铠甲
	JLabel armour;
	//鞋子
	JLabel shoe;
	//武器
	JLabel weapon;
	
	//英雄帖
	JLabel y_keys;
	JLabel b_keys;
	JLabel r_keys;
	//地图位置
	JLabel floors;
	/*//测试添加图片
	JButton b;*/
	public Info() throws IOException
	{
		//初始化Label
		//用户头像
		//setBorder(new EmptyBorder(10, 10, 10, 10));
		head = new JLabel();//35 55 90 90
		//用户等级
		lv = new JLabel();//200 100
		//HP
	    hp = new JLabel();//145 185
		attack = new JLabel();//145 235
		defence = new JLabel();//145 285
	    money = new JLabel();//145 335
		ex = new JLabel();//145 385
		
		//装备栏
		//头盔
		helmet = new JLabel();
		//铠甲
		armour = new JLabel("123");
		//鞋子
		shoe = new JLabel("12");
		//武器
		weapon = new JLabel("@!3");
		
		//英雄帖
		y_keys = new JLabel();//120 255 410 510
		b_keys = new JLabel();
		r_keys = new JLabel();
		
		//地图位置
		floors = new JLabel();
		//字体  粗细 字号
		lv.setFont(new   java.awt.Font("Dialog",   1,   45));
		lv.setForeground(new Color(255, 255, 255));
		hp.setFont(new   java.awt.Font("Dialog",   1,   35));
		hp.setForeground(new Color(255, 255, 255));
		attack.setFont(new   java.awt.Font("Dialog",   1,   35));
		attack.setForeground(new Color(255, 255, 255));
		defence.setFont(new   java.awt.Font("Dialog",   1,   35));
		defence.setForeground(new Color(255, 255, 255));
		money.setFont(new   java.awt.Font("Dialog",   1,   35));
		money.setForeground(new Color(255, 255, 255));
		ex.setFont(new   java.awt.Font("Dialog",   1,   35));
		ex.setForeground(new Color(255, 255, 255));
		
		y_keys.setFont(new   java.awt.Font("Dialog",   1,   35));
		y_keys.setForeground(new Color(255, 255, 255));
		b_keys.setFont(new   java.awt.Font("Dialog",   1,   35));
		b_keys.setForeground(new Color(255, 255, 255));
		r_keys.setFont(new   java.awt.Font("Dialog",   1,   35));
		r_keys.setForeground(new Color(255, 255, 255));
		
		floors.setFont(new   java.awt.Font("Dialog",   1,   55));
		floors.setForeground(new Color(254, 203, 0));
		//把JLabel都添加到labelList里面
		labelList.add(lv);
		labelList.add(hp);
		labelList.add(attack);
		labelList.add(defence);
		labelList.add(money);
		labelList.add(ex);
		labelList.add(y_keys);
		labelList.add(b_keys);
		labelList.add(r_keys);
		labelList.add(helmet);
		labelList.add(armour);
		labelList.add(shoe);
		labelList.add(weapon);
		labelList.add(floors);
		//给JLabel定好位置
		lv.setBounds(205, 100, 65, 45);
		hp.setBounds(110, 180, 160, 45);
		attack.setBounds(110, 230, 160, 45);
		defence.setBounds(110, 280, 160, 45);
		money.setBounds(110, 330, 160, 45);
		ex.setBounds(110, 380, 160, 45);
		y_keys.setBounds(120, 510, 120, 30);
		b_keys.setBounds(255, 510, 120, 30);
		r_keys.setBounds(410, 510, 120, 30);
		floors.setBounds(130,610,300,100);
		helmet.setBounds(360, 60, 70, 55);
		armour.setBounds(345, 180, 50, 130);
		shoe.setBounds(355, 375, 52, 45);
		weapon.setBounds(435,200,20,140);
		//实际上重写paint的JPanel
		Background back = new Background();
		
		//背景图
		background = ImageIO.read(new File("src/jingame/icon/info_back.png"));
		back.setBounds(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
		
		JLayeredPane layerPanel = new JLayeredPane();
		layerPanel.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		layerPanel.add(back,new Integer(1));
		//本来有好多行  体现集合的好处了   之后还要用它
		for(JLabel label : labelList)
		{
			layerPanel.add(label,new Integer(2));
		}
		this.add(layerPanel);
		/*f.add(this);
		f.pack();
		f.setVisible(true);*/
	}
	//JLabel 的  getter setter 方法
	public JLabel getLv() {
		return lv;
	}
	public void setLv(JLabel lv) {
		this.lv = lv;
	}
	public JLabel getHp() {
		return hp;
	}
	public void setHp(JLabel hp) {
		this.hp = hp;
	}
	public JLabel getAttack() {
		return attack;
	}
	public void setAttack(JLabel attack) {
		this.attack = attack;
	}
	public JLabel getDefence() {
		return defence;
	}
	public void setDefence(JLabel defence) {
		this.defence = defence;
	}
	public JLabel getEx() {
		return ex;
	}
	public void setEx(JLabel ex) {
		this.ex = ex;
	}
	public JLabel getY_keys() {
		return y_keys;
	}
	public void setY_keys(JLabel y_keys) {
		this.y_keys = y_keys;
	}
	public JLabel getB_keys() {
		return b_keys;
	}
	public void setB_keys(JLabel b_keys) {
		this.b_keys = b_keys;
	}
	public JLabel getR_keys() {
		return r_keys;
	}
	public void setR_keys(JLabel r_keys) {
		this.r_keys = r_keys;
	}
	public ArrayList<JLabel> getLabelList() {
		return labelList;
	}
	public void setLabelList(ArrayList<JLabel> labelList) {
		this.labelList = labelList;
	}
	public JLabel getHead() {
		return head;
	}
	public void setHead(JLabel head) {
		this.head = head;
	}
	public JLabel getMoney() {
		return money;
	}
	public void setMoney(JLabel money) {
		this.money = money;
	}
	public JLabel getHelmet() {
		return helmet;
	}
	public void setHelmet(JLabel helmet) {
		this.helmet = helmet;
	}
	public JLabel getArmour() {
		return armour;
	}
	public void setArmour(JLabel armour) {
		this.armour = armour;
	}
	public JLabel getShoe() {
		return shoe;
	}
	public void setShoe(JLabel shoe) {
		this.shoe = shoe;
	}

	
	public JLabel getWeapon() {
		return weapon;
	}
	public void setWeapon(JLabel weapon) {
		this.weapon = weapon;
	}
	public JLabel getFloors() {
		return floors;
	}
	public void setFloors(JLabel floors) {
		this.floors = floors;
	}


	//
	//应该是再来一个extends  JPanel 来做背景的    内部类
	class Background extends JPanel
	{
		public void paint(Graphics g)
	{
		//super.paint(g);
		g.drawImage(background, 0, 0, null);
	}
	}
	
	//更改JLabel里面数值
	/*public void setJLabel(String name, String num)
	{
		//遍历  找名字和name相同的
	}*/
	/*public static void main(String[] args) throws IOException
	{
		new Info();
	}*/
}
