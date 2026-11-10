package com.thisal.smartcity360.pattern.mediator.city_control_mediator;

import com.thisal.smartcity360.enums.CityEventType;
import com.thisal.smartcity360.enums.EventField;
import com.thisal.smartcity360.enums.PolicyOutcome;
import com.thisal.smartcity360.enums.PolicyVariable;
import com.thisal.smartcity360.record.CityEvent;
import com.thisal.smartcity360.record.EvaluationReport;
import com.thisal.smartcity360.record.PolicyContext;
import com.thisal.smartcity360.service.PolicyService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@ApplicationScoped
public class PolicyModule implements CityModule {

    private final PolicyService service;

    protected PolicyModule() {
        this.service = null;
    }

    @Inject
    public PolicyModule(PolicyService service) {
        this.service = Objects.requireNonNull(service, "Policy Service must not be Null!");
    }

    @Override
    public String name() {
        return "City Policies";
    }

    @Override
    public Set<CityEventType> subscriptions() {
        return Set.of(
                CityEventType.NOISE_MEASURED
        );
    }

    @Override
    public String handle(CityEvent event, CityMediator mediator) {
        PolicyContext context = new PolicyContext(
                Map.of(PolicyVariable.NOISE, event.number(EventField.NOISE)),
                event.time(EventField.TIME)
        );
        assert service != null;
        List<EvaluationReport> reports = service.evaluateAll(context);
        int evaluated = 0;
        int violated = 0;
        for (EvaluationReport report : reports) {
            if (report.outcome() == PolicyOutcome.NOT_EVALUATED) {
                continue;
            }
            evaluated++;
            if (report.outcome() == PolicyOutcome.VIOLATION) {
                violated++;
                mediator.publish(CityEvent.of(CityEventType.POLICY_VIOLATION, name())
                        .with(EventField.POLICY_ID, report.policyId())
                        .with(EventField.POLICY_NAME, report.policyName())
                        .with(EventField.DETAIL, report.message()));
            }
        }
        return evaluated + " Policy(ies) Evaluated and " + violated + " Policy(ies) Violated!";
    }

}