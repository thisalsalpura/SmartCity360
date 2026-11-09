package com.thisal.smartcity360.service;

import com.thisal.smartcity360.exception.PolicyNotFoundException;
import com.thisal.smartcity360.pattern.interpreter.policy_interpreter.PolicyParser;
import com.thisal.smartcity360.record.PolicyDefinition;
import com.thisal.smartcity360.record.PolicyView;
import com.thisal.smartcity360.repo.PolicyRepo;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

@ApplicationScoped
public class PolicyService {

    private static final int MAX_POLICIES = 50;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final PolicyRepo repo;

    protected PolicyService() {
        this.repo = null;
    }

    @Inject
    public PolicyService(PolicyRepo repo) {
        this.repo = Objects.requireNonNull(repo, "Policy Repo must not be Null!");
    }

    private static PolicyView toView(PolicyDefinition definition) {
        return new PolicyView(
                definition.id(),
                definition.name(),
                definition.description(),
                definition.rule(),
                definition.expression().describe()
        );
    }

    @PostConstruct
    void init() {
        write(() -> {
            seed(
                    "Night Noise Limit",
                    "Between 22:00 and 06:00 noise must not exceed 55 dB.",
                    "NOISE <= 55 OR NOT TIME BETWEEN 22:00 06:00"
            );
            seed(
                    "Daytime Noise Limit",
                    "Noise must never exceed 75 dB.",
                    "NOISE <= 75"
            );
            seed(
                    "Parking Time Limit",
                    "Parking is limited to 2 hours, except overnight from 18:00 to 08:00.",
                    "PARKING_HOURS <= 2 OR TIME BETWEEN 18:00 08:00"
            );
            seed(
                    "School Zone Speed",
                    "Speed must not exceed 30 km/h while school is in session (06:30 to 14:30).",
                    "SPEED <= 30 OR NOT TIME BETWEEN 07:00 15:00"
            );
            return null;
        });
    }

    

    private void seed(String name, String description, String rule) {
        assert repo != null;
        repo.save(new PolicyDefinition(repo.nextId(), name, description, rule, PolicyParser.parse(rule)));
    }

    private PolicyDefinition require(String id) {
        assert repo != null;
        return repo.find(id).orElseThrow(() -> new PolicyNotFoundException("PolicyId=" + id + " is Not Found!"));
    }

    private <T> T read(Supplier<T> action) {
        lock.readLock().lock();
        try {
            return action.get();
        } finally {
            lock.readLock().unlock();
        }
    }

    private <T> T write(Supplier<T> action) {
        lock.writeLock().lock();
        try {
            return action.get();
        } finally {
            lock.writeLock().unlock();
        }
    }

}