package projectHHFromLeonid.tracker.integration.hh;

import integration.projectHHFromLeonid.tracker.AreaDTO;
import integration.projectHHFromLeonid.tracker.EmployerDTO;
import integration.projectHHFromLeonid.tracker.Item;
import integration.projectHHFromLeonid.tracker.MetroDTO;
import integration.projectHHFromLeonid.tracker.PhoneDTO;

import integration.projectHHFromLeonid.tracker.ProfessionalRoleDTO;
import integration.projectHHFromLeonid.tracker.ScheduleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityBuilder.class);

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

    public Area createArea(AreaDTO areaDTO) {

        if (areaDTO != null){
            LOGGER.info("Work with area name - [{}]", areaDTO.getName());
            Area areaFromDates = areaRepo.findFirstByNaturalId(areaDTO.getId());
            if (areaFromDates == null) {
                LOGGER.info("Area with id - [{}] not found in db", areaDTO.getName());
                Area area = new Area();
                area.setName(areaDTO.getName());
                area.setNaturalId(areaDTO.getId());
                return area;
            } else {
                LOGGER.info("Area with id - [{}] was found in db");
                return areaFromDates;
            }
        }
            return null;
    }


    public Address createAddress(Item item) {
        //Пустой Address не имеет смысла возвращать, поэтому вернем объект только в случае если он нам пришел
        //иначе null
        if (item.getAddress() != null) {
            Address address = new Address();
            address.setBuilding(item.getAddress().getBuilding());
            address.setCity(item.getAddress().getCity());

            //Проверяем есть ли что-то в списке с Metro
            if (!item.getAddress().getMetroStations().isEmpty()) {
                //Создаем метро лист в случае если что-то есть
                List<Metro> metroList = new ArrayList<>();
                for (MetroDTO hhMetro : item.getAddress().getMetroStations()) {
                    Metro metro = createMetro(hhMetro);
                    metroList.add(metro);
                }
                address.getMetroStations().addAll(metroList);
            }
            return address;
        }
        return null;
    }



    public Phone createPhone(PhoneDTO phoneName) {
        //TODO: здесь ошибки нужно переписать
        Phone phone1 = new Phone();
        //TODO: В чем смысл этой проверки?
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
        //TODO: Почему из метода возращается пустой phone1, а не телефон из базы или заново созданный
        return phone1;

    }

    //TODO: Переписать как createAddress
    public Contacts createContacts(Item item) {
        Contacts contacts = new Contacts();
        if (item.getContacts() != null) {
            contacts.setEmail(item.getContacts().getEmail());
            contacts.setName(item.getContacts().getName());
            List<Phone> phoneList = new ArrayList<>();

            for (PhoneDTO phoneDTO : item.getContacts().getPhones()) {
                Phone phone = createPhone(phoneDTO);
                phoneList.add(phone);
            }
            contacts.getPhones().addAll(phoneList);
        }
        return contacts;
    }


    public List<ProfessionalRole> createProfessionalRole(List<ProfessionalRoleDTO> dtoList) {
        List<ProfessionalRole> profList = new ArrayList<ProfessionalRole>();
        //из ответа хх мы проходимся коллекцией листа? Далее вставляем
        for (ProfessionalRoleDTO role : dtoList) {
            //проходимся по всему мешку мандаринов
            ProfessionalRole dbRole = professionalRoleRepo.findFirstByNaturalId(role.getName());
            // далее объявляем объект нового имени в бд
            if (dbRole == null) {
                //если база не пустая, то создаем новый объект роли, если что-то есть, возвращаем это значение
                ProfessionalRole newRole = new ProfessionalRole();
                newRole.setName(role.getName());
                profList.add(newRole);
                // return profList;
            } else {
                profList.add(dbRole);
            }
        }
        return profList;
    }

    public Metro createMetro(MetroDTO metroDTO) {
        //Ищем в БД метро naturalId из ХХ
        if (metroDTO != null) {
            Metro dbMetro = metroRepo.findFirstByNaturalId(metroDTO.getStationId());
            if (dbMetro == null) {
                //Если вернулся null, то такого метро нет в БД. Создаем новое и отдаем его
                Metro metro = new Metro();
                metro.setName(metroDTO.getStationName());
                metro.setNaturalId(metroDTO.getStationId());
                return metro;
            } else {
                //Если из БД вернулось метро, то используем его
                return dbMetro;
            }
        }
        return null;
    }

    //TODO: переписать, те же ошибки что и в createPhone
    public Employer createEmployer(EmployerDTO employerName) {
         Employer employer1 = new Employer();
        if (employerName != null) {
            Employer employerFromDataBase = employerRepo.findFirstByNaturalId(employerName.getNaturalId());
            if(employerFromDataBase == null){
                Employer employerNew = new Employer();
                employerNew.setAccredited_it_employer(employerName.isAccreditedItEmployer());
                employerNew.setUrl(employerName.getUrl());
                employerNew.setTrusted(employerName.isTrusted());
                employerNew.setName(employerName.getName());
                return employerNew;
            } else {
                return employerFromDataBase;
            }
        }
        return employer1;
    }


    public Schedule createShedule(ScheduleDTO scheduleDTO) {
        if (scheduleDTO != null) {
            Schedule schedule = new Schedule();
            schedule.setName(scheduleDTO.getName());
            return schedule;
        }
        return null;
    }

    //TODO: переделать как createShedule
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
        vacancy.setEmployer(createEmployer(item.getEmployer()));
        vacancy.setSalary(createSalary(item));
        vacancy.setProfessionalRole(createProfessionalRole(item.getProfessionalRoles()));
        vacancy.setSchedule(createShedule(item.getSchedule()));
        vacancy.setType(createType(item));
        vacancy.setNaturalId(item.getId());
        return vacancy;
    }
}