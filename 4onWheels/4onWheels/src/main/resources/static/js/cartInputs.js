document.addEventListener('DOMContentLoaded', function() {

    updateCartTotal();
    const checkout = document.getElementById("btn_checkout");

    checkout.addEventListener("click", function (event) {

        event.preventDefault();
        var checkoutForm = document.getElementById('checkoutForm');

        const firstName = document.getElementById("firstName").value;
        const lastName = document.getElementById("lastName").value;
        const email = document.getElementById("email").value;
        const phone = document.getElementById("phone").value;
        const addressStreet = document.getElementById("addressStreet").value;
        const addressCity = document.getElementById("addressCity").value;
        const addressProvince = document.getElementById("addressProvince").value;
        const addressPostal = document.getElementById("addressPostal").value;
        const creditCard = document.getElementById("creditCard").value;
        const creditExp = document.getElementById("creditExp").value;
        const creditCVC = document.getElementById("creditCVC").value;
		
		if (firstName == "") {
			alert("Please Enter your First Name in Payment Details");
			return;
		}
		if (lastName == "") {
			alert("Please Enter your Last Name in Payment Details");
			return;
		}
		if (email == "") {
			alert("Please Enter your Email in Payment Details");
			return;
		}
        if (!(email.includes('@') && email.includes('.'))) {
			alert("Please Enter a valid  Email in Payment Details / A valid Email includes '@' and '.'");
			return;
		}
        if (phone == "") {
			alert("Please Enter your Phone Number in Payment Details");
			return;
		}
        if (isNaN(phone)) {
			alert("Please Enter a valid Phone Number in Payment Details / A valid Phone Number is numeric");
			return;
		}
		if (addressStreet == "") {
			alert("Please Enter your Address Street in Payment Details");
			return;
		}
		if (addressCity == "") {
			alert("Please Enter your Address City in Payment Details");
			return;
		}
        if (addressProvince == "") {
			alert("Please Enter your Address Province in Payment Details");
			return;
		}
		if (addressPostal == "") {
			alert("Please Enter your Postal Code in Payment Details");
			return;
		}
        if (addressPostal.length != 6) {
			alert("Please Enter a valid Postal Code in Payment Details");
			return;
		}
		if (creditCard == "") {
			alert("Please Enter your Credit Number in Payment Details");
			return;
		}
        if (isNaN(creditCard)) {
			alert("Please Enter a valid Credit Number in Payment Details / A valid Credit Number is numeric");
			return;
		}
        if (creditExp == "") {
			alert("Please Enter your Credit Expiry in Payment Details");
			return;
		}
		if (creditCVC == "") {
			alert("Please Enter your CVC in Payment Details");
			return;
		}
        if (creditCVC.length != 3) {
			alert("Please Enter your CVC in Payment Details");
			return;
		}

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
                }, 300); // for now keep at 300 milliseconds
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