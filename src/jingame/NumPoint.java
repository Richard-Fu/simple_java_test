package jingame;

public class NumPoint implements java.io.Serializable{

	//属性值  因为规定好按生命值 攻击力 防御力 经验来了  所以只需要按顺序存就行
	private int num;
	//消耗的金币
	private int money;
	//两个参数构造
	public NumPoint(int num, int money)
	{
		this.num = num;
		this.money = money;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
}
