package com.waracle.cakemgr.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

@Component
public class DataUtil {

    private String initialise;

    private CakeRepository cakeRepository;

    @Autowired
    public DataUtil( CakeRepository cakeRepository , @Value("${data.initialise}") String initialise) {
        this.cakeRepository = cakeRepository;
        this.initialise = initialise;
    }

    @PostConstruct
    public void initialiseData() throws Exception {
        if( initialise.equals( "false" ) ) {
            System.out.println("initialiseData not requested");
            return;
        }

        System.out.println("initialiseData started");


        System.out.println("downloading cake json");
        try (InputStream inputStream = new URL("https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json").openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line);
                line = reader.readLine();
            }

            System.out.println("parsing cake json");
            JsonParser parser = new JsonFactory().createParser(buffer.toString());
            if (JsonToken.START_ARRAY != parser.nextToken()) {
                throw new Exception("bad token");
            }

            JsonToken nextToken = parser.nextToken();
            while(nextToken == JsonToken.START_OBJECT) {
                System.out.println("creating cake entity");

                Cake cake = new Cake();
                System.out.println(parser.nextFieldName());
                cake.setTitle(parser.nextTextValue());

                System.out.println(parser.nextFieldName());
                cake.setDescription(parser.nextTextValue());

                System.out.println(parser.nextFieldName());
                cake.setImage(parser.nextTextValue());

                try {
                    cakeRepository.saveAndFlush(cake);
                } catch(Exception e) {

                }

                nextToken = parser.nextToken();
                System.out.println(nextToken);

                nextToken = parser.nextToken();
                System.out.println(nextToken);
            }

        } catch (Exception ex) {
            throw new Exception(ex);
        }

        System.out.println("initialiseData finished");
    }
}