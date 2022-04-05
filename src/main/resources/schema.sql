CREATE SCHEMA IF NOT EXISTS DB_SPRING_SEC_GP;

-- auto-generated definition
-- DROP sequence IF EXISTS HIBERNATE_SEQUENCE;
create sequence IF NOT EXISTS HIBERNATE_SEQUENCE;


-- auto-generated definition
-- DROP TABLE IF EXISTS ACL_USER;
create table IF NOT EXISTS ACL_USER
(
    ID               BIGINT auto_increment primary key,
    USERNAME         VARCHAR(15) not null,
    PASSWORD         VARCHAR(255),

    ENABLED          BOOLEAN default TRUE,
    ACCOUNT_EXPIRED  BOOLEAN     not null,
    ACCOUNT_LOCKED   BOOLEAN     not null,
    PASSWORD_EXPIRED BOOLEAN     not null,

    DISPLAY_NAME     VARCHAR(255),
    EMAIL            VARCHAR(255),
    PHONE_NUMBER     VARCHAR(255),
    GENDER           VARCHAR(255),
    PROFILE_PIC_PATH VARCHAR(300),

    DEVICE_TOKEN     VARCHAR(255),
    DEVICE_TYPE      VARCHAR(255),
    ACTIVE_ONLINE    BOOLEAN,

    CREATION_DATETIME    TIMESTAMP,
    CREATION_USER        VARCHAR(255),
    LAST_UPDATE_DATETIME TIMESTAMP,
    LAST_UPDATE_USER     VARCHAR(255)
);


-- auto-generated definition
-- DROP TABLE IF EXISTS ACL_ROLE;
create table IF NOT EXISTS ACL_ROLE
(
    ID                   BIGINT auto_increment primary key,
    AUTHORITY            VARCHAR(255),
    DESCRIPTION          VARCHAR(255),

    CREATION_DATETIME    TIMESTAMP,
    CREATION_USER        VARCHAR(255),
    LAST_UPDATE_DATETIME TIMESTAMP,
    LAST_UPDATE_USER     VARCHAR(255)
);


-- auto-generated definition
-- DROP TABLE IF EXISTS ACL_USER_ROLE;
create table IF NOT EXISTS ACL_USER_ROLE
(
    ID                    BIGINT not null,
    ROLE_ID               BIGINT not null,
    USER_ID               BIGINT not null,

    CREATION_DATE_TIME    TIMESTAMP,
    CREATION_USER         VARCHAR(255),
    LAST_UPDATE_DATE_TIME TIMESTAMP,
    LAST_UPDATE_USER      VARCHAR(255),
    primary key (USER_ID, ROLE_ID),
    constraint FKH4U2WT7GRWNO5YJDNHIGPOFCM
        foreign key (ROLE_ID) references ACL_ROLE (ID),
    constraint FKKP4BSG51DYAMUFWGWR0HTQKIC
        foreign key (USER_ID) references ACL_USER (ID)
);





-- auto-generated definition
-- DROP TABLE IF EXISTS BAS_ITEM;
create table IF NOT EXISTS BAS_ITEM
(
    ID                   BIGINT auto_increment
        primary key,
    CODE                 VARCHAR(255) not null
        constraint UK_5CAKN84YGW4KUNRASAPID88FW
            unique,
    DESCRIPTION          VARCHAR(255) not null,
    SELLING_PRICE        VARCHAR(255),

    CREATION_DATETIME    TIMESTAMP,
    CREATION_USER        VARCHAR(255),
    LAST_UPDATE_DATETIME TIMESTAMP,
    LAST_UPDATE_USER     VARCHAR(255)
);


-- auto-generated definition
-- DROP TABLE IF EXISTS BOOKS;
create table IF NOT EXISTS BOOKS
(
    ID    INT auto_increment
        primary key,
    NAME  VARCHAR(255),
    PRICE NUMERIC(15, 2)
);



-- auto-generated definition
-- DROP TABLE IF EXISTS ACL_SYSTEM_RESOURCE_DEF;
create table IF NOT EXISTS ACL_SYSTEM_RESOURCE_DEF
(
    ID                      BIGINT auto_increment
        primary key,
    ACTIVE                  BOOLEAN,
    ADMIN_ACCESS_ONLY       BOOLEAN,
    BACKEND_URL             VARCHAR(255),
    CHK_AUTHORIZATION       VARCHAR(255),
    CHK_AUTHORIZATION_CHAR  VARCHAR(255),
    CLIENT_REQ_URL          VARCHAR(255),
    CREATION_DATETIME       TIMESTAMP,
    CREATION_USER           VARCHAR(255),
    ENTITY_DESCRIPTION      VARCHAR(255),
    ENTITY_NAME             VARCHAR(255),
    LAST_UPDATE_DATETIME    TIMESTAMP,
    LAST_UPDATE_USER        VARCHAR(255),
    OPEN_URL                VARCHAR(255),
    RESOURCE_TYPE           VARCHAR(255),
    SEQUENCE                INTEGER,
    SUPER_ADMIN_ACCESS_ONLY BOOLEAN
);



-- auto-generated definition
-- DROP TABLE IF EXISTS ACL_SYSTEM_RESOURCE_AUTH;
create table IF NOT EXISTS ACL_SYSTEM_RESOURCE_AUTH
(
    ID                    BIGINT auto_increment
        primary key,
    CREATE_AUTH           VARCHAR(255),
    CREATION_DATETIME     TIMESTAMP,
    CREATION_USER         VARCHAR(255),
    CRUDQS_STRING         VARCHAR(255),
    DELETE_AUTH           VARCHAR(255),
    FULL_PRIVILEGE_STRING VARCHAR(255),
    LAST_UPDATE_DATETIME  TIMESTAMP,
    LAST_UPDATE_USER      VARCHAR(255),
    OTHERS_STRING         VARCHAR(255),
    QUERY_AUTH            VARCHAR(255),
    READ_AUTH             VARCHAR(255),
    SUBMIT_AUTH           VARCHAR(255),
    SYSTEM_RESOURCE_NAME  VARCHAR(255),
    UPDATE_AUTH           VARCHAR(255),
    USERNAME              VARCHAR(255),
    VISIBLE_TO_ALL        BOOLEAN,
    ROLE_ID               BIGINT,
    SYSTEM_RESOURCE_ID    BIGINT,
    constraint FK6VLO8IA9PA9OI17BWBBME10HM
        foreign key (SYSTEM_RESOURCE_ID) references ACL_SYSTEM_RESOURCE_DEF (ID),
    constraint FKF2RAAQ5P4MIKXR2BC03A76KLV
        foreign key (ROLE_ID) references ACL_ROLE (ID)
);



-- auto-generated definition
-- DROP TABLE IF EXISTS ACL_REQUEST_URL_MAP;
create table IF NOT EXISTS ACL_REQUEST_URL_MAP
(
    ID                   BIGINT auto_increment
        primary key,
    URL                  VARCHAR(255),
    CONFIG_ATTRIBUTE     VARCHAR(255),
    HTTP_METHOD          INTEGER,
    CREATION_DATETIME    TIMESTAMP,
    CREATION_USER        VARCHAR(255),
    LAST_UPDATE_DATETIME TIMESTAMP,
    LAST_UPDATE_USER     VARCHAR(255)
);
