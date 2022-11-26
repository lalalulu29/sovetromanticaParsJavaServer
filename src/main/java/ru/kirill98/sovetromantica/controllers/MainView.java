package ru.kirill98.sovetromantica.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import ru.kirill98.sovetromantica.entity.CommandsEnums;
import ru.kirill98.sovetromantica.entity.ParsRequest;
import ru.kirill98.sovetromantica.services.impl.ParsingAndResponseUrlImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@Log4j
public class MainView {
    private final ParsingAndResponseUrlImpl parsingAndResponseUrl;

    @PostMapping("/")
    public String parsLinkToAnime(@RequestBody String decodedParameters, HttpServletRequest request) throws UnsupportedEncodingException {
        ParsRequest parsRequests = parsingAndResponseUrl.parsRequest(decodedParameters);
        log.info(String.format("was get post request with body: %s, from address: %s", parsRequests.toString(), request.getRemoteAddr()));
        StringBuilder response = new StringBuilder("{\"command\": \"get_url_anime\", \"body\": \"");
        if(parsRequests.getCommand().equals(CommandsEnums.getUrlAnime.getCommand())) {
            response.append(parsingAndResponseUrl.parsLink(parsRequests.getBody()));
            response.append("\"}");
            log.info(String.format("Was create response: %s, to address: %s", response, request.getRemoteAddr()));
            return response.toString();
        }
        log.error(String.format("Was get not supported command: %s, from address: %s", parsRequests.getCommand(), request.getRemoteAddr()));
        response.append("error");
        return response.toString();

    }

    @GetMapping("/")
    public void notSupportedGetReq(HttpServletRequest request) {
        log.error(String.format("was get GET request, from address: %s", request.getRemoteAddr()));
    }
}