package projectHHFromLeonid.tracker;

import integration.projectHHFromLeonid.tracker.Item;
import integration.projectHHFromLeonid.tracker.MetroName;

import java.util.ArrayList;
import java.util.List;

public class ResponseHHentity {


   public Area createArea(Item item) {
       Area area = new Area(); //зачем нам здесь создавать Area, если мы уже передаем значения Item в метод
       area.setName(item.getArea().getName());
       return area;
   }

    public Address createAddress (Item item) {
       Address address = new Address();
       address.setBuilding(item.getAddress().getBuilding());
       //нужно ли сити устанавливать ?
       return address;
    }

    public Contacts createContacts (Item item) {
        Contacts contacts = new Contacts();
        contacts.setEmail(item.getContacts().getEmail());
        return contacts;
    }
        public List<Metro> createMetro(Item item) {
        List<Metro> metroList = new ArrayList<Metro>();
        for  (MetroName metroName : item.getAddress().getMetro()) {
            Metro newMetro = new Metro();
            newMetro.setName(metroName.getName());
            metroList.add(newMetro);
            //Здесь допиши свою реализацию
            //newMetro.setName(String.valueOf(metro));
        }
        return metroList;
    }

    public ProfessionalRole createProfessionalRole (Item item) {
       ProfessionalRole professionalRole = new ProfessionalRole();
       professionalRole.setName(item.getProfessionalRoles().toString());
       return professionalRole;
    }


    public Salary createSalary(Item item) {
        projectHHFromLeonid.tracker.Salary salary = new projectHHFromLeonid.tracker.Salary();
        salary.setSumm(item.getSalary().getCurrency());
        return salary;
    }

    public Shedule createShedule (Item item) {
       Shedule shedule = new Shedule();
       shedule.setName(item.getSchedule().getName());
       return shedule;
    }

    public Type createType (Item item) {
       Type type = new Type();
       type.setName(item.getType().getName());
        return type;
   }

   public Vacancies createVacancies (Item item) {
       Vacancies vacancy = new Vacancies();

       Address address = createAddress(item);
       vacancy.setAddress(address);


       //sa
       Area area = createArea(item);
       vacancy.setArea(area);

       Contacts contacts = createContacts(item);
       vacancy.setContacts(contacts);

       List<Metro> metro = createMetro(item);
       vacancy.setMetroName(metro);

       ProfessionalRole professionalRole = createProfessionalRole(item);
       vacancy.setProfessionalRole(professionalRole);

       Shedule shedule = createShedule(item);
       vacancy.setShedule(shedule);

       Type type = createType(item);
       vacancy.setType(type);

        return vacancy;
   }

}