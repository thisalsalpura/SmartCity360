package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.PolicyOutcome;

public record EvaluationReport(
        String policyId,
        String policyName,
        String canonical,
        PolicyOutcome outcome,
        String message
) {
}