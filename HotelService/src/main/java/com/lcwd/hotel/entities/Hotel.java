package com.lcwd.hotel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="hotels")
public class Hotel {
    @Id
    private String id;
    private String name;
    private String location;
    private String about;

    public void setId(String id){
        this.id = id;
    }
}
