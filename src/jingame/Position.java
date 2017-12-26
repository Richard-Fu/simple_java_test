package jingame;

public class Position implements java.io.Serializable{
	//这个类是当前的位置信息
		//房间位置
		private GameField f;
		//也是存房间位置  不过是存的fieldList
		//private int floor;
		//当前坐标  及上一次坐标	
		//xy[0]=x xy[1]=l_x xy[2]=y  xy[3]=l_y
		//5存楼层位置  位置存档方便
		private int[] xy =new int[5];
		public GameField getF() {
			return f;
		}
		public void setF(GameField f) {
			this.f = f;
		}
		public int[] getXy() {
			return xy;
		}
		public void setXy(int[] xy) {
			this.xy = xy;
		}
		/*public int getFloor() {
			return floor;
		}
		public void setFloor(int floor) {
			this.floor = floor;
		}*/
}
