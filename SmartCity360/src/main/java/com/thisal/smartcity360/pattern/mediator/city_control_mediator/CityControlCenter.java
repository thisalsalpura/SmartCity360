package com.thisal.smartcity360.pattern.mediator.city_control_mediator;

import com.thisal.smartcity360.exception.InvalidControlOperationException;
import com.thisal.smartcity360.record.CityEvent;
import com.thisal.smartcity360.record.ControlLogEntry;
import com.thisal.smartcity360.record.IncidentReport;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class CityControlCenter {

    static final int MAX_EVENTS_PER_INCIDENT = 50;

    private final List<CityModule> modules = new CopyOnWriteArrayList<>();

    private final Instance<CityModule> discovered;

    protected CityControlCenter() {
        this.discovered = null;
    }

    public CityControlCenter(Collection<? extends CityModule> initialModules) {
        this.discovered = null;
        Objects.requireNonNull(initialModules, "Initial Modules must not be Null!").forEach(this::addCheck);
    }

    @Inject
    public CityControlCenter(Instance<CityModule> discovered) {
        this.discovered = Objects.requireNonNull(discovered, "Discovered Modules must not be Null!");
    }

    @PostConstruct
    void init() {
        if (discovered != null) {
            discovered.forEach(this::addCheck);
        }
    }

    private void addCheck(CityModule module) {
        Objects.requireNonNull(module, "Module must not be Null!");
        boolean duplicated = modules.stream().anyMatch(existing -> existing.name().equals(module.name()));
        if (duplicated) {
            throw new InvalidControlOperationException(module.name() + " Module is already Registered!");
        }
        modules.add(module);
    }

    public void register(CityModule module) {
        addCheck(module);
    }

    public List<CityModule> modules() {
        return List.copyOf(modules);
    }

    public IncidentReport run(String scenario, CityEvent trigger) {
        Objects.requireNonNull(scenario, "Scenario must not be Null!");
        Session session = new Session();
        session.publish(trigger);
        session.drain();
        String summary = session.processed + " Event(s) Processed! " + session.log.size() + " Module(s) reaction Recorded.";
        return new IncidentReport(scenario, session.processed, List.copyOf(session.log), summary);
    }

    private final class Session implements CityMediator {

        private final Deque<CityEvent> queue = new ArrayDeque<>();

        private final List<ControlLogEntry> log = new ArrayList<>();

        private int processed;

        @Override
        public void publish(CityEvent event) {
            queue.add(Objects.requireNonNull(event, "Event must not be Null!"));
        }

        void drain() {
            while (!queue.isEmpty()) {
                if (++processed > CityControlCenter.MAX_EVENTS_PER_INCIDENT) {
                    throw new InvalidControlOperationException("Incident Stopped! More than " + CityControlCenter.MAX_EVENTS_PER_INCIDENT + " Events, Modules may be Looping.");
                }
                CityEvent event = queue.poll();
                List<CityModule> subscribers = modules.stream()
                        .filter(module -> module.subscriptions().contains(event.type()))
                        .toList();
                if (subscribers.isEmpty()) {
                    log.add(new ControlLogEntry(log.size() + 1, event.source(), event.type(), "-", "No Module is subscribed to this Event!"));
                } else {
                    for (CityModule module : subscribers) {
                        String outcome = module.handle(event, this);
                        log.add(new ControlLogEntry(log.size() + 1, event.source(), event.type(), module.name(), outcome));
                    }
                }
            }
        }

    }

}