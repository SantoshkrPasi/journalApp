package net.engineeringdigest.journalApp.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse{

    private Current current;

    @Getter
    @Setter
    public class Current{
        private String last_updated;
        private double temp_c;
        private double temp_f;
        private int is_day;

    }

}



