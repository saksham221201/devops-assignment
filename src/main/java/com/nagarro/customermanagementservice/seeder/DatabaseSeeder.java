package com.nagarro.customermanagementservice.seeder;

import com.nagarro.customermanagementservice.entity.Customer;
import com.nagarro.customermanagementservice.service.CustomerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder {

    @Autowired
    private CustomerService customerService;

    @PostConstruct
    public void seedDatabase(){
        List<Customer> hardCoated = getHardcodedData();
        customerService.createInitial(hardCoated);
    }

    private List<Customer> getHardcodedData() {
        return Arrays.asList(
                new Customer(1L, "Saksham", "Joshi", "sakshamjoshi@gmail.com", "9870993764"),
                new Customer(2L, "Ashish", "Gupta", "ashishgupta@gmail.com", "9870993734")
        );
    }
}
