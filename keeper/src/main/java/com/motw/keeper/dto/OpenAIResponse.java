package com.motw.keeper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIResponse {

    private String id;

    private String object;

    private long created;

    private String model;

    @JsonProperty("system_fingerprint")
    private String systemFingerprint;

    private List<Choice> choices;

    private Usage usage;

    public String getMessageAtIndex(int index) {
        return getChoices().get(index).getMessage().getContent();
    }


}