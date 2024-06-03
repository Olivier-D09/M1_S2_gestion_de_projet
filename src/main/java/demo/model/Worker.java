package demo.model;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

import jakarta.persistence.*;

@Entity
public class Worker {
    @Id
    private String hostname;
    
    private LocalDateTime lastInteractionTime;

    public Worker() {
    }
    public Worker(String hostname) {
        this.hostname = hostname;
    }

    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    public Temporal getLastInteractionTime() {
        return lastInteractionTime;
        }
    public void setLastInteractionTime(LocalDateTime now) {
        this.lastInteractionTime = now;
    }
}
