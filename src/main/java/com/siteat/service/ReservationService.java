package com.siteat.service;
import java.util.List;
import com.siteat.model.Reservation;
import com.siteat.model.Table;
import com.siteat.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TableService tableService;

    public ReservationService(ReservationRepository reservationRepository, TableService tableService) {
        this.reservationRepository = reservationRepository;
        this.tableService = tableService;
    }

    // Возвращает список всех бронирований
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Находит бронирование по ID
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));
    }

    // Создаёт новое бронирование
    public Reservation addReservation(Reservation reservation) {
        // Проверка доступности столика перед бронированием
        Table table = tableService.getTableById(reservation.getTable().getId());
        if (!table.isAvailable()) {
            throw new RuntimeException("Table is not available for reservation.");
        }

        // Устанавливаем столик как недоступный
        table.setAvailable(false);
        tableService.updateTable(table.getId(), table);

        return reservationRepository.save(reservation);
    }

    // Удаляет бронирование
    public void deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);

        // Освобождаем столик после удаления бронирования
        Table table = reservation.getTable();
        table.setAvailable(true);
        tableService.updateTable(table.getId(), table);

        reservationRepository.deleteById(id);
    }
}
