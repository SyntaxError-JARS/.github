
--drop table account_owner;
--drop table accounts;
--drop table transaction_history;



--create a table

create table account_owner (
	account_owner_ID varchar(24) not null primary key, -- id is set to primary so the different tables can have the same key
	fname varchar(15) not null, -- first name of account owner
	lname varchar(15) not null, -- last name of account owner
	dob date, -- date of birth that account owner made
	last4_social integer not null unique -- last 4 numbers of social security that accout owner made 
	);


-- store data for the account owner profiles: full name, email and social security; created random names and IDs
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00012c', 'Mike', 'Osbourne', '11/14/1991', '3118');
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00012d', 'Tina', 'Sinclaire', '1/8/1997', '7884');
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00012e', 'Donovan', 'McDonald', '6/23/1979', '9823');
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00012f', 'Kimberly', 'Jackson', '10/19/1981', '6592');
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00013g', 'Megan', 'Floyd', '6/17/1988', '9084');
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00013h', 'Keith', 'Brown', '8/8/1980', '3031');
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00013i', 'Robert', 'Camp', '7/26/1981', '9176');
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00014j', 'Clyde', 'Downs', '4/20/1980', '1588');
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00014k', 'Anthony', 'Wood', '3/23/1999', '3476');
insert into account_owner (account_owner_ID, fname, lname, dob, last4_social) values ('627c158efc13ae69cf00015l', 'Tim', 'White', '2/15/1983', '4473');


create table accounts (
	account_ID varchar(11) not null unique primary key,
	account_number varchar(12) not null unique,
	account_type varchar(10) not null,
	account_pin integer,
	account_name varchar(30) not null,
	last_login date,
	current_balance decimal(8,2),
	account_owner_ID varchar(24) not null,
	constraint fk_accounts foreign key(account_owner_ID) references account_owner(account_owner_ID)
);


insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('283-54-3118', '451516045113', 'Checking', '69843', 'Michael Osbourne', '05/08/2022', '313.00', '627c158efc13ae69cf00012c');
insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('443-76-7884', '937304787632', 'Savings', '54358', 'Tina Sinclaire',  '05/10/2022', '2500.00', '627c158efc13ae69cf00012d');
insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('728-56-9823', '421651922953', 'Checking', '68353', 'Donovan McDonald', '05/10/2022', '55750.00', '627c158efc13ae69cf00012e');
insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('263-24-6592', '473983438343', 'Checking', '88534', 'Kimberly Jackson', '05/11/2022', '9100.00', '627c158efc13ae69cf00012f');
insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('255-25-9084', '847083053878', 'Savings', '40731', 'Megan Ford', '05/09/2022', '10250.00', '627c158efc13ae69cf00013g');
insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('444-22-3031', '030050815842', 'Checking', '83635', 'Keith Brown', '05/10/2022', '810.00', '627c158efc13ae69cf00013h');
insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('343-23-9176', '017444168891', 'Savings', '41465', 'Robert Camp', '05/07/2022', '1050.00', '627c158efc13ae69cf00013i');
insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('244-77-1588', '863463416785', 'Savings', '89155', 'Clyde Downs', '05/11/2022', '900.00', '627c158efc13ae69cf00014j');
insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('624-74-3476', '687137385332', 'Checking', '93662', 'Anthony Wood', '05/06/2022', '250.00', '627c158efc13ae69cf00014k');
insert into accounts (account_ID, account_number, account_type, account_pin, account_name, last_login, current_balance, account_owner_ID) values ('654-67-4473', '119688789675', 'Savings', '68626', 'Tim White', '05/09/2022', '550.00', '627c158efc13ae69cf00015l');


--create table for bank to find customers based on 
create table transaction_history (
	transaction_ID varchar(4) primary key,
	transaction_reference varchar(50) not null,
	transaction_date date,
	account_number varchar(12) not null unique,
	account_ID varchar(11) not null unique,
	constraint fk_transaction_history foreign key(account_ID) references accounts(account_ID)
	);


insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('409p', 'Visa debit card', '05/09/2022', '451516045113', '283-54-3118');
insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('401f', 'Discovery debit card', '05/09/2022', '937304787632', '443-76-7884');
insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('407g', 'Mastercard debit card', '05/09/2022', '421651922953', '728-56-9823');
insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('404l', 'American Express card', '05/10/2022', '473983438343', '263-24-6592');
insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('469d', 'Master credit card', '05/10/2022', '847083053878', '255-25-9084');
insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('498k', 'Visa debit card', '05/10/2022', '030050815842', '444-22-3031');
insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('462c', 'American Express credit card', '05/10/2022', '017444168891', '343-23-9176');
insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('483e', 'Visa debit card', '05/09/2022', '863463416785', '244-77-1588');
insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('437w', 'Discovery credit card', '05/10/2022', '687137385332', '624-74-3476');
insert into transaction_history (transaction_ID, transaction_reference, transaction_date, account_number, account_ID) values ('416a', 'Visa debit card', '05/09/2022', '119688789675', '654-67-4473');



--select * from accounts;

--alter table <>
--add constraints <fk_>
--foreign key() references ()