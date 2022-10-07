package tms.entity;

import lombok.Data;

@Data
public class Pair {

    private Horse horse;
    private Rider rider;
    private Integer totalAmount;

    public Pair(Horse horse, Rider rider) {
        this.horse = horse;
        this.rider = rider;
        this.totalAmount = horse.getSpeed() *(1 + rider.getLevel() / 10);
    }
}
