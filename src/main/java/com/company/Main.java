package com.company;

import com.company.model.Employee;
import com.company.service.EmployeeAnalyzer;
import com.company.util.CsvReader;

import java.io.InputStream;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
        	InputStream is = Main.class.getClassLoader().getResourceAsStream("employees.csv");
        	Map<Integer, Employee> employees = CsvReader.readEmployees(is);

            EmployeeAnalyzer.analyze(employees);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}