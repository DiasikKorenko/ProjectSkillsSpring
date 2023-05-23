package com.tms.mapper;

import com.tms.domain.Reviews;
import com.tms.domain.response.ReviewsResponse;
import org.springframework.stereotype.Component;

@Component
public class ReviewsToReviewsResponseMapper {

    public ReviewsResponse reviewsToResponse(Reviews reviews){
        ReviewsResponse reviewsResponse = new ReviewsResponse();
        reviewsResponse.setReview(reviews.getReview());
        reviewsResponse.setCreated(reviews.getCreated());
        return reviewsResponse;
    }
}
