// DOM elements
const logContainer   = document.getElementById('logContainer');
const startDateInput = document.getElementById('startDate');
const endDateInput   = document.getElementById('endDate');
const applyBtn       = document.getElementById('applyBtn');
const resetBtn       = document.getElementById('resetBtn');

/**
 * Render array of records to the dashboard list
 * @param {Array} records
 */
function renderLogs(records) {
  logContainer.innerHTML = '';
  if (!records.length) {
    logContainer.textContent = 'No records match your filters.';
    return;
  }
  records.forEach(rec => {
    const row = document.createElement('div');
    row.className = 'log-entry';
    row.innerHTML = `
      <span class="log-date">${rec.timestamp}</span>
      <span class="log-env">${rec.environment}</span>
      <span class="log-service">${rec.service}</span>
      <span class="log-status">${rec.status}</span>
      <span class="log-severity">${rec.severity}</span>
      <span class="log-message">${rec.message}</span>
    `;
    logContainer.appendChild(row);
  });
}

/**
 * Fetch logs from backend
 * @param {Object} payload
 */
function fetchLogs(payload) {
  return fetch('/api/getLogs', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  })
    .then(res => res.json());
}

// Apply filters
applyBtn.addEventListener('click', () => {
  const startDate = startDateInput.value; // YYYY-MM-DD
  const endDate   = endDateInput.value;   // YYYY-MM-DD
  const selectedServices = Array.from(document.querySelectorAll('input[name="services"]:checked'))
                                  .map(cb => cb.value);

  if ((startDate && !endDate) || (!startDate && endDate)) {
    alert('Select both start and end dates or leave both empty.');
    return;
  }

  fetchLogs({
    startDate,
    endDate,
    services: selectedServices
  }).then(renderLogs);
});

// Reset filters
resetBtn.addEventListener('click', () => {
  startDateInput.value = '';
  endDateInput.value   = '';
  document.querySelectorAll('input[name="services"]').forEach(cb => cb.checked = false);

  fetchLogs({
    startDate: null,
    endDate: null,
    services: []
  }).then(renderLogs);
});

// Load all logs on initial page load
fetchLogs({
  startDate: null,
  endDate: null,
  services: []
}).then(renderLogs);
