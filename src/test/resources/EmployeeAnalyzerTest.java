package com.company;

import com.company.model.Employee;
import com.company.service.EmployeeAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class EmployeeAnalyzerTest {

    @Test
    void testAnalyzerRuns() {
        Map<Integer, Employee> map = new HashMap<>();

        Employee ceo = new Employee(1, "CEO", "Test", 100000, null);
        Employee emp = new Employee(2, "Emp", "Test", 50000, 1);

        ceo.addSubordinate(emp);

        map.put(1, ceo);
        map.put(2, emp);

        EmployeeAnalyzer.analyze(map);
    }
}