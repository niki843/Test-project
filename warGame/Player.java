package warGame;

import java.util.Scanner;

public class Player {
	
	private final String name;
	private final  int age;
	private final String sex;
	
	Player(String name,int age,String sex){
		Scanner sc = new Scanner(System.in);
		if(!name.isEmpty()){
			this.name = name;
		}
		else{
			do{
				System.out.println("Invalid name, try again: ");
				name = sc.nextLine();
			}while(name.isEmpty());
			this.name = name;
		}
		if(age < 0 || age > 100){
			do{
				System.out.println("Invalid age, try again: ");
				age = sc.nextInt();
			}while(age < 0 || age > 100);
			this.age = age;
		}
		else{
			this.age = age;
		}
		if(!sex.isEmpty()){
			this.sex = sex;
		}
		else{
			do{
				System.out.println("Invalid sex, try again: ");
				sex = sc.nextLine();
			}while(sex.isEmpty());
			this.sex = name;
		}
	}
	

}
