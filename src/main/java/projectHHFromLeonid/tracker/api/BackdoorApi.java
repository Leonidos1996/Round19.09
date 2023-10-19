package projectHHFromLeonid.tracker.api;

import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import projectHHFromLeonid.tracker.integration.hh.HHIntegrationService;

import java.util.ArrayList;
import java.util.List;

//Апи для вызова внутренних сервисов
@RestController
public class BackdoorApi {

    @Autowired
    private final HHIntegrationService HHIntegrationService;

    public BackdoorApi(HHIntegrationService HHIntegrationService) {
        this.HHIntegrationService = HHIntegrationService;
    }

    @GetMapping("/download_vacancies")
    public void downloadVacancies() {
        List<ResponseHH> resultList = new ArrayList<>();
        HHIntegrationService.generateUrl(1, 100, "Java");
    }
}