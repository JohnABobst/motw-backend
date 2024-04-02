package com.motw.keeper.dto;

import java.util.List;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    private String model;
    private List<Message> messages;

}

