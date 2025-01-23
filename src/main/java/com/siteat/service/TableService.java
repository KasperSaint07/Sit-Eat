package com.siteat.service;
import java.util.List;
import com.siteat.repository.TableRepository;
import com.siteat.model.Table;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    public Table getTableById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found with ID: " + id));
    }

    public Table addTable(Table table) {
        return tableRepository.save(table);
    }

    public Table updateTable(Long id, Table updatedDetails) {
        Table table = getTableById(id);
        table.setCapacity(updatedDetails.getCapacity());
        table.setAvailable(updatedDetails.isAvailable());
        return tableRepository.save(table);
    }
    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }
}

