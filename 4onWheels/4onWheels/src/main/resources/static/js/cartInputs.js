document.addEventListener('DOMContentLoaded', function() {

    updateCartTotal();
    const checkout = document.getElementById("btn_checkout");

    checkout.addEventListener("click", function () {
        // Add more if cases to check if the payment and order are correct
        window.location.href = `/cart`;
    });
 });

function updateCartTotal() {
    const table = document.getElementById("cartDetails-table");
    const rows = table.getElementsByTagName("tr");
    let total = 0;

    // Loop through each row to calculate the total
    for (let i = 1; i < rows.length - 1; i++) {
        const cells = rows[i].getElementsByTagName("td");
        const priceText = cells[3].innerText;
        console.log("Price Text found: ", priceText);
        const price = parseFloat(priceText.replace('$', '').trim());
        console.log("Price found: ", price);
        total += price;
    }

    // Update the total price in the last row
    console.log("total found: ", total);
    document.getElementById("total-price").textContent = "$" + total.toFixed(2);
}