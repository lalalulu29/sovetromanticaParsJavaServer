package ru.kirill98.sovetromantica.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.kirill98.sovetromantica.services.NetworkProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RequiredArgsConstructor
@Service
@Log4j
public class NetworkProviderImpl implements NetworkProvider {
    @Override
    public String getPageFromUrl(String url) {
        URL readyUrl;
        try {
            readyUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) readyUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//            connection.setRequestProperty("application", "x-www-form-urlencoded;charset=UTF-8");
            connection.setDoOutput(true);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String responseUrl = content.toString();
            log.info(String.format("Was get page from string: %s", url));
            return responseUrl;
        } catch (IOException e) {
            log.error(String.format("Error get url from string: %s. %s",url, e));
            return "error";
        }
    }
}
