package set_vacation_service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import set_vacation_service.Algorithms;
import set_vacation_service.VacationedEmployee;

@Component
public class MainManager {

    @Autowired
    RestTemplate restTemplate;


    public boolean check(

    ){
        return true;
    }

    public VacationedEmployee[] generate(
             String ruleNumber,
             VacationedEmployee[] vacationedEmployees
    ){
        //HttpHeaders headers = new HttpHeaders();
        //headers.set("departments", departments);

        /*
        Map<String, String> params = new HashMap<String, String>();
        params.put("msisdn", msisdn);
        params.put("email", email);
        params.put("clientVersion", clientVersion);
        params.put("clientType", clientType);
        params.put("issuerName", issuerName);
        params.put("applicationName", applicationName);
*/

        // HttpEntity entity = new HttpEntity(headers);
        //HttpEntity<VacationedEmployee[]> response = restTemplate.exchange("http://localhost:8081/vacation-employees", HttpMethod.GET, entity, VacationedEmployee[].class);
        Algorithms algorithms = new Algorithms(vacationedEmployees, 2017);
        algorithms.setRuleNumber(Integer.parseInt(ruleNumber));
        try {
            algorithms.setVacations();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (VacationedEmployee emp:
                vacationedEmployees
        ) {
            System.out.println(emp.toString());
        }
        return algorithms.getVacationedEmployees();
        //System.out.println(response.getBody().toString());
    }

}
