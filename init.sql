-- Run these master SQL queries to initialize your DB for the app

CREATE DATABASE IF NOT EXISTS db_user;

USE db_user;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) UNIQUE NOT NULL,
    age INT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (firstname, lastname, email, username, password, age)
VALUES ('John', 'Doe', 'johndoe@example.com', 'johndoe123', 'password123', 30);

CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    review_text VARCHAR(255) NOT NULL,
    bar_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);

INSERT INTO reviews (review_text, bar_id, user_id)
VALUES ('Great atmosphere and music!', 1, 1);

CREATE TABLE bars (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    place_id VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255), -- Optional: Store additional details,
    busyness INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE friends (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
