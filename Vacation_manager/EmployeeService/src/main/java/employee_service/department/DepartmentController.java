package employee_service.department;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/create-new-department", method = RequestMethod.GET)
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
