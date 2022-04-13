CREATE TABLE post (
    id bigserial NOT NULL,
	"title" varchar(255) NULL,
	content text NULL,
	CONSTRAINT post_pkey PRIMARY KEY (id)
);
CREATE INDEX post_id_index ON post (id);

CREATE TABLE tag (
    id bigserial NOT NULL,
	label varchar(255) NOT NULL,
	CONSTRAINT tag_pkey PRIMARY KEY (id),
	CONSTRAINT tag_label_unique UNIQUE (label)
);
CREATE INDEX tag_id_index ON tag (id);

CREATE TABLE post_has_tag (
    id bigserial NOT NULL,
    post_id int8 NOT NULL,
    tag_id int8 NOT NULL,
    CONSTRAINT post_has_tag_post_id_foreign FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE,
    CONSTRAINT post_has_tag_tag_id_foreign FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE
);
CREATE INDEX post_has_tag_id_index ON post_has_tag (id);
CREATE INDEX post_has_tag_post_id_tag_id_index ON post_has_tag (post_id, tag_id);

