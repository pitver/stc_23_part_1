package ru.vershinin.lesson16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBInit-подготовка бд
 *
 * @author Вершинин Пётр
 */
 class DBInit {
    private static final Logger loggerBusiness = LogManager.getLogger(DBInit.class);
    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");
    private static final Logger loggerSecurity = LogManager.getLogger("SecurityLog4J2");


    /**
     * создание и инициализация таблиц
     *
     * @param conn -Connection
     */
    protected static void Init(Connection conn) {

        String sql=" \n" +
                "DROP TABLE IF EXISTS shop;\n"+
                "DROP TABLE IF EXISTS \"order\";\n"+
                "DROP TABLE IF EXISTS client;\n"+
                "DROP TABLE IF EXISTS product;\n"+
                "DROP TABLE IF EXISTS app_logs;\n"+
                "CREATE SEQUENCE public.product_id_seq;\n" +
                "\n" +
                "CREATE TABLE public.Product (\n" +
                "                id BIGINT NOT NULL DEFAULT nextval('public.product_id_seq'),\n" +
                "                price INTEGER NOT NULL,\n" +
                "                present BOOLEAN NOT NULL,\n" +
                "                product_name VARCHAR NOT NULL,\n" +
                "                CONSTRAINT product_pk PRIMARY KEY (id)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "ALTER SEQUENCE public.product_id_seq OWNED BY public.Product.id;\n" +
                "\n" +
                "CREATE SEQUENCE public.client_id_seq;\n" +
                "\n" +
                "CREATE TABLE public.Client (\n" +
                "                id BIGINT NOT NULL DEFAULT nextval('public.client_id_seq'),\n" +
                "                fio VARCHAR NOT NULL,\n" +
                "                phonenumber INTEGER NOT NULL,\n" +
                "                CONSTRAINT client_pk PRIMARY KEY (id)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "ALTER SEQUENCE public.client_id_seq OWNED BY public.Client.id;\n" +
                "\n" +
                "CREATE SEQUENCE public.order_id_seq;\n" +
                "\n" +
                "CREATE TABLE public.Order (\n" +
                "                id VARCHAR NOT NULL DEFAULT nextval('public.order_id_seq'),\n" +
                "                Client_id BIGINT NOT NULL,\n" +
                "                Product_id BIGINT NOT NULL,\n" +
                "                CONSTRAINT order_pk PRIMARY KEY (id)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "ALTER SEQUENCE public.order_id_seq OWNED BY public.Order.id;\n" +
                "\n" +
                "CREATE SEQUENCE public.shop_id_seq;\n" +
                "\n" +
                "CREATE TABLE public.Shop (\n" +
                "                id BIGINT NOT NULL DEFAULT nextval('public.shop_id_seq'),\n" +
                "                Order_id VARCHAR NOT NULL,\n" +
                "                number_order INTEGER NOT NULL,\n" +
                "                CONSTRAINT shop_pk PRIMARY KEY (id)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "ALTER SEQUENCE public.shop_id_seq OWNED BY public.Shop.id;\n" +
                "\n" +
                "ALTER TABLE public.Order ADD CONSTRAINT product_order_fk\n" +
                "FOREIGN KEY (Product_id)\n" +
                "REFERENCES public.Product (id)\n" +
                "ON DELETE NO ACTION\n" +
                "ON UPDATE NO ACTION\n" +
                "NOT DEFERRABLE;\n" +
                "\n" +
                "ALTER TABLE public.Order ADD CONSTRAINT client_order_fk\n" +
                "FOREIGN KEY (Client_id)\n" +
                "REFERENCES public.Client (id)\n" +
                "ON DELETE NO ACTION\n" +
                "ON UPDATE NO ACTION\n" +
                "NOT DEFERRABLE;\n" +
                "\n" +
                "ALTER TABLE public.Shop ADD CONSTRAINT order_shop_fk\n" +
                "FOREIGN KEY (Order_id)\n" +
                "REFERENCES public.Order (id)\n" +
                "ON DELETE NO ACTION\n" +
                "ON UPDATE NO ACTION\n" +
                "NOT DEFERRABLE;"+
                "INSERT INTO public.client(fio, phonenumber)VALUES ( 'Tom', 121111)\n;"+
                "INSERT INTO public.product(price, present,product_name)VALUES ( 12.4, true,'book')\n;"+
                "INSERT INTO public.order(client_id, Product_id)VALUES ( 1,1)\n;"+
                "INSERT INTO public.shop(order_id, number_order)VALUES ( 1, 1687)\n;"+
                "create table app_logs\n" +
                "(\n" +
                "    log_id     varchar,\n" +
                "    entry_date date,\n" +
                "    logger     text,\n" +
                "    log_level  varchar,\n" +
                "    message    varchar,\n" +
                "    exception  varchar\n" +
                ");";

        try (Statement st=conn.createStatement()) {
            st.executeUpdate(sql);
            loggerBusiness.info("инициализация таблиц");

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
        }

    }
}
