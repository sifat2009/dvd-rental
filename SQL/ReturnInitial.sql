SELECT * FROM customer;
SELECT * FROM inventory;
SELECT * FROM film;
SELECT * FROM payment;
SELECT * FROM store;
SELECT * FROM staff;
SELECT * FROM rental;
SELECT * FROM rental WHERE customer_id = 621;
SELECT * FROM inventory WHERE inventory_id = 4638;
SELECT last_update FROM inventory WHERE last_update = '2022-01-30 21:10:35.531+00'
WHERE UPPER(title) = UPPER('Anything Savannah')) ORDER by inventory_id DESC;
SELECT * FROM customer WHERE customer_id = 622