package com.tms.controller;

import com.tms.domain.Reviews;
import com.tms.domain.response.ReviewsResponse;
import com.tms.exception.BadRequestEx;
import com.tms.service.ReviewsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;

    @Autowired
    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @Operation(summary = "Creation reviews for company")
    @PostMapping
    public ResponseEntity<Reviews> createFeedback(@RequestBody Reviews reviews, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            reviewsService.createReviews(reviews);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            throw new BadRequestEx("Check your info and try again");
        }
    }

    @Operation(summary = "Update review's for the user")
    @PutMapping
    public ResponseEntity<HttpStatus> updateReviews(@RequestBody Reviews reviews) {
        reviewsService.updateReviews(reviews);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Deleting review's for the user")
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteReviews(@RequestParam int id) {
        reviewsService.deleteReviews(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Information about all reviews for one company for the user")
    @GetMapping("/{toWhichCompanyId}")
    public ResponseEntity<List<ReviewsResponse>> getAllReviewsResponseForCompany(@PathVariable int toWhichCompanyId) {
        return new ResponseEntity<>(reviewsService.getAllReviewsResponseForCompany(toWhichCompanyId), HttpStatus.OK);
    }

    @Operation(summary = "Information about all reviews for one company for the admin")
    @GetMapping("/admin/{toWhichCompanyId}")
    public ResponseEntity<List<Reviews>> getAllReviewsForCompany(@PathVariable int toWhichCompanyId) {
        return new ResponseEntity<>(reviewsService.getAllReviewsForCompany(toWhichCompanyId), HttpStatus.OK);
    }

    @Operation(summary = "Deleting review's for the admin")
    @DeleteMapping("/admin")
    public ResponseEntity<HttpStatus> deleteUserByAdmin(@RequestParam int id) {
        reviewsService.deleteReviewsByAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}