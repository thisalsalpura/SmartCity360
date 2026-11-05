package com.thisal.smartcity360.service;

import com.thisal.smartcity360.enums.ComplaintSeverity;
import com.thisal.smartcity360.enums.ComplaintStatus;
import com.thisal.smartcity360.enums.StaffLevel;
import com.thisal.smartcity360.exception.ComplaintNotFoundException;
import com.thisal.smartcity360.pattern.chain.complaint_chain.ComplaintChainFactory;
import com.thisal.smartcity360.pattern.chain.complaint_chain.ComplaintHandler;
import com.thisal.smartcity360.record.Complaint;
import com.thisal.smartcity360.record.ComplaintReport;
import com.thisal.smartcity360.record.HandlerInfo;
import com.thisal.smartcity360.record.HandlingStep;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class ComplaintService {

    private static final int MAX_STORED_REPORTS = 200;

    private final AtomicInteger counter = new AtomicInteger();

    private final Deque<ComplaintReport> reports = new ArrayDeque<>();

    private static List<StaffLevel> dutyOrAll(Collection<StaffLevel> onDuty) {
        return onDuty == null || onDuty.isEmpty()
                ? List.of(StaffLevel.values())
                : List.copyOf(onDuty);
    }

    public List<HandlerInfo> availableHandlers() {
        List<HandlerInfo> handlers = new ArrayList<>();
        for (StaffLevel level : StaffLevel.values()) {
            ComplaintHandler handler = ComplaintChainFactory.create(List.of(level));
            handlers.add(new HandlerInfo(
                    level.name(),
                    level.getDisplayName(),
                    level.getDescription(),
                    handler.maxSeverity().getDisplayName(),
                    level.ordinal() + 1
            ));
        }
        return handlers;
    }

    public ComplaintReport submit(
            String residentName,
            String subject,
            String description,
            ComplaintSeverity severity,
            Collection<StaffLevel> onDuty
    ) {
        Instant now = Instant.now();
        Complaint complaint = new Complaint(
                String.format("CMP-%04d", counter.incrementAndGet()),
                residentName.trim(),
                subject.trim(),
                description,
                severity,
                now
        );

        ComplaintHandler chain = ComplaintChainFactory.create(dutyOrAll(onDuty));
        List<HandlingStep> trail = new ArrayList<>();
        Optional<StaffLevel> resolver = chain.handle(complaint, trail);

        ComplaintStatus status = resolver.isPresent()
                ? ComplaintStatus.RESOLVED
                : ComplaintStatus.UNRESOLVED;
        String message = resolver
                .map(level -> "Resolved by " + level.getDisplayName() + "! After " + (trail.size() - 1) + " Escalation(s).")
                .orElse("Nobody onDuty has the Authority to handle this Complaint! It stays Unresolved.");

        ComplaintReport report = new ComplaintReport(
                complaint.id(),
                complaint.residentName(),
                complaint.subject(),
                complaint.severity(),
                status,
                resolver.map(StaffLevel::getDisplayName).orElse(null),
                chain.describe(),
                List.copyOf(trail),
                message,
                now
        );
        store(report);
        return report;
    }

    public synchronized List<ComplaintReport> history() {
        return List.copyOf(reports);
    }

    public synchronized ComplaintReport find(String complaintId) {
        return reports.stream()
                .filter(report -> report.id().equals(complaintId))
                .findFirst()
                .orElseThrow(() -> new ComplaintNotFoundException("ComplaintId=" + complaintId + " is Not Found!"));
    }

    private synchronized void store(ComplaintReport report) {
        reports.addFirst(report);
        while (reports.size() > ComplaintService.MAX_STORED_REPORTS) {
            reports.removeLast();
        }
    }

}