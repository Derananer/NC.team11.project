package com.company.employeeMicroservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private MainController mainController;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private VacationRepository vacationRepository;


    @RequestMapping(value = "/set-date-and-days", method = RequestMethod.POST)
    public Vacation setDate(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody Vacation vacation
    ){
        vacation = vacationRepository.save(vacation);
        System.out.println(vacation);
        return vacation;
    }

    @RequestMapping(value = "/set-days-for-all-emps", method = RequestMethod.GET)
    public void setDaysForAllEmployees(
            @RequestHeader(value = "department") String departmentId,
            @RequestParam(value = "days") int countOfDays
    ){
        Employee[] employees = mainController.getEmployees(departmentId);
        Map <String,List<Vacation>> empVacations = new HashMap<>();
        for (Employee emp:
                employees
             ) {
             List<Vacation> vacations = vacationRepository.deleteByEmployeeId(emp.getId());
             System.out.println("deleted vac :" );
             if(vacations.isEmpty() == false) {
                 for (Vacation vacation :
                         vacations
                 ) {
                     vacationRepository.save(new Vacation(vacation.getEmployeeId(), vacation.getVacationDate(), countOfDays));
                 }
             }
             else{
                 Vacation vacation = vacationRepository.save(new Vacation(emp.getId(), null, countOfDays));
                 System.out.println("saved vac : " );
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
        HttpEntity<RuledGroup[]> response = restTemplate.exchange("http://localhost:8082/groups", HttpMethod.GET, entity, RuledGroup[].class);
        ArrayList<VacationedEmployee> vacationedEmpls = new ArrayList<>();
        for (RuledGroup ruledGroup:
                response.getBody()
        ) {
            for(Employee employee :
                    mainController.getEmployeeByGroup(ruledGroup.getGroupId(),departmentId)
            ) {
                List<Vacation> vacations = vacationRepository.findByEmployeeId(employee.getId());
                Date[] vacationDates = new Date[vacations.size()];
                int[] numbersOfVacationDays = new int[vacations.size()];
                int i = 0;
                for (Vacation vacation:
                        vacations
                ) {
                    vacationDates[i] = vacation.getVacationDate();
                    numbersOfVacationDays[i++] = vacation.getNumberOfDays();
                }
                vacationedEmpls.add(new VacationedEmployee(
                                employee.getId(),
                                departmentId,
                                vacationDates,
                                numbersOfVacationDays,
                                ruledGroup.getRuleNumber(),
                                ruledGroup.getGroupId()
                        )
                );
            }
        }
        VacationedEmployee[] vacationedEmployees = vacationedEmpls.toArray(new VacationedEmployee[0]);
        vacationedEmployees = restTemplate.postForObject("http://localhost:8085/generate", vacationedEmployees, VacationedEmployee[].class);
        System.out.println("get from vacation-service : " +  Arrays.toString(vacationedEmployees));
        List<EmployeeAndVacation> employeeAndVacations = new ArrayList<>();
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
        }
        return employeeAndVacations.toArray(new EmployeeAndVacation[0]);
    }


    public Vacation addEmptyVacation(String employeeId){
        return vacationRepository.save(new Vacation(employeeId, null, 0));
    }

}
