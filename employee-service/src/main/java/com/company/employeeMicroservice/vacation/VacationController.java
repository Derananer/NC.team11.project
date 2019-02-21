package com.company.employeeMicroservice.vacation;


import com.company.employeeMicroservice.*;
import com.company.employeeMicroservice.employee.Employee;
import com.company.employeeMicroservice.employee.EmployeeController;
import com.company.employeeMicroservice.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/vacations")
public class VacationController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VacationRepository vacationRepository;


    @RequestMapping(value = "/update-vacation", method = RequestMethod.POST)
    public Vacation updateVac(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody Vacation vacation
    ){
        vacationRepository.deleteById(vacation.getId());
        vacation = vacationRepository.save(vacation);
        return vacation;
    }

   @RequestMapping(value = "/delete-vacation", method = RequestMethod.GET)
   public boolean deleteVac(
           @RequestHeader (value = "department") String departmentId,
           @RequestBody Vacation vacation
   ){
        vacationRepository.deleteById(vacation.getId());
        return true;
   }

    @RequestMapping(value = "/delete-vacation", method = RequestMethod.POST)
    public boolean deleteVacation(
            @RequestHeader (value = "department") String departmentId,
            @RequestBody Vacation vacation
    ){
        vacationRepository.deleteById(vacation.getId());
        return true;
    }

    @RequestMapping(value = "/set-vacation-date", method = RequestMethod.POST)
    public Vacation setDate(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody Vacation vacation
    ) throws Exception {
        try {
            if (employeeRepository.findByIdAndDepartmentId(vacation.getEmployeeId(), departmentId) == null)
                throw new Exception("Employee with id=" + vacation.getEmployeeId() + " does not exist.");
            vacation = vacationRepository.save(vacation);
            System.out.println("set vacation : " + vacation.toString());
            return vacation;
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @RequestMapping(value = "/set-days-for-all-emps", method = RequestMethod.GET)
    public void setDaysForAllEmployees(
            @RequestHeader(value = "department") String departmentId,
            @RequestParam(value = "days") int countOfDays
    ){
        EmployeeAndVacation[] employees = employeeController.getEmployees(departmentId);
        for (EmployeeAndVacation emp:
                employees
             ) {
             List<Vacation> vacations = vacationRepository.deleteByEmployeeId(emp.employee.getId());
             System.out.println("deleted vac :" );
             if(vacations.isEmpty() == false) {
                 for (Vacation vacation :
                         vacations
                 ) {
                     vacationRepository.save(new Vacation(vacation.getEmployeeId(), vacation.getDay(),vacation.getMonth(),vacation.getYear(),countOfDays));
                 }
             }
             else{
                 Vacation vacation = vacationRepository.save(new Vacation(emp.employee.getId(), Vacation.NO_DATE, Vacation.NO_DATE, Vacation.NO_DATE, countOfDays));
                 System.out.println("saved vac : "  + vacation);
             }
        }
    }

    @RequestMapping(value = "/generate-vacations", method = RequestMethod.GET)
    public EmployeeAndVacation[] generateVacations(
            @RequestHeader (value = "department") String departmentId
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.set("department", departmentId);
        HttpEntity entity = new HttpEntity(headers);
        List<EmployeeAndVacation> employeeAndVacationList = Arrays.asList(employeeController.getEmployees(departmentId));
        HttpEntity<RuledGroup[]> response = restTemplate.exchange("http://localhost:8082/groups", HttpMethod.GET, entity, RuledGroup[].class);
        for (RuledGroup ruledGroup:
                response.getBody()
        ) {
            ArrayList<VacationedEmployee> vacationedEmpls = new ArrayList<>();
            for (Employee employee :
                    employeeController.getEmployeeByGroup(ruledGroup.getGroupId(), departmentId)
            ) {
                List<Vacation> vacations = vacationRepository.findByEmployeeId(employee.getId());
                Date[] vacationDates = new Date[vacations.size()];
                int[] numbersOfVacationDays = new int[vacations.size()];
                int i = 0;
                for (Vacation vacation :
                        vacations
                ) {
                    vacationDates[i] = vacation.getVacationStartDate();
                    numbersOfVacationDays[i++] = vacation.getNumberOfDays();
                }
                vacationedEmpls.add(new VacationedEmployee(
                                employee.getId(),
                                vacationDates,
                                numbersOfVacationDays
                        )
                );
            }
            HttpHeaders headers1 = new HttpHeaders();
            headers1.set("rule", Integer.toString(ruledGroup.getRuleNumber()));
            VacationedEmployee[] vacationedEmployees = vacationedEmpls.toArray(new VacationedEmployee[0]);
            HttpEntity entity1 = new HttpEntity(vacationedEmployees, headers1);
            HttpEntity<VacationedEmployee[]> response1 = restTemplate.exchange("http://localhost:8085/generate", HttpMethod.POST, entity1, VacationedEmployee[].class);
            vacationedEmployees = response1.getBody();
            System.out.println("get from vacation-service : " + Arrays.toString(vacationedEmployees));
            for (VacationedEmployee employee :
                    vacationedEmployees
            ) {
                List<Vacation> vacations = vacationRepository.deleteByEmployeeId(employee.getEmployeeId());
                System.out.println("deleted vacs : " + Arrays.toString(vacations.toArray(new Vacation[0])));
                for (int i = 0; i < vacations.size(); i++) {
                    System.out.println("saved new vac : " + vacationRepository.save(new Vacation(employee.getEmployeeId(), employee.getVacationDate()[i], employee.getNumberOfDays()[i])));
                }
            }
        }
        List<EmployeeAndVacation> employeeAndVacations = new ArrayList<>();
/*
        for (VacationedEmployee emp:
                vacationedEmployees
             ) {
            Vacation[] vacations = new Vacation[emp.getNumberOfDays().length];
            for (int i = 0; i < emp.getNumberOfDays().length; i++) {
                System.out.println("deleted vac : " + Arrays.toString(vacationRepository.deleteByEmployeeId(emp.getEmployeeId()).toArray(new Vacation[0])));
                vacations[i] = vacationRepository.save(new Vacation(emp.getEmployeeId(), emp.getVacationDate()[i],emp.getNumberOfDays()[i]));
                System.out.println(vacations[i].toString());
            }
            employeeAndVacations.add(new EmployeeAndVacation(mainController.getEmployee(departmentId, emp.getEmployeeId()), vacations));
        }*/
        return employeeAndVacations.toArray(new EmployeeAndVacation[0]);
    }


    /*public Vacation addEmptyVacation(String employeeId){
        return vacationRepository.save(new Vacation(employeeId, null, 0));
    }*/

}
