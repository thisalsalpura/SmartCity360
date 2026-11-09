package com.thisal.smartcity360.repo;

import com.thisal.smartcity360.record.PolicyDefinition;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class PolicyRepo {

    private final Map<String, PolicyDefinition> policies = new LinkedHashMap<>();

    private int lastId;

    public String nextId() {
        return "POL-" + (++lastId);
    }

    public List<PolicyDefinition> all() {
        return new ArrayList<>(policies.values());
    }

    public Optional<PolicyDefinition> find(String id) {
        return Optional.ofNullable(policies.get(id));
    }

    public void save(PolicyDefinition definition) {
        policies.put(definition.id(), definition);
    }

    public boolean remove(String id) {
        return policies.remove(id) != null;
    }

    public int size() {
        return policies.size();
    }

}