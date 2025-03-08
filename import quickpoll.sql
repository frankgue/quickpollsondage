-- Insérer des sondages
INSERT INTO poll (id, title, description) VALUES (1, 'Sondage sur la météo', 'Un sondage sur vos préférences météorologiques');
INSERT INTO poll (id, title, description) VALUES (2, 'Sondage sur les vacances', 'Partagez vos préférences pour les vacances');

-- Insérer des questions pour le sondage 1
INSERT INTO question (id, poll_id, text) VALUES (1, 1, 'Quelle est votre météo préférée ?');
INSERT INTO question (id, poll_id, text) VALUES (2, 1, 'Préférez-vous les journées ensoleillées ou nuageuses ?');

-- Insérer des options pour la question 1 du sondage 1
INSERT INTO option (id, question_id, text) VALUES (1, 1, 'Ensoleillée');
INSERT INTO option (id, question_id, text) VALUES (2, 1, 'Nuageuse');
INSERT INTO option (id, question_id, text) VALUES (3, 1, 'Pluvieuse');

-- Insérer des options pour la question 2 du sondage 1
INSERT INTO option (id, question_id, text) VALUES (4, 2, 'Ensoleillé');
INSERT INTO option (id, question_id, text) VALUES (5, 2, 'Nuageux');
INSERT INTO option (id, question_id, text) VALUES (6, 2, 'Pluvieux');

-- Insérer des questions pour le sondage 2
INSERT INTO question (id, poll_id, text) VALUES (3, 2, 'Préférez-vous la montagne ou la plage pour les vacances ?');
INSERT INTO question (id, poll_id, text) VALUES (4, 2, 'Quel type d’hébergement préférez-vous ?');

-- Insérer des options pour la question 3 du sondage 2
INSERT INTO option (id, question_id, text) VALUES (7, 3, 'Montagne');
INSERT INTO option (id, question_id, text) VALUES (8, 3, 'Plage');

-- Insérer des options pour la question 4 du sondage 2
INSERT INTO option (id, question_id, text) VALUES (9, 4, 'Hôtel');
INSERT INTO option (id, question_id, text) VALUES (10, 4, 'Camping');
INSERT INTO option (id, question_id, text) VALUES (11, 4, 'Maison de vacances');

-- Insérer des réponses pour le sondage 1
INSERT INTO response (id, poll_id, question_id, user_answer) VALUES (1, 1, 1, 'Ensoleillée');
INSERT INTO response (id, poll_id, question_id, user_answer) VALUES (2, 1, 2, 'Ensoleillé');

-- Insérer des réponses pour le sondage 2
INSERT INTO response (id, poll_id, question_id, user_answer) VALUES (3, 2, 3, 'Plage');
INSERT INTO response (id, poll_id, question_id, user_answer) VALUES (4, 2, 4, 'Hôtel');
