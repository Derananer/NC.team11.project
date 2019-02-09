package com.company.employeeMicroservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private VacationRepository vacationRepository;


    @RequestMapping(value = "/set-new-date", method = RequestMethod.POST)
    public Vacation setDate(
            @RequestHeader(value = "department") String departmentId,
            @RequestBody Vacation vacation
    ){
        vacation = vacationRepository.save(vacation);
        System.out.println(vacation);
        return vacation;
    }


}
