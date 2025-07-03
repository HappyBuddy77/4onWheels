/******** Mocked data for App Health Dashboard ********/
const mockedData = [
  {
    id: 1,
    timestamp: '2025-07-01T10:15:00Z',
    environment: 'Production',
    service: 'Auth',
    status: 200,
    severity: 'INFO',
    message: 'User login successful'
  },
  {
    id: 2,
    timestamp: '2025-07-01T10:17:30Z',
    environment: 'Staging',
    service: 'Billing',
    status: 500,
    severity: 'ERROR',
    message: 'Payment service failed to respond'
  },
  {
    id: 3,
    timestamp: '2025-07-02T08:43:00Z',
    environment: 'QA',
    service: 'Search',
    status: 400,
    severity: 'WARN',
    message: 'Invalid search query received'
  },
  {
    id: 4,
    timestamp: '2025-07-03T13:00:45Z',
    environment: 'Production',
    service: 'Notifications',
    status: 503,
    severity: 'CRITICAL',
    message: 'Push notification system unavailable'
  }
];

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

// Initial render
renderLogs(mockedData);

// Apply filters
applyBtn.addEventListener('click', () => {
  const startDate = startDateInput.value; // YYYY-MM-DD
  const endDate   = endDateInput.value;   // YYYY-MM-DD
  const selectedServices = Array.from(document.querySelectorAll('input[name="services"]:checked'))
                                  .map(cb => cb.value);

  // Validate full date range or none
  if ((startDate && !endDate) || (!startDate && endDate)) {
    alert('Select both start and end dates or leave both empty.');
    return;
  }

  const filtered = mockedData.filter(rec => {
    const recDate = rec.timestamp.slice(0, 10); // extract YYYY-MM-DD
    const dateOk = (!startDate || recDate >= startDate) && (!endDate || recDate <= endDate);
    const serviceOk = selectedServices.length === 0 || selectedServices.includes(rec.service);
    return dateOk && serviceOk;
  });

  renderLogs(filtered);
});

// Reset filters
resetBtn.addEventListener('click', () => {
  startDateInput.value = '';
  endDateInput.value   = '';
  document.querySelectorAll('input[name="services"]').forEach(cb => cb.checked = false);
  renderLogs(mockedData);
});

/*********************************************************************
 * Uncomment this block to delegate filtering to your backend API.  *
 * The server should return an array of records in the same shape.  *
 *********************************************************************/
/*
function fetchLogs(payload) {
  return fetch('/api/getLogs', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  })
    .then(res => res.json());
}
*/
