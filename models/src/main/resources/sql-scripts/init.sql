INSERT INTO sequence (name, status, fk_project_id, thumbnail) VALUES ('first sequence published', 3, 1, "");
INSERT INTO sequence (name, status, fk_project_id, thumbnail) VALUES ('first sequence project 1', 1, 1, "");
INSERT INTO sequence (name, status, fk_project_id, thumbnail) VALUES ('second sequence project 1', 1, 1, "");
INSERT INTO sequence (name, status, fk_project_id, thumbnail) VALUES ('project 2 sequence', 1, 2, "");


INSERT INTO sequence_media (fk_sequence_id, fk_media_id) VALUES (1, 1);
INSERT INTO sequence_media (fk_sequence_id, fk_media_id) VALUES (1, 1);
INSERT INTO sequence_media (fk_sequence_id, fk_media_id) VALUES (1, 2);
INSERT INTO sequence_media (fk_sequence_id, fk_media_id) VALUES (2, 2);