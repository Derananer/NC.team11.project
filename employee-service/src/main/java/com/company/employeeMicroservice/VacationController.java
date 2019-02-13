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
            @RequestParam(value = "days") String countOfDays
    ){
        Employee[] employees = mainController.getEmployees(departmentId);
        Map <String,List<Vacation>> empVacations = new HashMap<>();
        for (Employee emp:
                employees
             ) {
            empVacations.put(emp.getId(), vacationRepository.findByEmployeeId(emp.getId()));
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
        
    }


    public Vacation addEmptyVacation(String employeeId){
        return vacationRepository.save(new Vacation(employeeId, null, 0));
    }

}
