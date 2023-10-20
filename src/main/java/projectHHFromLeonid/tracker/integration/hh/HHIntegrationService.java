package projectHHFromLeonid.tracker.integration.hh;

import integration.projectHHFromLeonid.tracker.Item;
import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import projectHHFromLeonid.tracker.dao.entity.Vacancy;
import projectHHFromLeonid.tracker.dao.repos.VacancyRepo;



import java.util.ArrayList;
import java.util.List;

@Service
public class HHIntegrationService {

    private RestTemplate restTemplate;

    //инжектим бин VacancyRepo через конструктор, для того чтобы работать с БД
    private VacancyRepo vacancyRepo;

    public static final String BASE_URL = "https://api.hh.ru/vacancies";
    //На выходе из метода

    public HHIntegrationService(
            RestTemplate restTemplate,
            VacancyRepo vacancyRepo) {
        this.restTemplate = restTemplate;
        this.vacancyRepo = vacancyRepo;
    }

    public HHIntegrationService() {
        this.restTemplate = new RestTemplate();
    }

    public void downloadAndSaveVacancies(){

        //Создается список ключевых слов
        List<String> keyWords = new ArrayList<>();
        keyWords.add("Java");

        //По каждому ключевому слову делаем запросы в ХХ
        List<ResponseHH> responses = new ArrayList<>();
        for (String key : keyWords) {
            //проходимся по всем страницам и сохраняем ответ ХХ в список responses
            for (int i = 1; i < 101; i++) {
                String url = generateUrl(i, 100, key);
                ResponseEntity<ResponseHH> response = restTemplate.getForEntity(url, ResponseHH.class);
                responses.add(response.getBody());
            }
        }

        //Берем каждый респонс и для каждого Item создаем вакансию и сохраняем ее в базу данных
        for (ResponseHH response : responses) {
            for (Item item : response.getItems()) {
                Vacancy vacancy = createVacancy(item);
                vacancyRepo.save(vacancy);
            }
        }
    }

    public String generateUrl(int page, int perPage, String text) {
        return BASE_URL + "?page=" + page + "&per_page=" + perPage + "&text=" + text;
    }
}