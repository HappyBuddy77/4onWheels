function sortReviewList(dir) {
  const ul = document.getElementById("reviewList_li");
  const li = Array.from(ul.querySelectorAll("li"));
  li.sort((a, b) => {
    r1 = a.dataset.rating;
    r2 = b.dataset.rating;
    return dir === "asc" ? r1 - r2 : r2 - r1;
  });
  ul.innerHTML = "";
  li.forEach((i) => ul.appendChild(i));
}
