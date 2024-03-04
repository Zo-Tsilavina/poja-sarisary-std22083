package hei.school.sarisary.endpoint.rest.controller.sary;

import hei.school.sarisary.service.sary.SaryService;
import java.io.*;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SaryController {

  @Autowired private SaryService saryService;

  @RequestMapping(
      method = RequestMethod.PUT,
      path = "/black-and-white/{id}",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public String turnToBlackAndWhite(
      @PathVariable(name = "id") String id, @RequestPart MultipartFile file) throws IOException {
    System.out.println(file);
    return saryService.uploadTransformedImage(id, file);
  }

  @GetMapping("/black-and-white/{id}")
  public ResponseEntity<Map<String, String>> getBlackAndWhiteImage(@PathVariable String id) {
    Map<String, String> urls = saryService.getImageUrls(id);
    if (urls == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(urls);
  }
}
