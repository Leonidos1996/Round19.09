package projectHHFromLeonid.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MainControllerPresentation {

    @Autowired
    private final MainServiceDomain mainServiceDomain;


    public MainControllerPresentation(MainServiceDomain mainServiceDomain) {
        this.mainServiceDomain = mainServiceDomain;
    }


    @GetMapping("/download_vacancies")
    public void downloadVacancies() {
        mainServiceDomain.downloadAndSaveVacancies();
    }
}