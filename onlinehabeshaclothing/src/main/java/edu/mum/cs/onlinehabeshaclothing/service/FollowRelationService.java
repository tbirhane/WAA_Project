package edu.mum.cs.onlinehabeshaclothing.service;

import edu.mum.cs.onlinehabeshaclothing.model.FollowRelation;

import java.util.List;
import java.util.Optional;

public interface FollowRelationService {
    FollowRelation saveRelation(FollowRelation followRelation);

    FollowRelation findRelation(Long userId, Long targetId);

    void removeRelation(FollowRelation followRelation);
}
