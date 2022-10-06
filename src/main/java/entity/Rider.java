package entity;

import lombok.Data;

@Data
public class Rider {

    private String name;
    private Integer level;
    private Boolean horsewhip = true;

    public Rider(String name) {
        this.name = name;
        this.level = 1 + (int)(Math.random() * ((10 - 1) + 1));
    }
}
