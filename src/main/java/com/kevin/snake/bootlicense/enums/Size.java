package com.kevin.snake.bootlicense.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public enum Size {

    SMALL("S"),
    MEDIUM("M"),
    LARGE("L"),
    EXTRA_LARGE("XL");

    private String abbreviation;

    private Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        log.info("Enter a size:(SMALL,MEDIUM,LARGE,EXTRA_LARGE)");
        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        log.info("size= " + size);
        log.info("abbreviation= " + size.getAbbrevitation());
        if (size == size.EXTRA_LARGE) {
            log.info("Good job -- you paid attention to the _.");
        }
    }

    public String getAbbrevitation() {
        return abbreviation;
    }

}
