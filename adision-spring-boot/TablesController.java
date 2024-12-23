package com.example.adision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tables")
@CrossOrigin(origins = "http://10.0.2.2")
public class TablesController {

    @Autowired
    TablesRepository tablesRepository;

    @PutMapping("/{id}")
    public ResponseEntity<Tables> updateTables(@PathVariable int id, @RequestBody Tables table) {
        Optional<Tables> existingTable = tablesRepository.findById(id);
        if (!existingTable.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Tables updatedTable = existingTable.get();
        updatedTable.setState(table.getState());

        Tables savedTable = tablesRepository.save(updatedTable);
        return ResponseEntity.ok(savedTable);
    }

    @GetMapping
    public ResponseEntity<List<Tables>> getAllTables() {
        List<Tables> tables = tablesRepository.findAll();
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tables> getTableById(@PathVariable int id) {
        Optional<Tables> table = tablesRepository.findById(id);
        return table.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
