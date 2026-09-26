-- V1__baseline_schema.sql

CREATE TABLE `genre` (
  `genre_id` int NOT NULL,
  `genre_name` varchar(25) NOT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `storage_location` (
  `storage_location_id` int NOT NULL,
  `storage_location_name` varchar(50) NOT NULL,
  PRIMARY KEY (`storage_location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(255) NOT NULL,
  `display_name` varchar(32) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role` varchar(20) DEFAULT 'user',
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `book_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(100) NOT NULL,
  `fk_genre_id` int NOT NULL,
  `fk_storage_location_id` int NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT '未貸出',
  `summary` varchar(250) DEFAULT NULL,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  `available_count` int NOT NULL DEFAULT '1',
  `total_count` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_book_genre` (`fk_genre_id`),
  KEY `fk_book_storage_location` (`fk_storage_location_id`),
  CONSTRAINT `fk_book_genre` FOREIGN KEY (`fk_genre_id`) REFERENCES `genre` (`genre_id`),
  CONSTRAINT `fk_book_storage_location` FOREIGN KEY (`fk_storage_location_id`) REFERENCES `storage_location` (`storage_location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_cart_user_book` (`user_id`,`book_id`),
  KEY `fk_cart_book` (`book_id`),
  CONSTRAINT `fk_cart_book` FOREIGN KEY (`book_id`) REFERENCES `book_info` (`id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `loan_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `borrow_date` date NOT NULL,
  `return_due_date` date NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_loan_user` (`user_id`),
  KEY `fk_loan_book` (`book_id`),
  CONSTRAINT `fk_loan_book` FOREIGN KEY (`book_id`) REFERENCES `book_info` (`id`),
  CONSTRAINT `fk_loan_user` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;