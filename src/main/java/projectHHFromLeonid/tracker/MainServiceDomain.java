package projectHHFromLeonid.tracker;

import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class MainServiceDomain {
    public final RestTemplate restTemplate;

    public MainServiceDomain() {
        this.restTemplate = new RestTemplate();
    }
    //int ResponseHH;

    public void downloadAndSaveVacancies(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl
                = "https://api.hh.ru/vacancies";
      //  ArrayList<String> allVacancies = new ArrayList<>();

        int ResponseHH = 0;

        ResponseEntity<ResponseHH> response = restTemplate.getForEntity(resourceUrl, ResponseHH.class);
        ArrayList<Integer> list1 = new ArrayList<>(ResponseHH);
        System.out.println(list1);

    }
}