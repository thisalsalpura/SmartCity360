<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SmartCity360 | Smart City Management System | City Policies</title>
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

        textarea {
            width: 100%;
            box-sizing: border-box;
            font-family: ui-monospace, Menlo, Consolas, monospace;
            min-height: 70px;
            padding: 10px;
            border: 1px solid var(--input-border);
            border-radius: 6px;
            background: #fff;
            color: var(--text);
            font-size: 0.875rem;
            resize: vertical;
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
            white-space: nowrap;
        }

        .COMPLIANT, .okmsg {
            background: #d9f2e0;
            color: #0f5a26;
        }

        .VIOLATION, .errmsg {
            background: #fde2d9;
            color: #8a2c0f;
        }

        .NOT_EVALUATED {
            background: #fff0c2;
            color: #7a5800;
        }

        .okmsg, .errmsg {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 6px;
            font-size: 0.85rem;
            margin-top: 10px;
        }

        .error {
            color: #8a2c0f;
        }

        .message {
            min-height: 1.5em;
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
    <h1>City Policies</h1>
    <p class="lead">Part E &ndash; Interpreter pattern. City rules are written in a small language, parsed into an
        expression tree and evaluated against a situation. A rule is <strong>satisfied</strong> when the situation
        complies.</p>
    <p id="message" class="message"></p>

    <section>
        <h2>1. Policies</h2>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Rule</th>
                <th>Parsed form</th>
                <th></th>
            </tr>
            </thead>
            <tbody id="policies"></tbody>
        </table>
    </section>

    <section>
        <h2>2. Write or change a rule</h2>
        <div class="row" style="margin-bottom: 14px;">
            <label style="flex:1">Name
                <input type="text" id="name" maxlength="60">
            </label>
            <label style="flex:2">Description
                <input type="text" id="description" maxlength="200">
            </label>
        </div>
        <textarea id="rule" maxlength="500" placeholder="NOISE &lt;= 55 OR NOT TIME BETWEEN 22:00 06:00"></textarea>
        <p style="margin-top: 14px; display: flex; gap: 8px; flex-wrap: wrap;">
            <button id="validate" type="button" class="plain">Check rule</button>
            <button id="save" type="button">Save as new policy</button>
            <button id="update" type="button" disabled>Update selected policy</button>
            <button id="clear" type="button" class="plain">Clear</button>
        </p>
        <div id="validation"></div>
        <p style="margin-top: 12px;"><small>Variables: <span id="variables"></span>.
            Operators: <code>&lt;</code> <code>&lt;=</code> <code>&gt;</code> <code>&gt;=</code> <code>==</code> <code>!=</code>.
            Combine with <code>AND</code>, <code>OR</code>, <code>NOT</code> and parentheses (AND binds tighter than
            OR).
            Time windows: <code>TIME BETWEEN 22:00 06:00</code> (may cross midnight).</small></p>
    </section>

    <section>
        <h2>3. Evaluate a situation</h2>
        <div class="row">
            <label>Noise (dB)<input type="number" id="noise" value="68" step="any"></label>
            <label>Parking hours<input type="number" id="parking" step="any" placeholder="not measured"></label>
            <label>Speed (km/h)<input type="number" id="speed" step="any" placeholder="not measured"></label>
            <label>Time (HH:mm)<input type="text" id="time" value="23:00" maxlength="5"></label>
            <button id="evaluate" type="button">Evaluate all policies</button>
        </div>
        <p style="margin-top: 12px;"><small>Leave a field empty if it was not measured. Policies that need it are
            reported as not evaluated.</small></p>
        <table>
            <thead>
            <tr>
                <th>Policy</th>
                <th>Outcome</th>
                <th>Detail</th>
            </tr>
            </thead>
            <tbody id="results"></tbody>
        </table>
    </section>
</main>

<script>
    var API = '${pageContext.request.contextPath}/api/policies';
    var selectedId = null;

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
            if (r.status === 204) {
                return null;
            }
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

    function codeCell(text) {
        var td = document.createElement('td');
        var c = document.createElement('code');
        c.textContent = text;
        td.appendChild(c);
        return td;
    }

    function button(label, css, handler) {
        var b = document.createElement('button');
        b.type = 'button';
        b.textContent = label;
        if (css) {
            b.className = css;
        }
        b.addEventListener('click', handler);
        return b;
    }

    function select(policy) {
        selectedId = policy.id;
        el('name').value = policy.name;
        el('description').value = policy.description;
        el('rule').value = policy.rule;
        el('update').disabled = false;
        el('validation').textContent = '';
    }

    function clearForm() {
        selectedId = null;
        el('name').value = '';
        el('description').value = '';
        el('rule').value = '';
        el('update').disabled = true;
        el('validation').textContent = '';
    }

    function loadPolicies() {
        call('GET', '').then(function (policies) {
            var body = el('policies');
            body.textContent = '';
            policies.forEach(function (p) {
                var tr = document.createElement('tr');
                tr.appendChild(cell(p.id));
                var nameCell = cell(p.name);
                var small = document.createElement('div');
                var sm = document.createElement('small');
                sm.textContent = p.description;
                small.appendChild(sm);
                nameCell.appendChild(small);
                tr.appendChild(nameCell);
                tr.appendChild(codeCell(p.rule));
                tr.appendChild(codeCell(p.canonical));
                var actions = document.createElement('td');
                actions.appendChild(button('Edit', 'plain', function () {
                    select(p);
                }));
                actions.appendChild(document.createTextNode(' '));
                actions.appendChild(button('Delete', 'danger', function () {
                    call('DELETE', '/' + p.id).then(function () {
                        if (selectedId === p.id) {
                            clearForm();
                        }
                        say('Deleted ' + p.name + '.', false);
                        loadPolicies();
                    }).catch(function (e) {
                        say(e.message, true);
                    });
                }));
                tr.appendChild(actions);
                body.appendChild(tr);
            });
        }).catch(function (e) {
            say(e.message, true);
        });
    }

    function loadVariables() {
        call('GET', '/variables').then(function (vars) {
            el('variables').textContent = vars.map(function (v) {
                return v.name + ' (' + v.unit + ')';
            }).join(', ');
        });
    }

    function formBody() {
        return {name: el('name').value.trim(), description: el('description').value, rule: el('rule').value.trim()};
    }

    el('validate').addEventListener('click', function () {
        call('POST', '/validate', {rule: el('rule').value.trim()}).then(function (r) {
            var box = el('validation');
            box.textContent = '';
            var span = document.createElement('span');
            span.className = r.valid ? 'okmsg' : 'errmsg';
            span.textContent = r.valid ? 'Valid. Parsed as: ' + r.canonical : r.message;
            box.appendChild(span);
        }).catch(function (e) {
            say(e.message, true);
        });
    });

    el('save').addEventListener('click', function () {
        call('POST', '', formBody()).then(function (p) {
            say('Saved ' + p.id + ' ' + p.name + '.', false);
            clearForm();
            loadPolicies();
        }).catch(function (e) {
            say(e.message, true);
        });
    });

    el('update').addEventListener('click', function () {
        if (!selectedId) {
            return;
        }
        call('PUT', '/' + selectedId, formBody()).then(function (p) {
            say('Updated ' + p.id + '. The new rule is active immediately.', false);
            clearForm();
            loadPolicies();
        }).catch(function (e) {
            say(e.message, true);
        });
    });

    el('clear').addEventListener('click', clearForm);

    function numberOrNull(id) {
        var v = el(id).value.trim();
        return v === '' ? null : parseFloat(v);
    }

    el('evaluate').addEventListener('click', function () {
        call('POST', '/evaluate-all', {
            noise: numberOrNull('noise'),
            parkingHours: numberOrNull('parking'),
            speed: numberOrNull('speed'),
            time: el('time').value.trim()
        }).then(function (reports) {
            var body = el('results');
            body.textContent = '';
            reports.forEach(function (r) {
                var tr = document.createElement('tr');
                tr.appendChild(cell(r.policyName));
                var outcome = document.createElement('td');
                var badge = document.createElement('span');
                badge.className = 'badge ' + r.outcome;
                badge.textContent = r.outcome.replace('_', ' ');
                outcome.appendChild(badge);
                tr.appendChild(outcome);
                tr.appendChild(cell(r.message));
                body.appendChild(tr);
            });
        }).catch(function (e) {
            say(e.message, true);
        });
    });

    loadVariables();
    loadPolicies();
</script>
</body>
</html>