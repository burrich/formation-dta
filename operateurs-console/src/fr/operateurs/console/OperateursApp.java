package fr.operateurs.console;

import java.util.Scanner;

public class OperateursApp {

	public static void main(String[] args) {
		double number1, number2, result;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("****** Application Opérateurs *****");
		System.out.println("Veuillez saisir le premier nombre...");
		number1 = sc.nextDouble();
		System.out.println("Veuillez saisir le second nombre...");
		number2 = sc.nextDouble();
		
		result = number1 + number2;
		System.out.println(number1 + " + " + number2 + " = " + result);
		result = number1 - number2;
		System.out.println(number1 + " - " + number2 + " = " + result);
		result = number1 * number2;
		System.out.println(number1 + " * " + number2 + " = " + result);
		result = number1 / number2;
		System.out.println(number1 + " / " + number2 + " = " + result);
		result = number1 % number2;
		System.out.println(number1 + " % " + number2 + " = " + result);
		
		sc.close();
	}

}
