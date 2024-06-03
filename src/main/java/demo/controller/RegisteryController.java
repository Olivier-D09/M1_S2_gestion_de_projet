package demo.controller;

import demo.model.Worker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

@RestController
@RequestMapping("/workers")
public class RegisteryController {
    @Autowired
    private WorkerRepository workersRepo;

    @Transactional
    @GetMapping()
    public ResponseEntity<Object> getUsers() {
        Stream<Worker> s = workersRepo.streamAllBy();
        return new ResponseEntity<>(s.toList(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Worker> put(@RequestBody Worker user) {
        user.setLastInteractionTime(LocalDateTime.now());
        workersRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @Scheduled(fixedRate = 3000)
    public void removeInactiveWorkers() {
        Iterable<Worker> workers = workersRepo.findAll();
        LocalDateTime now = LocalDateTime.now();
        for (Worker worker : workers) {
            if (ChronoUnit.MINUTES.between(worker.getLastInteractionTime(), now) >= 2) {
                workersRepo.delete(worker);
            }
        }
    }
}
