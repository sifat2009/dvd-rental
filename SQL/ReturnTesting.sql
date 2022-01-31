SELECT * FROM customer WHERE UPPER(first_name) = UPPER('quincy') 
AND UPPER(last_name) = UPPER('john');
SELECT * FROM film WHERE LOWER(title) = LOWER('ANYTHING SAVANNAH');
SELECT * FROM customer WHERE customer_id = 629;
SELECT * FROM payment WHERE customer_id = 629;
SELECT * FROM rental WHERE customer_id = 629
SELECT * FROM customer WHERE last_update = '2022-01-31 00:23:01.794+00';
SELECT * FROM inventory WHERE film_id = 30 AND store_id = 1;
SELECT * FROM inventory;
SELECT * FROM film;
SELECT * FROM rental;
SELECT * FROM staff;
