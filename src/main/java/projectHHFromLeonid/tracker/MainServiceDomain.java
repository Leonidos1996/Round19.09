package projectHHFromLeonid.tracker;
import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class MainServiceDomain {
    public final RestTemplate restTemplate;

    public MainServiceDomain() {
        this.restTemplate = new RestTemplate();
    }

    public void workInLoop() {
        List<ResponseHH> resultList = new ArrayList<>();
        for (int i = 0; i < 20; i++ ){
            String url = "https://api.hh.ru/vacancies?page=" + i + "&per_page=100";
            List<ResponseHH> responseList = downloadAndSaveVacancies(url);
            resultList.addAll(responseList);
        }
    }



    public List<ResponseHH> downloadAndSaveVacancies(String url){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseHH> response = restTemplate.getForEntity(url, ResponseHH.class);
        System.out.println(response);
        return Collections.singletonList(response.getBody());
    }
}