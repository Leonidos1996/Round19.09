package projectHHFromLeonid.tracker.integration.hh;

import integration.projectHHFromLeonid.tracker.Item;
import integration.projectHHFromLeonid.tracker.ResponseHH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import projectHHFromLeonid.tracker.dao.entity.Vacancy;
import projectHHFromLeonid.tracker.dao.repos.VacancyRepo;
import projectHHFromLeonid.tracker.api.VacancyRepositoryForRequiest;

import java.util.ArrayList;
import java.util.List;

@Service
public class HHIntegrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HHIntegrationService.class);
    public static final String BASE_URL = "https://api.hh.ru/vacancies";

    public final VacancyRepositoryForRequiest vacancyRepositoryForRequiest;
    private final RestTemplate restTemplate;
    private final VacancyRepo vacancyRepo;
    private final EntityBuilder entityBuilder;

    public HHIntegrationService(
            @Qualifier("hh_resttemplate") RestTemplate restTemplate,
            VacancyRepo vacancyRepo,
            EntityBuilder entityBuilder, VacancyRepositoryForRequiest vacancyRepositoryForRequiest) {
        this.restTemplate = restTemplate;
        this.vacancyRepo = vacancyRepo;
        this.entityBuilder = entityBuilder;
        this.vacancyRepositoryForRequiest = vacancyRepositoryForRequiest;
    }


    public void downloadAndSaveVacancies(){
        //Создается список ключевых слов
        List<String> keyWords = new ArrayList<>();
        keyWords.add("Java");

        //По каждому ключевому слову делаем запросы в ХХ
        List<ResponseHH> responses = new ArrayList<>();
        for (String key : keyWords) {
            LOGGER.info("Current word - [{}]", key);
            //проходимся по всем страницам и сохраняем ответ ХХ в список responses
            for (int i = 1; i < 10; i++) {
                String url = generateUrl(i, 100, key);
                LOGGER.debug("Current URL - [{}]", url);
                ResponseEntity<ResponseHH> response = restTemplate.getForEntity(url, ResponseHH.class);
                responses.add(response.getBody());
            }
        }

        //
        LOGGER.trace("TRACE");
        LOGGER.debug("DEBUG");
        LOGGER.info("------------------------------------------------------------------------------------------");
        LOGGER.warn("WARN");
        LOGGER.error("ERROR");

        //Берем каждый респонс и для каждого Item создаем вакансию и сохраняем ее в базу данных
        for (ResponseHH response : responses) {
            for (Item item : response.getItems()) {
                Vacancy vacancy = entityBuilder.createVacancies(item);
                LOGGER.info("Save vacancy with natural id - [{}]", vacancy.getNaturalId());
                vacancyRepo.save(vacancy);
            }
        }
    }

    public String generateUrl(int page, int perPage, String text) {
        return BASE_URL + "?page=" + page + "&per_page=" + perPage + "&text=" + text;
    }
}