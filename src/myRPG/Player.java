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
    public int bhp; //current hp
    int bmp; //current mp
    Skill[] skills = new Skill[3];
    equipment[] equipment = new equipment[3];
    int count;
    String job;
    int[] explevel = {10,20,30,40,50,60,70,80};
    int currentExp;
    

	public Player(int j) { //create the warrior
		if (j == 1) {
			this.job = "warrior";
			level = 1;
			strength = (int)(Math.random() * 10 + 20);
			intelligence = (int)(Math.random() * 5);
			hp = 30 + strength*5;
			mp = 20 + intelligence*5;
			bhp = hp;
			bmp = mp;
			attack = strength * 10;
			defence = 5 * level;
			luck = 25 - strength - intelligence;
			skills[0] = new Skill("You have level 1 skill: Straight Cut",20,5);
			count = 0;
		}
		if (j == 2) {//create the wizard
			this.job = "wizard";
			level = 1;
			strength = (int)(Math.random() * 5);
			intelligence = (int)(Math.random() * 10 + 20);
			hp = 20 + strength * 5;
			mp = 30 + intelligence  *5;
			bhp = hp;
			bmp = mp;
			attack = intelligence * 10;
			defence = 5 * level;
			luck = 25 - strength - intelligence;
			skills[0] = new Skill("You have level 1 skill: Fire Ball",20,5);;
		}
	}
	
	public void levelUp() { 
		if (currentExp >= explevel[level]) {
			currentExp = currentExp - explevel[level];
			level += 1;
			System.out.println("Level Up!");
			if(job == "warrior") {
				strength += 5;
				intelligence += 2;
				bhp += 100;
				bmp += 100;
				hp = bhp;
				mp = bmp;
				attack += 50;
				defence += 50;
			} else if(job == "wizard") {
				strength += 2;
				intelligence += 5;
				bhp += 100;
				bmp += 100;
				hp = bhp;
				mp = bmp;
				attack += 50;
				defence += 50;
			}
		}
		
		
	}
	


}
