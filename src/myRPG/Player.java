package myRPG;

public class Player {
	
	String name;
    int level;
    int strength;
    int intelligence;
    int hp;
    int mp;
    int attack;
    int defence;
    int luck;
    int bhp; //当前血量
    int bmp; //当前法力
    Skill[] skills = new Skill[3];
    equipment[] equipment = new equipment[3];
    int count;
    String job;
    int[] explevel = {10,20,30,40,50,60,70,80};
    int currentExp;
    

	public Player(int j) { //创建人物 数值方面调整一下 招式名编一下
		if (j == 1) {
			this.job = "warrior";
			level = 1;
			strength = (int)(Math.random() * 15);
			intelligence = (int)(Math.random() * 5);
			hp = 30 + strength*5;
			mp = 20 + intelligence*5;
			bhp = hp;
			bmp = mp;
			attack = strength * 10;
			defence = 5 * level;
			luck = 25 - strength - intelligence;
			skills[0] = new Skill("战士招式1",20,5);
			count = 0;
		}
		if (j == 2) {
			this.job = "wizard";
			level = 1;
			strength = (int)(Math.random() * 5);
			intelligence = (int)(Math.random() * 15);
			hp = 20 + strength * 5;
			mp = 30 + intelligence  *5;
			bhp = hp;
			bmp = mp;
			attack = intelligence * 10;
			defence = 5 * level;
			luck = 25 - strength - intelligence;
			skills[0] = new Skill("法师招式1",20,5);;
		}
	}
	
	public void levelUp() { //升级 数值方面调整
		if (currentExp >= explevel[level]) {
			currentExp = currentExp - explevel[level];
			level += 1;
			System.out.println("Level Up!");
			if(job == "warrior") {
				strength += 5;
				intelligence += 2;
				bhp += strength*5;
				bmp += intelligence*5;
				hp = bhp;
				mp = bmp;
				attack += 25;
				defence += 5;
			} else if(job == "wizard") {
				strength += 2;
				intelligence += 5;
				bhp += strength*5;
				bmp += intelligence*5;
				hp = bhp;
				mp = bmp;
				attack += 25;
				defence += 5;
			}
		}
		
		
	}
	


}
