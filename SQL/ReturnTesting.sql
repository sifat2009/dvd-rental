SELECT * FROM inventory;
SELECT * FROM film;
SELECT * FROM rental;
SELECT * FROM staff;
SELECT * FROM payment;
SELECT * FROM customer WHERE UPPER(first_name) = UPPER('Abraham') 
AND UPPER(last_name) = UPPER('Lincoln');
SELECT * FROM film WHERE LOWER(title) = LOWER('ANYTHING SAVANNAH');
SELECT * FROM customer WHERE customer_id = 629;
SELECT * FROM payment WHERE customer_id = 629;
SELECT * FROM rental WHERE customer_id = 629
SELECT * FROM inventory WHERE film_id = 30 AND store_id = 1;
SELECT * FROM payment ORDER by payment_id DESC;
SELECT * FROM rental WHERE inventory_id = 144 ;
SELECT * FROM rental WHERE customer_id = 632;
SELECT * FROM rental WHERE customer_id = 633;
