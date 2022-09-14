package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entitities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		List<Employee> list = new ArrayList<>();
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter file full path: ");
			String path = sc.nextLine();
			System.out.print("Enter salary: ");
			Double salaryTarget = sc.nextDouble();
			System.out.println("Email of people whose salary is more than "+ String.format("%.2f", salaryTarget)+":");
			try (BufferedReader br = new BufferedReader(new FileReader(path))) {

				String line = br.readLine();
				while (line != null) {
					String[] fields = line.split(",");
					list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
					line = br.readLine();
				}
				List<String> emails = list.stream().filter(p -> p.getSalary()> salaryTarget).map(g -> g.getEmail()).collect(Collectors.toList());
				emails.forEach(System.out::println);
				
				double salaries = list.stream().filter(p -> p.getName().charAt(0) == 'M').map(g -> g.getSalary()).reduce(0.0,(x,y)->x+y);

				System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", salaries));

			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}
}