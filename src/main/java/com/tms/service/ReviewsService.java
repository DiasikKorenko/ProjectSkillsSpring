package com.tms.service;

import com.tms.domain.Reviews;
import com.tms.domain.User;
import com.tms.domain.response.ReviewsResponse;
import com.tms.exception.ForbiddenEx;
import com.tms.exception.NotFoundEx;
import com.tms.mapper.ReviewsToReviewsResponseMapper;
import com.tms.repository.ReviewsRepository;
import com.tms.repository.UserRepository;
import com.tms.security.CheckingAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewsService {

    private final ReviewsToReviewsResponseMapper reviewsToReviewsResponseMapper;
    private final ReviewsRepository reviewsRepository;
    private final CheckingAuthorization checkingAuthorization;
    private final UserRepository userRepository;

    @Autowired
    public ReviewsService(ReviewsToReviewsResponseMapper reviewsToReviewsResponseMapper, ReviewsRepository reviewsRepository, CheckingAuthorization checkingAuthorization, UserRepository userRepository) {
        this.reviewsToReviewsResponseMapper = reviewsToReviewsResponseMapper;
        this.reviewsRepository = reviewsRepository;
        this.checkingAuthorization = checkingAuthorization;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createReviews(Reviews reviews) {
        Optional<User> selectedUser = userRepository.findById(reviews.getToWhichCompanyId());
        if (selectedUser.isPresent()) {
            reviews.setFromWhichCompanyEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            reviews.setCreated(new Date(System.currentTimeMillis()));
            reviews.setDeleted(false);
            reviewsRepository.save(reviews);
        } else {
            throw new NotFoundEx("Id company is not found");
        }
    }

    @Transactional
    public void updateReviews(Reviews reviews) {
        if (checkingAuthorization.check(getUserEmail(reviews.getId()))) {
            if (reviewsRepository.findById(reviews.getId()).isPresent()) {
                reviewsRepository.updateReviews(reviews.getId(), reviews.getReview());
            } else {
                throw new NotFoundEx("There is no this reviews");
            }
        } else {
            throw new ForbiddenEx("You can't update reviews from another user");
        }
    }

    private String getUserEmail(int id) {
        Reviews reviews = reviewsRepository.findById(id).orElseThrow
                (() -> new NotFoundEx("There are no any reviews for this company"));
        return reviews.getFromWhichCompanyEmail();
    }

    @Transactional
    public void deleteReviews(int id) {
        reviewsRepository.findById(id).orElseThrow
                (() -> new NotFoundEx("There are no any reviews for this company"));
        if (checkingAuthorization.check(getUserEmail(id))) {
            reviewsRepository.deleteReviews(id);
        } else {
            throw new ForbiddenEx("You can't delete reviews from another user");
        }
    }

    public List<ReviewsResponse> getAllReviewsResponseForCompany(int toWhichCompanyId) {
        List<ReviewsResponse> review = reviewsRepository.findAllBytoWhichCompanyId(toWhichCompanyId)
                .stream().filter(reviews -> !reviews.isDeleted())
                .map(reviewsToReviewsResponseMapper::reviewsToResponse)
                .collect(Collectors.toList());
        if (!review.isEmpty()) {
            return review;
        } else {
            throw new NotFoundEx("There are no reviews for this company");
        }
    }

    public List<Reviews> getAllReviewsForCompany(int toWhichCompanyId) {
        List<Reviews> review = reviewsRepository.findAllBytoWhichCompanyId(toWhichCompanyId);
        if (!review.isEmpty()) {
            return review;
        } else {
            throw new NotFoundEx("There are no any reviews for this company");
        }
    }

    @Transactional
    public void deleteReviewsByAdmin(int id) {
        Reviews reviews = reviewsRepository.findById(id).orElseThrow(() -> new NotFoundEx("Not found reviews"));
        if (!reviews.isDeleted()) {
            reviews.setDeleted(true);
        } else {
            throw new NotFoundEx("Reviews is already deleted");
        }
    }
}