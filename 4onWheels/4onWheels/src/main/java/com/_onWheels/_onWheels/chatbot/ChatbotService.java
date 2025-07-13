package com._onWheels._onWheels.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    private final GeminiClient geminiClient;

    @Autowired
    public ChatbotService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    public String generateReply(String userMessage) {
    	String context = """
    			Only answer questions related on OnWheels data.

    			The OnWheels data can be found between <data> and </data> xml tags.
    			in the data contains categories. A category can be identified by this format:
    			<{category name}>
    			====
    			{data}
    			====


    			<data>
    			    <Vehicles>
    			    ====
    			    ('3.1 sec', '75 kWh', '6.5 hours', 'Red', 'High-performance electric sedan', 'Tesla', 'Model S', 89999.99, '396 miles', '200 mph', 'New', 2022),
    			    ('4.8 sec', '66 kWh', '8 hours', 'Blue', 'Reliable and efficient EV', 'Nissan', 'Leaf', 31999.00, '226 miles', '98 mph', 'New', 2021),
    			    ('5.2 sec', '77 kWh', '7.5 hours', 'White', 'Spacious and tech-savvy SUV', 'Ford', 'Mustang Mach-E', 45999.50, '270 miles', '124 mph', 'Used', 2021),
    			    ('6.3 sec', '64 kWh', '7 hours', 'Grey', 'Compact EV with fast charging', 'Kia', 'Niro EV', 38999.00, '239 miles', '104 mph', 'New', 2023),
    			    ('3.5 sec', '100 kWh', '8.5 hours', 'Black', 'Luxury SUV with Autopilot', 'Tesla', 'Model X', 109999.99, '348 miles', '155 mph', 'New', 2022),
    			    ('6.9 sec', '58 kWh', '6 hours', 'Silver', 'Affordable city EV', 'Hyundai', 'Ioniq 5', 41999.00, '303 miles', '115 mph', 'Used', 2022),
    			    ('4.4 sec', '90 kWh', '9 hours', 'Red', 'Sports-focused luxury EV', 'Audi', 'e-tron GT', 99999.00, '238 miles', '152 mph', 'New', 2023),
    			    ('7.5 sec', '40 kWh', '5 hours', 'White', 'Entry-level compact EV', 'Chevrolet', 'Bolt EV', 27999.99, '259 miles', '93 mph', 'Used', 2020),
    			    ('3.9 sec', '82 kWh', '7.25 hours', 'Blue', 'Modern EV crossover', 'Tesla', 'Model Y', 55999.00, '330 miles', '135 mph', 'New', 2023),
    			    ('5.6 sec', '77 kWh', '7.75 hours', 'Green', 'Stylish EV with great range', 'Volkswagen', 'ID.4', 36999.99, '275 miles', '112 mph', 'New', 2021);
    			    ====

    			    <Features>
    			    ====
    			    List electric vehicles available in the catalogue
    			    Sort vehicles by prices (sort electric vehicles based on ascending prices or based on the descending prices) or by mileage (i.e., low to high and high to low)
    			    Filter electric vehicles by brands, by shapes, by model year, by vehicle history (i.e., with reported accidents/damages, and without reported accidents/damages)
    			    View full details of each electric vehicle (including the vehicle history report if applicable) available in the catalogue
    			    View hot deals
    			    Use a loan calculator (*)
    			    Be able to compare vehicles
    			    Select a vehicle customization options
    			    Add vehicles to the shopping cart
    			    Edit or remove vehicles from the shopping cart
    			    “Check out” by providing credit card information and
    			    shipping information to purchase the vehicles in the
    			    shopping cart
    			    Write reviews on vehicles and rate vehicles using five stars
    			    Ask questions to a chatbot able to answer basic questions and
    			    help customers navigate to a specific vehicle or support page 
    			    ====
    			    <Navigation>
    			    ====
    			    Find everything we sell in the homepage: http://localhost:8080/HomePage
    			    Your cart can be accessed by clicking the icon on the top right.
    			    We value your feedback, visit this link to voice your feedback: https://zzz.zoomquilt.org/ 
    			    ====

    			    <About our Company>
    			    ====
    			    We are a pretend company. We have no company mission.
    			    ====

    			</data>

    			If the user asks anything not related to the data provided above. Kindly remind them that you are only capable of answering the data on these topics:
    			Vehicles Available, Website Features, Navigation Assistance, About our company.
    			""";
        String prompt = context + "\n\nUser: " + userMessage + "\nBot:";

        return geminiClient.sendToGemini(prompt);
    }
}
