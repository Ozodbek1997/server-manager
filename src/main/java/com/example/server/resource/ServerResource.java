package com.example.server.resource;

import com.example.server.enumaration.Status;
import com.example.server.model.Response;
import com.example.server.model.Server;
import com.example.server.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;

import static java.time.LocalDate.*;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/servers")
@RequiredArgsConstructor
public class ServerResource {

    private final ServerServiceImpl serverService;


    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
          Response.builder()
                  .timeStamp(now())
                  .statusCode(OK.value())
                  .status(OK)
                  .message("Servers retrieved")
                  .data(of("servers",serverService.list(30)))
                  .build()
        );
    }
    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .statusCode(OK.value())
                        .status(OK)
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping Success" : "Ping failed")
                        .data(of("servers",server))
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getServer(@PathVariable("id") Long id){
        return ResponseEntity.ok(
          Response.builder()
                  .timeStamp(now())
                  .statusCode(OK.value())
                  .status(OK)
                  .message("Server retrieved")
                  .data(of("server",serverService.get(id)))
                  .build()

        );
    }


  @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server){
        return ResponseEntity.ok(
          Response.builder()
                  .timeStamp(now())
                  .statusCode(CREATED.value())
                  .status(CREATED)
                  .message("Server created")
                  .data(of("server",serverService.create(server)))
                  .build()
        );
  }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Server deleted")
                        .statusCode(OK.value())
                        .status(OK)
                        .data(of("deleted",serverService.delete(id)))
                      .build()
        );
    }

    @GetMapping(path = "/image/{fileName}",produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images" + fileName));
    }

}
