package io.springguru.reactivecommentdataservice.client;

import io.springguru.reactivecommentdataservice.model.Comment;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
/**
 * @author serdardundar
 * @since 12/12/2018
 */
@Slf4j
@Setter
@Component
@ConfigurationProperties("webflux")
public class WebfluxClient {

    private String host;
    private String port;
    private String path;

    public Flux<Comment> getCommentStream(){

        String url = "http://" + host + ":" + port;

        log.debug("Url Set is: " + url);

        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(path)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Comment.class);
    }
}
