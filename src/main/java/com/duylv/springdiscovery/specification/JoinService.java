package com.duylv.springdiscovery.specification;

import com.duylv.springdiscovery.entity.Home;
import com.duylv.springdiscovery.entity.Home_;
import com.duylv.springdiscovery.entity.User;
import com.duylv.springdiscovery.entity.User_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.SingularAttribute;

@Service
public class JoinService {

    public Specification<User> joinHandler() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<User, Home> userHomeSetJoin = root.join(User_.homes, JoinType.LEFT);
            return criteriaQuery
                    .multiselect(
                            userHomeSetJoin.get(Home_.address)
                    )
                    .where(
                            criteriaBuilder.equal(userHomeSetJoin.get(Home_.address), "Duy")
                    ).getRestriction();
        };
    }

}