<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SmartCity360 | Smart City Management System | Citizen Complaints</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&display=swap"
          rel="stylesheet">
    <style>

        :root {
            --page-bg: #f4f6f8;
            --surface: #ffffff;
            --text: #1d2733;
            --muted: #55606e;
            --border: #dde3ea;
            --input-border: #b8c2cc;
            --primary: #1f6feb;
            --primary-hover: #1859c9;
            --danger: #c0392b;
            --secondary: #6b7785;
            --radius: 8px;
        }

        * {
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            margin: 0;
            background: var(--page-bg);
            color: var(--text);
            font-family: 'Poppins', sans-serif;
            line-height: 1.5;
        }

        main {
            width: min(100% - 32px, 900px);
            margin: 0 auto;
            padding: 28px 0 48px;
        }

        .site-link {
            display: inline-block;
            margin-bottom: 12px;
            font-size: 0.9rem;
            font-weight: 600;
            text-decoration: none;
        }

        h1 {
            margin: 0 0 6px;
            font-size: 1.55rem;
            line-height: 1.3;
            font-weight: 700;
        }

        h2 {
            margin: 0 0 14px;
            font-size: 1.05rem;
            line-height: 1.4;
            font-weight: 600;
        }

        p.lead {
            margin: 0;
            color: var(--muted);
            font-size: 0.94rem;
        }

        section {
            margin-top: 18px;
            padding: 20px;
            background: var(--surface);
            border: 1px solid var(--border);
            border-radius: var(--radius);
        }

        a {
            color: var(--primary);
        }

        a:hover {
            color: var(--primary-hover);
        }

        button,
        .button {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            min-height: 38px;
            padding: 8px 16px;
            border: 1px solid transparent;
            border-radius: 6px;
            background: var(--primary);
            color: #fff;
            font-size: 0.875rem;
            font-weight: 600;
            line-height: 1.2;
            text-align: center;
            white-space: nowrap;
            cursor: pointer;
            transition: background-color 0.15s ease, border-color 0.15s ease;
        }

        button:hover:not(:disabled) {
            background: var(--primary-hover);
        }

        button:disabled {
            background: #8aa8d8;
            cursor: wait;
            opacity: 0.85;
        }

        input[type="text"],
        select,
        textarea {
            width: 100%;
            min-width: 0;
            min-height: 38px;
            padding: 8px 10px;
            border: 1px solid var(--input-border);
            border-radius: 6px;
            background: #fff;
            color: var(--text);
            font-size: 0.875rem;
        }

        textarea {
            resize: vertical;
            min-height: 80px;
            margin-top: 14px;
        }

        input[type="checkbox"] {
            width: 16px;
            height: 16px;
            margin: 0;
            accent-color: var(--primary);
        }

        input:focus,
        select:focus,
        textarea:focus,
        button:focus-visible,
        a:focus-visible {
            outline: 3px solid rgba(31, 111, 235, 0.2);
            outline-offset: 2px;
        }

        .row {
            display: flex;
            flex-wrap: wrap;
            align-items: end;
            gap: 14px;
        }

        .row label {
            display: flex;
            flex: 1 1 180px;
            flex-direction: column;
            gap: 6px;
            min-width: 150px;
            font-size: 0.85rem;
            font-weight: 500;
        }

        .badge {
            display: inline-flex;
            align-items: center;
            padding: 3px 10px;
            border-radius: 999px;
            font-size: 0.78rem;
            font-weight: 600;
        }

        .ok {
            background: #d9f2e0;
            color: #0f5a26;
        }

        .bad {
            background: #fde2d9;
            color: #8a2c0f;
        }

        .error {
            color: #8a2c0f;
            margin-top: 10px;
            font-size: 0.875rem;
        }

        label.duty {
            display: block;
            padding: 8px 0;
        }

        label.duty input {
            margin-right: 6px;
            accent-color: var(--primary);
        }

        label.duty small {
            display: block;
            margin: 3px 0 0 22px;
            color: var(--muted);
            font-size: 0.82rem;
        }

        table {
            width: 100%;
            margin-top: 8px;
            border-collapse: collapse;
        }

        td, th {
            padding: 9px 8px;
            border-bottom: 1px solid var(--border);
            font-size: 0.88rem;
            text-align: left;
        }

        th {
            color: var(--muted);
            font-weight: 600;
        }

        .HANDLED {
            color: #0f5a26;
            font-weight: 600;
        }

        .ESCALATED {
            color: #7a5800;
            font-weight: 600;
        }

        .UNHANDLED {
            color: #8a2c0f;
            font-weight: 600;
        }

        @media (max-width: 600px) {
            main {
                width: min(100% - 24px, 900px);
                padding-top: 20px;
            }

            section {
                padding: 16px;
            }

            .row {
                align-items: stretch;
            }

            .row label {
                flex-basis: 100%;
            }

            .row button {
                width: 100%;
            }
        }

    </style>
</head>
<body>
<main>
    <a class="site-link" href="${pageContext.request.contextPath}/">SmartCity360</a>
    <h1>Citizen Complaint Escalation</h1>
    <p class="lead">Part C &ndash; Chain of Responsibility. A complaint enters at the first handler on duty and is
        passed up the chain until someone has the authority to deal with it.</p>

    <section>
        <h2>1. Who is on duty?</h2>
        <div id="handlers">Loading handlers...</div>
        <p class="lead" style="margin-top: 10px;">Untick a level to see the chain re-route around it.</p>
    </section>

    <section>
        <h2>2. Submit a complaint</h2>
        <div class="row">
            <label>Resident name
                <input type="text" id="resident" value="Nimal Perera" maxlength="60">
            </label>
            <label>Subject
                <input type="text" id="subject" value="Street light not working" maxlength="100">
            </label>
            <label>Severity
                <select id="severity">
                    <option value="LOW">Low</option>
                    <option value="MEDIUM">Medium</option>
                    <option value="HIGH">High</option>
                    <option value="CRITICAL">Critical</option>
                </select>
            </label>
        </div>
        <textarea id="description" maxlength="1000" placeholder="Describe the problem (optional)"></textarea>
        <div style="margin-top: 14px;">
            <button id="submit" type="button">Submit complaint</button>
        </div>
        <p id="error" class="error"></p>
    </section>

    <section id="result" hidden>
        <h2>3. Result</h2>
        <p style="display: flex; align-items: center; gap: 8px; margin-bottom: 8px;">
            <span id="status" class="badge"></span>
            <span id="message"></span>
        </p>
        <p style="margin-bottom: 12px;"><strong>Chain on duty:</strong> <span id="chain"></span></p>
        <table>
            <thead>
            <tr>
                <th>Handler</th>
                <th>Action</th>
                <th>Note</th>
            </tr>
            </thead>
            <tbody id="trail"></tbody>
        </table>
    </section>

    <section>
        <h2>4. History</h2>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Subject</th>
                <th>Severity</th>
                <th>Status</th>
                <th>Resolved by</th>
            </tr>
            </thead>
            <tbody id="history"></tbody>
        </table>
    </section>
</main>

<script>
    var API = '${pageContext.request.contextPath}/api/complaints';

    function el(id) {
        return document.getElementById(id);
    }

    function cell(text, css) {
        var td = document.createElement('td');
        td.textContent = text;
        if (css) {
            td.className = css;
        }
        return td;
    }

    function loadHandlers() {
        fetch(API + '/handlers').then(function (r) {
            return r.json();
        }).then(function (handlers) {
            var box = el('handlers');
            box.textContent = '';
            handlers.forEach(function (h) {
                var label = document.createElement('label');
                label.className = 'duty';
                var input = document.createElement('input');
                input.type = 'checkbox';
                input.value = h.name;
                input.checked = true;
                label.appendChild(input);
                label.appendChild(document.createTextNode(' ' + h.order + '. ' + h.displayName + ' (up to ' + h.maxSeverity + ')'));
                var small = document.createElement('small');
                small.textContent = h.description;
                label.appendChild(small);
                box.appendChild(label);
            });
        }).catch(function () {
            el('handlers').textContent = 'Could not load handlers from the API.';
        });
    }

    function onDuty() {
        var values = [];
        document.querySelectorAll('#handlers input:checked').forEach(function (i) {
            values.push(i.value);
        });
        return values;
    }

    function showReport(report) {
        var badge = el('status');
        badge.textContent = report.status === 'RESOLVED' ? 'Resolved' : 'Unresolved';
        badge.className = 'badge ' + (report.status === 'RESOLVED' ? 'ok' : 'bad');
        el('message').textContent = report.message;
        el('chain').textContent = report.chain;
        var body = el('trail');
        body.textContent = '';
        report.trail.forEach(function (step) {
            var tr = document.createElement('tr');
            tr.appendChild(cell(step.handler));
            tr.appendChild(cell(step.action, step.action));
            tr.appendChild(cell(step.note));
            body.appendChild(tr);
        });
        el('result').hidden = false;
    }

    function loadHistory() {
        fetch(API).then(function (r) {
            return r.json();
        }).then(function (reports) {
            var body = el('history');
            body.textContent = '';
            reports.slice(0, 15).forEach(function (rep) {
                var tr = document.createElement('tr');
                tr.appendChild(cell(rep.id));
                tr.appendChild(cell(rep.subject));
                tr.appendChild(cell(rep.severity));
                tr.appendChild(cell(rep.status));
                tr.appendChild(cell(rep.resolvedBy || '-'));
                body.appendChild(tr);
            });
        });
    }

    el('submit').addEventListener('click', function () {
        var button = el('submit');
        el('error').textContent = '';
        if (onDuty().length === 0) {
            el('error').textContent = 'At least one handler must be on duty.';
            return;
        }
        button.disabled = true;
        fetch(API, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                residentName: el('resident').value.trim(),
                subject: el('subject').value.trim(),
                description: el('description').value,
                severity: el('severity').value,
                onDuty: onDuty()
            })
        }).then(function (r) {
            if (!r.ok) {
                return r.text().then(function (t) {
                    throw new Error('HTTP ' + r.status + ': ' + t);
                });
            }
            return r.json();
        }).then(function (report) {
            showReport(report);
            loadHistory();
        }).catch(function (e) {
            el('error').textContent = e.message;
        }).finally(function () {
            button.disabled = false;
        });
    });

    loadHandlers();
    loadHistory();
</script>
</body>
</html>