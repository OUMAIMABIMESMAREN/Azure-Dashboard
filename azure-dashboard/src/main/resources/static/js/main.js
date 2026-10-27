/* ═══════════════════════════════════════════════════════════════
   Azure Management Dashboard - Main JavaScript
   ═══════════════════════════════════════════════════════════════ */

document.addEventListener('DOMContentLoaded', function () {

    // ── Sidebar Toggle ─────────────────────────────────────────
    const sidebar    = document.getElementById('sidebar');
    const topbar     = document.getElementById('topbar');
    const mainContent = document.getElementById('mainContent');
    const toggleBtn  = document.getElementById('sidebarToggle');

    if (toggleBtn && sidebar) {
        toggleBtn.addEventListener('click', function () {
            const isMobile = window.innerWidth <= 900;
            if (isMobile) {
                sidebar.classList.toggle('mobile-open');
            } else {
                sidebar.classList.toggle('collapsed');
                if (topbar)      topbar.classList.toggle('sidebar-collapsed');
                if (mainContent) mainContent.classList.toggle('sidebar-collapsed');
            }
        });
    }

    // ── Auto-dismiss flash messages ────────────────────────────
    const flashAlerts = document.querySelectorAll('.flash-alert');
    flashAlerts.forEach(function (alert) {
        setTimeout(function () {
            alert.style.transition = 'opacity 0.5s ease';
            alert.style.opacity = '0';
            setTimeout(function () { alert.remove(); }, 500);
        }, 5000);
    });

    // ── Animate stat numbers on load ───────────────────────────
    document.querySelectorAll('.stat-value[data-target]').forEach(function (el) {
        const target = parseFloat(el.getAttribute('data-target'));
        const isFloat = el.getAttribute('data-float') === 'true';
        const prefix  = el.getAttribute('data-prefix') || '';
        const suffix  = el.getAttribute('data-suffix') || '';
        let start = 0;
        const duration = 1000;
        const step = (timestamp) => {
            if (!start) start = timestamp;
            const progress = Math.min((timestamp - start) / duration, 1);
            const eased = 1 - Math.pow(1 - progress, 3);
            const current = eased * target;
            el.textContent = prefix + (isFloat ? current.toFixed(1) : Math.floor(current)) + suffix;
            if (progress < 1) requestAnimationFrame(step);
        };
        requestAnimationFrame(step);
    });

    // ── Progress bars animate on load ─────────────────────────
    document.querySelectorAll('.az-progress-bar[data-width]').forEach(function (bar) {
        const width = bar.getAttribute('data-width');
        setTimeout(function () { bar.style.width = width + '%'; }, 100);
    });

    // ── Gauge fills animate on load ───────────────────────────
    document.querySelectorAll('.gauge-fill[data-percent]').forEach(function (el) {
        const pct = parseFloat(el.getAttribute('data-percent')) / 100;
        const circumference = 226;
        const offset = circumference * (1 - pct);
        setTimeout(function () { el.style.strokeDashoffset = offset; }, 100);
    });

    // ── Search filter for tables ───────────────────────────────
    const tableSearch = document.getElementById('tableSearch');
    if (tableSearch) {
        tableSearch.addEventListener('input', function () {
            const term = this.value.toLowerCase();
            document.querySelectorAll('.az-table tbody tr').forEach(function (row) {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(term) ? '' : 'none';
            });
        });
    }

    // ── Confirm dialogs for destructive actions ────────────────
    document.querySelectorAll('[data-confirm]').forEach(function (el) {
        el.addEventListener('click', function (e) {
            if (!confirm(this.getAttribute('data-confirm'))) {
                e.preventDefault();
            }
        });
    });
});

/* ── Chart.js Helpers ──────────────────────────────────────── */

function createLineChart(canvasId, labels, datasets, options) {
    const ctx = document.getElementById(canvasId);
    if (!ctx) return;
    return new Chart(ctx, {
        type: 'line',
        data: { labels: labels, datasets: datasets },
        options: Object.assign({
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: { display: datasets.length > 1, labels: { font: { size: 11 }, boxWidth: 12 } }
            },
            scales: {
                x: { grid: { color: 'rgba(0,0,0,0.04)' }, ticks: { font: { size: 11 } } },
                y: { grid: { color: 'rgba(0,0,0,0.04)' }, ticks: { font: { size: 11 } }, beginAtZero: false }
            },
            elements: { point: { radius: 3, hoverRadius: 5 } }
        }, options || {})
    });
}

function createBarChart(canvasId, labels, datasets, options) {
    const ctx = document.getElementById(canvasId);
    if (!ctx) return;
    return new Chart(ctx, {
        type: 'bar',
        data: { labels: labels, datasets: datasets },
        options: Object.assign({
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: { display: datasets.length > 1, labels: { font: { size: 11 }, boxWidth: 12 } }
            },
            scales: {
                x: { grid: { display: false }, ticks: { font: { size: 11 } } },
                y: { grid: { color: 'rgba(0,0,0,0.04)' }, ticks: { font: { size: 11 } }, beginAtZero: true }
            }
        }, options || {})
    });
}

function createDoughnutChart(canvasId, labels, data, colors) {
    const ctx = document.getElementById(canvasId);
    if (!ctx) return;
    return new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: labels,
            datasets: [{ data: data, backgroundColor: colors, borderWidth: 2, borderColor: '#fff' }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: { position: 'right', labels: { font: { size: 11 }, boxWidth: 12, padding: 10 } }
            },
            cutout: '65%'
        }
    });
}
