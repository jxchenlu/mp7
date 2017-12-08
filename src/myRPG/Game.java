
package myRPG;
import java.util.Scanner;
import java.lang.Thread;

public class Game {
	static final int MAX_NUM_ROUNDS = 10;
	static final int MAX_NUM_DAYS = 10;
    
   
    public static Monster monsterCreator(){
    	Dice d100 = new Dice(100);
    	int a = d100.roll();
    	if (a < 60) {
    		return new Monster("Slime",300,40,10,5); //怪物hp，atk，def，经验 调整一下
    	} else if (a < 90) {
    		return new Monster("Ghoul",500,80,20,5); //怪物hp，atk，def，经验 调整一下
    	} else {
    		return new Monster("Chimera",1000,120,40,5); //怪物hp，atk，def，经验 调整一下
    	}
  
    	
    }
    
	public static boolean battle(Monster a, Player b) { 
		Dice d20 = new Dice(20);
		Scanner sc2 = new Scanner(System.in);
		System.out.println("You encouter " + a.name);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int damage;
		for (int round = 1; round < MAX_NUM_ROUNDS; round++) {
			System.out.println("What do you want to do?");
			System.out.println("1. fight; 2. skill; 3. escape");
			int c = sc2.nextInt();
			while (c > 3 || c < 1) {
				System.out.println("Please enter again");
				c = sc2.nextInt();
			}
			if (c == 3) {
				System.out.println("success!");
				return true; 
			}
			if (c == 1) {
				damage = b.attack + Math.max(b.intelligence, b.strength) * b.luck - a.defence;
				if (damage < 0) {
					damage = 1;
				}
				System.out.println("You hits " + damage + " damage!");
				a.hp = a.hp + a.defence - damage;
			}
			int s1 = 0;
			if (c == 2) {
				System.out.println("Please choose the skill you want to use");
				if (b.skills[1] == null) {
					System.out.println("1. " + b.skills[0].name);
					s1 = sc2.nextInt() - 1;					
					while (s1 == 1) {
						System.out.println("Please choose the skill you want to use");
						s1 = sc2.nextInt() - 1;
					}
				} else if (b.skills[2] == null) {
					System.out.println("1. " + b.skills[0].name + " 2. " + b.skills[1].name);
					s1 = sc2.nextInt() - 1;
					while (s1 > 2 || s1 < 1) {
						System.out.println("Please choose the skill you want to use");
						s1 = sc2.nextInt() - 1;
					}
				} else if (b.skills[2] != null) {
					System.out.println("1. " + b.skills[0].name + " 2. " + b.skills[1].name
							+ " 3. " + b.skills[2].name);
					s1 = sc2.nextInt() - 1;
					while (s1 > 3 || s1 < 1) {
						System.out.println("Please choose the skill you want to use");
						s1 = sc2.nextInt() - 1;
					}
				}
				damage = b.attack + Math.max(b.intelligence, b.strength) * b.luck + b.skills[s1].attack - a.defence;
				if (damage < 0) {
					damage = 1;
				}
				System.out.println("You hits " + damage + " damage!");
				a.hp = a.hp - damage;
			}
			int damageM = a.attack - b.defence;
			if (damageM < 0) {
				damageM = 1;
			}
			System.out.println(a.name + " hits " + damageM + " damage!");
			b.bhp = b.bhp - (a.attack - b.defence);
			if (a.hp <= 0) {
				System.out.println("You beat " + a.name + "!");
				b.currentExp +=5;
				b.levelUp();
				if(d20.roll() + b.luck >= 20) {
					equipment a1 = new equipment(6);
					int roll = d20.roll();
					if (roll < 5) {
						if (b.job == "wizard") {
							a1 = new equipment(3);
						}
						a1 = new equipment(1);
					} else if (roll < 10) {
						if (b.job == "wizard") {
							a1 = new equipment(4);
						}
						a1 = new equipment(2);
					} else if (roll < 15) {
						a1 = new equipment(5);
					}
					System.out.println("You get " + a1.name + "!");
					System.out.println("Do you want to equip yourself with it?");
					System.out.println("1. yes; 2. no");
					int aq = sc2.nextInt();
					while (aq > 2 || aq < 1) {
						System.out.println("Enter again");
						aq = sc2.nextInt();
					}
					if (aq == 1) {
						a1.equip(b);
						System.out.println("Now you ATK = " + b.attack + "  DEF = " + b.defence);
					}
				}
				return true;
			}
			if (b.bhp <= 0) {
				System.out.println("You are beaten!");
				return false;
			}
		}
		sc2.close();
		if (a.hp > 0) {
			System.out.println("You are beaten!");
			return false;
		}
		return true;

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("You have ten days to beat the boss!");
		System.out.println("Player enter your name:");
		String name = sc.nextLine();
		System.out.println("Do you wnat to be a warrior or a wizard?");
		System.out.println("Enter 1 for warrior or 2 for wizard：");
		int a = sc.nextInt();
		while (a > 2 || a < 1) {
			System.out.println("Enter 1 for warrior or 2 for wizard：");
			a = sc.nextInt();
		}
		Player newPlayer = new Player(a);
		System.out.println("This is you！");
		System.out.println("HP = " + newPlayer.hp + "  " + "MP = " + newPlayer.mp + "  " 
				+ "STR = " + newPlayer.strength  + "  " + "INT = " + newPlayer.intelligence);
		System.out.println("ATK = " + newPlayer.attack  + "  " + "DEF = " + newPlayer.defence + "  " 
				+ "LUCK = " + newPlayer.luck );
		
		System.out.println("Begin your adventure!");
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int scount = 0;

		int day = 1;
		for (day = 1; day <= 8; day++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Monster mon1 = monsterCreator();
			Monster mon2 = monsterCreator();
			System.out.println("Day " + day);
			System.out.println("Your HP is " + newPlayer.bhp +"  " +  "Your MP is " + newPlayer.bmp);
			System.out.println("What do you want to do?");
			System.out.println("1. battle  2. train  3. rest");
			int a1 = sc.nextInt();
			while (a1 > 3 || a1 < 1) {
				System.out.println("Please enter again");
				a1 = sc.nextInt();
			}
			if (a1 == 1) {
				boolean nd = battle(mon1, newPlayer); 
				if (nd) {
					System.out.println("Do you want to go home?");
					System.out.println("1. yes   2. no ");
					int aq = sc.nextInt();
					while (aq > 2 || aq < 1) {
						System.out.println("Enter again");
						aq = sc.nextInt();
					}
					if (aq == 2) {
					      boolean n1d = battle(mon2, newPlayer); 
					}
					if (newPlayer.bhp < 0) {
						newPlayer.bhp = 0;
					}
				}
			} else if (a1 == 2) {
				System.out.println("You are learning skills!");
				scount += 1;
				if (newPlayer.job == "wizard") {
					if (scount == 2) {
						newPlayer.skills[1] = new Skill("法师招式二",40,10);
						System.out.println("You get the skill " + newPlayer.skills[1].name);
					} else if (scount == 4) {
						newPlayer.skills[2] = new Skill("法师招式三",60,10);
						System.out.println("You get the skill " + newPlayer.skills[2].name);
						System.out.println("You cannot learn more skills!");
					}
				} else {
					if (scount == 2) {
						newPlayer.skills[1] = new Skill("战士招式二",40,10);
						System.out.println("You get the skill " + newPlayer.skills[1].name);
					} else if (scount == 4) {
						newPlayer.skills[2] = new Skill("战士招式三",60,10);
						System.out.println("You get the skill " + newPlayer.skills[2].name);
						System.out.println("You cannot learn more skills!");
					}
				}
				
			} else {
				System.out.println("You take a rest and recover to the best condition");
				newPlayer.hp = newPlayer.bhp;
				newPlayer.mp = newPlayer.bmp;
				Dice d100 = new Dice(100);
				if (d100.roll() < 6) {
					System.out.println("You luckily find a ring!");
				}
				equipment ring = new equipment(6);
				ring.equip(newPlayer);
			}

			System.out.println();
			System.out.println();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		day = 9;
		System.out.println("休息一天，明天打怪"); // 加点剧情？？？？
		newPlayer.hp = newPlayer.bhp;
		newPlayer.mp = newPlayer.bmp;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Monster boss = new Monster("Dragon", 2, 200, 50, 0);
		boolean nd = battle(boss, newPlayer); 
		if(boss.hp <= 0) {
			System.out.println("胜利剧情");
		} else {
			System.out.println("失败剧情");
		}
		

		
		
		
		sc.close();
	}

}
