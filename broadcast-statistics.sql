--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

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
-- Name: broadcastdb; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.broadcastdb (
    name character varying NOT NULL,
    channel_id bigint NOT NULL,
    age_rating bigint,
    start date,
    "end" date,
    duration bigint,
    category character varying,
    genres character varying[]
);


ALTER TABLE public.broadcastdb OWNER TO postgres;

--
-- Name: channeldb; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.channeldb (
    channel_id bigint NOT NULL,
    pack character varying
);


ALTER TABLE public.channeldb OWNER TO postgres;

--
-- Name: clientdb; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientdb (
    client_id bigint NOT NULL,
    address character varying,
    gender character varying,
    age_min bigint,
    age_max bigint
);


ALTER TABLE public.clientdb OWNER TO postgres;

--
-- Data for Name: broadcastdb; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.broadcastdb (name, channel_id, age_rating, start, "end", duration, category, genres) FROM stdin;
\.


--
-- Data for Name: channeldb; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.channeldb (channel_id, pack) FROM stdin;
0	\N
\.


--
-- Data for Name: clientdb; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clientdb (client_id, address, gender, age_min, age_max) FROM stdin;
0	adasdsafsdadfsd	1	\N	\N
13	adasdsafsdadfsd	1	\N	\N
\.


--
-- Name: broadcastdb broadcast_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.broadcastdb
    ADD CONSTRAINT broadcast_pkey PRIMARY KEY (name);


--
-- Name: channeldb channel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.channeldb
    ADD CONSTRAINT channel_pkey PRIMARY KEY (channel_id);


--
-- Name: clientdb client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientdb
    ADD CONSTRAINT client_pkey PRIMARY KEY (client_id);


--
-- Name: broadcastdb channel_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.broadcastdb
    ADD CONSTRAINT channel_id FOREIGN KEY (channel_id) REFERENCES public.channeldb(channel_id) NOT VALID;


--
-- PostgreSQL database dump complete
--

