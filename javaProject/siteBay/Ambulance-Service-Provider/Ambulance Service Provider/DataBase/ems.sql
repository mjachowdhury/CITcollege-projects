
CREATE TABLE AMBULANCE_DET
(
  AID            NUMBER(10),
  AMBULANCENO    VARCHAR2(255 BYTE),
  AMBULANCETYPE  VARCHAR2(255 BYTE),
  LOCATION       VARCHAR2(255 BYTE),
  EQUIPEMENT     VARCHAR2(255 BYTE),
  STATUS         VARCHAR2(255 BYTE)
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


CREATE TABLE AMBULANCE_INFO
(
  PID      NUMBER(10),
  AID      NUMBER(10),
  DID      NUMBER(10),
  HID      NUMBER(10),
  DOCID    NUMBER(10),
  ASTATUS  VARCHAR2(255 BYTE)
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


CREATE TABLE LOGIN_AUDIT
(
  LOGINID     VARCHAR2(255 BYTE),
  LOGINDATE   DATE,
  LOGIN_DESC  VARCHAR2(255 BYTE)
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


CREATE TABLE LOGIN_DETAILS
(
  LOGINNAME         VARCHAR2(255 BYTE),
  PASSWORD          VARCHAR2(255 BYTE),
  FIRST_NAME        VARCHAR2(255 BYTE),
  LAST_NAME         VARCHAR2(255 BYTE),
  LOGINTYPE         VARCHAR2(255 BYTE),
  LOGIN_STATUS      NUMBER(10),
  REG_DATE          DATE,
  SQUESTIONID       NUMBER(10),
  SANSWER           VARCHAR2(255 BYTE),
  FIRSTLOGIN        NUMBER(10),
  PASSMODIFIEDDATE  DATE
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


CREATE TABLE LOGIN_PROFILE
(
  LOGINID              VARCHAR2(255 BYTE),
  BIRTHDATE            DATE,
  CITY                 VARCHAR2(255 BYTE),
  STATE                VARCHAR2(255 BYTE),
  COUNTRY              VARCHAR2(255 BYTE),
  LOCALE               VARCHAR2(255 BYTE),
  PROFILEMODIFIEDDATE  DATE
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


CREATE TABLE PATEINT_DET
(
  PID        NUMBER(10),
  FIRSTNAME  VARCHAR2(255 BYTE),
  LASTNAME   VARCHAR2(255 BYTE),
  BIRTHDATE  DATE,
  LOCATION   VARCHAR2(255 BYTE),
  CITY       VARCHAR2(255 BYTE),
  STATE      VARCHAR2(255 BYTE),
  ADDRESS    VARCHAR2(255 BYTE),
  PHONE      VARCHAR2(255 BYTE),
  HEIGHT     VARCHAR2(255 BYTE),
  WEIGHT     VARCHAR2(255 BYTE),
  JOINDATE   DATE,
  TYPE       VARCHAR2(255 BYTE),
  STATUS     VARCHAR2(255 BYTE)
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


CREATE TABLE PATIENT_DISCHARGE_INFO
(
  PID              NUMBER(10),
  STATEOFPATIENT   VARCHAR2(255 BYTE),
  DATEOFDISCHARGE  DATE
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


CREATE TABLE PATIENT_INFO
(
  PID       NUMBER(10),
  DOCID     NUMBER(10),
  HID       NUMBER(10),
  ROOMNO    VARCHAR2(255 BYTE),
  DISCEASE  VARCHAR2(255 BYTE),
  MEDICINE  VARCHAR2(255 BYTE),
  PSTATUS   VARCHAR2(255 BYTE)
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


CREATE TABLE STAFF_DET
(
  SID           NUMBER(10),
  FIRSTNAME     VARCHAR2(255 BYTE),
  LASTNAME      VARCHAR2(255 BYTE),
  STAFFTYPE     VARCHAR2(255 BYTE),
  PHONE         VARCHAR2(255 BYTE),
  EMAIL         VARCHAR2(255 BYTE),
  LOCATION      VARCHAR2(255 BYTE),
  ADDRESS       VARCHAR2(255 BYTE),
  WORKINGHOURS  VARCHAR2(255 BYTE),
  JOININGDATE   DATE,
  STATUS        VARCHAR2(255 BYTE)
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



Insert into AMBULANCE_DET
   (AID, AMBULANCENO, AMBULANCETYPE, LOCATION, EQUIPEMENT, 
    STATUS)
 Values
   (1, '1234', 'Big Ambulance', 'ameerpet', ' ctscan,xray ', 
    'Y');
Insert into AMBULANCE_DET
   (AID, AMBULANCENO, AMBULANCETYPE, LOCATION, EQUIPEMENT, 
    STATUS)
 Values
   (2, '111', 'Big Ambulance', 'ameerpet', 'first aid kit, oxygen cylinder...', 
    'N');
Insert into AMBULANCE_DET
   (AID, AMBULANCENO, AMBULANCETYPE, LOCATION, EQUIPEMENT, 
    STATUS)
 Values
   (3, '12345', 'Big Ambulance', 'sr.nagar', 'first aid kit', 
    'N');
Insert into AMBULANCE_DET
   (AID, AMBULANCENO, AMBULANCETYPE, LOCATION, EQUIPEMENT, 
    STATUS)
 Values
   (4, '1235', 'Small Ambulance', 'kukatpally', 'first aid kit, oxygen cylinder', 
    'N');
Insert into AMBULANCE_DET
   (AID, AMBULANCENO, AMBULANCETYPE, LOCATION, EQUIPEMENT, 
    STATUS)
 Values
   (5, '1436', 'Big Ambulance', 'miyapur', ' OxyzenCylinder and hi equipment ', 
    'Y');
COMMIT;

Insert into AMBULANCE_INFO
   (PID, AID, DID, HID, DOCID, 
    ASTATUS)
 Values
   (1, 1, 4, 3, 2, 
    'R');
Insert into AMBULANCE_INFO
   (PID, AID, DID, HID, DOCID, 
    ASTATUS)
 Values
   (2, 1, 4, 3, 1, 
    'R');
Insert into AMBULANCE_INFO
   (PID, AID, DID, HID, DOCID, 
    ASTATUS)
 Values
   (4, 1, 4, 3, 1, 
    'R');
Insert into AMBULANCE_INFO
   (PID, AID, DID, HID, DOCID, 
    ASTATUS)
 Values
   (5, 1, 4, 3, 1, 
    'R');
Insert into AMBULANCE_INFO
   (PID, AID, DID, HID, DOCID, 
    ASTATUS)
 Values
   (6, 1, 4, 5, 2, 
    'R');
Insert into AMBULANCE_INFO
   (PID, AID, DID, HID, DOCID, 
    ASTATUS)
 Values
   (7, 1, 4, 3, 1, 
    'R');
Insert into AMBULANCE_INFO
   (PID, AID, DID, HID, DOCID, 
    ASTATUS)
 Values
   (8, 1, 4, 3, 1, 
    'R');
Insert into AMBULANCE_INFO
   (PID, AID, DID, HID, DOCID, 
    ASTATUS)
 Values
   (11, 5, 4, 3, 9, 
    'S');
COMMIT;

Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('bhargav', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('bhargav', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajanika', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('bhargav', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('bhargav', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sss', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('arjun', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('arjun', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sai', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sai', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('fdfdf', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('fdfdf', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('fdfdf', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('fdfdf', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/01/2005 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('08/28/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('08/28/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('08/29/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('08/29/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('ramkumar', TO_DATE('08/29/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('08/29/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('08/29/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('08/29/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('08/29/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('08/29/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('08/29/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rakikumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajukumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajukumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('09/06/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   (NULL, TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sravan', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sravan', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sravan', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sravan', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sravan', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/21/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/21/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sravan', TO_DATE('10/21/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/21/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sravan', TO_DATE('10/21/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/21/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('10/24/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/24/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('sravan', TO_DATE('10/24/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('rajkumar', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('12/01/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Entered Invalid password');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('anil', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('12/10/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('admin', TO_DATE('01/23/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
Insert into LOGIN_AUDIT
   (LOGINID, LOGINDATE, LOGIN_DESC)
 Values
   ('bhargav', TO_DATE('01/23/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Login Success');
COMMIT;

Insert into LOGIN_DETAILS
   (LOGINNAME, PASSWORD, FIRST_NAME, LAST_NAME, LOGINTYPE, 
    LOGIN_STATUS, REG_DATE, SQUESTIONID, SANSWER, FIRSTLOGIN, 
    PASSMODIFIEDDATE)
 Values
   ('bhargav', 'bhargav', 'bhargav', 'bhargav', 'user', 
    1, TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 9, 'nani', 0, 
    TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LOGIN_DETAILS
   (LOGINNAME, PASSWORD, FIRST_NAME, LAST_NAME, LOGINTYPE, 
    LOGIN_STATUS, REG_DATE, SQUESTIONID, SANSWER, FIRSTLOGIN, 
    PASSMODIFIEDDATE)
 Values
   ('admin', 'admin', 'anil', 'anil', 'admin', 
    1, TO_DATE('08/09/2007 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 'childhood', 1, 
    TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LOGIN_DETAILS
   (LOGINNAME, PASSWORD, FIRST_NAME, LAST_NAME, LOGINTYPE, 
    LOGIN_STATUS, REG_DATE, SQUESTIONID, SANSWER, FIRSTLOGIN, 
    PASSMODIFIEDDATE)
 Values
   ('rajkumar', 'rajkumar', 'rajkumar', 'rajkumar', 'user', 
    1, TO_DATE('08/28/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 2, 'sai', 0, 
    TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LOGIN_DETAILS
   (LOGINNAME, PASSWORD, FIRST_NAME, LAST_NAME, LOGINTYPE, 
    LOGIN_STATUS, REG_DATE, SQUESTIONID, SANSWER, FIRSTLOGIN, 
    PASSMODIFIEDDATE)
 Values
   ('sravan', 'sai', 'sravan', 'sravan', 'user', 
    1, TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 1, 'chating', 0, 
    TO_DATE('01/23/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LOGIN_DETAILS
   (LOGINNAME, PASSWORD, FIRST_NAME, LAST_NAME, LOGINTYPE, 
    LOGIN_STATUS, REG_DATE, SQUESTIONID, SANSWER, FIRSTLOGIN, 
    PASSMODIFIEDDATE)
 Values
   ('ramarao', 'ramarao', 'ramarao', 'ramarao', 'user', 
    1, TO_DATE('01/23/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 9, 'ramu', 0, 
    TO_DATE('01/23/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

Insert into LOGIN_PROFILE
   (LOGINID, BIRTHDATE, CITY, STATE, COUNTRY, 
    LOCALE, PROFILEMODIFIEDDATE)
 Values
   ('bhargav', TO_DATE('10/16/1998 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Hyderabad', 'Andhra pradesh', 'India', 
    'en_US', TO_DATE('12/30/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LOGIN_PROFILE
   (LOGINID, BIRTHDATE, CITY, STATE, COUNTRY, 
    LOCALE, PROFILEMODIFIEDDATE)
 Values
   ('rajkumar', TO_DATE('07/01/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Hyderabad', 'Andhra pradesh', 'India', 
    'en_US', TO_DATE('08/28/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LOGIN_PROFILE
   (LOGINID, BIRTHDATE, CITY, STATE, COUNTRY, 
    LOCALE, PROFILEMODIFIEDDATE)
 Values
   ('sravan', TO_DATE('01/21/1980 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Hyderabad', 'Andhra pradesh', 'India', 
    'en_US', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into LOGIN_PROFILE
   (LOGINID, BIRTHDATE, CITY, STATE, COUNTRY, 
    LOCALE, PROFILEMODIFIEDDATE)
 Values
   ('ramarao', TO_DATE('01/25/1985 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Hyderabad', 'Andhra pradesh', 'India', 
    'en_US', TO_DATE('01/23/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (1, 'rakhi', 'kumar', TO_DATE('01/15/1985 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'ameerpet', 
    'hyderbad', 'andrapradesh', 'ameerpet,hyd', '9885345432', '5', 
    '65', TO_DATE('08/12/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'Y');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (2, 'a', 'b', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'f', 
    'f', 'f', 'f', '9885148211', '11', 
    '11', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'SMS', 'Y');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (3, 'a', 'a', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'a', 
    'a', 'a', 'a', '1', '1', 
    '1', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'N');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (4, 'Arun', 'Kumar', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'hyd', 
    'a', 'a', 'a', '9393851915', '5', 
    '5', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'Y');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (5, 'g', 'g', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'g', 
    'g', 'g', 'g', '221221', '5', 
    '5', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'Y');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (6, 'v', 'v', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'v', 
    'v', 'v', 'v', '221221', '5', 
    '5', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'Y');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (7, 'p', 'p', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'p', 
    'p', 'p', 'p', '9999999999', '45', 
    '44', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'Y');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (8, 'k', 'k', TO_DATE('01/21/1980 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'k', 
    'k', 'k', 'k', '9898989898', '160', 
    '56', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'Y');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (9, 'kiran', 'kumar', TO_DATE('10/22/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'ameer pet', 
    'hyd', 'ap', '43-253-10', '9885148211', '179', 
    '60', TO_DATE('10/21/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'Y');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (10, 'j', 'j', TO_DATE('10/12/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'j', 
    'j', 'j', 'j', '221221', '180', 
    '79', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'N');
Insert into PATEINT_DET
   (PID, FIRSTNAME, LASTNAME, BIRTHDATE, LOCATION, 
    CITY, STATE, ADDRESS, PHONE, HEIGHT, 
    WEIGHT, JOINDATE, TYPE, STATUS)
 Values
   (11, 'raghava', 'raghava', TO_DATE('01/17/1985 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'miyapur', 
    'hyd', 'a.p.', 'hyd', '9192949393', '5.3', 
    '45', TO_DATE('01/22/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Phone', 'Y');
COMMIT;

Insert into PATIENT_DISCHARGE_INFO
   (PID, STATEOFPATIENT, DATEOFDISCHARGE)
 Values
   (1, 'Recovered', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into PATIENT_DISCHARGE_INFO
   (PID, STATEOFPATIENT, DATEOFDISCHARGE)
 Values
   (2, 'Recovered', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into PATIENT_DISCHARGE_INFO
   (PID, STATEOFPATIENT, DATEOFDISCHARGE)
 Values
   (4, 'Recovered', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into PATIENT_DISCHARGE_INFO
   (PID, STATEOFPATIENT, DATEOFDISCHARGE)
 Values
   (5, 'Recovered', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into PATIENT_DISCHARGE_INFO
   (PID, STATEOFPATIENT, DATEOFDISCHARGE)
 Values
   (6, 'Recovered', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into PATIENT_DISCHARGE_INFO
   (PID, STATEOFPATIENT, DATEOFDISCHARGE)
 Values
   (7, 'Recovered', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into PATIENT_DISCHARGE_INFO
   (PID, STATEOFPATIENT, DATEOFDISCHARGE)
 Values
   (8, 'Recovered', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
Insert into PATIENT_DISCHARGE_INFO
   (PID, STATEOFPATIENT, DATEOFDISCHARGE)
 Values
   (9, 'Recovered', TO_DATE('10/21/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

Insert into PATIENT_INFO
   (PID, DOCID, HID, ROOMNO, DISCEASE, 
    MEDICINE, PSTATUS)
 Values
   (1, 2, 3, '12', 'fever', 
    'nycil', 'D');
Insert into PATIENT_INFO
   (PID, DOCID, HID, ROOMNO, DISCEASE, 
    MEDICINE, PSTATUS)
 Values
   (2, 1, 7, '111', 'a', 
    'a', 'D');
Insert into PATIENT_INFO
   (PID, DOCID, HID, ROOMNO, DISCEASE, 
    MEDICINE, PSTATUS)
 Values
   (2, 1, 7, '111', 'a', 
    'a', 'D');
Insert into PATIENT_INFO
   (PID, DOCID, HID, ROOMNO, DISCEASE, 
    MEDICINE, PSTATUS)
 Values
   (2, 6, 3, '111', 'f', 
    'f', 'D');
Insert into PATIENT_INFO
   (PID, DOCID, HID, ROOMNO, DISCEASE, 
    MEDICINE, PSTATUS)
 Values
   (6, 2, 3, '1', '1', 
    '1', 'D');
Insert into PATIENT_INFO
   (PID, DOCID, HID, ROOMNO, DISCEASE, 
    MEDICINE, PSTATUS)
 Values
   (8, 1, 3, '125', 'diabetics', 
    'platic', 'D');
Insert into PATIENT_INFO
   (PID, DOCID, HID, ROOMNO, DISCEASE, 
    MEDICINE, PSTATUS)
 Values
   (9, 1, 7, '123', 'fever', 
    'parcetomol', 'D');
Insert into PATIENT_INFO
   (PID, DOCID, HID, ROOMNO, DISCEASE, 
    MEDICINE, PSTATUS)
 Values
   (11, 1, 7, '12', 'fever', 
    'crocin,dolofer', 'A');
COMMIT;

Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (9, 'rajanikar', 'rajanikar', 'Doctor', '9872345679', 
    'rajanikar@gmail.com', 'hyd', 'srnagar', '8', TO_DATE('12/08/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'Y');
Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (1, 'ramesh', 'kumar', 'Doctor', '9885675345', 
    'ramkumar@g.com', 'hyderbad', 'ernagar,hyderbad', '6hrs', TO_DATE('08/11/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'Y');
Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (2, 'Guru', 'Charan', 'Doctor', '9885148211', 
    'guru@gmail.com', 'hyd', 'ameer pet', '8am - 8pm', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'Y');
Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (3, 'harish', 'chandra', 'Helper', '9885148211', 
    'harish@gmail.com', 'hyd', 'ameer pet', '10am-6pm', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'Y');
Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (4, 'dinesh', 'rana', 'Driver', '9492851915', 
    'dinesh@gmail.com', 'hyd', 'ameer pet', '10am-7pm', TO_DATE('10/16/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'Y');
Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (5, 'h', 'h', 'Helper', '9885148211', 
    'help@gmail.com', 'hyd', 'h', '2am-10pm', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'N');
Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (6, 'dd', 'dd', 'Doctor', '9292924343', 
    'sai@gmail.com', 'hyd', 'dp', '12:00-23:00', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'Y');
Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (7, 'h1', 'h1', 'Helper', '221221', 
    'charan@gmail.com', 'hyd', 'sp', '12:00-1600', TO_DATE('10/19/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'Y');
Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (8, 'Swaroop', 'Kumar', 'Doctor', '9492851915', 
    'swaroop@gmail.com', 'hyd', 'kukatpally', '10am-6pm', TO_DATE('10/21/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'Y');
Insert into STAFF_DET
   (SID, FIRSTNAME, LASTNAME, STAFFTYPE, PHONE, 
    EMAIL, LOCATION, ADDRESS, WORKINGHOURS, JOININGDATE, 
    STATUS)
 Values
   (10, 'RAJA', 'RAJA', 'Driver', '9192919292', 
    'RAJA@GMAIL.COM', 'HYD', 'SRNAGar', '8', TO_DATE('01/20/2010 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 
    'N');
COMMIT;

