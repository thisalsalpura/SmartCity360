<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SmartCity360 | Smart City Management System</title>
    <style>
        * {
            font-family: 'Poppins', sans-serif;
        }

        body {
            font-family: system-ui, sans-serif;
            margin: 0;
            background: #f4f6f8;
            color: #1d2733;
        }

        main {
            max-width: 860px;
            margin: 0 auto;
            padding: 24px 16px 48px;
        }

        h1 {
            font-size: 1.4rem;
            margin-bottom: 4px;
        }

        p.lead {
            margin-top: 0;
            color: #55606e;
        }

        section {
            background: #fff;
            border: 1px solid #dde3ea;
            border-radius: 8px;
            padding: 16px;
            margin-top: 16px;
        }

        h2 {
            font-size: 1rem;
            margin: 0 0 12px;
        }

        label.layer {
            display: block;
            padding: 6px 0;
        }

        label.layer small {
            display: block;
            color: #55606e;
            margin-left: 24px;
        }

        .row {
            display: flex;
            gap: 16px;
            flex-wrap: wrap;
            align-items: end;
        }

        .row label {
            display: flex;
            flex-direction: column;
            font-size: 0.85rem;
            gap: 4px;
        }

        input[type=text], input[type=number] {
            padding: 6px 8px;
            border: 1px solid #b8c2cc;
            border-radius: 4px;
        }

        button {
            padding: 8px 18px;
            border: 0;
            border-radius: 4px;
            background: #1f6feb;
            color: #fff;
            font-size: 0.95rem;
            cursor: pointer;
        }

        button:disabled {
            background: #8aa8d8;
            cursor: wait;
        }

        .badge {
            display: inline-block;
            padding: 3px 10px;
            border-radius: 12px;
            font-weight: 600;
            font-size: 0.85rem;
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
            border-collapse: collapse;
            margin-top: 8px;
        }

        td, th {
            text-align: left;
            padding: 6px 4px;
            font-size: 0.9rem;
        }

        td.bar-cell {
            width: 45%;
        }

        .bar {
            height: 12px;
            background: #1f6feb;
            border-radius: 3px;
            min-width: 2px;
        }

        pre {
            background: #f0f3f6;
            padding: 10px;
            border-radius: 4px;
            overflow-x: auto;
            font-size: 0.8rem;
            white-space: pre-wrap;
        }

        .error {
            color: #8a2c0f;
        }

        a {
            color: #1f6feb;
        }
    </style>
</head>
<body>
<main>
    <p><a href="${pageContext.request.contextPath}/">SmartCity360</a></p>
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
            <label style="flex-direction: row; align-items: center; gap: 6px;">
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