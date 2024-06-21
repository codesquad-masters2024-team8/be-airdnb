package team8.airbnb.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team8.airbnb.hostroom.Hostroom;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByHostroom(Hostroom hostroom);
}
