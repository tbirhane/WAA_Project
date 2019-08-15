package edu.mum.cs.onlinehabeshaclothing.service;

import edu.mum.cs.onlinehabeshaclothing.model.FollowRelation;
import edu.mum.cs.onlinehabeshaclothing.repository.FollowRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class FollowRelationServiceImpl implements FollowRelationService {

    @Autowired
    FollowRelationRepository followRelationRepository;
    @Override
    public FollowRelation saveRelation(FollowRelation followRelation) {
         followRelationRepository.save(followRelation);
         return followRelation;
    }

    @Override
    public FollowRelation findRelation(Long userId, Long targetId) {
        FollowRelation followRelation = followRelationRepository.findRelation(userId, targetId);
        return followRelation;
    }

    @Override
    public void removeRelation(FollowRelation followRelation) {

    }
}
