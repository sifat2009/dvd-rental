SELECT * FROM address WHERE address_id = 605;
DELETE FROM address
WHERE address_id = 613;.
SELECT customer_id FROM customer WHERE first_name = 'not';
SELECT * FROM rental WHERE rental_id > 16050;
DELETE FROM customer WHERE first_name = 'sifat';
SELECT COUNT(address_id) FROM address;
SELECT * FROM address WHERE address_id > 600;
DELETE FROM address WHERE address_id > 610;
SELECT * FROM inventory WHERE inventory_id > 4578;
DELETE FROM inventory WHERE inventory_id > 4581;
SELECT * FROM rental WHERE rental_id IN (1655,1665);
DELETE FROM rental WHERE rental_id>1658;
