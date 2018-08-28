mysql> create database if not exists Bank_account;
Query OK, 1 row affected (0.20 sec)

mysql> use Bank_account;
Database changed

mysql> create table if not exists customer(cust_id int unsigned not null auto_increment, fname varchar(10), lname varchar(10), primary key(cust_id))engine=innodb;
Query OK, 0 rows affected (2.15 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| AdultLiteracy      |
| BankAccount        |
| Bank_account       |
| ClassSchedule      |
| HotelBooking       |
| StayHome           |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
10 rows in set (0.00 sec)

mysql> desc customer;
+---------+------------------+------+-----+---------+----------------+
| Field   | Type             | Null | Key | Default | Extra          |
+---------+------------------+------+-----+---------+----------------+
| cust_id | int(10) unsigned | NO   | PRI | NULL    | auto_increment |
| fname   | varchar(10)      | YES  |     | NULL    |                |
| lname   | varchar(10)      | YES  |     | NULL    |                |
+---------+------------------+------+-----+---------+----------------+
3 rows in set (0.06 sec)

mysql> create table if not exists account(account_id int unsigned not null, product_cd varchar (15), cust_id int unsigned not null, balance float(5,2))engine=innodb;
Query OK, 0 rows affected (0.46 sec)

mysql> alter table account add constraint pk_account primary key(account_id);
Query OK, 0 rows affected (1.08 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> alter table account add constraint fk_account foreign key (cust_id) references customer(cust_id) on update cascade on delete cascade;
Query OK, 0 rows affected (2.12 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> desc account;
+------------+------------------+------+-----+---------+-------+
| Field      | Type             | Null | Key | Default | Extra |
+------------+------------------+------+-----+---------+-------+
| account_id | int(10) unsigned | NO   | PRI | NULL    |       |
| product_cd | varchar(15)      | YES  |     | NULL    |       |
| cust_id    | int(10) unsigned | NO   | MUL | NULL    |       |
| balance    | float(5,2)       | YES  |     | NULL    |       |
+------------+------------------+------+-----+---------+-------+
4 rows in set (0.00 sec)

mysql> create table if not exists product(product_cd varchar(20) not null, name varchar(20)not null, primary key (product_cd))engine =innodb;
Query OK, 0 rows affected (0.52 sec)

mysql> desc product;
+------------+-------------+------+-----+---------+-------+
| Field      | Type        | Null | Key | Default | Extra |
+------------+-------------+------+-----+---------+-------+
| product_cd | varchar(20) | NO   | PRI | NULL    |       |
| name       | varchar(20) | NO   |     | NULL    |       |
+------------+-------------+------+-----+---------+-------+
2 rows in set (0.00 sec)

mysql> create table if not exists transaction(txn_id int unsigned not null, txn_type_cd varchar(15), account_id int unsigned not null, amount float (5,2), date date, primary key(txn_id), foreign key (account_id) references account(account_id) on update cascade on delete cascade)engine=innodb;
Query OK, 0 rows affected (0.48 sec)


mysql> desc transaction;
+-------------+------------------+------+-----+---------+-------+
| Field       | Type             | Null | Key | Default | Extra |
+-------------+------------------+------+-----+---------+-------+
| txn_id      | int(10) unsigned | NO   | PRI | NULL    |       |
| txn_type_cd | varchar(15)      | YES  |     | NULL    |       |
| account_id  | int(10) unsigned | NO   | MUL | NULL    |       |
| amount      | float(5,2)       | YES  |     | NULL    |       |
| date        | date             | YES  |     | NULL    |       |
+-------------+------------------+------+-----+---------+-------+
5 rows in set (0.00 sec)

mysql> create table if not exists mail(sentDateTime datetime, srcuser varchar(8), srchost varchar(20), dstuser varchar (8), dsthost varchar (20), primary key (srcuser))engine=innodb;
Query OK, 0 rows affected (0.47 sec)


mysql> desc mail;
+--------------+-------------+------+-----+---------+-------+
| Field        | Type        | Null | Key | Default | Extra |
+--------------+-------------+------+-----+---------+-------+
| sentDateTime | datetime    | YES  |     | NULL    |       |
| srcuser      | varchar(8)  | NO   | PRI | NULL    |       |
| srchost      | varchar(20) | YES  |     | NULL    |       |
| dstuser      | varchar(8)  | YES  |     | NULL    |       |
| dsthost      | varchar(20) | YES  |     | NULL    |       |
+--------------+-------------+------+-----+---------+-------+
5 rows in set (0.01 sec)






