package tms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Horse {

    private String name;
    private Integer speed;

    public Horse(String name) {
        this.name = name;
        this.speed = 15 + (int)(Math.random() * ((30 - 15) + 1));
    }
}
