package com.lcwd.user.service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="micro_users")
public class User {
    @Id
    @Column(name="ID")
    private String userId;

    @Column(name = "NAME",length = 20)
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Column(name="ABOUT")
    private  String about;

    @Transient  // -- that means this not store in db
    private List<Rating> ratings = new ArrayList<>();

}
