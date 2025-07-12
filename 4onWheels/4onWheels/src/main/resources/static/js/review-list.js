function sortReviewList(dir) {
  const table = document.getElementById("reviewList");
  const tbody = table.querySelector("tbody")
  const rows = Array.from(tbody.querySelectorAll("tr"));

  rows.sort((a, b) => {
    r1 = a.dataset.rating;
    r2 = b.dataset.rating;
    return dir === "asc" ? r1 - r2 : r2 - r1;
  });

  tbody.innerHTML = "";
  rows.forEach((i) => tbody.appendChild(i));
}
