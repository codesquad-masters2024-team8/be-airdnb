package team8.airbnb.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import team8.airbnb.hostroom.Hostroom;
import team8.airbnb.review.Review;

@Entity
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "role")
  private String role;

  @Column(name = "oauth_type")
  private String oauthType;

  @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Hostroom> hostrooms;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Review> reviews;
}
