package ru.kirill98.sovetromantica.entity;

import lombok.Getter;

@Getter
public enum CommandsEnums {
    getUrlAnime("get_url_anime");

    private final String command;

    CommandsEnums(String command) {
        this.command = command;
    }
}
