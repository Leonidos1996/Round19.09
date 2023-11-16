package projectHHFromLeonid.tracker.integration.hh;

import integration.projectHHFromLeonid.tracker.Item;
import integration.projectHHFromLeonid.tracker.MetroName;
import org.springframework.stereotype.Service;
import projectHHFromLeonid.tracker.dao.entity.Address;
import projectHHFromLeonid.tracker.dao.entity.Area;
import projectHHFromLeonid.tracker.dao.entity.Contacts;
import projectHHFromLeonid.tracker.dao.entity.Employer;
import projectHHFromLeonid.tracker.dao.entity.Metro;
import projectHHFromLeonid.tracker.dao.entity.Phone;
import projectHHFromLeonid.tracker.dao.entity.ProfessionalRole;
import projectHHFromLeonid.tracker.dao.entity.Salary;
import projectHHFromLeonid.tracker.dao.entity.Schedule;
import projectHHFromLeonid.tracker.dao.entity.Type;
import projectHHFromLeonid.tracker.dao.entity.Vacancy;
import projectHHFromLeonid.tracker.dao.repos.MetroRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityBuilder {

    //Для инжекта:
    // 1. объявляем бин в классе
    // 2. Прокидывваем его в конструктор
    private final MetroRepo metroRepo;

    public EntityBuilder(MetroRepo metroRepo) {
        this.metroRepo = metroRepo;
    }


   public Area createArea(Item item) {
       Area area = new Area(); //зачем нам здесь создавать Area, если мы уже передаем значения Item в метод
       area.setName(item.getArea().getName());
       return area;
   }

    public Address createAddress (Item item) {
       Address address = new Address();
       if (item.getAddress() != null){
           address.setBuilding(item.getAddress().getBuilding());
           address.setCity(item.getAddress().getCity());
           List<Metro> metroList = new ArrayList<>();
               for  (MetroName hhMetro : item.getAddress().getMetroStations()) {
                   Metro metro = createMetro(hhMetro);
                   metroList.add(metro);
                   metro.setName(hhMetro.getName());
               }
       }
       return address;
    }

    public Metro createMetro(MetroName metroName) {
        //Ищем в БД метро naturalId из ХХ
        Metro metroFromDatabase = metroRepo.findFirstByNaturalId(metroName.getStationId());
        if (metroFromDatabase == null) {
            //Если вернулся null, то такого метро нет в БД. Создаем новое и отдаем его
            Metro metro = new Metro();
            metro.setName(metroName.getName());
            metro.setNaturalId(metroName.getStationId());
            return metro;
        } else {
            //Если из БД вернулось метро, то используем его
            return metroFromDatabase;
        }
    }

    public Contacts createContacts (Item item) {
        Contacts contacts = new Contacts();
        if (item.getContacts() != null){
            contacts.setEmail(item.getContacts().getEmail());
            contacts.setName(item.getContacts().getName());
            List<Phone> phoneList = new ArrayList<>();
//                for (integration.projectHHFromLeonid.tracker.PhoneDTO phoneName : item.getContacts().getPhones()){
//                    Phone newPhones = new Phone();
//                    newPhones.setNumber(phoneName.getNumber());
//                    phoneList.add(newPhones);
//               }
        }
        //TODO почему установил только email
        return contacts;
    }


    public  List<ProfessionalRole> createProfessionalRole (Item item) {
        List<ProfessionalRole> professionalRolesList = new ArrayList<ProfessionalRole>();
       if (item.getProfessionalRoles() != null){
           for (integration.projectHHFromLeonid.tracker.ProfessionalRole roles : item.getProfessionalRoles()){
               ProfessionalRole professionalRole = new ProfessionalRole();
               professionalRole.setName(roles.getName());
               professionalRolesList.add(professionalRole);
           }
       }
       return professionalRolesList;
    }

    public Salary createSalary (Item item) {
       Salary salary = new Salary();
       if (item.getSalary() != null){
           salary.setStringFrom(item.getSalary().getFrom());
           salary.setStringTo(item.getSalary().getTo());
           salary.setGross(item.getSalary().isGross());
           salary.setCurrency(item.getSalary().getCurrency());
       }
       return salary;
    }


    public Employer createEmployer (Item item){
            Employer employer = new Employer();
            if (item.getEmployer() != null){
                employer.setAccredited_it_employer(item.getEmployer().isAccreditedItEmployer());
                employer.setUrl(item.getEmployer().getUrl());
                employer.setTrusted(item.getEmployer().isTrusted());
                employer.setName(item.getEmployer().getName());
            }
            return employer;
    }

    public Schedule createShedule (Item item) {
       Schedule schedule = new Schedule();
       if (item.getSchedule() != null){
           schedule.setName(item.getSchedule().getName());
       }
       return schedule;
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
       vacancy.setEmployer(createEmployer(item));
       vacancy.setSalary(createSalary(item));
       vacancy.setProfessionalRole(createProfessionalRole(item));
       vacancy.setSchedule(createShedule(item));
       vacancy.setType(createType(item));

       return vacancy;
   }

}