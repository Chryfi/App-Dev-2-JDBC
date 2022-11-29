--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2022-11-29 19:07:16

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

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 16451)
-- Name: konto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.konto (
    nr integer NOT NULL,
    saldo double precision
);


ALTER TABLE public.konto OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16450)
-- Name: konto_nr_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.konto_nr_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.konto_nr_seq OWNER TO postgres;

--
-- TOC entry 3324 (class 0 OID 0)
-- Dependencies: 209
-- Name: konto_nr_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.konto_nr_seq OWNED BY public.konto.nr;


--
-- TOC entry 212 (class 1259 OID 16458)
-- Name: kunden; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kunden (
    nr integer NOT NULL,
    name text NOT NULL,
    konto_nr integer NOT NULL
);


ALTER TABLE public.kunden OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16457)
-- Name: kunden_nr_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kunden_nr_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.kunden_nr_seq OWNER TO postgres;

--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 211
-- Name: kunden_nr_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kunden_nr_seq OWNED BY public.kunden.nr;


--
-- TOC entry 3169 (class 2604 OID 16454)
-- Name: konto nr; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.konto ALTER COLUMN nr SET DEFAULT nextval('public.konto_nr_seq'::regclass);


--
-- TOC entry 3170 (class 2604 OID 16461)
-- Name: kunden nr; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kunden ALTER COLUMN nr SET DEFAULT nextval('public.kunden_nr_seq'::regclass);


--
-- TOC entry 3316 (class 0 OID 16451)
-- Dependencies: 210
-- Data for Name: konto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.konto (nr, saldo) FROM stdin;
\.


--
-- TOC entry 3318 (class 0 OID 16458)
-- Dependencies: 212
-- Data for Name: kunden; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kunden (nr, name, konto_nr) FROM stdin;
\.


--
-- TOC entry 3326 (class 0 OID 0)
-- Dependencies: 209
-- Name: konto_nr_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.konto_nr_seq', 1, false);


--
-- TOC entry 3327 (class 0 OID 0)
-- Dependencies: 211
-- Name: kunden_nr_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kunden_nr_seq', 1, false);


--
-- TOC entry 3172 (class 2606 OID 16456)
-- Name: konto konto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.konto
    ADD CONSTRAINT konto_pkey PRIMARY KEY (nr);


--
-- TOC entry 3174 (class 2606 OID 16465)
-- Name: kunden kunden_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kunden
    ADD CONSTRAINT kunden_pkey PRIMARY KEY (nr);


--
-- TOC entry 3175 (class 2606 OID 16466)
-- Name: kunden konto_nr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kunden
    ADD CONSTRAINT konto_nr FOREIGN KEY (konto_nr) REFERENCES public.konto(nr);


-- Completed on 2022-11-29 19:07:16

--
-- PostgreSQL database dump complete
--

