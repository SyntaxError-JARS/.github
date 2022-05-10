
--drop table customer;
--drop table custaccounts;
--drop table transactionhis;
--drop table accountinfo;

--create a table
create table customer (
	id serial primary key, -- id is set to primary so the different tables can have the same key
	first varchar(50) not null, -- first name of customer
	last varchar(50) not null, -- last name of customer
	age int, 
	pin int not null, --pin code that customer made
	email varchar(50) not null unique, -- email that customer made
	password varchar(50) not null -- password that customer made
);

-- store data for customer profile: name, and pin; created random names and pin codes
insert into customer (id, first, last, age, pin, email, password) values (1, 'Mike', 'Osbourne', '51', '1991', 'mo@gmail.com', 'mopassword');
insert into customer (id, first, last, age, pin, email, password) values (2, 'Tina', 'Sinclaire', '33',  '4856', 'ts@gmail.com', 'tspassword');
insert into customer (id, first, last, age, pin, email, password) values (3, 'Donovan', 'McDonald', '45', '7965', 'dm@gmail.com', 'dmpassword');
insert into customer (id, first, last, age, pin, email, password) values (4, 'Kimberly', 'Jackson', '45', '2045', 'kj@gmail.com', 'kjpassword');
insert into customer (id, first, last, age, pin, email, password) values (5, 'Megan', 'Floyd', '9473', '45', 'mf@gmail.com', 'mfpassword');
insert into customer (id, first, last, age, pin, email, password) values (6, 'Keith', 'Brown', '2468', '40', 'kb@gmail.com', 'kbpassword');
insert into customer (id, first, last, age, pin, email, password) values (7, 'Robert', 'Camp', '8964', '30', 'rc@gmail.com', 'rcpassword');
insert into customer (id, first, last, age, pin, email, password) values (8, 'Clyde', 'Downs', '3457', '50', 'cd@gmail.com', 'cdpassword');
insert into customer (id, first, last, age, pin, email, password) values (9, 'Anthony', 'Wood', '5578', '24',  'aw@gmail.com', 'awpassword');
insert into customer (id, first, last, age, pin, email, password) values (10, 'Tim', 'White', '3234', '62', 'tw@gmail.com', 'twpassword');


create table custaccounts (
	email varchar(50) primary key,
	accountnum varchar(12) unique,
	accounttype varchar(20) not null,
	balance decimal(8,2),
	constraint fk_custaccounts foreign key(email) references customer(email)
); 



insert into custaccounts (email, accounttype, accountnum, balance) values ('mo@gmail.com', 'Checking', '451516045113', '313.00');
insert into custaccounts (email, accounttype, accountnum, balance) values ('ts@gmail.com', 'Savings', '937304787632', '2500.00');
insert into custaccounts (email, accounttype, accountnum, balance) values ('dm@gmail.com', 'Checking', '421651922953', '55750.00');
insert into custaccounts (email, accounttype, accountnum, balance) values ('kj@gmail.com', 'Checking', '473983438343', '9100.00');
insert into custaccounts (email, accounttype, accountnum, balance) values ('mf@gmail.com', 'Savings', '847083053878', '10250.00');
insert into custaccounts (email, accounttype, accountnum, balance) values ('kb@gmail.com', 'Checking', '030050815842', '810.00');
insert into custaccounts (email, accounttype, accountnum, balance) values ('rc@gmail.com', 'Savings', '017444168891', '1050.00');
insert into custaccounts (email, accounttype, accountnum, balance) values ('cd@gmail.com', 'Savings', '863463416785', '900.00');
insert into custaccounts (email, accounttype, accountnum, balance) values ('aw@gmail.com', 'Checking', '687137385332', '250.00');
insert into custaccounts (email, accounttype, accountnum, balance) values ('tw@gmail.com', 'Savings', '119688789675', '550.00');


--create table for bank to find customers based on 
create table accountinfo (
	accountnum varchar(50) primary key,
	address VARCHAR(50),
	mobile varchar(50) not null,
	social varchar(50) not null,
	constraint fk_accountinfo foreign key(accountnum) references custaccounts(email)
	);


insert into accountinfo (accountnum, address, mobile, social) values (''119688789675', '', '', '');
insert into accountinfo (accountnum, address, mobile, social) values (''451516045113', '', '', '');
insert into accountinfo (accountnum, address, mobile, social) values (''937304787632', '', '', '');
insert into accountinfo (accountnum, address, mobile, social) values (''473983438343', '', '', '');
insert into accountinfo (accountnum, address, mobile, social) values (''421651922953', '', '', '');
insert into accountinfo (accountnum, address, mobile, social) values (''847083053878', '', '', '');
insert into accountinfo (accountnum, address, mobile, social) values (''030050815842', '', '', '');
insert into accountinfo (accountnum, address, mobile, social) values (''017444168891', '', '', '');
insert into accountinfo (accountnum, address, mobile, social values (''687137385332', '', '', '');
insert into accountinfo (accountnum, address, mobile, social) values (''863463416785', '', '', '');

---
create table transactionhis (
	transactionid varchar(10) primary key,
	recentactivity varchar(80) not null,
	monthlystatements VARCHAR(12) not null,
	constraint fk_transactionhis foreign key(transactionid) references accountinfo(accountnum)
);
