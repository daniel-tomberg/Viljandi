package com.viljandi.repository;

import com.viljandi.entity.UpcomingDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UpcomingDeliveryRepository extends JpaRepository<UpcomingDelivery, Long> {
    List<UpcomingDelivery> findByClientId(String clientId);
}
