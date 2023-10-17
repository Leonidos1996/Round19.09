package projectHHFromLeonid.tracker.integration.hh;

import integration.projectHHFromLeonid.tracker.Item;
import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class HHIntegrationService {

    private RestTemplate restTemplate;

    public static final String BASE_URL = "https://api.hh.ru/vacancies";
    //На выходе из метода

    public HHIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HHIntegrationService() {
        this.restTemplate = new RestTemplate();
    }

    public void downloadAndSaveVacancies(){

        List<String> urlList = new ArrayList<>();

        List<String> keyWords = new ArrayList<>();
        keyWords.add("PHP");
        keyWords.add("Java");


        List<ResponseHH> responses = new ArrayList<>();
        for (String key : keyWords) {
            for (int i = 1; i < 101; i++) {
                String url = generateUrl(i, 100, key);
                ResponseEntity<ResponseHH> response = restTemplate.getForEntity(url, ResponseHH.class);
                responses.add(response.getBody());
            }
        }

        for (ResponseHH response : responses) {
            for (Item item : response.getItems()) {

            }
        }
    }

    public String generateUrl(int page, int perPage, String text) {
        return BASE_URL + "?page=" + page + "&per_page=" + perPage + "&text=" + text;
    }
}