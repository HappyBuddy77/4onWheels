const logContainer   = document.getElementById('logContainer');
const startDateInput = document.getElementById('startDate');
const endDateInput   = document.getElementById('endDate');
const applyBtn       = document.getElementById('applyBtn');
const resetBtn       = document.getElementById('resetBtn');

let fetchedData = []; // Store fetched data here
function randomDate(start, end) {
  const date = new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
  return date.toISOString().slice(0, 10);
}

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
      <span class="log-title">${rec.id}</span>
      <span class="log-date">${rec.Car}</span>
      <span class="log-category">${rec.Quantity}</span>
      <span class="log-content">${rec.Price}</span>
    `;
    logContainer.appendChild(row);
  });
}

// Fetch data from the backend endpoint
function fetchOrderItems() {
  fetch('/order-items')
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok ' + response.statusText);
      }
      return response.json();
    })
    .then(data => {
      // Adapt this if your backend data shape is different!
      fetchedData = data.map(item => ({
        id: item.id,
        Car: item.productName,
        Quantity: item.quantity,
        Price: item.price,
        date_sold: randomDate(new Date(2024, 6, 1), new Date())
      }));
      renderLogs(fetchedData);
    })
    .catch(error => {
      logContainer.textContent = 'Failed to load data: ' + error.message;
    });
}

// Call fetch on page load
fetchOrderItems();

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

  const filtered = fetchedData.filter(rec => {
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
  renderLogs(fetchedData);
});
