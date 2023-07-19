CREATE TABLE TEST2 (
	TEST2_ID BigInt NOT NULL,
	NAME text,
	CONSTRAINT pk_TEST2 PRIMARY KEY (TEST2_ID)
)

CREATE TABLE BRANDS (
    BRAND_ID BigInt   NOT NULL,
    NAME text   NOT NULL,
    DESCRIPTION text   NOT NULL,
    CONSTRAINT pk_BRAND PRIMARY KEY (
        BRAND_ID
     )
);

CREATE TABLE PRICES (
    PRICE_ID BigInt   NOT NULL,
    BRAND_ID BigInt   NOT NULL,
    START_DATE date   NOT NULL,
    END_DATE date   NOT NULL,
    PRICE_LIST int   NOT NULL,
    PRODUCT_ID int   NOT NULL,
    PRIORITY int   NOT NULL,
    PRICE numeric   NOT NULL,
    CURR text   NOT NULL,
    CONSTRAINT pk_Price PRIMARY KEY (
        PRICE_ID
     )
);

CREATE TABLE PRICE_LIST (
    PRICE_LIST_ID BigInt   NOT NULL,
    PRODUCT_ID int   NOT NULL,
    CONSTRAINT pk_PRICE_LIST PRIMARY KEY (
        PRICE_LIST_ID
     )
);

CREATE TABLE PRODUCTS (
    PRODUCT_ID BigInt   NOT NULL,
    NAME text   NOT NULL,
    DESCRIPTION text   NOT NULL,
    CONSTRAINT pk_PRODUCTS PRIMARY KEY (
        PRODUCT_ID
     )
);

ALTER TABLE PRICES ADD CONSTRAINT fk_Price_BRAND_ID FOREIGN KEY(BRAND_ID)
REFERENCES BRANDS (BRAND_ID);

ALTER TABLE PRICES ADD CONSTRAINT fk_Price_PRICE_LIST FOREIGN KEY(PRICE_LIST)
REFERENCES PRICE_LIST (PRICE_LIST_ID);

ALTER TABLE PRICES ADD CONSTRAINT fk_Price_PRODUCT_ID FOREIGN KEY(PRODUCT_ID)
REFERENCES PRODUCTS (PRODUCT_ID);

ALTER TABLE PRICE_LIST ADD CONSTRAINT fk_PRICE_LIST_PRODUCT_ID FOREIGN KEY(PRODUCT_ID)
REFERENCES PRODUCTS (PRODUCT_ID);

CREATE INDEX idx_BRAND_NAME
ON BRANDS (NAME);