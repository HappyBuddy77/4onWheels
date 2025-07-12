// document.addEventListener('DOMContentLoaded', function() {

// Sort the Table

// Source From: https://www.w3schools.com/howto/howto_js_sort_table.asp
// Implemented to fit pourpose of project
// Fix Arrow Buttons

    function sortTable(n, btn) {
        var switching = true;
        var rows, i, x, y, switchcount = 0;
        var shouldSwitch = true;
        var table = document.getElementById("vehicleList");
        //Set the sorting direction to ascending:
        var dir = "asc"; 
        /*Make a loop that will continue until
        no switching has been done:*/
        while (switching) {
            //start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /*Loop through all table rows (except the
            first, which contains table headers):*/
            for (i = 1; i < (rows.length - 1); i++) {
                //start by saying there should be no switching:
                shouldSwitch = false;
                /*Get the two elements you want to compare,
                one from current row and one from the next:*/
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];

                var isNumeric = !isNaN(x.innerHTML) && !isNaN(y.innerHTML);

                if (dir == "asc") {
                    if (isNumeric) {
                        // Compare as numbers if the values are numeric
                        if (parseFloat(x.innerHTML) > parseFloat(y.innerHTML)) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                    else {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                            shouldSwitch= true;
                            break;
                        }
                    }
                } 
                else if (dir == "desc") {
                    if (isNumeric) {
                        if (parseFloat(x.innerHTML) < parseFloat(y.innerHTML)) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                    else{
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchcount ++;
            } 
            else {
                if (switchcount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
        // let originalDir = btn.getAttribute("dir");
        // if (originalDir == "asc") {
        //     btn.setAttribute("dir", "desc");
        //     btn.textContent  = "↓";
        // }
        // else {
        //     btn.setAttribute("dir", "asc");
        //     btn.textContent  = "↑";
        // }
    }

    

document.addEventListener('DOMContentLoaded', function() {

    // Filter Functions
    const filter_form = document.getElementById("filter_form");
    const filter_on = document.getElementById("filter_on");
    const filter_off = document.getElementById("filter_off");

    // Vehicle Table
    const table = document.getElementById("vehicleList");
    const rows = table.getElementsByTagName("tr");

    // View Specific Vehicle
    for (let i = 1; i < rows.length; i++) {
        rows[i].addEventListener("click", function () {
            // Highlight selected row
            // for (let j = 1; j < rows.length; j++) {
            //     rows[j].classList.remove("selected");
            // }
            this.classList.add("selected");

            // Collect data from clicked row
            const cells = this.getElementsByTagName("td");
            const data = {
                id: cells[0].innerText,

            };

            if(cells[4].innerText == "Used") {
                window.location.href = `/usedVehicle/${data.id}`;
            } else {
                window.location.href = `/newVehicle/${data.id}`;
            }

            // Redirect to vehicle.html with query string
            // const queryString = new URLSearchParams(data).toString();
            // window.location.href = `vehicle.html?${queryString}`;
        });
    }

    // Filter Vehicle 
    filter_on.addEventListener("click", function () {
        // Get input values
        const filterMake = document.getElementById("make").value.toLowerCase();
        const filterModel = document.getElementById("model").value.toLowerCase();
        const filterYear = document.getElementById("year").value.toLowerCase();
        const filterType = document.getElementById("type").value.toLowerCase();
        const filterEvHistory = document.getElementById("evHistory").value;

        // const rows = table.getElementsByTagName("tr");

        // Loop through table rows (start from 1 to skip header)
        for (let i = 1; i < rows.length; i++) {
            const cells = rows[i].getElementsByTagName("td");

            const make = cells[1].innerText.toLowerCase();
            const model = cells[2].innerText.toLowerCase();
            const year = cells[3].innerText.toLowerCase();
            const type = cells[4].innerText.toLowerCase();
            const evHistory = rows[i].getAttribute("evhistory-data");

            const match =
                (filterMake == "" || make.includes(filterMake)) &&
                (filterModel == "" || model.includes(filterModel)) &&
                (filterYear == "" || year.includes(filterYear)) &&
                (filterType == "" || type.includes(filterType)) &&
                (filterEvHistory === "" || (filterEvHistory === "History" && evHistory != "" && evHistory != null) ||
                (filterEvHistory === "No History" && (evHistory === "" || evHistory === null)));

            if(match) {
                rows[i].style.display = "";
            }
            else {
                rows[i].style.display = "none";
            }
            
        }
    });

    filter_off.addEventListener("click", function () {
        filter_form.reset(); 

        // const rows = document.getElementById("vehicleList").getElementsByTagName("tr");
        for (let i = 1; i < rows.length; i++) {
            rows[i].style.display = "";
        }
    });

    document.getElementById("compare_btn").addEventListener("click", function(event) {
        event.preventDefault(); // Stop default submission
        const id1 = document.getElementById("compareID1").value;
        const id2 = document.getElementById("compareID2").value;

        // Validate IDs if needed here

        // Redirect to the dynamic path
        window.location.href = `/compare/${id1}/${id2}`;
    });
	
});