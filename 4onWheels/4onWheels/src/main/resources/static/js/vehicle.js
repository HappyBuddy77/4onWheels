document.addEventListener('DOMContentLoaded', function() {

    document.getElementById('EV_details_new').addEventListener('submit', function(event) {

        event.preventDefault();

        var color = document.getElementById('color').value;
        var battery_capacity = document.getElementById('battery_capacity').value;
        var charging_time = document.getElementById('charging_time').value;
        var range = document.getElementById('range').value;
        var top_speed = document.getElementById('top_speed').value;
        var acceleration = document.getElementById('acceleration').value;
        var qty = document.getElementById('qty_hidden').value;

        document.getElementById('color_hidden').value = color;
        document.getElementById('battery_capacity_hidden').value = battery_capacity;
        document.getElementById('charging_time_hidden').value = charging_time;
        document.getElementById('range_hidden').value = range;
        document.getElementById('top_speed_hidden').value = top_speed;
        document.getElementById('acceleration_hidden').value = acceleration;

        if(qty != 0) {
            this.submit();
            alert("Item successfully added to cart");
        }
        else {
            alert("The current stock of this item is 0, Please try again later");
        }
    });

    document.getElementById('EV_details_used').addEventListener('submit', function(event) {

        event.preventDefault();
        var qty = document.getElementById('qty_hidden').value;

        if(qty != 0) {
            this.submit();
            alert("Item successfully added to cart");
        }
        else {
            alert("The current stock of this item is 0, Please try again later");
        }
    }); 
});