package com.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/test-db")
public class DbTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/connection")
    public String testConnection() {
        try {
            String dbName = jdbcTemplate.queryForObject(
                "SELECT current_database()", String.class);
            String schema = jdbcTemplate.queryForObject(
                "SELECT current_schema()", String.class);
            return "✅ Conexión exitosa a Supabase!<br>Base de datos: " + dbName + "<br>Schema: " + schema;
        } catch (Exception e) {
            return "❌ Error de conexión: " + e.getMessage();
        }
    }

    @GetMapping("/tables")
    public List<String> listTables() {
        return jdbcTemplate.queryForList(
            "SELECT table_name FROM information_schema.tables WHERE table_schema = 'gestock'", 
            String.class);
    }
}