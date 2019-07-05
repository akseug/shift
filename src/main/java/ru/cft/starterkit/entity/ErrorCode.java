package ru.cft.starterkit.entity;

public enum ErrorCode {

    SERVER_INTERNAL(1),
    CLIENT_ARGUMENT(2),
    NOT_FOUND_OBJECT(3);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
