package ru.vershinin.lesson24.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBInit-подготовка бд
 *
 * @author Вершинин Пётр
 */
class DBInitWithOutReferens {
    private static final Logger loggerBusiness = LogManager.getLogger(DBInitWithOutReferens.class);
    private static final Logger loggerSystem = LogManager.getLogger("SystemLog4J2");


    /**
     * создание и инициализация таблиц
     *
     * @param conn -Connection
     */
    protected static void Init(Connection conn) {


        String sql = new StringBuilder().append("DROP TABLE IF EXISTS shop;\n")
                .append("DROP TABLE IF EXISTS \"order\";\n")
                .append("DROP TABLE IF EXISTS client;\n")
                .append("DROP TABLE IF EXISTS product;\n")
                .append("DROP TABLE IF EXISTS app_logs;\n")
                .append("CREATE SEQUENCE public.product_id_seq;\n")
                .append("\n").append("CREATE TABLE public.product\n")
                .append("(\n")
                .append("    id           BIGINT           NOT NULL DEFAULT nextval('public.product_id_seq'),\n")
                .append("    price        DOUBLE PRECISION NOT NULL,\n")
                .append("    present      BOOLEAN          NOT NULL,\n")
                .append("    product_name VARCHAR          NOT NULL,\n")
                .append("    CONSTRAINT product_pk PRIMARY KEY (id)\n")
                .append(");\n")
                .append("\n")
                .append("\n")
                .append("ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;\n")
                .append("\n").append("CREATE SEQUENCE public.client_id_seq;\n")
                .append("\n").append("CREATE TABLE public.client\n")
                .append("(\n")
                .append("    id         BIGINT  NOT NULL DEFAULT nextval('public.client_id_seq'),\n")
                .append("    first_name VARCHAR NOT NULL,\n")
                .append("    last_name  VARCHAR NOT NULL,\n")
                .append("    username   VARCHAR NOT NULL,\n")
                .append("    password   VARCHAR NOT NULL,\n")
                .append("    CONSTRAINT client_pk PRIMARY KEY (id)\n")
                .append(");\n")
                .append("\n")
                .append("\n")
                .append("ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;\n")
                .append("\n")
                .append("CREATE SEQUENCE public.order_id_seq;\n")
                .append("\n").append("CREATE TABLE public.Order\n")
                .append("(\n")
                .append("    id         BIGINT NOT NULL DEFAULT nextval('public.order_id_seq'),\n")
                .append("    Client_id  BIGINT NOT NULL,\n")
                .append("    Product_id BIGINT NOT NULL,\n")
                .append("    number_order BIGINT NOT NULL,\n")
                .append("    CONSTRAINT order_pk PRIMARY KEY (id)\n")
                .append(");\n")
                .append("\n")
                .append("\n")
                .append("ALTER SEQUENCE public.order_id_seq OWNED BY public.Order.id;\n")
                .append("\n").append("CREATE SEQUENCE public.shop_id_seq;\n")
                .append("\n").append("CREATE TABLE public.Shop\n")
                .append("(\n")
                .append("    id           BIGINT  NOT NULL DEFAULT nextval('public.shop_id_seq'),\n")
                .append("    Order_id     INTEGER NOT NULL,\n")
                .append("    number_order INTEGER NOT NULL,\n")
                .append("    CONSTRAINT shop_pk PRIMARY KEY (id)\n")
                .append(");\n")
                .append("\n")
                .append("\n")
                .append("ALTER SEQUENCE public.shop_id_seq OWNED BY public.Shop.id;\n")
                .append("\n")
                .append("\n")
                .append("\n")
                .append("                create table app_logs\n")
                .append("                (\n")
                .append("                    log_id     varchar,\n")
                .append("                    entry_date date,\n")
                .append("                    logger     text,\n")
                .append("                    log_level  varchar,\n")
                .append("                    message    varchar,\n")
                .append("                    exception  varchar\n")
                .append("                );")
                .toString();

        try (

                Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            loggerBusiness.info("инициализация таблиц");

        } catch (SQLException e) {
            loggerSystem.error(e.getMessage());
            e.printStackTrace();
        }

    }
}
