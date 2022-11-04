package com.senla.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {

    private String longitude;
    private String latitude;

    @Override
    public String toString() {
        return longitude + "," + latitude;
    }
}
