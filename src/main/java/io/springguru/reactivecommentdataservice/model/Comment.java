package io.springguru.reactivecommentdataservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * @author serdardundar
 * @since 12/12/2018
 */
@Data
@Document
public class Comment {

    @Id
    private String id;
    private String message;
    private Instant createdTime;
    private String subscriber;
    private boolean liked;
}
