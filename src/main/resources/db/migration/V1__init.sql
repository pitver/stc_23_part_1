DROP TABLE IF EXISTS shop;
                DROP TABLE IF EXISTS "order";
                DROP TABLE IF EXISTS client;
                DROP TABLE IF EXISTS product;
                DROP TABLE IF EXISTS app_logs;
                CREATE SEQUENCE public.product_id_seq;

                CREATE TABLE public.product (
                                id BIGINT NOT NULL DEFAULT nextval('public.product_id_seq'),
                                price DOUBLE PRECISION NOT NULL,
                                present BOOLEAN NOT NULL,
                                product_name VARCHAR NOT NULL,
                                CONSTRAINT product_pk PRIMARY KEY (id)
                );


                ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;

                CREATE SEQUENCE public.client_id_seq;

                CREATE TABLE public.client (
                                id BIGINT NOT NULL DEFAULT nextval('public.client_id_seq'),
                                first_name VARCHAR NOT NULL,
                                last_name VARCHAR NOT NULL,
                                username VARCHAR NOT NULL,
                                password VARCHAR NOT NULL,
                                roles VARCHAR NOT NULL,
                                CONSTRAINT client_pk PRIMARY KEY (id)
                );


                ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;

                CREATE SEQUENCE public.order_id_seq;

                CREATE TABLE public.Order (
                                id BIGINT NOT NULL DEFAULT nextval('public.order_id_seq'),
                                Client_id BIGINT NOT NULL,
                                Product_id BIGINT NOT NULL,
                                number_order BIGINT NOT NULL,
                                CONSTRAINT order_pk PRIMARY KEY (id)
                );


                ALTER SEQUENCE public.order_id_seq OWNED BY public.Order.id;

                CREATE SEQUENCE public.shop_id_seq;

                CREATE TABLE public.Shop (
                                id BIGINT NOT NULL DEFAULT nextval('public.shop_id_seq'),
                                Order_id INTEGER NOT NULL,
                                number_order INTEGER NOT NULL,
                                CONSTRAINT shop_pk PRIMARY KEY (id)
                );


                ALTER SEQUENCE public.shop_id_seq OWNED BY public.Shop.id;

                ALTER TABLE public.Order ADD CONSTRAINT product_order_fk
                FOREIGN KEY (Product_id)
                REFERENCES public.product (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
                NOT DEFERRABLE;

                ALTER TABLE public.Order ADD CONSTRAINT client_order_fk
                FOREIGN KEY (Client_id)
                REFERENCES public.client (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
                NOT DEFERRABLE;

                ALTER TABLE public.Shop ADD CONSTRAINT order_shop_fk
                FOREIGN KEY (Order_id)
                REFERENCES public.Order (id)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION
                NOT DEFERRABLE;
                create table app_logs
                (
                    log_id     varchar,
                    entry_date date,
                    logger     text,
                    log_level  varchar,
                    message    varchar,
                    exception  varchar
                );