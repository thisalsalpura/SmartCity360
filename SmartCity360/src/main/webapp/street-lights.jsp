<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SmartCity360 | Smart City Management System | Smart Street Lights</title>
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

        .bad, .rejected {
            background: #fde2d9;
            color: #8a2c0f;
        }

        .error {
            color: #8a2c0f;
        }

        .message {
            min-height: 1.5em;
        }

        .on {
            background: #d9f2e0;
            color: #0f5a26;
        }

        .off {
            background: #e6eaee;
            color: #55606e;
        }

        .bar {
            height: 14px;
            border-radius: 4px;
            min-width: 2px;
        }

        .bar.shared {
            background: var(--primary);
        }

        .bar.unshared {
            background: var(--danger);
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

        small {
            color: var(--muted);
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
    <h1>Smart Street Lights</h1>
    <p class="lead">Part D &ndash; Flyweight pattern. Thousands of lights share a few light types (model, wattage,
        schedule). Each light only stores its own location and on/off state.</p>
    <p id="message" class="message"></p>

    <section>
        <h2>1. Shared light types (intrinsic state)</h2>
        <table>
            <thead>
            <tr>
                <th>Model</th>
                <th>Technology</th>
                <th>Watts</th>
                <th>Lumens</th>
                <th>Schedule</th>
                <th>Lights using it</th>
            </tr>
            </thead>
            <tbody id="types"></tbody>
        </table>
        <p style="margin-top: 12px;"><small>Shared type objects in memory: <strong id="sharedCount">-</strong>
            &nbsp;|&nbsp; Lights in fleet: <strong id="total">-</strong>
            &nbsp;|&nbsp; Lights on: <strong id="lightsOn">-</strong>
            &nbsp;|&nbsp; Power draw: <strong id="power">-</strong> kW</small></p>
    </section>

    <section>
        <h2>2. Manage the fleet</h2>
        <div class="row">
            <label>Add lights
                <input type="number" id="genCount" value="1000" min="1" max="100000">
            </label>
            <button id="generate" type="button">Add lights</button>
            <label>City time (HH:mm)
                <input type="text" id="time" value="22:00" maxlength="5">
            </label>
            <button id="schedule" type="button">Apply schedule</button>
            <button id="reset" type="button" class="danger">Reset fleet</button>
        </div>
        <p style="margin-top: 12px;"><small>Applying the schedule passes the time to every light's shared type. The time
            is extrinsic state,
            so the type itself never changes.</small></p>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Type</th>
                <th>Street</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>State</th>
                <th>Draw (W)</th>
            </tr>
            </thead>
            <tbody id="sample"></tbody>
        </table>
    </section>

    <section>
        <h2>3. Memory benchmark: with and without Flyweight</h2>
        <div class="row">
            <label>Number of lights
                <input type="number" id="benchCount" value="50000" min="1" max="100000">
            </label>
            <button id="benchmark" type="button">Run benchmark</button>
        </div>
        <div id="bench" hidden style="margin-top: 16px;">
            <table>
                <thead>
                <tr>
                    <th></th>
                    <th>Type objects</th>
                    <th>Estimated bytes</th>
                    <th>Build time (ms)</th>
                    <th>Size</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>With Flyweight</td>
                    <td id="sTypes"></td>
                    <td id="sBytes"></td>
                    <td id="sMs"></td>
                    <td style="width:30%">
                        <div id="sBar" class="bar shared"></div>
                    </td>
                </tr>
                <tr>
                    <td>Without Flyweight</td>
                    <td id="uTypes"></td>
                    <td id="uBytes"></td>
                    <td id="uMs"></td>
                    <td>
                        <div id="uBar" class="bar unshared"></div>
                    </td>
                </tr>
                </tbody>
            </table>
            <p style="margin-top: 12px;"><strong id="saved"></strong></p>
            <p><small id="note"></small></p>
        </div>
        <p id="error" class="error" style="margin-top: 10px;"></p>
    </section>
</main>

<script>
    var API = '${pageContext.request.contextPath}/api/street-lights';

    function el(id) {
        return document.getElementById(id);
    }

    function say(text, isError) {
        var m = el('message');
        m.textContent = text;
        m.className = 'message' + (isError ? ' error' : '');
    }

    function call(method, path, body) {
        var options = {method: method, headers: {'Content-Type': 'application/json'}};
        if (body) {
            options.body = JSON.stringify(body);
        }
        return fetch(API + path, options).then(function (r) {
            if (!r.ok) {
                return r.text().then(function (t) {
                    throw new Error('HTTP ' + r.status + ': ' + t);
                });
            }
            if (r.status === 204) {
                return null;
            }
            return r.json();
        });
    }

    function cell(text) {
        var td = document.createElement('td');
        td.textContent = text;
        return td;
    }

    function showFleet(f) {
        el('sharedCount').textContent = f.sharedTypeObjects;
        el('total').textContent = f.totalLights;
        el('lightsOn').textContent = f.lightsOn;
        el('power').textContent = f.powerDrawKw.toFixed(2);
        var body = el('sample');
        body.textContent = '';
        f.sample.forEach(function (l) {
            var tr = document.createElement('tr');
            tr.appendChild(cell(l.id));
            tr.appendChild(cell(l.model));
            tr.appendChild(cell(l.street));
            tr.appendChild(cell(l.latitude.toFixed(5)));
            tr.appendChild(cell(l.longitude.toFixed(5)));
            var state = document.createElement('td');
            var badge = document.createElement('span');
            badge.className = 'badge ' + (l.on ? 'on' : 'off');
            badge.textContent = l.on ? 'ON' : 'OFF';
            state.appendChild(badge);
            tr.appendChild(state);
            tr.appendChild(cell(l.powerDrawWatts));
            body.appendChild(tr);
        });
    }

    function loadTypes() {
        call('GET', '/types').then(function (types) {
            var body = el('types');
            body.textContent = '';
            types.forEach(function (t) {
                var tr = document.createElement('tr');
                tr.appendChild(cell(t.model));
                tr.appendChild(cell(t.technology));
                tr.appendChild(cell(t.watts));
                tr.appendChild(cell(t.lumens));
                tr.appendChild(cell(t.schedule));
                tr.appendChild(cell(t.lightCount));
                body.appendChild(tr);
            });
        }).catch(function (e) {
            say(e.message, true);
        });
    }

    function refresh() {
        call('GET', '?limit=15').then(showFleet).catch(function (e) {
            say(e.message, true);
        });
        loadTypes();
    }

    el('generate').addEventListener('click', function () {
        call('POST', '/generate', {count: parseInt(el('genCount').value, 10) || 0}).then(function (f) {
            say('Fleet now has ' + f.totalLights + ' lights sharing ' + f.sharedTypeObjects + ' type objects.', false);
            showFleet(f);
            loadTypes();
        }).catch(function (e) {
            say(e.message, true);
        });
    });

    el('schedule').addEventListener('click', function () {
        call('POST', '/schedule', {time: el('time').value.trim()}).then(function (r) {
            say('At ' + r.time + ': ' + r.lightsOn + ' on, ' + r.lightsOff + ' off, ' + r.powerDrawKw.toFixed(2) + ' kW.', false);
            refresh();
        }).catch(function (e) {
            say(e.message, true);
        });
    });

    el('reset').addEventListener('click', function () {
        call('DELETE', '').then(function (f) {
            say('Fleet reset to ' + f.totalLights + ' lights.', false);
            showFleet(f);
            loadTypes();
        }).catch(function (e) {
            say(e.message, true);
        });
    });

    el('benchmark').addEventListener('click', function () {
        var button = el('benchmark');
        el('error').textContent = '';
        button.disabled = true;
        call('POST', '/benchmark', {count: parseInt(el('benchCount').value, 10) || 0}).then(function (r) {
            el('sTypes').textContent = r.sharedTypeObjects;
            el('uTypes').textContent = r.unsharedTypeObjects;
            el('sBytes').textContent = r.estimatedSharedBytes.toLocaleString();
            el('uBytes').textContent = r.estimatedUnsharedBytes.toLocaleString();
            el('sMs').textContent = r.sharedBuildMillis;
            el('uMs').textContent = r.unsharedBuildMillis;
            el('sBar').style.width = (r.estimatedSharedBytes / r.estimatedUnsharedBytes * 100) + '%';
            el('uBar').style.width = '100%';
            el('saved').textContent = 'Estimated memory saved: ' + r.savedPercent + '%';
            el('note').textContent = r.note;
            el('bench').hidden = false;
        }).catch(function (e) {
            el('error').textContent = e.message;
        }).finally(function () {
            button.disabled = false;
        });
    });

    refresh();
</script>
</body>
</html>