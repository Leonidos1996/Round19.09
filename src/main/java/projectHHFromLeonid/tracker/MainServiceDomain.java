package projectHHFromLeonid.tracker;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainServiceDomain {
    public final RestTemplate restTemplate;

    public MainServiceDomain(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void downloadAndSaveVacancies(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl
                = "https://api.hh.ru/vacancies";
      //  ArrayList<String> allVacancies = new ArrayList<>();

        ResponseEntity<List> response
                = restTemplate.getForEntity(resourceUrl, List.class);
        System.out.println(response);



    }
}