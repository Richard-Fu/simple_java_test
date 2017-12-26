package jingame;

import java.awt.image.BufferedImage;

public class Equip implements Thing{
	//装备的父类  可以直接构造也能自己继承
	//首先有名字
	protected String name;
	//到底是哪个装备
	protected String sort;
	//然后是图片   直接存BufferedImage吧
	protected transient BufferedImage head;
	//然后是属性值
	//我想有一个可以吸血的？
	//一个偷钱的？
	//这是药
	protected int hp;
	protected int attack;
	protected int defence;
	protected int get_hp;
	protected int get_money;
	//对应英雄装备序号
	protected int num;
	public Equip() {}
	public Equip(String name,String sort,int num , BufferedImage head, int attack
			, int defence, int get_hp, int get_money) {
		this.name = name;
		this.sort = sort;
		this.num = num;
		this.head = head;
		this.attack = attack;
		this.defence = defence;
		this.get_hp = get_hp;
		this.get_money = get_money;
	}
	@Override
	public void doJob(Hero man, int i, int j) {
		//刷新Info
		//刷新hero信息
		//首先判断是哪个sort
		switch(this.sort)
		{
		case "helmet":System.out.println("asd");
		man.setHelmet(this);
		man.getNum()[9] = this.num;
		break;
		case "Armour":
			man.setArmour(this);
			man.getNum()[10] = this.num;
		break;
		case "Shoe":man.setShoe(this);
		man.getNum()[12] = this.num;
		break;
		case "Weapon":man.setWeapon(this);
		man.getNum()[11] = this.num;
		break;
		}
		man.setHp(man.getHp()+this.hp);
		man.setAttack(man.getAttack()+this.attack);
		man.setDefence(man.getDefence()+this.defence);
		man.setGet_hp(this.get_hp);
		man.getNum()[13] = this.get_hp;
		man.setGet_money(this.get_money);
		man.getNum()[14] = this.get_money;
		//该位置变成地板
		//之后可以拿个宝物  然后来个守宝物的
		Game.position.getF().things[i][j] = new Floor();
		//确定该处已被改变  
		Game.position.getF().flag[i][j] = 1;
		//调用战利品
		Game.booty.showGetEquip(this, man);
	}
	@Override
	public BufferedImage getImage() {
		// TODO 自动生成的方法存根
		return head;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BufferedImage getHead() {
		return head;
	}
	public void setHead(BufferedImage head) {
		this.head = head;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public int getGet_hp() {
		return get_hp;
	}
	public void setGet_hp(int get_hp) {
		this.get_hp = get_hp;
	}
	public int getGet_money() {
		return get_money;
	}
	public void setGet_money(int get_money) {
		this.get_money = get_money;
	}
}
