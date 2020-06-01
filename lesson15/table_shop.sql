
CREATE SEQUENCE public.product_id_seq;

CREATE TABLE public.Product (
                id BIGINT NOT NULL DEFAULT nextval('public.product_id_seq'),
                price INTEGER NOT NULL,
                present BOOLEAN NOT NULL,
                product_name VARCHAR NOT NULL,
                CONSTRAINT product_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.product_id_seq OWNED BY public.Product.id;

CREATE SEQUENCE public.client_id_seq;

CREATE TABLE public.Client (
                id BIGINT NOT NULL DEFAULT nextval('public.client_id_seq'),
                fio VARCHAR NOT NULL,
                phonenumber INTEGER NOT NULL,
                CONSTRAINT client_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.client_id_seq OWNED BY public.Client.id;

CREATE SEQUENCE public.order_id_seq;

CREATE TABLE public.Order (
                id VARCHAR NOT NULL DEFAULT nextval('public.order_id_seq'),
                Client_id BIGINT NOT NULL,
                Product_id BIGINT NOT NULL,
                CONSTRAINT order_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.order_id_seq OWNED BY public.Order.id;

CREATE SEQUENCE public.shop_id_seq;

CREATE TABLE public.Shop (
                id BIGINT NOT NULL DEFAULT nextval('public.shop_id_seq'),
                Order_id VARCHAR NOT NULL,
                number_order INTEGER NOT NULL,
                CONSTRAINT shop_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.shop_id_seq OWNED BY public.Shop.id;

ALTER TABLE public.Order ADD CONSTRAINT product_order_fk
FOREIGN KEY (Product_id)
REFERENCES public.Product (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Order ADD CONSTRAINT client_order_fk
FOREIGN KEY (Client_id)
REFERENCES public.Client (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Shop ADD CONSTRAINT order_shop_fk
FOREIGN KEY (Order_id)
REFERENCES public.Order (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;