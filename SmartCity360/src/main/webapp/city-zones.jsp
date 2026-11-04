<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SmartCity360 | Smart City Management System | Smart City Zones</title>
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


        ul.tree, ul.tree ul {
            margin: 0;
            padding-left: 22px;
            list-style: none;
        }

        ul.tree {
            padding-left: 0;
        }

        li.zone {
            margin: 6px 0;
        }

        .node {
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            gap: 8px;
            padding: 8px;
            border-radius: 6px;
        }

        .node:hover {
            background: #f0f3f6;
        }

        .level {
            width: 76px;
            color: var(--muted);
            font-size: 0.7rem;
            font-weight: 600;
            text-transform: uppercase;
        }

        .name {
            font-weight: 600;
        }

        .usage {
            color: var(--muted);
            font-size: 0.82rem;
        }

        .node .badge {
            font-size: 0.72rem;
        }

        .on {
            background: #d9f2e0;
            color: #0f5a26;
        }

        .off {
            background: #fde2d9;
            color: #8a2c0f;
        }

        .node button {
            min-height: 34px;
            padding: 7px 12px;
            font-size: 0.8rem;
        }

        @media (max-width: 600px) {
            .node {
                align-items: flex-start;
            }

            .level {
                width: auto;
                min-width: 76px;
            }

            .node button {
                flex: 1 1 auto;
            }

            ul.tree ul {
                padding-left: 14px;
            }
        }

    </style>
</head>
<body>
<main>
    <a class="site-link" href="${pageContext.request.contextPath}/">SmartCity360</a>
    <h1>Smart City Zones</h1>
    <p class="lead">Part B &ndash; Composite pattern. Every level (City, District, Street, Building) answers the same
        commands. Shut down power or report usage on any node and it applies to everything beneath it.</p>

    <section>
        <h2>1. Zone hierarchy</h2>
        <p id="message" class="message"></p>
        <div id="tree">Loading zones...</div>
    </section>

    <section>
        <h2>2. Add a zone</h2>
        <div class="row">
            <label>Parent zone
                <select id="parent"></select>
            </label>
            <label>Name
                <input type="text" id="name" maxlength="60">
            </label>
            <label>Kilowatt hours (buildings only)
                <input type="number" id="kwh" value="100" min="0" step="0.01">
            </label>
            <button id="add" type="button">Add zone</button>
        </div>
        <p class="usage">The new zone is created one level below the parent.</p>
    </section>
</main>

<script>
    var API = '${pageContext.request.contextPath}/api/city-zones';
    var LEVELS = ['CITY', 'DISTRICT', 'STREET', 'BUILDING'];

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

    function act(method, path, describe) {
        call(method, path).then(function (data) {
            say(describe(data), false);
            load();
        }).catch(function (e) {
            say(e.message, true);
        });
    }

    function renderNode(zone, parents) {
        var li = document.createElement('li');
        li.className = 'zone';
        var row = document.createElement('div');
        row.className = 'node';

        var level = document.createElement('span');
        level.className = 'level';
        level.textContent = zone.level;
        var name = document.createElement('span');
        name.className = 'name';
        name.textContent = zone.name;
        var usage = document.createElement('span');
        usage.className = 'usage';
        usage.textContent = zone.kilowattHours.toFixed(2) + ' kWh';
        var badge = document.createElement('span');
        badge.className = 'badge ' + (zone.poweredOn ? 'on' : 'off');
        badge.textContent = zone.poweredOn ? 'Powered' : 'Off';

        row.appendChild(level);
        row.appendChild(name);
        row.appendChild(usage);
        row.appendChild(badge);
        row.appendChild(button('Shutdown', 'danger', function () {
            act('POST', '/' + zone.id + '/shutdown', function (r) {
                return 'Shutdown ' + r.zoneName + ': ' + r.buildingsAffected + ' building(s) switched off.';
            });
        }));
        row.appendChild(button('Restore', '', function () {
            act('POST', '/' + zone.id + '/restore', function (r) {
                return 'Restored ' + r.zoneName + ': ' + r.buildingsAffected + ' building(s) switched on.';
            });
        }));
        row.appendChild(button('Report usage', 'plain', function () {
            call('GET', '/' + zone.id + '/usage').then(function (r) {
                say('Usage of ' + zone.name + ': ' + r.kilowattHours.toFixed(2) + ' kWh', false);
            }).catch(function (e) {
                say(e.message, true);
            });
        }));
        if (parents.length > 0) {
            row.appendChild(button('Remove', 'danger', function () {
                act('DELETE', '/' + zone.id, function () {
                    return 'Removed ' + zone.name + '.';
                });
            }));
        }
        li.appendChild(row);

        if (zone.children.length > 0) {
            var ul = document.createElement('ul');
            zone.children.forEach(function (child) {
                ul.appendChild(renderNode(child, parents.concat(zone.id)));
            });
            li.appendChild(ul);
        }
        return li;
    }

    function fillParents(zone, select) {
        if (zone.level !== 'BUILDING') {
            var option = document.createElement('option');
            option.value = zone.id;
            option.textContent = zone.level + ': ' + zone.name;
            select.appendChild(option);
        }
        zone.children.forEach(function (child) {
            fillParents(child, select);
        });
    }

    function load() {
        call('GET', '').then(function (root) {
            var ul = document.createElement('ul');
            ul.className = 'tree';
            ul.appendChild(renderNode(root, []));
            var box = el('tree');
            box.textContent = '';
            box.appendChild(ul);

            var select = el('parent');
            var previous = select.value;
            select.textContent = '';
            fillParents(root, select);
            if (previous) {
                select.value = previous;
            }
        }).catch(function () {
            el('tree').textContent = 'Could not load zones from the API.';
        });
    }

    el('add').addEventListener('click', function () {
        var select = el('parent');
        if (!select.value) {
            say('Choose a parent zone first.', true);
            return;
        }
        var parentLevel = select.options[select.selectedIndex].textContent.split(':')[0];
        var level = LEVELS[LEVELS.indexOf(parentLevel) + 1];
        call('POST', '', {
            parentId: select.value,
            name: el('name').value.trim(),
            level: level,
            kilowattHours: parseFloat(el('kwh').value) || 0
        }).then(function (zone) {
            say('Added ' + zone.level + ' "' + zone.name + '".', false);
            el('name').value = '';
            load();
        }).catch(function (e) {
            say(e.message, true);
        });
    });

    load();
</script>
</body>
</html>