DROP TABLE IF EXISTS public.game_state CASCADE;
CREATE TABLE public.game_state (
    id serial NOT NULL PRIMARY KEY,
    current_map text NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.player CASCADE;
CREATE TABLE public.player (
    id serial NOT NULL PRIMARY KEY,
    player_name text NOT NULL,
    hp integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    attack_power integer NOT NULL,
    game_state_id integer NOT NULL
);

DROP TABLE IF EXISTS public.item CASCADE;
CREATE TABLE public.item(
    id serial NOT NULL PRIMARY KEY,
    item_name text NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    game_state_id integer NOT NULL
);

DROP TABLE IF EXISTS public.enemy CASCADE;
CREATE TABLE public.enemy(
    id serial NOT NULL PRIMARY KEY,
    monster_name text NOT NULL,
    hp integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL,
    game_state_id integer NOT NULL
);

ALTER TABLE ONLY public.item
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id) ON DELETE CASCADE ;

ALTER TABLE ONLY public.enemy
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id) ON DELETE CASCADE ;

ALTER TABLE ONLY public.player
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id) ON DELETE CASCADE ;
