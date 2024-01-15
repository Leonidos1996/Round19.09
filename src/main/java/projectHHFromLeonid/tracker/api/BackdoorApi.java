package projectHHFromLeonid.tracker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import projectHHFromLeonid.tracker.integration.hh.HHIntegrationService;

import java.util.List;

//Апи для вызова внутренних сервисов
@RestController
public class BackdoorApi {

    private final HHIntegrationService HHIntegrationService;
   // private final List<VacancyRepositoryForRequiest> vacancyRepositoryForRequiest;

    private final ServiceJDBC serviceJDBC;

    @Autowired
    public BackdoorApi(HHIntegrationService HHIntegrationService, ServiceJDBC serviceJDBC) {
        this.HHIntegrationService = HHIntegrationService;
        this.serviceJDBC = serviceJDBC;
    }

    @GetMapping("/download_vacancies")
    public void downloadVacancies() {
        //TODO убрать лишнее
        HHIntegrationService.downloadAndSaveVacancies();

    }

    //http://localhost:8080/get_vacanciess
    @GetMapping(value = "/get_vacanciess", produces = MediaType.APPLICATION_JSON_VALUE)
    List<VacancyDTOForJdbc> novaRuchka() {

       return serviceJDBC.convertFromRepo();
    }


    @GetMapping(value = "/get_vacancies", produces = MediaType.APPLICATION_JSON_VALUE)
    List<VacancyDTOAmount> novaRuchkaAmount() {

        return serviceJDBC.convertFromSum();
    }
}