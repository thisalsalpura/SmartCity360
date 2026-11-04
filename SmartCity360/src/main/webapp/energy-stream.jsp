<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SmartCity360 | Smart City Management System | Energy Meter Data Stream</title>
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

        .row label.checkbox-label {
            flex: 0 1 auto;
            flex-direction: row;
            align-items: center;
            align-self: center;
            gap: 8px;
            min-width: 0;
            padding: 8px 0;
            font-weight: 400;
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

            .row label.checkbox-label {
                flex-basis: 100%;
            }

            .row button {
                width: 100%;
            }
        }


        label.layer {
            display: block;
            padding: 8px 0;
        }

        label.layer input {
            margin-right: 6px;
            accent-color: var(--primary);
        }

        label.layer small {
            display: block;
            margin: 3px 0 0 24px;
            color: var(--muted);
            font-size: 0.82rem;
        }

        .ok {
            background: #d9f2e0;
            color: #0f5a26;
        }

        .rejected {
            background: #fde2d9;
            color: #8a2c0f;
        }

        .undetected {
            background: #fff0c2;
            color: #7a5800;
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

        td.bar-cell {
            width: 45%;
        }

        .bar {
            min-width: 2px;
            height: 12px;
            border-radius: 4px;
            background: var(--primary);
        }

        pre {
            overflow-x: auto;
            padding: 12px;
            border-radius: 6px;
            background: #f0f3f6;
            font-size: 0.8rem;
            white-space: pre-wrap;
            overflow-wrap: anywhere;
        }

        @media (max-width: 480px) {
            td, th {
                padding: 7px 5px;
                font-size: 0.78rem;
            }
        }

    </style>
</head>
<body>
<main>
    <a class="site-link" href="${pageContext.request.contextPath}/">SmartCity360</a>
    <h1>Energy Meter Data Stream</h1>
    <p class="lead">Part A &ndash; Decorator pattern. Pick the security layers to wrap around the meter stream,
        send a batch of readings and see what each layer does to the frame.</p>

    <section>
        <h2>1. Choose layers</h2>
        <div id="layers">Loading layers...</div>
    </section>

    <section>
        <h2>2. Send readings</h2>
        <div class="row">
            <label>Meter ID
                <input type="text" id="meterId" value="METER-001" maxlength="40">
            </label>
            <label>Readings in batch
                <input type="number" id="count" value="96" min="1" max="5000">
            </label>
            <label class="checkbox-label">
                <input type="checkbox" id="tamper"> Tamper with the frame in transit
            </label>
            <button id="send" type="button">Send and receive</button>
        </div>
        <p id="error" class="error"></p>
    </section>

    <section id="result" hidden>
        <h2>3. Result</h2>
        <p><span id="outcome" class="badge"></span> <span id="message"></span></p>
        <p><strong>Pipeline (send order):</strong> <span id="pipeline"></span></p>
        <table>
            <thead>
            <tr>
                <th>Stage</th>
                <th>Bytes</th>
                <th>Relative size</th>
            </tr>
            </thead>
            <tbody id="stages"></tbody>
        </table>
        <h2 style="margin-top:16px;">Payload as the receiver saw it</h2>
        <pre id="preview"></pre>
    </section>
</main>

<script>
    var API = '${pageContext.request.contextPath}/api/energy-stream';

    function el(id) {
        return document.getElementById(id);
    }

    function buildReadings(count) {
        var readings = [];
        var start = Date.now() - count * 15 * 60 * 1000;
        for (var i = 0; i < count; i++) {
            readings.push({
                timestamp: new Date(start + i * 15 * 60 * 1000).toISOString(),
                kilowattHours: Math.round((0.2 + Math.random() * 1.3) * 1000) / 1000
            });
        }
        return readings;
    }

    function loadLayers() {
        fetch(API + '/layers')
            .then(function (r) {
                return r.json();
            })
            .then(function (layers) {
                var box = el('layers');
                box.textContent = '';
                layers.forEach(function (layer) {
                    var label = document.createElement('label');
                    label.className = 'layer';
                    var input = document.createElement('input');
                    input.type = 'checkbox';
                    input.value = layer.name;
                    input.checked = true;
                    label.appendChild(input);
                    label.appendChild(document.createTextNode(' ' + layer.order + '. ' + layer.displayName));
                    var small = document.createElement('small');
                    small.textContent = layer.description;
                    label.appendChild(small);
                    box.appendChild(label);
                });
            })
            .catch(function () {
                el('layers').textContent = 'Could not load layers from the API.';
            });
    }

    function selectedLayers() {
        var values = [];
        document.querySelectorAll('#layers input:checked').forEach(function (i) {
            values.push(i.value);
        });
        return values;
    }

    function showResult(report) {
        var badge = el('outcome');
        var labels = {
            DELIVERED_INTACT: ['Delivered Intact', 'ok'],
            TAMPERING_REJECTED: ['Tampering Rejected', 'rejected'],
            TAMPERING_UNDETECTED: ['Tampering Undetected', 'undetected']
        };
        var info = labels[report.outcome];
        badge.textContent = info[0];
        badge.className = 'badge ' + info[1];
        el('message').textContent = report.message;
        el('pipeline').textContent = report.pipeline;
        el('preview').textContent = report.receivedPreview || '(nothing delivered)';

        var max = 0;
        report.stages.forEach(function (s) {
            max = Math.max(max, s.bytes);
        });
        var body = el('stages');
        body.textContent = '';
        report.stages.forEach(function (stage) {
            var tr = document.createElement('tr');
            var name = document.createElement('td');
            name.textContent = stage.layer;
            var bytes = document.createElement('td');
            bytes.textContent = stage.bytes;
            var barCell = document.createElement('td');
            barCell.className = 'bar-cell';
            var bar = document.createElement('div');
            bar.className = 'bar';
            bar.style.width = (stage.bytes / max * 100) + '%';
            barCell.appendChild(bar);
            tr.appendChild(name);
            tr.appendChild(bytes);
            tr.appendChild(barCell);
            body.appendChild(tr);
        });
        el('result').hidden = false;
    }

    el('send').addEventListener('click', function () {
        var button = el('send');
        el('error').textContent = '';
        button.disabled = true;
        var body = {
            meterId: el('meterId').value.trim(),
            readings: buildReadings(parseInt(el('count').value, 10) || 1),
            layers: selectedLayers(),
            tamper: el('tamper').checked
        };
        fetch(API + '/round-trip', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(body)
        })
            .then(function (r) {
                if (!r.ok) {
                    return r.text().then(function (t) {
                        throw new Error('HTTP ' + r.status + ': ' + t);
                    });
                }
                return r.json();
            })
            .then(showResult)
            .catch(function (e) {
                el('error').textContent = e.message;
            })
            .finally(function () {
                button.disabled = false;
            });
    });

    loadLayers();
</script>
</body>
</html>