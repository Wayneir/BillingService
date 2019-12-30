package com.example.BillingService;

import com.example.BillingService.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ESRegistry extends ElasticsearchRepository<User, String> {
    public Optional<User> findByUserId(String userId);

}
