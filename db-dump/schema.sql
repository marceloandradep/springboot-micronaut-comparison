CREATE TABLE subscriptions
(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	subscriber_id BIGINT NOT NULL,
	event VARCHAR(255) NOT NULL,
	method VARCHAR(4) NOT NULL,
	request_url VARCHAR(255) NOT NULL
);

CREATE TABLE webhooks
(
	id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	response_code int(5) NULL,
	response VARCHAR(255) NULL,
	payload VARCHAR(255) NULL,
	method VARCHAR(4) NOT NULL,
	request_url VARCHAR(255) NOT NULL,
	sent timestamp NULL
);

INSERT INTO subscriptions (id, subscriber_id, event, method, request_url) VALUES (1, 1, 'message/send', 'POST', 'http://webhook.site/44eacd07-a116-4c0b-8f41-9ecbd73b1d46');
INSERT INTO subscriptions (id, subscriber_id, event, method, request_url) VALUES (2, 2, 'message/send', 'POST', 'http://localhost:8180/slowOperation');
