package com.example.demo.apis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TimeAPI {
    private String currentDateTime;
    private String UTCOffset;
    private Boolean isDayLightSavingsTime;
    private String dayOfTheWeek;
    private String timeZoneName;
    private Long currentFileTime;
    private String ordinalDate;
    private String serviceResponse;
}
