SELECT * FROM customer WHERE customer_id = 633;
SELECT * FROM film WHERE UPPER(title) = UPPER('Anything Savannah');
SELECT store_id FROM customer WHERE customer_id = 633;

SELECT * FROM inventory WHERE store_id = 
(SELECT store_id FROM customer WHERE customer_id = 633) 
AND film_id = 
(SELECT film_id FROM film WHERE UPPER(title) = UPPER('Anything Savannah'));

SELECT * FROM rental WHERE inventory_id IN
(SELECT inventory_id FROM inventory WHERE store_id = 
(SELECT store_id FROM customer WHERE customer_id = 633) 
AND film_id = 
(SELECT film_id FROM film WHERE UPPER(title) = UPPER('Anything Savannah')));


