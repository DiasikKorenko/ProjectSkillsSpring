package com.tms.repository;


import com.tms.domain.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewsRepository extends JpaRepository<Reviews,Integer> {

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE reviews SET reviews = :newReviews WHERE id = :id",
            countQuery = "SELECT * FROM reviews WHERE id = :id")
    void updateReviews(int id, String newReviews);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE reviews SET is_deleted = true WHERE id = :id",
            countQuery = "SELECT * FROM reviews WHERE id = :id")
    void deleteReviews(int id);

    List<Reviews> findAllBytoWhichCompanyId(int toWhichCompanyId);

}
