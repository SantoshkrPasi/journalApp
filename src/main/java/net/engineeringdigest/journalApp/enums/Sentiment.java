package net.engineeringdigest.journalApp.enums;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


public enum Sentiment {
    HAPPY,
    SAD,
    ANGRY,
    ANXIOUS;

}
