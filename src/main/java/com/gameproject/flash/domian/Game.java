package com.gameproject.flash.domian;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game {

    @Id
    private Integer id;
    private String name;
    private String imageUrl;
    private String swfUrl;
}
