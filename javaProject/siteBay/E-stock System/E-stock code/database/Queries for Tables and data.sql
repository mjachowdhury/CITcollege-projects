
CREATE TABLE ADMIN
(
  NAME      VARCHAR2(20 BYTE),
  PASSWORD  VARCHAR2(20 BYTE)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE BUYER
(
  CID             NUMBER(10),
  COMPANY_NAME    VARCHAR2(25 BYTE),
  NO_OF_SHARES    NUMBER(5),
  RATE_PER_SHARE  NUMBER(5,2),
  VALIDITY        VARCHAR2(25 BYTE),
  DATE_B          VARCHAR2(25 BYTE),
  ORDER_TYPE      VARCHAR2(25 BYTE),
  STATUS          VARCHAR2(25 BYTE),
  COMMISSION      NUMBER(8,2)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE CBALANCE
(
  CID               NUMBER(5),
  BALANCE           NUMBER(10,2),
  MODE_OF_PAYM      VARCHAR2(25 BYTE),
  CARD_TYPE         VARCHAR2(25 BYTE),
  CARD_NO           VARCHAR2(25 BYTE),
  CARD_HOLDER_NAME  VARCHAR2(25 BYTE),
  EXPIRY_DATE       VARCHAR2(25 BYTE),
  CHEQUE_NO         VARCHAR2(25 BYTE),
  CHEQUE_DATE       VARCHAR2(25 BYTE),
  BANK_DRAWN        VARCHAR2(25 BYTE),
  LOCATION          VARCHAR2(25 BYTE)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE CUSTOMER_MASTER
(
  CID              NUMBER(25),
  NAME             VARCHAR2(25 BYTE),
  ADDRESS          VARCHAR2(25 BYTE),
  PHONE_R          VARCHAR2(25 BYTE),
  PHONE_O          VARCHAR2(25 BYTE),
  PHONE_M          VARCHAR2(25 BYTE),
  FAX              VARCHAR2(25 BYTE),
  CITY             VARCHAR2(25 BYTE),
  STATE            VARCHAR2(25 BYTE),
  DOB              VARCHAR2(25 BYTE),
  PINCODE          VARCHAR2(25 BYTE),
  EMAILID          VARCHAR2(25 BYTE),
  OCCUPATION       VARCHAR2(25 BYTE),
  INCOME           VARCHAR2(25 BYTE),
  DATE_OF_OPENING  VARCHAR2(25 BYTE),
  LOGNAME          VARCHAR2(25 BYTE),
  PASSWORD         VARCHAR2(25 BYTE),
  LNAME            VARCHAR2(25 BYTE),
  TITLE            VARCHAR2(25 BYTE)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE FEEDBACK
(
  SNO       NUMBER(5),
  NAME      VARCHAR2(25 BYTE),
  EMAIL     VARCHAR2(25 BYTE),
  COMMENTS  VARCHAR2(25 BYTE)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE PORTFOLIO
(
  CID               NUMBER(5),
  SYMBOL            VARCHAR2(20 BYTE),
  NO_SHARES_TRADED  NUMBER(10),
  BUY_RATE          NUMBER(25,3),
  SALE_RATE         NUMBER(25,3),
  PURCHASE_VALUE    NUMBER(25,3),
  SALE_VALUE        NUMBER(25,3),
  PROFIT            NUMBER(25,3)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE QUOTE
(
  SYMBOL         VARCHAR2(25 BYTE),
  CURRENT_PRICE  NUMBER(5,2),
  CHANGE         NUMBER(5),
  VOLUME         NUMBER(8),
  HIGH           NUMBER(5),
  LOW            NUMBER(5),
  DAY_OPEN       NUMBER(5),
  PREV_PRICE     NUMBER(5)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE SELLER
(
  CID             NUMBER(10),
  COMPANY_NAME    VARCHAR2(25 BYTE),
  NO_OF_SHARES    NUMBER,
  RATE_PER_SHARE  NUMBER,
  VALIDITY        VARCHAR2(30 BYTE),
  DATE_S          VARCHAR2(20 BYTE),
  ORDER_TYPE      VARCHAR2(30 BYTE),
  STATUS          VARCHAR2(25 BYTE),
  COMMISSION      NUMBER(8,2)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE SYMBOL
(
  COMPANYNAME  VARCHAR2(25 BYTE),
  SYMBOL       VARCHAR2(5 BYTE)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;



Insert into ADMIN
   (NAME, PASSWORD)
 Values
   ('admin', 'admin');
COMMIT;

Insert into BUYER
   (CID, COMPANY_NAME, NO_OF_SHARES, RATE_PER_SHARE, VALIDITY, 
    DATE_B, ORDER_TYPE, STATUS, COMMISSION)
 Values
   (8, 'W', 10000, 700, 'Day Order', 
    '3/12/09', 'Market', NULL, 140000);
Insert into BUYER
   (CID, COMPANY_NAME, NO_OF_SHARES, RATE_PER_SHARE, VALIDITY, 
    DATE_B, ORDER_TYPE, STATUS, COMMISSION)
 Values
   (8, 'tcs', 1000, 110, 'Day Order', 
    '3/12/09', 'Market', NULL, 2200);
COMMIT;

Insert into CBALANCE
   (CID, BALANCE, MODE_OF_PAYM, CARD_TYPE, CARD_NO, 
    CARD_HOLDER_NAME, EXPIRY_DATE, CHEQUE_NO, CHEQUE_DATE, BANK_DRAWN, 
    LOCATION)
 Values
   (1, NULL, 'card', 'VISA', 'visa', 
    'Vara', NULL, NULL, NULL, NULL, 
    NULL);
Insert into CBALANCE
   (CID, BALANCE, MODE_OF_PAYM, CARD_TYPE, CARD_NO, 
    CARD_HOLDER_NAME, EXPIRY_DATE, CHEQUE_NO, CHEQUE_DATE, BANK_DRAWN, 
    LOCATION)
 Values
   (2, NULL, 'card', 'VISA', '22', 
    '21', NULL, NULL, NULL, NULL, 
    NULL);
Insert into CBALANCE
   (CID, BALANCE, MODE_OF_PAYM, CARD_TYPE, CARD_NO, 
    CARD_HOLDER_NAME, EXPIRY_DATE, CHEQUE_NO, CHEQUE_DATE, BANK_DRAWN, 
    LOCATION)
 Values
   (3, NULL, 'cheque', NULL, NULL, 
    NULL, NULL, '1', '11/04/2008', 'Hyderabad', 
    NULL);
Insert into CBALANCE
   (CID, BALANCE, MODE_OF_PAYM, CARD_TYPE, CARD_NO, 
    CARD_HOLDER_NAME, EXPIRY_DATE, CHEQUE_NO, CHEQUE_DATE, BANK_DRAWN, 
    LOCATION)
 Values
   (4, NULL, 'draft', NULL, NULL, 
    NULL, NULL, '12345', '11/12/2008', 'Hyderabad', 
    NULL);
Insert into CBALANCE
   (CID, BALANCE, MODE_OF_PAYM, CARD_TYPE, CARD_NO, 
    CARD_HOLDER_NAME, EXPIRY_DATE, CHEQUE_NO, CHEQUE_DATE, BANK_DRAWN, 
    LOCATION)
 Values
   (5, NULL, 'draft', NULL, NULL, 
    NULL, NULL, '12232', '11/04/2008', 'Hyderabad', 
    NULL);
Insert into CBALANCE
   (CID, BALANCE, MODE_OF_PAYM, CARD_TYPE, CARD_NO, 
    CARD_HOLDER_NAME, EXPIRY_DATE, CHEQUE_NO, CHEQUE_DATE, BANK_DRAWN, 
    LOCATION)
 Values
   (7, NULL, NULL, 'VISA', '12345', 
    'Vara', NULL, NULL, NULL, NULL, 
    NULL);
Insert into CBALANCE
   (CID, BALANCE, MODE_OF_PAYM, CARD_TYPE, CARD_NO, 
    CARD_HOLDER_NAME, EXPIRY_DATE, CHEQUE_NO, CHEQUE_DATE, BANK_DRAWN, 
    LOCATION)
 Values
   (8, NULL, NULL, 'VISA', '1234567890123456', 
    'Sathya', NULL, NULL, NULL, NULL, 
    NULL);
Insert into CBALANCE
   (CID, BALANCE, MODE_OF_PAYM, CARD_TYPE, CARD_NO, 
    CARD_HOLDER_NAME, EXPIRY_DATE, CHEQUE_NO, CHEQUE_DATE, BANK_DRAWN, 
    LOCATION)
 Values
   (9, NULL, NULL, 'VISA', '36567676788899', 
    'kiran', NULL, NULL, NULL, NULL, 
    NULL);
COMMIT;

Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (1, 'KanakaDharVara', '221221', '08518', '221221', 
    '9885148211', '111', 'Hyderabad', 'Hyderabad', '11/19/1985', 
    '518001', 's.varaprasad@yahoo.co.in', 'Self Employed', '2', '11/3/08', 
    'vara', 'sairam', 'Prasad', 'MR.');
Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (2, 'ab', '5', '4', '8', 
    '7', '10', 'Hyderabad', 'Hyderabad', '11/19/1985', 
    '19', 'a16@sai.com', 'Student', '6', '11/3/08', 
    '21', '22', 'c', 'MS.');
Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (3, 'aa', '1', '1', '1', 
    '1', '1', 'NEWDELHI', 'Kolkata', '9/10/1985', 
    '518001', 'vara@sai.com', 'Self Employed', '2', '11/4/08', 
    '1', '2', 'a', 'MS.');
Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (3, 'aa', '1', '1', '1', 
    '1', '1', 'NEWDELHI', 'Kolkata', '9/10/1985', 
    '518001', 'vara@sai.com', 'Self Employed', '2', '11/4/08', 
    '1', '2', 'a', 'MS.');
Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (4, 'sekhar', 'Kadiri', '123', '123456', 
    '999999999', '1234567', 'Hyderabad', 'Hyderabad', '5/15/1987', 
    '500037', 'sekhar@gmail.com', 'Student', '6', '11/12/08', 
    'sekhar', 'sekhar', 'reddy', 'MR.');
Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (5, 'abcddff', 'fgdfg', '123458', '32113', 
    '13131131', '4654646', 'AHMEDABAD', 'Hyderabad', '1/22/1980', 
    '123456', 'asddfd@uu.hh', 'Govt. Employee', '2', '12/5/08', 
    'abcd', 'abcd', 'dff', 'MR.');
Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (6, 'kiran', 'sng', NULL, NULL, 
    '9701683900', NULL, 'Hyderabad', 'Hyderabad', '11/8/1985', 
    '506366', 'kiran_naini@yahoo.com', 'Student', '1', '2/4/09', 
    'kiran', 'kiran', 'kumar', 'MS.');
Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (7, 'aa', 'a', '1', '1', 
    '1', '1', 'Hyderabad', 'Andhra Pradesh', '2/3/1985', 
    '1', 'sai@gmail.com', 'CEO/MD/Director', '6', '3/4/09', 
    'Vara', 'sai', 'a', 'MS.');
Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (8, 'Sathyanarayana', '43-253', '221221', '221222', 
    '9885148211', '221222', 'Hyderabad', 'Andhra Pradesh', '12/29/1985', 
    '51800038', 'sathya@gmail.com', 'CEO/MD/Director', '6', '3/4/09', 
    'Sathya', 'Sathya', 'reddy', 'MR.');
Insert into CUSTOMER_MASTER
   (CID, NAME, ADDRESS, PHONE_R, PHONE_O, 
    PHONE_M, FAX, CITY, STATE, DOB, 
    PINCODE, EMAILID, OCCUPATION, INCOME, DATE_OF_OPENING, 
    LOGNAME, PASSWORD, LNAME, TITLE)
 Values
   (9, 'kiran', '3-64warangal', NULL, NULL, 
    '9999999999', NULL, 'Hyderabad', 'Karnataka', '11/8/1986', 
    '506366', 'kiran_naini@yahoo.co.in', 'Student', '2', '3/7/09', 
    'kiran', 'kiran', 'kumar', 'MS.');
COMMIT;

Insert into FEEDBACK
   (SNO, NAME, EMAIL, COMMENTS)
 Values
   (NULL, 'vara prasad', 's.varaprasad@yahoo.co.in', 'no comments');
Insert into FEEDBACK
   (SNO, NAME, EMAIL, COMMENTS)
 Values
   (NULL, 'sai', 'sai@gmail.com', ' good services. thank u.');
COMMIT;

Insert into PORTFOLIO
   (CID, SYMBOL, NO_SHARES_TRADED, BUY_RATE, SALE_RATE, 
    PURCHASE_VALUE, SALE_VALUE, PROFIT)
 Values
   (8, 'W', 1000, 700, 800, 
    700000, 800000, 100000);
Insert into PORTFOLIO
   (CID, SYMBOL, NO_SHARES_TRADED, BUY_RATE, SALE_RATE, 
    PURCHASE_VALUE, SALE_VALUE, PROFIT)
 Values
   (8, 'tcs', 100, 110, 100, 
    11000, 10000, -1000);
COMMIT;

Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('G', 100, NULL, 100000, NULL, 
    NULL, 100, NULL);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('O', 600, NULL, 50000, NULL, 
    NULL, 600, NULL);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('tcs', 100, -50, 100000, 150, 
    100, 100, 150);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('W', 800, 100, 100000, 800, 
    700, 600, 700);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('al', 10, NULL, 10000, NULL, 
    NULL, 10, NULL);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('pc', 20, 5, 50000, 20, 
    15, 15, 15);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('satt', 200, 100, 100, 200, 
    100, 100, 100);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('Zind', 60, 10, 10000, 60, 
    50, 50, 50);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('QT', 120, 20, 100000, 120, 
    100, 100, 100);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('J', 120, 20, 100000, 120, 
    100, 100, 100);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('S', 90, -110, 10000, 200, 
    90, 100, 200);
Insert into QUOTE
   (SYMBOL, CURRENT_PRICE, CHANGE, VOLUME, HIGH, 
    LOW, DAY_OPEN, PREV_PRICE)
 Values
   ('cts', 120, 20, 10000, 120, 
    100, 100, 100);
COMMIT;

Insert into SELLER
   (CID, COMPANY_NAME, NO_OF_SHARES, RATE_PER_SHARE, VALIDITY, 
    DATE_S, ORDER_TYPE, STATUS, COMMISSION)
 Values
   (8, 'W', 1000, 800, 'Day Order', 
    '3/12/09', 'Market', NULL, 16000);
Insert into SELLER
   (CID, COMPANY_NAME, NO_OF_SHARES, RATE_PER_SHARE, VALIDITY, 
    DATE_S, ORDER_TYPE, STATUS, COMMISSION)
 Values
   (8, 'tcs', 100, 100, 'Day Order', 
    '3/12/09', 'Market', NULL, 200);
COMMIT;

Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('Priya Cements', 'pc');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('Tata Consultancy', 'tcs');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('Ashok Leyland', 'al');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('Wipro', 'W');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('Google', 'G');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('Oracle', 'O');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('satya tech', 'satt');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('zindal', 'Zind');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('Quant Technologies', 'QT');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('Sathya', 'S');
Insert into SYMBOL
   (COMPANYNAME, SYMBOL)
 Values
   ('Cognizant', 'cts');
COMMIT;

