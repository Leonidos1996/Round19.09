package projectHHFromLeonid.tracker;

import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        ResponseEntity<ResponseHH> response = restTemplate.getForEntity(resourceUrl, ResponseHH.class);
        System.out.println(response);


    }
}