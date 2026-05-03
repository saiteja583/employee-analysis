package com.company.service;

import com.company.model.Employee;

import java.util.Map;

public class EmployeeAnalyzer {

    public static void analyze(Map<Integer, Employee> employees) {

        for (Employee manager : employees.values()) {

            if (manager.getSubordinates().isEmpty()) continue;

            double avgSalary = manager.getSubordinates()
                    .stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0);

            double minAllowed = avgSalary * 1.2;
            double maxAllowed = avgSalary * 1.5;

            if (manager.getSalary() < minAllowed) {
                System.out.println("Manager earns LESS: " + manager +
                        " | Diff: " + (minAllowed - manager.getSalary()));
            }

            if (manager.getSalary() > maxAllowed) {
                System.out.println("Manager earns MORE: " + manager +
                        " | Diff: " + (manager.getSalary() - maxAllowed));
            }
        }

        // reporting line check
        for (Employee emp : employees.values()) {
            int levels = countManagers(emp, employees);

            if (levels > 4) {
                System.out.println("Long reporting line: " + emp +
                        " | Levels: " + levels +
                        " | Excess: " + (levels - 4));
            }
        }
    }

    private static int countManagers(Employee emp, Map<Integer, Employee> map) {
        int count = 0;

        while (emp.getManagerId() != null) {
            emp = map.get(emp.getManagerId());
            count++;
        }

        return count;
    }
}