package sms.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import common.rabbit.model.MessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestModel {
    @JsonProperty(value = "type")
    private String type = "SMS";
    @JsonProperty(value = "contentType")
    private String contentType = "COMM";
    @JsonProperty(value = "countryCode")
    private String countryCode = "82";
    @JsonProperty(value = "from")
    private String from = "01050625132";
    @JsonProperty(value = "content")
    private String content;
    @JsonProperty(value = "messages")
    private List<MessageDto> messages;
}
