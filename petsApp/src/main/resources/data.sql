DROP TABLE IF EXISTS CAT;
DROP TABLE IF EXISTS DOG;
DROP TABLE IF EXISTS PET;
DROP TABLE IF EXISTS "USER";



create table USER
(
    ID   INT auto_increment,
    NAME VARCHAR(32),
    constraint USER_PK
        primary key (ID)
);

create unique index USER_ID_UINDEX
    on USER (ID);

create table PET
(
    ID      INT auto_increment,
    NAME    VARCHAR(32),
    USER_ID INT,
    constraint PET_USER_ID_FK
        foreign key (ID) references USER (ID)
);

create unique index PET_ID_UINDEX
    on PET (ID);

alter table PET
    add constraint PET_PK
        primary key (ID);

create table CAT
(
    ID    INT,
    CLAWS BOOLEAN,
    constraint CAT_PET_ID_FK
        foreign key (ID) references PET (ID)
);

create table DOG
(
    ID         INT,
    PAW_COLOUR VARCHAR(32),
    constraint DOG_PET_ID_FK
        foreign key (ID) references PET (ID)
);

