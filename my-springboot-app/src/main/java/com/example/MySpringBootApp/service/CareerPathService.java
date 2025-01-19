package com.example.MySpringBootApp.service;

import org.springframework.stereotype.Service;

@Service
public class CareerPathService {

    public String generateCareerPath(String cvContent) {
        // Add logic here to generate the career path
        // For example, you can use predefined rules or a machine learning model to generate the career path

        // This is a simple example that uses predefined rules to generate a career path
        StringBuilder mindMap = new StringBuilder();
        mindMap.append("Career Path:\n");

        if (cvContent.contains("Java") && cvContent.contains("Spring")) {
            mindMap.append("Junior Developer\n");
            mindMap.append("  |\n");
            mindMap.append("  -> Mid-Level Developer\n");
            mindMap.append("      |\n");
            mindMap.append("      -> Senior Developer\n");
            mindMap.append("          |\n");
            mindMap.append("          -> Solution Architect\n");
        } else if (cvContent.contains("Python") && cvContent.contains("Django")) {
            mindMap.append("Junior Developer\n");
            mindMap.append("  |\n");
            mindMap.append("  -> Mid-Level Developer\n");
            mindMap.append("      |\n");
            mindMap.append("      -> Senior Developer\n");
            mindMap.append("          |\n");
            mindMap.append("          -> Technical Lead\n");
        } else {
            mindMap.append("Junior Developer\n");
            mindMap.append("  |\n");
            mindMap.append("  -> Mid-Level Developer\n");
            mindMap.append("      |\n");
            mindMap.append("      -> Senior Developer\n");
        }

        return mindMap.toString();
    }
}