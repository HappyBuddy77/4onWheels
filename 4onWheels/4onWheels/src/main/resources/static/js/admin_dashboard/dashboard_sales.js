/******** Mocked data ********/
const mockedData = [
  { id: 1, Car: 'Audi 1',       date_sold: '2025-07-01', Manafacturer: 'Audi',     Buyer: 'qwer' },
  { id: 2, Car: 'BMW 11',       date_sold: '2025-06-15', Manafacturer: 'BMW',      Buyer: 'asdf' },
  { id: 3, Car: 'Cadillac 111', date_sold: '2025-07-02', Manafacturer: 'Cadillac', Buyer: 'zxcv' },
  { id: 4, Car: 'Ford 1111',    date_sold: '2025-05-31', Manafacturer: 'Ford',     Buyer: 'bnm,' },
];

const logContainer   = document.getElementById('logContainer');
const startDateInput = document.getElementById('startDate');
const endDateInput   = document.getElementById('endDate');
const applyBtn       = document.getElementById('applyBtn');
const resetBtn       = document.getElementById('resetBtn');

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
      <span class="log-title">${rec.Car}</span>
      <span class="log-date">${rec.date_sold}</span>
      <span class="log-category">${rec.Manafacturer}</span>
      <span class="log-content">${rec.Buyer}</span>
    `;
    logContainer.appendChild(row);
  });
}

// Show all logs initially
renderLogs(mockedData);

applyBtn.addEventListener('click', () => {
  const startDate = startDateInput.value;
  const endDate   = endDateInput.value;
  const selectedManufacturers = Array.from(document.querySelectorAll('input[name="categories"]:checked'))
                                      .map(cb => cb.value);

  // Validation for partial date range
  if ((startDate && !endDate) || (!startDate && endDate)) {
    alert('Select both start and end dates or leave both empty.');
    return;
  }

  const filtered = mockedData.filter(rec => {
    const dateOk = (!startDate || rec.date_sold >= startDate) && (!endDate || rec.date_sold <= endDate);
    const manuOk = selectedManufacturers.length === 0 || selectedManufacturers.includes(rec.Manafacturer);
    return dateOk && manuOk;
  });

  renderLogs(filtered);
});

// Reset button: clears inputs & checkboxes, shows all logs
resetBtn.addEventListener('click', () => {
  startDateInput.value = '';
  endDateInput.value   = '';
  document.querySelectorAll('input[name="categories"]').forEach(cb => cb.checked = false);
  renderLogs(mockedData);
});