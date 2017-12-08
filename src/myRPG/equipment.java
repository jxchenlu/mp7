package myRPG;

public class equipment {
	
	String name;
	int eattack;
	int edefence;
	int eluck;
	int num;
	int seriesnumber;
	// 数值名字编一下

	public equipment(int n) {
		if (n == 1) {
			name = "a";
			eattack = 20;
			edefence = 0;
			eluck = 0;
			num = 0;
		} // 低级武器 战士 a:名字
		if (n == 2) {
			name = "b";
			eattack = 40;
			edefence = 0;
			eluck = 0;
			num = 0;
		} // 高级武器 战士 b：名字
		if (n == 3) {
			name = "c";
			eattack = 20;
			edefence = 0;
			eluck = 0;
			num = 0;
		} // 低级武器 法师 c:名字
		if (n == 4) {
			name = "d";
			eattack = 40;
			edefence = 0;
			eluck = 0;
			num = 0;
		} // 高级武器 法师 d：名字
		if (n == 5) {
			name = "e";
			eattack = 0;
			edefence = 10;
			eluck = 0;
			num = 1;
		} // 低级衣服 e：名字
		if (n == 6) {
			name = "f";
			eattack = 0;
			edefence = 20;
			eluck = 0;
			num = 1;
		} // 高级衣服 f：名字
		if (n == 7) {
			name = "q";
			eattack = 0;
			edefence = 0;
			eluck = 5;
			num = 2;
		} // 首饰
		

	}
	
	public void equip(Player a){
		if(a.equipment[this.num] != null) {
			a.attack += (eattack - a.equipment[this.num].eattack);
			a.defence += (edefence - a.equipment[this.num].edefence);
			a.luck += (eluck - a.equipment[this.num].eluck);
		}
		a.attack += eattack;
		a.defence += edefence;
		a.luck += eluck;
		a.equipment[this.num] = this;
	}

}
