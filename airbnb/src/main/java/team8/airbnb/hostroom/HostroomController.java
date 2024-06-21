package team8.airbnb.hostroom;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hostrooms")
public class HostroomController {

  @Autowired
  private HostroomService hostroomService;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Hostroom createHostroom(@RequestHeader("Authorization") String token,
      @RequestBody HostroomRequest hostroomRequest) {

    String jwtToken = token.substring(7);
    return hostroomService.createHostroom(
        jwtToken,
        hostroomRequest.getHostroomName(),
        hostroomRequest.getBedNumber(),
        hostroomRequest.getRestroomNumber(),
        hostroomRequest.getBathroomNumber(),
        hostroomRequest.getRegion(),
        hostroomRequest.getLimitedAdults(),
        hostroomRequest.getLimitedChildren(),
        hostroomRequest.getLimitedInfants(),
        hostroomRequest.getLimitedPet(),
        hostroomRequest.isPet(),
        hostroomRequest.isInstantbook(),
        hostroomRequest.isSelfcheckin(),
        hostroomRequest.getPrice(),
        hostroomRequest.getCheckinDate(),
        hostroomRequest.getCheckoutDate()
    );
  }

  @PutMapping("/{id}")
  public Hostroom updateHostroom(@PathVariable Long id,
      @RequestBody HostroomRequest hostroomRequest) {
    return hostroomService.updateHostroom(
        id,
        hostroomRequest.getHostroomName(),
        hostroomRequest.getBedNumber(),
        hostroomRequest.getRestroomNumber(),
        hostroomRequest.getBathroomNumber(),
        hostroomRequest.getRegion(),
        hostroomRequest.getLimitedAdults(),
        hostroomRequest.getLimitedChildren(),
        hostroomRequest.getLimitedInfants(),
        hostroomRequest.getLimitedPet(),
        hostroomRequest.isPet(),
        hostroomRequest.isInstantbook(),
        hostroomRequest.isSelfcheckin(),
        hostroomRequest.getPrice(),
        hostroomRequest.getCheckinDate(),
        hostroomRequest.getCheckoutDate()
    );
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteHostroom(@PathVariable Long id) {
    hostroomService.deleteHostroom(id);
  }

  @GetMapping("/{id}")
  public HostroomResponse getHostroomById(@PathVariable Long id) {
    Hostroom hostroom = hostroomService.getHostroomById(id);
    return hostroomService.convertToDto(hostroom);
  }

  @GetMapping
  public List<Hostroom> getAllHostrooms() {
    return hostroomService.getAllHostrooms();
  }
}
