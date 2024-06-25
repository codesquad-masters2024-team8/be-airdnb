package team8.airbnb.hostroom;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team8.airbnb.jwt.jwtToken.JwtUtil;
import team8.airbnb.map.MapPointResponse;
import team8.airbnb.user.User;
import team8.airbnb.user.UserService;

@Service
public class HostroomService {

  private HostroomRepository hostroomRepository;
  private UserService userService;
  private JwtUtil jwtUtil;

  @Autowired
  public HostroomService(HostroomRepository hostroomRepository, UserService userService,
      JwtUtil jwtUtil) {
    this.hostroomRepository = hostroomRepository;
    this.userService = userService;
    this.jwtUtil = jwtUtil;
  }

  public Hostroom createHostroom(String token, HostroomRequest hostroomRequest) {

    String username = jwtUtil.getUsernameFromToken(token);

    User host = userService.findByUsername(username);

    Hostroom hostroom = new Hostroom();
    setHostroomDetails(hostroom, hostroomRequest);
    hostroom.setHost(host);
    hostroom.setReserved(false);
    return hostroomRepository.save(hostroom);
  }

  public Hostroom updateHostroom(Long id, HostroomRequest hostroomRequest) {
    Hostroom hostroom = hostroomRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Hostroom not found with id: " + id));
    setHostroomDetails(hostroom, hostroomRequest);
    return hostroomRepository.save(hostroom);
  }

  public void deleteHostroom(Long id) {
    Hostroom hostroom = hostroomRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Hostroom not found with id: " + id));

    if (hostroom.isReserved()) {
      throw new RuntimeException("Cannot delete reserved hostroom with id: " + id);
    }

    hostroomRepository.delete(hostroom);
  }

  public Hostroom getHostroomById(Long id) {
    return hostroomRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Hostroom not found with id: " + id));
  }

  public List<Hostroom> getAllHostrooms() {
    return hostroomRepository.findAll();
  }


  public List<MapPointResponse> getAllposition() {
    List<Hostroom> hostrooms = getAllHostrooms();
    List<MapPointResponse> MapPointResponses = new ArrayList<>();

    for (Hostroom room : hostrooms) {
      MapPointResponse mapPointResponse = new MapPointResponse(
          room.getLatitude(),
          room.getLongitude(),
          room.getHostroomName()
      );
      MapPointResponses.add(mapPointResponse);
    }

    return MapPointResponses;
  }

  private void setHostroomDetails(Hostroom hostroom, HostroomRequest hostroomRequest) {
    hostroom.setHostroomName(hostroomRequest.getHostroomName());
    hostroom.setBedNumber(hostroomRequest.getBedNumber());
    hostroom.setRestroomNumber(hostroomRequest.getRestroomNumber());
    hostroom.setBathroomNumber(hostroomRequest.getBathroomNumber());
    hostroom.setRegion(hostroomRequest.getRegion());
    hostroom.setLimitedAdults(hostroomRequest.getLimitedAdults());
    hostroom.setLimitedChildren(hostroomRequest.getLimitedChildren());
    hostroom.setLimitedInfants(hostroomRequest.getLimitedInfants());
    hostroom.setLimitedPet(hostroomRequest.getLimitedPet());
    hostroom.setPet(hostroomRequest.isPet());
    hostroom.setInstantbook(hostroomRequest.isInstantbook());
    hostroom.setSelfcheckin(hostroomRequest.isSelfcheckin());
    hostroom.setPrice(hostroomRequest.getPrice());
    hostroom.setCheckinDate(hostroomRequest.getCheckinDate());
    hostroom.setCheckoutDate(hostroomRequest.getCheckoutDate());
  }
}
