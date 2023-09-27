package projectHHFromLeonid.tracker;

import integration.projectHHFromLeonid.tracker.ResponseHH;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MainServiceDomain {


    public String baseUrl;
    @Getter
    @Setter
    private int page;
    @Getter
    @Setter
    private int perPage;
    public String keyWord;

    public final RestTemplate restTemplate;

    public MainServiceDomain() {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
        this.page = page;
        this.perPage = perPage;
        this.keyWord = keyWord;
    }




    public void workInLoop(){
        List<ResponseHH> resultList = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            String url = "https://api.hh.ru/vacancies?page=" + i + "&per_page=100";
            List<ResponseHH> responseList = downloadAndSaveVacancies(url);
            resultList.addAll(responseList);
        }
    }

    public String generateUrl() {
        this.baseUrl = "https://api.hh.ru/vacancies";
        List<ResponseHH> resultList2 = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            String url = "https://api.hh.ru/vacancies?page=" + i + "&per_page=100" + "&text=PHP";
            List<ResponseHH> responseList = downloadAndSaveVacancies(url);
            resultList2.addAll(responseList);
        }

        this.page = 2;
        this.perPage = 100;
        this.keyWord = "PHP";
        return resultList2.toString();
    }

    public List<ResponseHH> downloadAndSaveVacancies(String url){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseHH> response = restTemplate.getForEntity(url, ResponseHH.class);
        System.out.println(response);
        return Collections.singletonList(response.getBody());
    }
}