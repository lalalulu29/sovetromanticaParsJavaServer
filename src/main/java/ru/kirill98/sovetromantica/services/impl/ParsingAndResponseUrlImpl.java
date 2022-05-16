package ru.kirill98.sovetromantica.services.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.kirill98.sovetromantica.entity.ParsRequest;
import ru.kirill98.sovetromantica.services.NetworkProvider;
import ru.kirill98.sovetromantica.services.ParsingAndResponseUrl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
@Log4j
public class ParsingAndResponseUrlImpl implements ParsingAndResponseUrl {
    private static final Gson gson = new Gson();
    private final NetworkProvider networkProvider;
    @Override
    public String parsLink(String url) {
        String page = networkProvider.getPageFromUrl(url);
        int endIndex = page.indexOf("\", \"preroll\"");
        int startIndex = page.indexOf("\"file\":\"https://") + 8;
        if (endIndex == -1) {
            return "error";
        }
        return page.substring(startIndex, endIndex);
    }
    public ParsRequest parsRequest(String string) throws UnsupportedEncodingException {
        String parameters = URLDecoder.decode(string, StandardCharsets.UTF_8.name());
        String readyString = parameters.replaceAll("=", "");
        Type itemsListType = new TypeToken<ParsRequest>() {}.getType();
        return gson.fromJson(readyString, itemsListType);
    }
}
