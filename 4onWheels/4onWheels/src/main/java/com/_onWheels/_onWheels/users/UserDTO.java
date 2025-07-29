package com._onWheels._onWheels.users;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com._onWheels._onWheels.Order;
import com._onWheels._onWheels.OrderDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private int numebrOfOrders;
    private List<OrderDTO> orders; 
}
