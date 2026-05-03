package com.company.util;

import com.company.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CsvReader {

	public static Map<Integer, Employee> readEmployees(InputStream is) throws Exception {

	    Map<Integer, Employee> map = new HashMap<>();

	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    String line;

	    br.readLine(); // skip header

	    while ((line = br.readLine()) != null) {
	        String[] parts = line.split(",");

	        int id = Integer.parseInt(parts[0]);
	        String firstName = parts[1];
	        String lastName = parts[2];
	        double salary = Double.parseDouble(parts[3]);

	        Integer managerId = (parts.length > 4 && !parts[4].isEmpty())
	                ? Integer.parseInt(parts[4])
	                : null;

	        Employee emp = new Employee(id, firstName, lastName, salary, managerId);
	        map.put(id, emp);
	    }

	    br.close();

	    // build hierarchy
	    for (Employee e : map.values()) {
	        if (e.getManagerId() != null) {
	            Employee manager = map.get(e.getManagerId());
	            manager.addSubordinate(e);
	        }
	    }

	    return map;
	}
}