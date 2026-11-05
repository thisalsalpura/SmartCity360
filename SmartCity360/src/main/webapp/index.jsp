<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SmartCity360 | Smart City Management System</title>
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

        .page-intro {
            margin-bottom: 24px;
        }

        .page-links {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
            gap: 16px;
            margin-top: 22px;
        }

        .page-card {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            gap: 10px;
            padding: 20px;
            border: 1px solid var(--border);
            border-radius: var(--radius);
            background: var(--surface);
        }

        .page-card h2 {
            margin: 0;
        }

        .page-card p {
            flex: 1;
            margin: 0 0 6px;
            color: var(--muted);
            font-size: 0.9rem;
        }

        .page-card a.button {
            text-decoration: none;
        }
    </style>
</head>
<body>
<main>
    <div class="page-intro">
        <h1>SmartCity360</h1>
        <p class="lead">Smart City Management System</p>
    </div>

    <div class="page-links">
        <section class="page-card">
            <h2>Energy Meter Data Stream</h2>
            <p>Part A &ndash; Decorator pattern. Configure security layers and inspect how they affect meter
                readings.</p>
            <a class="button" href="${pageContext.request.contextPath}/energy-stream.jsp">
                Open Energy Stream
            </a>
        </section>

        <section class="page-card">
            <h2>Smart City Zones</h2>
            <p>Part B &ndash; Composite pattern. Manage city zones and apply power or usage operations across the
                hierarchy.</p>
            <a class="button" href="${pageContext.request.contextPath}/city-zones.jsp">
                Open City Zones
            </a>
        </section>

        <section class="page-card">
            <h2>Citizen Complaint Escalation</h2>
            <p>Part C &ndash; Chain of Responsibility. Submit and track citizen complaints through an escalating chain
                of handlers.</p>
            <a class="button" href="${pageContext.request.contextPath}/complaints.jsp">
                Open Citizen Complaints
            </a>
        </section>
    </div>
</main>
</body>
</html>