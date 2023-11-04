package projectHHFromLeonid.tracker.api;

import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import projectHHFromLeonid.tracker.integration.hh.HHIntegrationService;

import java.util.ArrayList;
import java.util.List;

//Апи для вызова внутренних сервисов
@RestController
public class BackdoorApi {

    private final HHIntegrationService HHIntegrationService;

    public BackdoorApi(HHIntegrationService HHIntegrationService) {
        this.HHIntegrationService = HHIntegrationService;
    }

    @GetMapping("/download_vacancies")
    public void downloadVacancies() {
        //TODO убрать лишнее
        List<ResponseHH> resultList = new ArrayList<>();
        HHIntegrationService.downloadAndSaveVacancies();
    }
}