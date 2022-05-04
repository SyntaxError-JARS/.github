--create a table

create table customer (
	id serial primary key, -- id is set to primary so the different tables can have the same key
	first varchar(50) not null, -- first name of customer
	last varchar(50) not null, -- last name of customer
	pin int not null -- pin code that customer made
);


-- store data for customer profile: name, and pin; created random names and pin codes
insert into customer (id, first, last, pin) values (1, 'Mike', 'Osbourne', '1991');
insert into customer (id, first, last, pin) values (2, 'Tina', 'Sinclaire', '4856');
insert into customer (id, first, last, pin) values (3, 'Donovan', 'McDonald', '7965');
insert into customer (id, first, last, pin) values (4, 'Kimberly', 'Jackson', '2045');
insert into customer (id, first, last, pin) values (5, 'Megan', 'Floyd', '9473');
insert into customer (id, first, last, pin) values (6, 'Keith', 'Brown', '2468');
insert into customer (id, first, last, pin) values (7, 'Robert', 'Camp', '8964');
insert into customer (id, first, last, pin) values (8, 'Clyde', 'Downs', '3457');
insert into customer (id, first, last, pin) values (9, 'Anthony', 'Wood', '5578');
insert into customer (id, first, last, pin) values (10, 'Tim', 'White', '3234');


create table custaccounts ( 
	id serial primary key,
	account varchar(50) not null,
	routing varchar(50) not null,
	social varchar(50) not null,
	balance varchar(50) not null
);


insert into custaccounts (id, account, routing, social, balance) values ('1', '8651356323', '4515160451', '22-823-5626', '$507.26');
insert into custaccounts (id, account, routing, social, balance) values ('2', '8194475473', '9373047876', '65-234-5688', '$48,687.56');
insert into custaccounts (id, account, routing, social, balance) values ('3', '2819692338', '4216519229', '76-131-9878', '$99,999.99');
insert into custaccounts (id, account, routing, social, balance) values ('4', '7467359543', '4739834383', '89-545-6768', '$24,888.00');
insert into custaccounts (id, account, routing, social, balance) values ('5', '6936783627', '8470830538', '89-224-4557', '$87.46');
insert into custaccounts (id, account, routing, social, balance) values ('6', '5012255941', '0300508158', '34-899-3546', '$907.02');
insert into custaccounts (id, account, routing, social, balance) values ('7', '2899717006', '0174441681', '79-343-7875', '$102,466.98');
insert into custaccounts (id, account, routing, social, balance) values ('8', '5036976967', '8634634167', '86-467-6846', '$48,688.81');
insert into custaccounts (id, account, routing, social, balance) values ('9', '2448976449', '6871373853', '58-698-7979', '$23,788.67');
insert into custaccounts (id, account, routing, social, balance) values ('10', '7761969027', '1196887896', '24-686-2424', '$2,367.54');


alter table "BankofBanks".customer add constraint customer_fkey foreign key (id) -- I altered the table to add a foreign key on the customer table for (id)

--alter table "BankofBanks" .custaccounts ADD savings varchar(50) not null;
--I altered the table to add savings column to it then I commented it out so it would work

UPDATE custaccounts -- updating/adding data into savings account to specific customers by (id)
SET savings = '$313.00'
WHERE ID = 1;

UPDATE custaccounts 
SET savings = '$25,500.00'
WHERE ID = 2;

UPDATE custaccounts 
SET savings = '$55,750.00'
WHERE ID = 3;

UPDATE custaccounts 
SET savings = '$9,100.00'
WHERE ID = 4;

UPDATE custaccounts 
SET savings = '$10,250.00'
WHERE ID = 5;

UPDATE custaccounts 
SET savings = '$810.00'
WHERE ID = 6;

UPDATE custaccounts 
SET savings = '$1050.00'
WHERE ID = 7;

UPDATE custaccounts 
SET savings = '$900.00'
WHERE ID = 8;

UPDATE custaccounts 
SET savings = '$250.00'
WHERE ID = 9;

UPDATE custaccounts 
SET savings = '$550.00'
WHERE ID = 10;

