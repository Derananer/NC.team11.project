package employee_service.departments.manager;


import employee_service.departments.model.Department;
import employee_service.departments.model.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class DepartmentManager {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RestTemplate restTemplate;

    public DepartmentManager() {
    }

    public String createDepartment(
            @RequestParam(value = "name") String departmentName
            //@RequestParam(value = "info") String someInfo
    ) throws Exception {
        Department dep = departmentRepository.save(new Department(departmentName, null));
        Map<String,String> request = new HashMap<>();
        request.put("department", dep.getId());
        String standardGroupId = restTemplate.getForObject("http://localhost:8082/create-standard-group", String.class, request);
        departmentRepository.deleteById(dep.getId());
        dep.setStandardGroupId(standardGroupId);
        departmentRepository.save(dep);
        if( dep == null)
            throw new Exception("didn`t save");
        else return dep.getId();
    }
}