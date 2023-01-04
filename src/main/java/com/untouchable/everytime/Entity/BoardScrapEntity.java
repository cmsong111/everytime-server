package com.untouchable.everytime.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BoardScrapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BoardScarp_PK;

    @ManyToOne
    BoardEntity board;
    
    @ManyToOne
    UserEntity user;

}
