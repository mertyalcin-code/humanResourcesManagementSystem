
-- This script was generated by a beta version of the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE public.employees
(
    user_id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    birth_year character(4) NOT NULL,
    nationality_id character(11) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE public.employers
(
    user_id integer NOT NULL,
    company_name character varying(100) NOT NULL,
    website character varying(200) NOT NULL,
    phone_number character(10) NOT NULL,
    system_approval boolean NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE public.mail_activation
(
    id integer NOT NULL,
    employee_id integer NOT NULL,
    employer_id integer NOT NULL,
    is_activated boolean NOT NULL,
    activation_date date,
    PRIMARY KEY (id)
);

CREATE TABLE public.professions
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    title character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.system_users
(
    user_id integer NOT NULL,
    "position" character varying(50) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE public.users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email character varying(100) NOT NULL,
    password character varying(30) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.employees
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.employers
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.mail_activation
    ADD FOREIGN KEY (employee_id)
    REFERENCES public.employees (user_id)
    NOT VALID;


ALTER TABLE public.mail_activation
    ADD FOREIGN KEY (employer_id)
    REFERENCES public.employers (user_id)
    NOT VALID;


ALTER TABLE public.system_users
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;

END;
