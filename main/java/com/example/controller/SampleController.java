### Sample Java Code

#### `src/main/java/com/example/controller/SampleController.java`
```java
package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
public class SampleController {

    @GetMapping("/test")
    public String test() {
        String result = "Database Connection Successful: ";
        try {
            Connection conn = DriverManager.getConnection(
                System.getenv("DB_URL"),
                System.getenv("DB_USER"),
                System.getenv("DB_PASSWORD")
            );

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 1");

            if (rs.next()) {
                result += rs.getInt(1);
            }

            conn.close();
        } catch (Exception e) {
            result = "Database Connection Failed: " + e.getMessage();
        }

        return result;
    }
}
