package jingame;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class Hero extends Creature implements java.io.Serializable, Cloneable{

	//用户等级
		private int lv;

		//装备栏
		//头盔
		private  Equip helmet;
		//铠甲
		private  Equip armour;
		//鞋子
		private  Equip shoe;
		//武器
		private  Equip weapon;
		
		//英雄帖
		private int y_keys;
		private int b_keys;
		private int r_keys;
		//把属性及装备存成数组
		private int[] num = new int[15];
		//初始化
		public Hero(int lv,int hp,int attack,int defence,
				int money,int ex
				,int y_keys,int b_keys, int r_keys
				, BufferedImage head)
		{
			this.lv = lv;
			this.hp = hp;
			this.attack = attack;
			this.defence = defence;
			this.money = money;
			this.ex = ex;
			this.y_keys = y_keys;
			this.b_keys = b_keys;
			this.r_keys = r_keys;
			this.head = head;
			name = "傅超人";
			
			this.num[9]=-1;
			this.num[10]=-1;
			this.num[11]=-1;
			this.num[12]=-1;
			//存装备 helmet armour weapon shoe
			//num[13] [14] get_hp get_money
		}
		
		public int[] getNum() {
			return num;
		}

		public void setNum(int[] num) {
			this.num = num;
		}

		public int getLv() {
			return lv;
		}

		public void setLv(int lv) {
			this.lv = lv;
		}

		public int getMoney() {
			return money;
		}

		public void setMoney(int money) {
			this.money = money;
		}

		public int getEx() {
			return ex;
		}

		public void setEx(int ex) {
			this.ex = ex;
		}

		public int getY_keys() {
			return y_keys;
		}

		public void setY_keys(int y_keys) {
			this.y_keys = y_keys;
		}

		public int getB_keys() {
			return b_keys;
		}

		public void setB_keys(int b_keys) {
			this.b_keys = b_keys;
		}

		public int getR_keys() {
			return r_keys;
		}

		public void setR_keys(int r_keys) {
			this.r_keys = r_keys;
		}

		public Equip getHelmet() {
			return helmet;
		}

		public void setHelmet(Equip helmet) {
			this.helmet = helmet;
		}

		public Equip getArmour() {
			return armour;
		}

		public void setArmour(Equip armour) {
			this.armour = armour;
		}

		public Equip getShoe() {
			return shoe;
		}

		public void setShoe(Equip shoe) {
			this.shoe = shoe;
		}

		public Equip getWeapon() {
			return weapon;
		}

		public void setWeapon(Equip weapon) {
			this.weapon = weapon;
		}

		/*public String toString()
		{
			return ("lv:" +lv+" hp:"+hp+" attack: "+attack);
		}*/
		public Hero clone() throws CloneNotSupportedException
		{
			return (Hero)super.clone();
		}
		public void refreshNum()
		{
			num[0] = lv;
			num[1] = hp;
			num[2] = attack;
			num[3] = defence;
			num[4] = money;
			num[5] = ex;
			num[6] = y_keys;
			num[7] = b_keys;
			num[8] = r_keys;
			num[13] = get_hp;
			num[14] = get_money;
		}
}
