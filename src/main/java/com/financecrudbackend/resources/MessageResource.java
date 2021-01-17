package com.financecrudbackend.resources;

import lombok.Data;

@Data

public class MessageResource {
    private String message;

    public MessageResource(String message) {
        this.message = message;
    }
}
