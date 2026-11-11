package com.thisal.smartcity360.service;

import com.thisal.smartcity360.enums.PolicyOutcome;
import com.thisal.smartcity360.enums.PolicyVariable;
import com.thisal.smartcity360.exception.InvalidPolicyOperationException;
import com.thisal.smartcity360.exception.PolicyEvaluationException;
import com.thisal.smartcity360.exception.PolicyNotFoundException;
import com.thisal.smartcity360.exception.PolicyParseException;
import com.thisal.smartcity360.pattern.interpreter.policy_interpreter.PolicyExpression;
import com.thisal.smartcity360.pattern.interpreter.policy_interpreter.PolicyParser;
import com.thisal.smartcity360.record.*;
import com.thisal.smartcity360.repo.PolicyRepo;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.List;
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
                    "PARKING <= 2 OR TIME BETWEEN 18:00 08:00"
            );
            seed(
                    "School Zone Speed",
                    "Speed must not exceed 30 km/h while school is in session (07:00 to 15:00).",
                    "SPEED <= 30 OR NOT TIME BETWEEN 07:00 15:00"
            );
            return null;
        });
    }

    public List<VariableInfo> variables() {
        return Arrays.stream(PolicyVariable.values())
                .map(v -> new VariableInfo(v.name(), v.getDescription(), v.getUnit()))
                .toList();
    }

    public List<PolicyView> list() {
        return read(() -> {
            assert repo != null;
            return repo.all().stream().map(PolicyService::toView).toList();
        });
    }

    public PolicyView create(String name, String description, String rule) {
        PolicyExpression expression = PolicyParser.parse(rule);
        return write(() -> {
            assert repo != null;
            if (repo.size() >= PolicyService.MAX_POLICIES) {
                throw new InvalidPolicyOperationException("At most " + PolicyService.MAX_POLICIES + " Policies are Allowed!");
            }
            PolicyDefinition definition = new PolicyDefinition(repo.nextId(), name.trim(), description, rule.trim(), expression);
            repo.save(definition);
            return toView(definition);
        });
    }

    public PolicyView update(String id, String name, String description, String rule) {
        PolicyExpression expression = PolicyParser.parse(rule);
        return write(() -> {
            PolicyDefinition existing = require(id);
            PolicyDefinition updated = new PolicyDefinition(existing.id(), name.trim(), description, rule.trim(), expression);
            assert repo != null;
            repo.save(updated);
            return toView(updated);
        });
    }

    public void delete(String id) {
        write(() -> {
            require(id);
            assert repo != null;
            repo.remove(id);
            return null;
        });
    }

    public ValidationReport validate(String rule) {
        try {
            PolicyExpression expression = PolicyParser.parse(rule);
            return new ValidationReport(true, expression.describe(), "Rule is Valid!", -1);
        } catch (PolicyParseException e) {
            return new ValidationReport(false, null, e.getMessage(), e.getPosition());
        }
    }

    public EvaluationReport evaluate(String id, PolicyContext context) {
        return read(() -> report(require(id), context, true));
    }

    public List<EvaluationReport> evaluateAll(PolicyContext context) {
        return read(() -> {
            assert repo != null;
            return repo.all().stream().map(definition -> report(definition, context, false)).toList();
        });
    }

    private EvaluationReport report(PolicyDefinition definition, PolicyContext context, boolean strict) {
        String canonical = definition.expression().describe();
        try {
            boolean compliant = definition.expression().interpret(context);
            return new EvaluationReport(
                    definition.id(),
                    definition.name(),
                    canonical,
                    compliant
                            ? PolicyOutcome.COMPLIANT
                            : PolicyOutcome.VIOLATION,
                    compliant
                            ? "The Situation Complies with this Policy!"
                            : "The Situation Violates with this Policy!"
            );
        } catch (PolicyEvaluationException e) {
            if (strict) {
                throw e;
            }
            return new EvaluationReport(
                    definition.id(),
                    definition.name(),
                    canonical,
                    PolicyOutcome.NOT_EVALUATED,
                    e.getMessage()
            );
        }
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