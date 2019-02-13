package com.vacationalgoservice.vacationalgoservice;

import java.time.Year;
import java.util.*;

public class Algorithms {
    final private int DAYS_IN_YEAR = 365;
    final private int YEAR;

    private List<VacationedEmployee> vacationedEmployees;


    public Algorithms(VacationedEmployee[] vacationedEmployees, int year) {
        this.vacationedEmployees = Arrays.asList(vacationedEmployees);
        YEAR = year;
    //this.vacationedEmployees = new ArrayList<VacationedEmployee>(vacationedEmployees);
    }



    public void setVacations() throws Exception {
        Map<String,List<VacationedEmployee>> map = sortByGroupId();
        for (String key:
                map.keySet()
             ) {
            if(map.get(key).get(0).getRuleNumber() == Rules.NO_REPETITIONS){
                setWithNoRepetitions(map.get(key));
            }
        }
    }



    private void setWithNoRepetitions(List<VacationedEmployee> vacationedEmployees) throws Exception {
        int totalDaysCount = 0;
        double koef = 0;
        //int totalVacationCount = 0;
        for (VacationedEmployee emp :
                vacationedEmployees
        ) {
            for (int count : emp.getNumberOfDays()) {
                totalDaysCount += count;
            }
        }
        if(totalDaysCount > DAYS_IN_YEAR) throw new Exception("невозможна расставить");
        int daysPool = DAYS_IN_YEAR / vacationedEmployees.size();
        int startVacation =0;
        for (VacationedEmployee emp :
                vacationedEmployees
        ) {
            for (int i = 0; i < emp.getNumberOfDays().length; i++) {
                koef = emp.getNumberOfDays()[i] / totalDaysCount;
                daysPool = (int) Math.floor(koef * DAYS_IN_YEAR);
                emp.getVacationDate()[i] = new Date(new Date(YEAR, 0, startVacation).getTime());
                startVacation += daysPool;
            }
        }

    }

    private void setWithMaxmallyEven(){

    }

    private Map<String,List<VacationedEmployee>> sortByGroupId(){
        Map<String,List<VacationedEmployee>> map = new HashMap<>();
        Set<String> groupIdSet = new HashSet<>();
        for (VacationedEmployee vacationedEmployee:
                this.vacationedEmployees
             ) {

            if(map.containsKey(vacationedEmployee.getGroupId())){
                map.get(vacationedEmployee.getGroupId()).add(vacationedEmployee);
            }
            else{
                map.put(vacationedEmployee.getGroupId(),new ArrayList<>());
                map.get(vacationedEmployee.getGroupId()).add(vacationedEmployee);
            }

            //groupIdSet.add(vacationedEmployee.getGroupId());
        }
        for (List<VacationedEmployee> list:
                map.values()
        ) {
            System.out.println(Arrays.toString(list.toArray(new VacationedEmployee[0])));
        }

        //System.out.println("set of groupIds " + Arrays.toString(map.keySet().toArray(new String[0])));
        return map;
    }

    public VacationedEmployee[] getVacationedEmployees() {
        return vacationedEmployees.toArray(new VacationedEmployee[0]);
    }
}
