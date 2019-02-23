package employee_service.department;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping(value = "/create-new-department", method = RequestMethod.GET)
    public String createDepartment(
            @RequestParam(value = "name") String departmentName
            //@RequestParam(value = "info") String someInfo
    ) throws Exception {
        Department dep = departmentRepository.save(new Department(departmentName, null));
        if( dep == null)
            throw new Exception("didn`t save");
        else return dep.getId();



    }
}
