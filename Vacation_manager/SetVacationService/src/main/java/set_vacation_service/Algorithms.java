package set_vacation_service;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Algorithms {
    final private int DAYS_IN_YEAR = 365;
    final private int YEAR;
    private int ruleNumber;
    private List<VacationedEmployee> vacationedEmployees;

    public int getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(int ruleNumber) {
        this.ruleNumber = ruleNumber;
    }




    public Algorithms(VacationedEmployee[] vacationedEmployees, int year) {
        this.vacationedEmployees = Arrays.asList(vacationedEmployees);
        YEAR = year;
    //this.vacationedEmployees = new ArrayList<VacationedEmployee>(vacationedEmployees);
    }


    private void randomizeEmployees(@NotNull List<VacationedEmployee> vacationedEmployees){
        Random random = new Random(new Date().getTime());
        int randomInt = 0;
        VacationedEmployee temp;
        for (int i = 0; i < vacationedEmployees.size(); i++) {
            randomInt = random.nextInt(vacationedEmployees.size());
            temp = vacationedEmployees.get(i);
            vacationedEmployees.add(i, vacationedEmployees.get(randomInt));
            vacationedEmployees.add(randomInt, temp);
        }
    }

    public void setVacations() throws Exception {
        if (this.ruleNumber == Rules.NO_REPETITIONS) {
            setWithNoRepetitions(this.vacationedEmployees);
        }
    }


    private void setStandard(@NotNull List<VacationedEmployee> vacationedEmployees){
        for(VacationedEmployee employee:
                vacationedEmployees
        ){
            for (int i = 0; i < employee.getNumberOfDays().length; i++) {
                //koef = emp.getNumberOfDays()[i] / (double)totalDaysCount;
                System.out.println("koef : " + koef);
                daysPool = (int) Math.floor(koef * DAYS_IN_YEAR);
                System.out.println("daysPool : " + daysPool);
                System.out.println("startVacation : " + startVacation);
                Calendar calendar = Calendar.getInstance();
                calendar.set(this.YEAR,Calendar.JANUARY ,startVacation);
                employee.getVacationDate()[i] = calendar.getTime();
                startVacation += daysPool;
            }
        }

    }

    private void setWithNoRepetitions(@NotNull List<VacationedEmployee> vacationedEmployees) throws Exception {
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
        if(totalDaysCount > DAYS_IN_YEAR) throw new Exception("невозможно расставить");
        int daysPool = 0;
        int startVacation = 1;
        for (VacationedEmployee emp :
                vacationedEmployees
        ) {
            for (int i = 0; i < emp.getNumberOfDays().length; i++) {
                koef = emp.getNumberOfDays()[i] / (double)totalDaysCount;
                System.out.println("koef : " + koef);
                daysPool = (int) Math.floor(koef * DAYS_IN_YEAR);
                System.out.println("daysPool : " + daysPool);
                System.out.println("startVacation : " + startVacation);
                Calendar calendar = Calendar.getInstance();
                calendar.set(this.YEAR,Calendar.JANUARY ,startVacation);
                emp.getVacationDate()[i] = calendar.getTime();
                startVacation += daysPool;
            }
        }

    }
    private boolean check(VacationedEmployee vacationedEmployee) {

        for (Date date :
                vacationedEmployee.getVacationDate()
        ) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            for (VacationedEmployee emp :
                    vacationedEmployees
            ) {

                for (int i = 0; i < emp.getVacationDate().length; i++) {
                    Calendar calendar1 = Calendar.getInstance();
                    Calendar calendar2 = Calendar.getInstance();
                    calendar1.setTime(emp.getVacationDate()[i]);
                    calendar2.setTime(emp.getVacationDate()[i]);
                    calendar2.add(Calendar.DAY_OF_MONTH, emp.getNumberOfDays()[i]);
                    if (calendar.after(calendar1) && calendar.before(calendar2)) {
                        return false;
                    }
                    calendar.add(Calendar.DAY_OF_MONTH,emp.getNumberOfDays()[i]);
                    if (calendar.after(calendar1) && calendar.before(calendar2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }



    //private void setWithMaxmallyEven(){

    //}

    /*
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
    }*/

    public VacationedEmployee[] getVacationedEmployees() {
        return vacationedEmployees.toArray(new VacationedEmployee[0]);
    }
}
