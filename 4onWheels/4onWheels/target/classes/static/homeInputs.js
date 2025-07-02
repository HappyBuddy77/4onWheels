// document.addEventListener('DOMContentLoaded', function() {

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
                /*check if the two rows should switch place,
                based on the direction, asc or desc:*/
                if (dir == "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        //if so, mark as a switch and break the loop:
                        shouldSwitch= true;
                        break;
                    }
                } else if (dir == "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        //if so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                /*If a switch has been marked, make the switch
                and mark that a switch has been done:*/
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                //Each time a switch is done, increase this count by 1:
                switchcount ++;
                // document.getElementById("btn").textContent = "↑";      
            } 
            else {
                /*If no switching has been done AND the direction is "asc",
                set the direction to "desc" and run the while loop again.*/
                if (switchcount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                    // document.getElementById("btn").textContent  = "↓";
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
	
// });