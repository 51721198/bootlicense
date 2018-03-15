package com.kevin.snake.bootlicense.enums;

import java.util.Scanner;

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
        System.out.println("Enter a size:(SMALL,MEDIUM,LARGE,EXTRA_LARGE)");
        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        System.out.println("size= " + size);
        System.out.println("abbreviation= " + size.getAbbrevitation());
        if (size == size.EXTRA_LARGE) {
            System.out.println("Good job -- you paid attention to the _.");
        }
    }

    public String getAbbrevitation() {
        return abbreviation;
    }

}
