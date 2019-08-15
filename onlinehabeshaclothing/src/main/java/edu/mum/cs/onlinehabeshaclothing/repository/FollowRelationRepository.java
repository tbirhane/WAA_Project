package edu.mum.cs.onlinehabeshaclothing.repository;

import edu.mum.cs.onlinehabeshaclothing.model.FollowRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface FollowRelationRepository extends CrudRepository<FollowRelation, Long> {

    @Query(value = "select f from FollowRelation f where f.userId =:userId and f.targetId=:targetId")
    FollowRelation findRelation(Long userId, Long targetId);
}
