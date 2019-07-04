package employee_service.vacations.controllers;


import employee_service.EmployeeAndVacation;
import employee_service.vacations.model.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// NEED REFACTOR
@RestController
@RequestMapping("/vacations")
public class VacationController {

    @Autowired
    VacationController vacationController;


    @RequestMapping(value = "/update-vacation", method = RequestMethod.POST)
    public Vacation updateVac(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody Vacation vacation
    ){
        return vacationController.updateVac(departmentId, vacation);
    }

   @RequestMapping(value = "/delete-vacation", method = RequestMethod.GET)
   public boolean deleteVac(
           @RequestHeader (value = "department") String departmentId,
           @RequestBody Vacation vacation
   ){
        return vacationController.deleteVac(departmentId, vacation);
   }

    @RequestMapping(value = "/delete-vacation", method = RequestMethod.POST)
    public boolean deleteVacation(
            @RequestHeader (value = "department") String departmentId,
            @RequestBody Vacation vacation
    ){
        return vacationController.deleteVacation(departmentId, vacation);
    }

    @RequestMapping(value = "/set-vacation-date", method = RequestMethod.POST)
    public Vacation setDate(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody Vacation vacation
    ) throws Exception {
        return vacationController.setDate(departmentId, vacation);
    }

    @RequestMapping(value = "/set-days-for-all-emps", method = RequestMethod.GET)
    public void setDaysForAllEmployees(
            @RequestHeader(value = "department") String departmentId,
            @RequestParam(value = "days") int countOfDays
    ){
        setDaysForAllEmployees(departmentId, countOfDays);
    }

    @RequestMapping(value = "/generate-vacations", method = RequestMethod.GET)
    public EmployeeAndVacation[] generateVacations(
            @RequestHeader (value = "department") String departmentId
    ){
        return vacationController.generateVacations(departmentId);
    }


    /*public Vacation addEmptyVacation(String employeeId){
        return vacationRepository.save(new Vacation(employeeId, null, 0));
    }*/

}
