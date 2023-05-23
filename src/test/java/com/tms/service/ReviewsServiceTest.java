package com.tms.service;

import com.tms.domain.*;
import com.tms.domain.response.ReviewsResponse;
import com.tms.mapper.ReviewsToReviewsResponseMapper;
import com.tms.repository.*;
import com.tms.security.CheckingAuthorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewsServiceTest {
    @InjectMocks
    private ReviewsService reviewsService;

    @Mock
    private ReviewsRepository reviewsRepository;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ReviewsToReviewsResponseMapper reviewsToReviewsResponseMapper;

    @Mock
    private CheckingAuthorization checkingAuthorization;

    @Mock
    private User user;
    private int id;

    private Reviews reviews;

    private ReviewsResponse reviewsResponse;

    private final List<Reviews> reviewes = new ArrayList<>();

    private final List<ReviewsResponse> reviewsResponses = new ArrayList<>();
    private final Timestamp time = new Timestamp(System.currentTimeMillis());


    @BeforeEach
    public void init() {
        id = 1;
        reviews = new Reviews();
        reviews.setId(1);
        reviews.setReview("TestReviews");
        reviews.setFromWhichCompanyEmail("test@gmail.com");
        reviews.setToWhichCompanyId(1);
        reviews.setCreated(time);
        reviews.setDeleted(false);
        reviewes.add(reviews);
        reviewsResponse = new ReviewsResponse();
        reviewsResponses.add(reviewsResponse);
    }

    @Test
    public void createFeedbackTest() {
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("test@gmail.com");
        reviews.setCreated(time);
        reviewsService.createReviews(reviews);
        verify(reviewsRepository).save(reviews);
    }

    @Test
    public void updateReviewsTest() {
        when(checkingAuthorization.check(reviews.getFromWhichCompanyEmail())).thenReturn(true);
        when(reviewsRepository.findById(id)).thenReturn(Optional.of(reviews));
        reviewsService.updateReviews(reviews);
        verify(reviewsRepository).updateReviews(id, reviews.getReview());
    }

    @Test
    public void deleteReviewsTest() {
        when(reviewsRepository.findById(id)).thenReturn(Optional.of(reviews));
        when(checkingAuthorization.check(reviews.getFromWhichCompanyEmail())).thenReturn(true);
        reviewsService.deleteReviews(id);
        verify(reviewsRepository).deleteReviews(id);
    }








    @Test
    public void getAllReviewsResponseForCompanyTest() {
        when(reviewsRepository.findAllBytoWhichCompanyId(id)).thenReturn(reviewes);
        when(reviewsToReviewsResponseMapper.reviewsToResponse(reviews)).thenReturn(reviewsResponse);
        assertEquals(reviewsResponses, reviewsService.getAllReviewsResponseForCompany(id));
    }


}