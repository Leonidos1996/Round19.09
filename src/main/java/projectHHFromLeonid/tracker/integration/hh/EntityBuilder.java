package projectHHFromLeonid.tracker.integration.hh;

import integration.projectHHFromLeonid.tracker.Item;
import integration.projectHHFromLeonid.tracker.MetroName;
import integration.projectHHFromLeonid.tracker.PhoneDTO;
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
import projectHHFromLeonid.tracker.dao.repos.AddressRepo;
import projectHHFromLeonid.tracker.dao.repos.AreaRepo;
import projectHHFromLeonid.tracker.dao.repos.ContactsRepo;
import projectHHFromLeonid.tracker.dao.repos.EmployerRepo;
import projectHHFromLeonid.tracker.dao.repos.MetroRepo;
import projectHHFromLeonid.tracker.dao.repos.PhoneRepo;
import projectHHFromLeonid.tracker.dao.repos.ProfessionalRoleRepo;
import projectHHFromLeonid.tracker.dao.repos.SalaryRepo;
import projectHHFromLeonid.tracker.dao.repos.ScheduleRepo;
import projectHHFromLeonid.tracker.dao.repos.TypeRepo;
import projectHHFromLeonid.tracker.dao.repos.VacancyRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityBuilder {

    //Для инжекта:
    // 1. объявляем бин в классе
    // 2. Прокидывваем его в конструктор
    private final MetroRepo metroRepo;
    private final AddressRepo addressRepo;
    private final AreaRepo areaRepo;
    private final ContactsRepo contactsRepo;
    private final EmployerRepo employerRepo;
    private final PhoneRepo phoneRepo;
    private final ProfessionalRoleRepo professionalRoleRepo;
    private final SalaryRepo salaryRepo;
    private final ScheduleRepo scheduleRepo;
    private final TypeRepo typeRepo;
    private final VacancyRepo vacancyRepo;


    public EntityBuilder(MetroRepo metroRepo, AddressRepo addressRepo, AreaRepo areaRepo, ContactsRepo contactsRepo, EmployerRepo employerRepo, PhoneRepo phoneRepo, ProfessionalRoleRepo professionalRoleRepo, SalaryRepo salaryRepo, ScheduleRepo scheduleRepo, TypeRepo typeRepo, VacancyRepo vacancyRepo) {
        this.metroRepo = metroRepo;
        this.addressRepo = addressRepo;
        this.areaRepo = areaRepo;
        this.contactsRepo = contactsRepo;
        this.employerRepo = employerRepo;
        this.phoneRepo = phoneRepo;
        this.professionalRoleRepo = professionalRoleRepo;
        this.salaryRepo = salaryRepo;
        this.scheduleRepo = scheduleRepo;
        this.typeRepo = typeRepo;
        this.vacancyRepo = vacancyRepo;
    }


    public Salary createSalary(Item item) {
        Salary salary = new Salary();
        if (item.getSalary() != null) {
            salary.setStringFrom(item.getSalary().getFrom());
            salary.setStringTo(item.getSalary().getTo());
            salary.setGross(item.getSalary().isGross());
            salary.setCurrency(item.getSalary().getCurrency());
        }
        return salary;
    }

    public Area createArea(integration.projectHHFromLeonid.tracker.Area areaName) {
        Area areaFromDates = areaRepo.findFirstByNaturalId(areaName.getName());
        if (areaName != null && areaFromDates == null) {
            Area area = new Area();
            area.setName(areaName.getName());
            area.setNaturalId(area.getNaturalId());
            return area;
        } else {
            return areaFromDates;
        }
    }


    public Address createAddress(Item item) {
        Address address = new Address();
        if (item.getAddress() != null) {
            address.setBuilding(item.getAddress().getBuilding());
            address.setCity(item.getAddress().getCity());
            List<Metro> metroList = new ArrayList<>();
            for (MetroName hhMetro : item.getAddress().getMetroStations()) {
                Metro metro = createMetro(hhMetro);
                metroList.add(metro);
            }
        }
        return address;
    }



    public Phone createPhone(PhoneDTO phoneName) {
        Phone phone1 = new Phone();
        if (phoneName != null) {
            Phone phoneFromDataBase = phoneRepo.findFirstByNaturalId(phoneName.getNumber());
            if (phoneFromDataBase == null) {
                Phone phone = new Phone();
                phone.setNumber(phoneName.getNumber());
                return phone;
            } else {
                return phoneFromDataBase;
            }
        }
        return phone1;

    }

    public Contacts createContacts(Item item) {
        Contacts contacts = new Contacts();
        if (item.getContacts() != null) {
            contacts.setEmail(item.getContacts().getEmail());
            contacts.setName(item.getContacts().getName());
            List<Phone> phoneList = new ArrayList<>();
            for (integration.projectHHFromLeonid.tracker.PhoneDTO phoneName : item.getContacts().getPhones()) {
                Phone phone = createPhone(phoneName);
                phoneList.add(phone);
            }
        }
        //TODO почему установил только email
        return contacts;
    }


    public List<ProfessionalRole> createProfessionalRole(List<integration.projectHHFromLeonid.tracker.ProfessionalRole> professionalRoleNameList) {
        List<ProfessionalRole> profList = new ArrayList<ProfessionalRole>();
        if (professionalRoleNameList != null) {

        //из ответа хх мы проходимся коллекцией листа? Далее вставляем
        for (integration.projectHHFromLeonid.tracker.ProfessionalRole role : professionalRoleNameList) {
            ProfessionalRole pRoleFromDataBase = professionalRoleRepo.findFirstByNaturalId(role.getNaturalId());
            if (pRoleFromDataBase == null) {
                ProfessionalRole newRole = new ProfessionalRole();
                newRole.setName(role.getName());
                profList.add(newRole);
                return (List<ProfessionalRole>) newRole;
            } else {
                return (List<ProfessionalRole>) pRoleFromDataBase;
            }
        }
        return profList;
        }
        return profList;
    }

    public Metro createMetro(MetroName metroName) {
        Metro metro2 = new Metro();
        //Ищем в БД метро naturalId из ХХ
        if (metroName != null) {
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
        return metro2;
    }

    public Employer createEmployer(integration.projectHHFromLeonid.tracker.Employer employerName) {
        Employer employer1 = new Employer();
        if (employerName != null) {
            Employer employerFromDataBase = employerRepo.findFirstByNaturalId(employerName.getNaturalId());
            if(employerFromDataBase == null){
                Employer employerNew = new Employer();
                employerNew.setAccredited_it_employer(employerName.isAccreditedItEmployer());
                employerNew.setUrl(employerName.getUrl());
                employerNew.setTrusted(employerName.isTrusted());
                employerNew.setName(employerName.getName());
            }

        }
        return employer1;
    }

    public Schedule createShedule(Item item) {
        Schedule schedule = new Schedule();
        if (item.getSchedule() != null) {
            schedule.setName(item.getSchedule().getName());
        }
        return schedule;
    }

    public Type createType(Item item) {
        Type type = new Type();
        if (item.getType() != null) {
            type.setName(item.getType().getName());
        }
        return type;
    }

    public Vacancy createVacancies(Item item) {
        Vacancy vacancy = new Vacancy();
        vacancy.setAddress(createAddress(item));
        vacancy.setArea(createArea(item.getArea()));
        vacancy.setContacts(createContacts(item));
       // vacancy.setEmployer(createEmployer(item));
        vacancy.setSalary(createSalary(item));
      //  vacancy.setProfessionalRole(createProfessionalRole(item));
        vacancy.setSchedule(createShedule(item));
        vacancy.setType(createType(item));
        return vacancy;
    }

}