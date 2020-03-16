package com.gojek.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

import com.gojek.CommandServices.CommandEvaluator;

@SpringBootApplication
public class GoJekAppMain {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Press 1 for interacting experience");
		System.out.println("Press 2 for file based");

		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("Please enter Exit to quit or else proceed with command");
			while (scanner.hasNext()) {
				String enteredCommand = scanner.nextLine();
				if (enteredCommand.equalsIgnoreCase("Exit")) {
					return;
				} else {
					//System.out.println("The command entered is " + enteredCommand);
					if(!StringUtils.isEmpty(enteredCommand)) {
						CommandEvaluator.consume(enteredCommand);
					}
					
				}
			}
			break;

		case 2:
			System.out.println("Please enter the fileName from where to read commands");
			String fileName = scanner.next();
			File inputFile = new File(fileName);
			try {
				BufferedReader br = new BufferedReader(new FileReader(inputFile));
				String line;
				try {
					while ((line = br.readLine()) != null) {
						line = line.trim();
						CommandEvaluator.consume(line);
					}
				} catch (IOException ex) {
					System.out.println("Error in reading the input file.");
					ex.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found in the path specified.");
				e.printStackTrace();
			}
		}

	}

}
