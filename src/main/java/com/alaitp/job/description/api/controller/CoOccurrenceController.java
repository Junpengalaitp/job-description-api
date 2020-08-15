package com.alaitp.job.description.api.controller;

import com.alaitp.job.description.api.constant.CategoryConst;
import com.alaitp.job.description.api.service.CoOccurrenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alaitp.job.description.api.constant.ControllerConst.NO_WORD_FOUND_RES;
import static com.alaitp.job.description.api.constant.ControllerConst.WORDS;

@Slf4j
@RestController
public class CoOccurrenceController {

    @Autowired
    private CoOccurrenceService coOccurrenceService;

    /**
     * First standardize the word, then get the correlated word from the matrix by category
     *
     * @param word       input word
     * @param amount     amount of correlated words
     * @param categories desired correlated word categories
     * @return top amount of correlated words in desired categories
     */
    @GetMapping(value = "/most-correlated-words/{word}/{amount}/{categories}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMostCorrelatedWords(@PathVariable String word, @PathVariable Integer amount, @PathVariable String categories) {
        log.info("received get most correlated words request, word: {}, amount: {}, categories: {}", word, amount, categories);
        String standardWord = coOccurrenceService.getStandardWord(word);
        log.info("standardized input word: {} to {}", word, standardWord);
        Set<String> categoryList;
        if (categories.contains("all")) {
            categoryList = CategoryConst.ALL_CATEGORY;
        } else {
            categoryList = Arrays.stream(categories.split(",")).map(CategoryConst.CATEGORY_MAP::get).collect(Collectors.toSet());
        }
        Map<String, Map<String, Object>> topCoOccurredWordsInCategories = coOccurrenceService.getTopRelatedWords(standardWord, amount, categoryList);
        if (topCoOccurredWordsInCategories.isEmpty()) {
            topCoOccurredWordsInCategories = NO_WORD_FOUND_RES;
        }
        return ResponseEntity.ok().body(Map.of(WORDS, topCoOccurredWordsInCategories));
    }
}
