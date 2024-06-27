package team8.airbnb.reservedroom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import team8.airbnb.hostroom.Hostroom;
import team8.airbnb.user.User;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "reservedroom")
public class Reservedroom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "guest_id")
  private User guest;

  @Column(name = "adults")
  private int adults;

  @Column(name = "children")
  private int children;

  @Column(name = "infants")
  private int infants;

  @Column(name = "total_price")
  private int totalPrice;

  @Column(name = "checkin_date")
  private LocalDateTime checkinDate;

  @Column(name = "checkout_date")
  private LocalDateTime checkoutDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hostroom_id")
  private Hostroom hostroom;

  // 기본 생성자
  public Reservedroom() {}

  // 모든 필드를 포함하는 생성자
  public Reservedroom(User guest, int adults, int children, int infants, int totalPrice,
      LocalDateTime checkinDate, LocalDateTime checkoutDate, Hostroom hostroom) {
    this.guest = guest;
    this.adults = adults;
    this.children = children;
    this.infants = infants;
    this.totalPrice = totalPrice;
    this.checkinDate = checkinDate;
    this.checkoutDate = checkoutDate;
    this.hostroom = hostroom;
  }

  @Override
  public String toString() {
    return "Reservedroom{" +
        "id=" + id +
        ", guest=" + (guest != null ? guest.getId() : null) +
        ", adults=" + adults +
        ", children=" + children +
        ", infants=" + infants +
        ", totalPrice=" + totalPrice +
        ", checkinDate=" + checkinDate +
        ", checkoutDate=" + checkoutDate +
        ", hostroom=" + (hostroom != null ? hostroom.getId() : null) +
        '}';
  }
}