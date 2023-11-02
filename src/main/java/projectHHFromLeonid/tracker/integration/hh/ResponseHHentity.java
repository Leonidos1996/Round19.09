package projectHHFromLeonid.tracker.integration.hh;

import integration.projectHHFromLeonid.tracker.Item;
import integration.projectHHFromLeonid.tracker.MetroName;
import integration.projectHHFromLeonid.tracker.SalaryDTO;
import org.springframework.stereotype.Service;
import projectHHFromLeonid.tracker.dao.entity.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseHHentity {


   public Area createArea(Item item) {
       Area area = new Area(); //зачем нам здесь создавать Area, если мы уже передаем значения Item в метод
       area.setName(item.getArea().getName());
       return area;
   }

    public Address createAddress (Item item) {
       Address address = new Address();
       if(item.getAddress() != null){
           address.setBuilding(item.getAddress().getBuilding());
       }
       //нужно ли сити устанавливать ?
       return address;
    }

    public Contacts createContacts (Item item) {
        Contacts contacts = new Contacts();
        if (item.getContacts() != null){
            contacts.setEmail(item.getContacts().getEmail());
        }
        return contacts;
    }
        public List<Metro> createMetro(Item item) {
        List<Metro> metroList = new ArrayList<Metro>();
        if (item.getAddress() != null) {
            for  (MetroName metroName : item.getAddress().getMetroStations()) {
                Metro newMetro = new Metro();
                newMetro.setName(metroName.getName());
                metroList.add(newMetro);

            }
        }

        return metroList;
    }

    public ProfessionalRole createProfessionalRole (Item item) {
       ProfessionalRole professionalRole = new ProfessionalRole();
       if (item.getProfessionalRoles() != null){
           professionalRole.setName(item.getProfessionalRoles().toString());
       }
       return professionalRole;
    }


    public SalaryDTO createSalary(Item item) {

        SalaryDTO  salary = new SalaryDTO();
        if (item.getSalary() != null) {
            salary.setCurrency(item.getSalary().getCurrency());
            salary.setFrom(item.getSalary().getFrom());
            salary.setTo(item.getSalary().getTo());
        }
        return salary;
    }

    public Shedule createShedule (Item item) {
       Shedule shedule = new Shedule();
       if (item.getSchedule() != null){
           shedule.setName(item.getSchedule().getName());
       }
       return shedule;
    }

    public Type createType (Item item) {
       Type type = new Type();
       if (item.getType() != null){
           type.setName(item.getType().getName());
       }
        return type;
   }

   public Vacancy createVacancies (Item item) {
       Vacancy vacancy = new Vacancy();

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