package com.java.rest.twitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/tweets")
public class TwitterController
{
    Logger logger = LoggerFactory.getLogger(TwitterController.class);
    @Autowired
    private Twitter twitter;



    @GetMapping(path="/search", produces = "application/json")
    public String getTweets(@RequestParam final String query, @RequestParam final int count)
    {
        ObjectMapper mapper = new ObjectMapper();

        SearchParameters searchParameters = new SearchParameters(query);
        searchParameters.resultType(SearchParameters.ResultType.RECENT);
        searchParameters.count(count);

        String prettyJsonString = null;
        try {
            prettyJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(twitter.searchOperations().search(searchParameters).getTweets());
        } catch (JsonProcessingException e) {
            logger.error("Error while fetching tweets"+e.getMessage());
        }
        return prettyJsonString;
    }
    

}
