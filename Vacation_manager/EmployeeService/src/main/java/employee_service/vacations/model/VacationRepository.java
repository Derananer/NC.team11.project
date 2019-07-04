package employee_service.vacations.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VacationRepository extends MongoRepository<Vacation, String> {

    List<Vacation> findByEmployeeId(String employeeId);
    List<Vacation> deleteByEmployeeId(String employeeId);
}
