package ru.kirill98.sovetromantica.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.*;
import ru.kirill98.sovetromantica.entity.CommandsEnums;
import ru.kirill98.sovetromantica.entity.ParsRequest;
import ru.kirill98.sovetromantica.services.impl.ParsingAndResponseUrlImpl;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@Log4j
public class MainView {
    private final ParsingAndResponseUrlImpl parsingAndResponseUrl;

    @PostMapping("/")
    public String parsLinkToAnime(@RequestBody String decodedParameters) throws UnsupportedEncodingException {
        ParsRequest parsRequests = parsingAndResponseUrl.parsRequest(decodedParameters);
        log.info(String.format("was get post request with body: %s", parsRequests.toString()));
        StringBuilder response = new StringBuilder("{\"command\": \"get_url_anime\", \"body\": \"");
        if(parsRequests.getCommand().equals(CommandsEnums.getUrlAnime.getCommand())) {
            response.append(parsingAndResponseUrl.parsLink(parsRequests.getBody()));
            response.append("\"}");
            log.info(String.format("Was create response: %s", response));
            return response.toString();
        }
        log.error(String.format("Was get not supported command: %s", parsRequests.getCommand()));
        response.append("error");
        return response.toString();

    }
}
