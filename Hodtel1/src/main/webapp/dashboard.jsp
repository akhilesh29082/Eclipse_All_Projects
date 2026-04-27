<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700;900&family=DM+Sans:wght@300;400;500;700&display=swap" rel="stylesheet">

<style>
    * { box-sizing: border-box; margin: 0; padding: 0; }

    body {
        background: #f7f9fc;
        font-family: 'DM Sans', sans-serif;
        color: #1f2937;
        min-height: 100vh;
    }

    .main {
        max-width: 1200px;
        margin-left: 240px;
        padding: 40px 24px 60px;
    }

    /* HERO */
    .hero {
        position: relative;
        border-radius: 24px;
        overflow: hidden;
        min-height: 500px;
        display: flex;
        align-items: flex-end;
        box-shadow: 0 12px 32px rgba(15, 23, 42, 0.10);
    }

    .hero-bg {
        position: absolute;
        inset: 0;
        background-image: url('https://images.unsplash.com/photo-1555854877-bab0e564b8d5?auto=format&fit=crop&w=1400&q=80');
        background-size: cover;
        background-position: center;
        filter: brightness(0.68);
        transition: transform 8s ease;
    }

    .hero:hover .hero-bg {
        transform: scale(1.04);
    }

    .hero-overlay {
        position: absolute;
        inset: 0;
        background: linear-gradient(
            to top,
            rgba(17, 24, 39, 0.78) 0%,
            rgba(17, 24, 39, 0.38) 45%,
            rgba(17, 24, 39, 0.08) 100%
        );
    }

    .hero-content {
        position: relative;
        z-index: 2;
        padding: 48px;
        width: 100%;
    }

    .hero-badge {
        display: inline-flex;
        align-items: center;
        gap: 8px;
        background: rgba(255,255,255,0.82);
        border: 1px solid rgba(255,255,255,0.95);
        border-radius: 100px;
        padding: 7px 16px;
        font-size: 12px;
        letter-spacing: 0.12em;
        text-transform: uppercase;
        color: #8b6b1f;
        margin-bottom: 20px;
        backdrop-filter: blur(6px);
        font-weight: 700;
    }

    .hero-badge span {
        width: 6px;
        height: 6px;
        background: #d4a11e;
        border-radius: 50%;
        display: inline-block;
    }

    .hero h1 {
        font-family: 'Playfair Display', serif;
        font-size: clamp(36px, 5vw, 62px);
        font-weight: 900;
        line-height: 1.1;
        color: #ffffff;
        max-width: 720px;
    }

    .hero h1 em {
        font-style: normal;
        color: #ffe08a;
    }

    .hero-sub {
        margin-top: 16px;
        font-size: 16px;
        color: rgba(255,255,255,0.88);
        font-weight: 400;
        max-width: 560px;
        line-height: 1.75;
    }

    .hero-actions {
        margin-top: 32px;
        display: flex;
        gap: 12px;
        flex-wrap: wrap;
    }

    .btn-primary {
        background: #ffffff;
        color: #1d4ed8;
        border: none;
        border-radius: 12px;
        padding: 13px 28px;
        font-size: 14px;
        font-weight: 700;
        font-family: 'DM Sans', sans-serif;
        text-decoration: none;
        display: inline-block;
        transition: all 0.2s ease;
        box-shadow: 0 8px 18px rgba(255,255,255,0.20);
    }

    .btn-primary:hover {
        background: #eff6ff;
        transform: translateY(-1px);
    }

    .btn-secondary {
        background: rgba(255,255,255,0.10);
        color: #ffffff;
        border: 1px solid rgba(255,255,255,0.35);
        border-radius: 12px;
        padding: 13px 28px;
        font-size: 14px;
        font-weight: 500;
        font-family: 'DM Sans', sans-serif;
        text-decoration: none;
        display: inline-block;
        transition: all 0.2s ease;
    }

    .btn-secondary:hover {
        background: rgba(255,255,255,0.18);
        border-color: rgba(255,255,255,0.6);
    }

    /* STATS */
    .stats-row {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
        gap: 16px;
        margin-top: 32px;
    }

    .stat-card {
        background: #ffffff;
        border: 1px solid #e5e7eb;
        border-radius: 18px;
        padding: 22px;
        box-shadow: 0 8px 22px rgba(15, 23, 42, 0.06);
        transition: all 0.2s ease;
    }

    .stat-card:hover {
        transform: translateY(-2px);
        box-shadow: 0 12px 28px rgba(15, 23, 42, 0.09);
    }

    .stat-icon {
        width: 38px;
        height: 38px;
        border-radius: 10px;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 12px;
        font-size: 16px;
    }

    .stat-icon.gold  { background: #fff7db; }
    .stat-icon.blue  { background: #e8f1ff; }
    .stat-icon.green { background: #e9f9ef; }
    .stat-icon.red   { background: #ffecec; }

    .stat-value {
        font-family: 'Playfair Display', serif;
        font-size: 26px;
        font-weight: 700;
        color: #111827;
        line-height: 1;
    }

    .stat-label {
        margin-top: 6px;
        font-size: 12px;
        color: #6b7280;
        text-transform: uppercase;
        letter-spacing: 0.08em;
        font-weight: 700;
    }

    /* SECTION TITLE */
    .section-title {
        font-family: 'Playfair Display', serif;
        font-size: 24px;
        font-weight: 700;
        color: #111827;
        margin-top: 48px;
        margin-bottom: 20px;
    }

    /* MODULES */
    .modules-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
        gap: 16px;
    }

    .module-card {
        background: #ffffff;
        border: 1px solid #e5e7eb;
        border-radius: 18px;
        padding: 22px;
        text-decoration: none;
        display: block;
        transition: all 0.22s ease;
        position: relative;
        overflow: hidden;
        box-shadow: 0 8px 22px rgba(15, 23, 42, 0.05);
    }

    .module-card::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        height: 3px;
        opacity: 0;
        transition: opacity 0.22s;
    }

    .module-card:hover {
        transform: translateY(-3px);
        box-shadow: 0 14px 28px rgba(15, 23, 42, 0.08);
        border-color: #d8dee9;
        background: #ffffff;
    }

    .module-card:hover::before { opacity: 1; }

    .mod-gold::before { background: #d4a11e; }
    .mod-blue::before { background: #3b82f6; }
    .mod-green::before { background: #22c55e; }
    .mod-purple::before { background: #8b5cf6; }
    .mod-red::before { background: #ef4444; }
    .mod-teal::before { background: #14b8a6; }

    .module-icon {
        width: 46px;
        height: 46px;
        border-radius: 12px;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-bottom: 14px;
        font-size: 20px;
    }

    .module-icon.gold   { background: #fff7db; }
    .module-icon.blue   { background: #e8f1ff; }
    .module-icon.green  { background: #e9f9ef; }
    .module-icon.purple { background: #f1ebff; }
    .module-icon.red    { background: #ffecec; }
    .module-icon.teal   { background: #e8fffb; }

    .module-name {
        font-size: 15px;
        font-weight: 700;
        color: #111827;
        margin-bottom: 6px;
    }

    .module-desc {
        font-size: 12px;
        color: #6b7280;
        line-height: 1.6;
    }

    /* NOTICE */
    .notice-banner {
        margin-top: 32px;
        background: #fffdf5;
        border: 1px solid #f3e6b2;
        border-left: 4px solid #d4a11e;
        border-radius: 14px;
        padding: 16px 22px;
        display: flex;
        align-items: center;
        gap: 14px;
        font-size: 14px;
        color: #5b4b1c;
        box-shadow: 0 8px 18px rgba(15, 23, 42, 0.04);
    }

    .notice-banner strong {
        color: #8b6b1f;
        font-weight: 700;
    }

    /* FOOTER */
    .page-footer {
        margin-top: 60px;
        padding-top: 24px;
        border-top: 1px solid #e5e7eb;
        display: flex;
        justify-content: space-between;
        align-items: center;
        flex-wrap: wrap;
        gap: 12px;
        font-size: 12px;
        color: #6b7280;
    }

    @media (max-width: 900px) {
        .main {
            margin-left: 0;
        }
    }
</style>

<div class="main">

    <div class="hero">
        <div class="hero-bg"></div>
        <div class="hero-overlay"></div>
        <div class="hero-content">
            <div class="hero-badge">
                <span></span> Hostel Management System
            </div>
            <h1>Manage Your <em>Hostel</em><br>Smarter Than Ever</h1>
            <p class="hero-sub">
                One unified dashboard for students, rooms, allocations, fees, complaints and reports — designed for your project.
            </p>
            <div class="hero-actions">
                <a href="viewStudents.jsp" class="btn-primary">View Students</a>
                <a href="viewRooms.jsp" class="btn-secondary">View Rooms</a>
            </div>
        </div>
    </div>

    <div class="stats-row">
        <div class="stat-card">
            <div class="stat-icon gold">🏠</div>
            <div class="stat-value">Rooms</div>
            <div class="stat-label">Room Management</div>
        </div>
        <div class="stat-card">
            <div class="stat-icon blue">👤</div>
            <div class="stat-value">Students</div>
            <div class="stat-label">Student Records</div>
        </div>
        <div class="stat-card">
            <div class="stat-icon green">&#8377;</div>
            <div class="stat-value">Fees</div>
            <div class="stat-label">Payments & Dues</div>
        </div>
        <div class="stat-card">
            <div class="stat-icon red">⚠</div>
            <div class="stat-value">Issues</div>
            <div class="stat-label">Complaints</div>
        </div>
    </div>

    <div class="section-title">Quick Access</div>
    <div class="modules-grid">

        <a href="addStudent.jsp" class="module-card mod-blue">
            <div class="module-icon blue">➕</div>
            <div class="module-name">Add Student</div>
            <div class="module-desc">Register new hostel students with complete details</div>
        </a>

        <a href="viewStudents.jsp" class="module-card mod-gold">
            <div class="module-icon gold">👥</div>
            <div class="module-name">Student Records</div>
            <div class="module-desc">View, search and manage student information</div>
        </a>

        <a href="addRoom.jsp" class="module-card mod-green">
            <div class="module-icon green">🛏</div>
            <div class="module-name">Add Room</div>
            <div class="module-desc">Create room entries with type, floor and capacity</div>
        </a>

        <a href="viewRooms.jsp" class="module-card mod-blue">
            <div class="module-icon blue">🏠</div>
            <div class="module-name">View Rooms</div>
            <div class="module-desc">Check room availability, status and room details</div>
        </a>

        <a href="allocateRoom.jsp" class="module-card mod-purple">
            <div class="module-icon purple">🔗</div>
            <div class="module-name">Allocate Room</div>
            <div class="module-desc">Assign students to rooms and manage occupancy</div>
        </a>

        <a href="viewAllocations.jsp" class="module-card mod-teal">
            <div class="module-icon teal">📋</div>
            <div class="module-name">View Allocations</div>
            <div class="module-desc">Track active room allocations and vacate rooms</div>
        </a>

        <a href="payment.jsp" class="module-card mod-green">
            <div class="module-icon green">💳</div>
            <div class="module-name">Fee Payment</div>
            <div class="module-desc">Record student fee payments and receipts</div>
        </a>

        <a href="paymentDetails.jsp" class="module-card mod-gold">
            <div class="module-icon gold">&#8377;</div>
            <div class="module-name">Payment Details</div>
            <div class="module-desc">Check paid amount, due amount and payment history</div>
        </a>

        <a href="complaint.jsp" class="module-card mod-red">
            <div class="module-icon red">⚠</div>
            <div class="module-name">Add Complaint</div>
            <div class="module-desc">Submit hostel complaints related to rooms and facilities</div>
        </a>

        <a href="viewComplaints.jsp" class="module-card mod-purple">
            <div class="module-icon purple">📌</div>
            <div class="module-name">View Complaints</div>
            <div class="module-desc">Track complaint status and update resolutions</div>
        </a>

        <a href="viewData.jsp" class="module-card mod-teal">
            <div class="module-icon teal">📊</div>
            <div class="module-name">Reports</div>
            <div class="module-desc">Access records, summaries and project reports</div>
        </a>

        <a href="addHostel.jsp" class="module-card mod-gold">
            <div class="module-icon gold">🏢</div>
            <div class="module-name">Add Hostel</div>
            <div class="module-desc">Create and manage hostel master details</div>
        </a>

    </div>

    <div class="notice-banner">
        <span>📢</span>
        <div><strong>Notice:</strong> Hostel fee submission deadline for this semester is <strong>30th April 2025</strong>. Students are requested to clear dues on time.</div>
    </div>

    <div class="page-footer">
        <span>© 2025 Hostel Management System. All rights reserved.</span>
        <span>Built for hostel administration project</span>
    </div>

</div>