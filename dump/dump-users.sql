--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.21
-- Dumped by pg_dump version 12.2

-- Started on 2021-01-04 12:47:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2110 (class 1262 OID 16815)
-- Name: users; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE users WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Italian_Italy.1252' LC_CTYPE = 'Italian_Italy.1252';


ALTER DATABASE users OWNER TO postgres;

\connect users

CREATE USER demoexcel WITH PASSWORD 'jw8s0F4';

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- TOC entry 182 (class 1259 OID 16824)
-- Name: configuration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configuration (
    key text NOT NULL,
    value text
);


ALTER TABLE public.configuration OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16816)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    name text,
    surname text,
    fiscal_code text,
    city text,
    email text,
    phone text
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 2104 (class 0 OID 16824)
-- Dependencies: 182
-- Data for Name: configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.configuration (key, value) VALUES ('users-count-data', 'select city, count(*)
from users
group by city');
INSERT INTO public.configuration (key, value) VALUES ('users-list-header', 'NAME,SURNAME,FISCAL CODE,SEX,YEARS,CITY,EMAIL,PHONE');
INSERT INTO public.configuration (key, value) VALUES ('users-count-header', 'CITY,COUNT');
INSERT INTO public.configuration (key, value) VALUES ('users-list-data', 'select 
	name, 
	surname, 
	fiscal_code, 
	CASE
		WHEN substring(fiscal_code from 10 for 2)\:\:INTEGER <= 31 THEN ''M''
		ELSE ''F''
	END as sex,
	CASE 
		WHEN substring(fiscal_code from 7 for 2)\:\:INTEGER > substring(date_part(''year'', CURRENT_DATE)\:\:text from 3 for 2)\:\:INTEGER THEN date_part(''year'', CURRENT_DATE)\:\:INTEGER - (''19'' || substring(fiscal_code from 7 for 2))\:\:INTEGER
	ELSE 
		date_part(''year'', CURRENT_DATE)\:\:INTEGER - (''20'' || substring(fiscal_code from 7 for 2))\:\:INTEGER
	END as year,
	city,
	email,
	phone
from
	users');


--
-- TOC entry 2103 (class 0 OID 16816)
-- Dependencies: 181
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, name, surname, fiscal_code, city, email, phone) VALUES ('182fc1b1-c767-4008-800a-3ed6cecf70cc', 'Paolo', 'Rossi', 'RSSPLA80M05F839Z', 'Naples', 'paolo@rossi.it', '333 33 33 333');
INSERT INTO public.users (id, name, surname, fiscal_code, city, email, phone) VALUES ('5b69f600-81db-42d8-9850-a6e78a3bbae6', 'Maria', 'Esposito', 'SPSMRA85D49H501F', 'Rome', 'maria@esposito.it', '333 44 44 444');


--
-- TOC entry 1988 (class 2606 OID 16831)
-- Name: configuration configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configuration
    ADD CONSTRAINT configuration_pkey PRIMARY KEY (key);


--
-- TOC entry 1986 (class 2606 OID 16823)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2111 (class 0 OID 0)
-- Dependencies: 2110
-- Name: DATABASE users; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON DATABASE users FROM PUBLIC;
REVOKE ALL ON DATABASE users FROM postgres;
GRANT ALL ON DATABASE users TO postgres;


--
-- TOC entry 2112 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 2113 (class 0 OID 0)
-- Dependencies: 182
-- Name: TABLE configuration; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE public.configuration FROM PUBLIC;
REVOKE ALL ON TABLE public.configuration FROM postgres;
GRANT ALL ON TABLE public.configuration TO postgres;
GRANT ALL ON TABLE public.configuration TO demoexcel;


--
-- TOC entry 2114 (class 0 OID 0)
-- Dependencies: 181
-- Name: TABLE users; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE public.users FROM PUBLIC;
REVOKE ALL ON TABLE public.users FROM postgres;
GRANT ALL ON TABLE public.users TO postgres;
GRANT ALL ON TABLE public.users TO demoexcel;


-- Completed on 2021-01-04 12:47:43

--
-- PostgreSQL database dump complete
--

