<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SmartCity360 | Smart City Management System | City Control Center</title>
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

        button.danger {
            background: var(--danger);
        }

        button.danger:hover:not(:disabled) {
            background: #a93226;
        }

        button.plain {
            background: var(--secondary);
        }

        button.plain:hover:not(:disabled) {
            background: #56616e;
        }

        input[type="text"],
        input[type="number"],
        select {
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

        input[type="checkbox"] {
            width: 16px;
            height: 16px;
            margin: 0;
            accent-color: var(--primary);
        }

        input:focus,
        select:focus,
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
            margin-bottom: 12px;
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

        .error {
            color: #8a2c0f;
        }

        .message {
            min-height: 1.5em;
            margin-top: 10px;
        }

        code {
            font-family: ui-monospace, Menlo, Consolas, monospace;
            font-size: 0.82rem;
            background: #f0f3f6;
            padding: 2px 6px;
            border-radius: 4px;
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
            vertical-align: top;
        }

        th {
            color: var(--muted);
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
    <h1>City Control Center</h1>
    <p class="lead">Compound design &ndash; Mediator. The Composite, Chain of Responsibility, Flyweight and Interpreter
        modules never call each other. They publish events to the control center, which routes them to subscribers.</p>

    <section>
        <h2>1. Modules and their subscriptions</h2>
        <table>
            <thead>
            <tr>
                <th>Module</th>
                <th>Listens for</th>
            </tr>
            </thead>
            <tbody id="modules"></tbody>
        </table>
    </section>

    <section>
        <h2>2. Trigger a scenario</h2>
        <div class="row">
            <label>Power outage in zone
                <select id="zone"></select>
            </label>
            <button id="outage" type="button">Report outage</button>
        </div>
        <div class="row">
            <label>Noise (dB)
                <input type="number" id="noise" value="80" step="any">
            </label>
            <label>Time (HH:mm)
                <input type="text" id="noiseTime" value="23:00" maxlength="5">
            </label>
            <button id="measure" type="button">Report noise reading</button>
        </div>
        <div class="row">
            <label>City clock (HH:mm)
                <input type="text" id="clock" value="19:00" maxlength="5">
            </label>
            <button id="tick" type="button">Change city time</button>
        </div>
        <p id="message" class="message"></p>
    </section>

    <section id="result" hidden>
        <h2>3. What happened</h2>
        <p style="margin-bottom: 12px;"><strong id="scenario"></strong> &ndash; <span id="summary"></span></p>
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>Event</th>
                <th>From</th>
                <th>Handled by</th>
                <th>Outcome</th>
            </tr>
            </thead>
            <tbody id="trace"></tbody>
        </table>
    </section>
</main>

<script>
    var CONTEXT = '${pageContext.request.contextPath}';
    var API = CONTEXT + '/api/control-center';

    function el(id) {
        return document.getElementById(id);
    }

    function say(text, isError) {
        var m = el('message');
        m.textContent = text;
        m.className = 'message' + (isError ? ' error' : '');
    }

    function call(url, body) {
        var options = {headers: {'Content-Type': 'application/json'}};
        if (body) {
            options.method = 'POST';
            options.body = JSON.stringify(body);
        }
        return fetch(url, options).then(function (r) {
            return r.json().then(function (data) {
                if (!r.ok) {
                    throw new Error(data.message || ('HTTP ' + r.status));
                }
                return data;
            });
        });
    }

    function cell(text) {
        var td = document.createElement('td');
        td.textContent = text;
        return td;
    }

    function loadModules() {
        call(API + '/modules').then(function (modules) {
            var body = el('modules');
            body.textContent = '';
            modules.forEach(function (m) {
                var tr = document.createElement('tr');
                tr.appendChild(cell(m.name));
                var td = document.createElement('td');
                m.subscriptions.forEach(function (s) {
                    var c = document.createElement('code');
                    c.textContent = s;
                    td.appendChild(c);
                    td.appendChild(document.createTextNode(' '));
                });
                tr.appendChild(td);
                body.appendChild(tr);
            });
        }).catch(function (e) {
            say(e.message, true);
        });
    }

    function fillZones(zone, select) {
        var option = document.createElement('option');
        option.value = zone.id;
        option.textContent = zone.level + ': ' + zone.name;
        select.appendChild(option);
        zone.children.forEach(function (child) {
            fillZones(child, select);
        });
    }

    function loadZones() {
        call(CONTEXT + '/api/city-zones').then(function (root) {
            var select = el('zone');
            select.textContent = '';
            fillZones(root, select);
        }).catch(function () {
            say('Could not load zones. Is the city zones module running?', true);
        });
    }

    function show(report) {
        el('scenario').textContent = report.scenario;
        el('summary').textContent = report.summary;
        var body = el('trace');
        body.textContent = '';
        report.log.forEach(function (entry) {
            var tr = document.createElement('tr');
            tr.appendChild(cell(entry.step));
            var ev = document.createElement('td');
            var c = document.createElement('code');
            c.textContent = entry.event;
            ev.appendChild(c);
            tr.appendChild(ev);
            tr.appendChild(cell(entry.source));
            tr.appendChild(cell(entry.handledBy));
            tr.appendChild(cell(entry.outcome));
            body.appendChild(tr);
        });
        el('result').hidden = false;
    }

    function run(button, path, body) {
        button.disabled = true;
        say('', false);
        call(API + path, body).then(show).catch(function (e) {
            say(e.message, true);
        }).finally(function () {
            button.disabled = false;
        });
    }

    el('outage').addEventListener('click', function () {
        run(el('outage'), '/outage', {zoneId: el('zone').value});
    });

    el('measure').addEventListener('click', function () {
        run(el('measure'), '/noise', {noise: parseFloat(el('noise').value) || 0, time: el('noiseTime').value.trim()});
    });

    el('tick').addEventListener('click', function () {
        run(el('tick'), '/time', {time: el('clock').value.trim()});
    });

    loadModules();
    loadZones();
</script>
</body>
</html>