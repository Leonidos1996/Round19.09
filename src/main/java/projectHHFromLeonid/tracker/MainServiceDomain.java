package projectHHFromLeonid.tracker;

import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceDomain {
    public final RestTemplate restTemplate;
    public static final String BASE_URL = "https://api.hh.ru/vacancies";

    //На выходе из метода

    String url_exit = "https://hh.api?page=1&per_page=100&text=java";


    public MainServiceDomain() {
        this.restTemplate = new RestTemplate();
    }

    public void downloadAndSaveVacancies(){

        RestTemplate restTemplate = new RestTemplate();

        List<String> urlList = new ArrayList<>();

        List<String> keyWords = new ArrayList<>();
        keyWords.add("PHP");
        keyWords.add("Java");
        keyWords.add("C#");

        List<ResponseHH> responses = new ArrayList<>();
        for (String key : keyWords) {
            for (int i = 1; i < 101; i++) {
                String url = generateUrl(i, 100, key);
                ResponseEntity<ResponseHH> response = restTemplate.getForEntity(url, ResponseHH.class);
                responses.add(response.getBody());
            }
        }

//        for (ResponseHH hh : responses) {
//            putToDatabase(hh);
//        }


//        String resourceUrl
//                = "https://api.hh.ru/vacancies";
      //  ArrayList<String> allVacancies = new ArrayList<>();

//        ResponseEntity<ResponseHH> response = restTemplate.getForEntity(resourceUrl, ResponseHH.class);
        //System.out.println(response);

    }

    public String generateUrl(int page, int perPage, String text) {
        return BASE_URL + "?page=" + page + "&per_page=" + perPage + "&text=" + text;
    }
}