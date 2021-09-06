package com.example.java_example;


import java.io.*;

public class Employee {
    String name, designation;
    // String designation; 支持多同类型变量声明
    int age;
    double salary;

    public Employee(String name) {
        this.name = name;
    }

    public void empAge(int empAge) {
        age = empAge;
    }
}
