package network.stresser;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		String choice = "";
		while (!choice.toLowerCase().equals("quit")) {
			choice = JOptionPane.showInputDialog(null, "Choose Desktop or Console: ");
			if (choice.toLowerCase().equals("desktop")) {
				StresserGUI.main(args);
				break;
			} else if (choice.toLowerCase().equals("console")) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter the name of the request file:");
				String request = sc.nextLine();
				System.out.println("Enter the name of the response file:");
				String response = sc.nextLine();
				NetworkStresserStarter stresser;
				try {
					stresser = new NetworkStresserStarter(request, response);
					sc.close();
					stresser.startStresser();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
