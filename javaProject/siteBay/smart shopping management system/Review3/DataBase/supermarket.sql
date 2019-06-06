
CREATE TABLE ADMINTABLE
(
  ADMINNAME      VARCHAR2(255 BYTE),
  ADMINPASSWORD  VARCHAR2(255 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE CUSTOMERDETAILS
(
  FIRSTNAME       VARCHAR2(255 BYTE),
  LASTNAME        VARCHAR2(255 BYTE),
  DOB             VARCHAR2(255 BYTE),
  HNO             VARCHAR2(255 BYTE),
  STREET          VARCHAR2(255 BYTE),
  CITY            VARCHAR2(255 BYTE),
  STATE           VARCHAR2(255 BYTE),
  COUNTRY         VARCHAR2(255 BYTE),
  PINCODE         VARCHAR2(255 BYTE),
  CONTACTNO       VARCHAR2(255 BYTE),
  EMAIL           VARCHAR2(255 BYTE),
  LOGINNAME       VARCHAR2(255 BYTE),
  PASSWORD        VARCHAR2(255 BYTE),
  SECRETQUESTION  VARCHAR2(255 BYTE),
  SECRETANSWER    VARCHAR2(255 BYTE),
  CREDITCARDNO    VARCHAR2(255 BYTE),
  STATUS          VARCHAR2(255 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE EMPLOYEEDETAILS
(
  EMPID           NUMBER(10),
  EMPNAME         VARCHAR2(255 BYTE),
  DESIGNATION     VARCHAR2(255 BYTE),
  SALARY          VARCHAR2(255 BYTE),
  DOJ             VARCHAR2(255 BYTE),
  ACCOUTNO        VARCHAR2(255 BYTE),
  CONTACTADDRESS  VARCHAR2(255 BYTE),
  PHONENO         VARCHAR2(255 BYTE),
  STATUS          VARCHAR2(255 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE PRODUCTLIST
(
  PRODUCTID       NUMBER(10),
  PRODUCTNAME     VARCHAR2(255 BYTE),
  PRICE           VARCHAR2(255 BYTE),
  CATEGORY        VARCHAR2(255 BYTE),
  STATUS          VARCHAR2(255 BYTE),
  AVAILABLESTOCK  NUMBER(10)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE PRODUCTSBOUGHT
(
  CUSTNAME    VARCHAR2(255 BYTE),
  GRANDTOTAL  VARCHAR2(255 BYTE),
  BOUGHTDATE  VARCHAR2(255 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE SHOPPINGCART
(
  CUSTNAME      VARCHAR2(255 BYTE),
  PRODNAME      VARCHAR2(255 BYTE),
  QUANTITY      VARCHAR2(255 BYTE),
  PURCHASEDATE  VARCHAR2(255 BYTE),
  PAID_STATUS   VARCHAR2(255 BYTE),
  CART_ID       VARCHAR2(255 BYTE)
)
TABLESPACE USERS
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;



Insert into ADMINTABLE
   (ADMINNAME, ADMINPASSWORD)
 Values
   ('admin', 'admin');
COMMIT;

Insert into CUSTOMERDETAILS
   (FIRSTNAME, LASTNAME, DOB, HNO, STREET, 
    CITY, STATE, COUNTRY, PINCODE, CONTACTNO, 
    EMAIL, LOGINNAME, PASSWORD, SECRETQUESTION, SECRETANSWER, 
    CREDITCARDNO, STATUS)
 Values
   ('rajanikar', 'rajanikar', '15-JAN-1980 ', '2-56', 'srnagar', 
    'Hyderabad', 'Andhra pradesh', 'India', '500036', '9989674523', 
    'rajanikar@gmail.com', 'rajanikar', 'rajanikar', '1', 'childhood', 
    '123456789', 'Y');
Insert into CUSTOMERDETAILS
   (FIRSTNAME, LASTNAME, DOB, HNO, STREET, 
    CITY, STATE, COUNTRY, PINCODE, CONTACTNO, 
    EMAIL, LOGINNAME, PASSWORD, SECRETQUESTION, SECRETANSWER, 
    CREDITCARDNO, STATUS)
 Values
   ('udaykumar', 'balla', '12-SEP-1984 ', '2-17', 'checkpost', 
    'Hyderabad', 'Andhra pradesh', 'India', '123456', '9247203699', 
    'uday@uday.com', 'uday', 'uday', '2', 'chiru', 
    '1234567891234567', 'Y');
Insert into CUSTOMERDETAILS
   (FIRSTNAME, LASTNAME, DOB, HNO, STREET, 
    CITY, STATE, COUNTRY, PINCODE, CONTACTNO, 
    EMAIL, LOGINNAME, PASSWORD, SECRETQUESTION, SECRETANSWER, 
    CREDITCARDNO, STATUS)
 Values
   ('Bhargav', 'surimenu', '2-JAN-1984 ', '2-18', 'madhapur', 
    'Hyderabad', 'Andhra pradesh', 'India', '654321', '9988123456', 
    'bhargav@bhargav.com', 'bhargav', 'bhargav', '5', 'india', 
    '1234567891234567', 'Y');
Insert into CUSTOMERDETAILS
   (FIRSTNAME, LASTNAME, DOB, HNO, STREET, 
    CITY, STATE, COUNTRY, PINCODE, CONTACTNO, 
    EMAIL, LOGINNAME, PASSWORD, SECRETQUESTION, SECRETANSWER, 
    CREDITCARDNO, STATUS)
 Values
   ('sivakumar', 'boyana', '1-JAN-1947 ', '2-19', 'banjarahills', 
    'Hyderabad', 'Andhra pradesh', 'India', '123456', '1122334455', 
    'siva@siva.com', 'siva', 'siva', '8', 'passion', 
    '0007000800090000', 'Y');
Insert into CUSTOMERDETAILS
   (FIRSTNAME, LASTNAME, DOB, HNO, STREET, 
    CITY, STATE, COUNTRY, PINCODE, CONTACTNO, 
    EMAIL, LOGINNAME, PASSWORD, SECRETQUESTION, SECRETANSWER, 
    CREDITCARDNO, STATUS)
 Values
   ('sunil', 'sunil', '19-NOV-2009 ', '43-253-10', 'Ameerpet', 
    'Hyderabad', 'Andhra pradesh', 'India', '500038', '9885148211', 
    'sunil@gmail.com', 'sunil', 'sunil', '1', 'chat', 
    '9885148211', 'Y');
Insert into CUSTOMERDETAILS
   (FIRSTNAME, LASTNAME, DOB, HNO, STREET, 
    CITY, STATE, COUNTRY, PINCODE, CONTACTNO, 
    EMAIL, LOGINNAME, PASSWORD, SECRETQUESTION, SECRETANSWER, 
    CREDITCARDNO, STATUS)
 Values
   ('anilkumar', 'anilkumar', '15-JAN-1985 ', '35f', 'rrstreat', 
    'Hyderabad', 'Andhra pradesh', 'India', '500038', '9885654234', 
    'anilkumar@g.com', 'anilkumar', 'anilkumar', '5', 'india', 
    '234567892', 'Y');
COMMIT;

Insert into EMPLOYEEDETAILS
   (EMPID, EMPNAME, DESIGNATION, SALARY, DOJ, 
    ACCOUTNO, CONTACTADDRESS, PHONENO, STATUS)
 Values
   (1, 'anilkumar', 'saesboy', '6000', '12-8-2009 ', 
    '3456', 'ernagar,hyd', '9885345432', 'Y');
Insert into EMPLOYEEDETAILS
   (EMPID, EMPNAME, DESIGNATION, SALARY, DOJ, 
    ACCOUTNO, CONTACTADDRESS, PHONENO, STATUS)
 Values
   (2, 'kumar', 'cashier', '7000', '16-12-2009 ', 
    '123456789', 'hyd', '4567832122', 'Y');
COMMIT;

Insert into PRODUCTLIST
   (PRODUCTID, PRODUCTNAME, PRICE, CATEGORY, STATUS, 
    AVAILABLESTOCK)
 Values
   (9, 'Fair', '25', 'beauty', 'Y', 
    13);
Insert into PRODUCTLIST
   (PRODUCTID, PRODUCTNAME, PRICE, CATEGORY, STATUS, 
    AVAILABLESTOCK)
 Values
   (10, 'LeeCooper Jeans', '2000', 'garments', 'Y', 
    15);
Insert into PRODUCTLIST
   (PRODUCTID, PRODUCTNAME, PRICE, CATEGORY, STATUS, 
    AVAILABLESTOCK)
 Values
   (11, 'Necklace', '100000', 'jewelary', 'Y', 
    25);
COMMIT;

Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('rajanikar', '500075', '11-DEC-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('sunil', '410000', '11-DEC-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('uday', '0', '11-DEC-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('sunil', '100000', '21-OCT-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('bhargav', '21', '2-FEB-2008');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('boyana', '9055', '2-FEB-2008');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('uday', '155', '2-FEB-2008');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('sunil', '102025', '5-OCT-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('uday', '50', '29-AUG-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('uday', '4025', '29-AUG-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('sunil', '200000', '22-OCT-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('sunil', '102050', '21-OCT-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('sunil', '4000', '21-OCT-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('sunil', '4000', '21-OCT-2009');
Insert into PRODUCTSBOUGHT
   (CUSTNAME, GRANDTOTAL, BOUGHTDATE)
 Values
   ('sunil', '4000', '21-OCT-2009');
COMMIT;

Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'LeeCooper Jeans', '2', '11-DEC-2009', 'No', 
    'cart_22');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('rajanikar', 'Necklace', '5', '11-DEC-2009', 'Yes', 
    'cart_23');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('rajanikar', 'Fair', '3', '11-DEC-2009', 'Yes', 
    'cart_24');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'LeeCooper Jeans', '5', '11-DEC-2009', 'Yes', 
    'cart_20');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'Necklace', '4', '11-DEC-2009', 'Yes', 
    'cart_21');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'LeeCooper Jeans', '2', '21-OCT-2009', 'Yes', 
    'cart_17');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('uday', 'Fair', '1', '10-FEB-2008', 'Yes', 
    'cart_17');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('uday', 'Fair', '1', '10-FEB-2008', 'Yes', 
    'cart_18');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('uday', 'Nokia-6030', '2', '10-FEB-2008', 'Yes', 
    'cart_19');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('uday', 'Fair', '1000', '10-FEB-2008', 'Yes', 
    'cart_20');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('uday', 'Pen', '1000', '10-FEB-2008', 'Yes', 
    'cart_21');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   (NULL, 'Fair', '111', '10-FEB-2008', 'No', 
    'cart_22');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('uday', 'LeeCooper Jeans', '2', '29-AUG-2009', 'Yes', 
    'cart_8');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'Fair', '1', '5-OCT-2009', 'Yes', 
    'cart_10');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'Necklace', '1', '5-OCT-2009', 'Yes', 
    'cart_11');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'LeeCooper Jeans', '1', '5-OCT-2009', 'Yes', 
    'cart_12');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'LeeCooper Jeans', '1', '5-OCT-2009', 'No', 
    'cart_13');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('uday', 'Fair', '2', '29-AUG-2009', 'Yes', 
    'cart_7');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('uday', 'Fair', '1', '29-AUG-2009', 'Yes', 
    'cart_9');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'Necklace', '2', '22-OCT-2009', 'Yes', 
    'cart_19');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'Fair', '2', '21-OCT-2009', 'Yes', 
    'cart_14');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'LeeCooper Jeans', '1', '21-OCT-2009', 'Yes', 
    'cart_15');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'Necklace', '1', '21-OCT-2009', 'Yes', 
    'cart_16');
Insert into SHOPPINGCART
   (CUSTNAME, PRODNAME, QUANTITY, PURCHASEDATE, PAID_STATUS, 
    CART_ID)
 Values
   ('sunil', 'Necklace', '1', '21-OCT-2009', 'Yes', 
    'cart_18');
COMMIT;

