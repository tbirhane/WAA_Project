package edu.mum.cs.onlinehabeshaclothing.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class FollowRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long targetId;

    public FollowRelation(Long userId, Long targetId) {

        this.userId = userId;
        this.targetId = targetId;
    }
}