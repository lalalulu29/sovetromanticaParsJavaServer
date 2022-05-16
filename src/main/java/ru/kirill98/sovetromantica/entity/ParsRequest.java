package ru.kirill98.sovetromantica.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParsRequest {
    private String command;
    private String body;
}
