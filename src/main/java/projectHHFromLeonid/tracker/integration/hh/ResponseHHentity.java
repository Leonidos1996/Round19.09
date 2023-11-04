package projectHHFromLeonid.tracker.integration.hh;

import integration.projectHHFromLeonid.tracker.Item;
import integration.projectHHFromLeonid.tracker.MetroName;
//TODO: убрать лишние импорты
import integration.projectHHFromLeonid.tracker.SalaryDTO;
import org.springframework.stereotype.Service;
//TODO: звездочек быть не должно, поменять настройки идеи
import projectHHFromLeonid.tracker.dao.entity.*;

import java.util.ArrayList;
import java.util.List;

@Service
//TODO: переименовать класс, сейчас его имя не говорит ни о чем, за что он отвечает
//TODO: убрать лишние пустые строки
public class ResponseHHentity {


   public Area createArea(Item item) {
       Area area = new Area(); //зачем нам здесь создавать Area, если мы уже передаем значения Item в метод
       area.setName(item.getArea().getName());
       return area;
   }

    public Address createAddress (Item item) {
       Address address = new Address();
       if (item.getAddress() != null){
           address.setBuilding(item.getAddress().getBuilding());
       }
       //нужно ли сити устанавливать ?
        //TODO установить city.
       return address;
    }

    public Contacts createContacts (Item item) {
        Contacts contacts = new Contacts();
        if (item.getContacts() != null){
            contacts.setEmail(item.getContacts().getEmail());
        }
        //TODO почему установил только email
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
           //TODO: переделать этот кусок. здесь в поле name устанавливается не то что нужно
           professionalRole.setName(item.getProfessionalRoles().toString());
       }
       return professionalRole;
    }

    public Salary createSalary (Item item) {
       Salary salary = new Salary();
       if (item.getSalary() != null){
           salary.setStringFrom(item.getSalary().getFrom());
           salary.setStringTo(item.getSalary().getTo());
           salary.setGross(item.getSalary().isGross());
       }
       //TODO: почему установил только getFrom, остальные поля тоже нужны
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

       vacancy.setAddress(createAddress(item));
       vacancy.setArea(createArea(item));
       vacancy.setContacts(createContacts(item));
       vacancy.setMetroName(createMetro(item));
       vacancy.setSalary(createSalary(item));
       vacancy.setProfessionalRole(createProfessionalRole(item));
       vacancy.setShedule(createShedule(item));
       vacancy.setType(createType(item));

       return vacancy;
   }

}