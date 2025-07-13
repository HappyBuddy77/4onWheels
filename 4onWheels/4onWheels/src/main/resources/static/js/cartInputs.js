document.addEventListener('DOMContentLoaded', function() {

    updateCartTotal();
    const checkout = document.getElementById("btn_checkout");

    checkout.addEventListener("click", function (event) {
        event.preventDefault();

        var checkoutForm = document.getElementById('checkoutForm');


        checkoutForm.submit();  

    });

    document.getElementById("paymentType").addEventListener("change", function () {
        const loanFields = document.getElementById("loanFields");
        if (this.value === "Loan") {
            loanFields.style.display = "block";
        } else {
            loanFields.style.display = "none";
            document.getElementById("monthlyPayment").textContent = "$0.00";
        }
    });

    document.getElementById("depositAmount").addEventListener("input", calculateMonthlyPayment);
    document.getElementById("loanLength").addEventListener("change", calculateMonthlyPayment);
 });

function updateCartTotal() {
    const table = document.getElementById("cartDetails-table");
    const rows = table.getElementsByTagName("tr");
    let total = 0;

    for (let i = 1; i < rows.length - 1; i++) {
        const cells = rows[i].getElementsByTagName("td");
        const priceText = cells[2].innerText;
        const price = parseFloat(priceText.replace('$', '').trim());
        total += price;
    }

    console.log("total found: ", total);
    document.getElementById("total-price").textContent = "$" + total.toFixed(2);
    document.getElementById('total_amount').value = total.toFixed(2);
}

function deleteItem(event, cartItemId) {
    const isConfirmed = confirm("Are you sure you want to delete this item from your cart?");
    if (isConfirmed) {

        const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute("content");
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute("content");

        fetch("/cart/remove", {
            method: "POST",
            headers: {"Content-Type": "application/x-www-form-urlencoded", [csrfHeader]: csrfToken},
            body: `cartItem=${encodeURIComponent(cartItemId)}`
        })
        .then(response => {
            if (response.ok) {
                const row = event.target.closest('tr');
                row.remove();
                setTimeout(() => {
                    updateCartTotal();
                }, 100); // for now keep at 100 milliseconds
            }
        })
        .catch(error => {
            console.error("Network error:", error);
        });
    } 
}

function calculateMonthlyPayment() {
    const totalText = document.getElementById("total-price").textContent; 
    const total = parseFloat(totalText.replace("$", ""));
    const deposit = parseFloat(document.getElementById("depositAmount").value); 
    const loanLength = parseInt(document.getElementById("loanLength").value);

    if (loanLength > 0 && deposit >= 0 && deposit <= total) {
        const monthlyPayment = (total - deposit) / loanLength;
        document.getElementById("monthlyPayment").textContent = "$" + monthlyPayment; 
    } else {
        document.getElementById("monthlyPayment").textContent = "$0.00"; 
    }
}