package projectHHFromLeonid.tracker;

import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MainControllerPresentation {

    @Autowired
    private final MainServiceDomain mainServiceDomain;


    public MainControllerPresentation(MainServiceDomain mainServiceDomain) {
        this.mainServiceDomain = mainServiceDomain;
    }


    @GetMapping("/download_vacancies")
    public void downloadVacancies() {
        List<ResponseHH> resultList = new ArrayList<>();

        mainServiceDomain.generateUrl();
    }
}