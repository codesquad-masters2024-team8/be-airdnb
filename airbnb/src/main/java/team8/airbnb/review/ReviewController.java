package team8.airbnb.review;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

  @Autowired
  private ReviewService reviewService;

  @GetMapping("/{hostroomId}")
  public List<ReviewResponse> getAllReviewsByHostroomId(@PathVariable Long hostroomId) {
    return reviewService.getAllReviewsByHostroomId(hostroomId);
  }

  @PostMapping("/{hostroomId}")
  @ResponseStatus(HttpStatus.CREATED)
  public ReviewResponse createReview(@PathVariable Long hostroomId, @RequestBody ReviewRequest reviewRequest) {
    return reviewService.createReview(
        hostroomId,
        reviewRequest.getUserId(),
        reviewRequest.getContent(),
        reviewRequest.getRating()
    );
  }

  @PutMapping("/{hostroomId}/{reviewId}")
  public ReviewResponse updateReview(@PathVariable Long hostroomId, @PathVariable Long reviewId, @RequestBody ReviewRequest reviewRequest) {
    return reviewService.updateReview(
        hostroomId,
        reviewId,
        reviewRequest.getContent(),
        reviewRequest.getRating()
    );
  }

  @DeleteMapping("/{hostroomId}/{reviewId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteReview(@PathVariable Long hostroomId, @PathVariable Long reviewId) {
    reviewService.deleteReview(hostroomId, reviewId);
  }
}
