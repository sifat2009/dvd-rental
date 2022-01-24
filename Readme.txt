[1/21 12:33 PM] Saifur RahmanRent a DVD: (Due by Sunday)
    
Dev
Rent a DVD: (Due by Sunday)

Users can rent a DVD 
			
 Insert a new row in the rental table
			
 If it’s a new customer, create a customer, add the address, etc.
		

Implementation Hints:
	
 Find film by name, if not found return error with a message
	
 Find Store’s address and then find store by store id. If Store is not found return an error with a message
	
 Find the stuff by email, if not found return an error message
	
 Find customer address and then customer by FirstName, LastName, and Address. If the customer is not found, insert the address first and then the customer.
	
 Insert a new inventory
	
 Insert a new Rental

Sample Request:
    
      Sample Request:
{​​​
        "film": {
        "filmName": "ACADEMY DINOSAUR",
        },
        "store": {​​​
			"storeId":2
	    },
        "address": {​​​
                        "address": "47 MySakila Drive",
                        "address2": null,
                        "district": "Alberta",
                        "cityId": 300,
                        "postalCode": "",
                        "phone": ""
           
        }​​​,
        "customer": {​​​
                "firstName": "",
                "lastName": "",
                "email": "",
                "activebool":true
        }​​​,
        "staff":{​​​
               "email" :"Mike.Hillyer@sakilastaff.com"
        }​​​
}​​​
Any questions reach out to me
    
    
  
  

 

Any questions reach out to me



-- Different queries
SELECT * FROM address;
SELECT DISTINCT(address) FROM address;
SELECT COUNT(address_id) AS numOfAddress FROM address;
SELECT DISTINCT COUNT(address2) FROM address;
SELECT * FROM film;
SELECT * FROM store;
SELECT * FROM staff;
SELECT * FROM customer;
SELECT * FROM RENTAL;
SELECT * FROM inventory;
SELECT COUNT (email) as num_of_email from customer;
SELECT DISTINCT COUNT(email) as num_of_email FROM customer;
SELECT * FROM address WHERE address_id = 605;
DELETE FROM address
WHERE address_id = 613;
--Testing after post call.
SELECT * FROM customer WHERE first_name = 'sifat';
DELETE FROM customer WHERE first_name = 'sifat';
SELECT * FROM address WHERE address_id > 600;
DELETE FROM address WHERE address_id = 614;