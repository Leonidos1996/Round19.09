package projectHHFromLeonid.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MainServiceDomain {
    public final RestTemplate restTemplate;

    public MainServiceDomain() {
        this.restTemplate = new RestTemplate();
    }

    public void downloadAndSaveVacancies(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl
                = "https://api.hh.ru/vacancies";
      //  ArrayList<String> allVacancies = new ArrayList<>();

        ResponseEntity<List> response = restTemplate.getForEntity(resourceUrl, List.class);
        System.out.println(response);


    }
}