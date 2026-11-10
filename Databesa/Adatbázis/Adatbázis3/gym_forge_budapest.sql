-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Gép: localhost:8889
-- Létrehozás ideje: 2026. Nov 10. 09:06
-- Kiszolgáló verziója: 5.7.24
-- PHP verzió: 8.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `gym_forge_budapest`
--

DELIMITER $$
--
-- Eljárások
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `createadmins` (IN `image_idIN` INT, IN `emailIN` VARCHAR(150), IN `password_hashIN` VARCHAR(255), IN `usernameIN` VARCHAR(100))   INSERT INTO admin(image_id, email, password_hash, username, is_deleted)
VALUES (image_idIN, emailIN, password_hashIN, usernameIN, 0)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createclasses` (IN `nameIN` ENUM('gerinctorna','joga','noi_kondicionalo_torna'), IN `trainer_idIN` INT, IN `descriptionIN` TEXT, IN `duration_minutesIN` VARCHAR(50), IN `difficulty_levelIN` VARCHAR(50), IN `room_typeIN` ENUM('A_terem','B_terem','kuzdoter'))   BEGIN
INSERT INTO classes (name, trainer_id, description, duration_minutes, difficulty_level, room_type) 
VALUES (nameIN, trainer_idIN, descriptionIN, duration_minutesIN, difficulty_levelIN, room_typeIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createclass_bookings` (IN `user_idIN` INT, IN `class_schedule_idIN` INT, IN `booking_dateIN` DATETIME)   BEGIN
INSERT INTO class_bookings (user_id, class_schedule_id, booking_date) 
VALUES (user_idIN, class_schedule_idIN, booking_dateIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createclass_schedules` (IN `class_idIN` INT, IN `day_of_weekIN` ENUM('monday','tuesday','wednesday','thursday','friday','saturday','sunday'), IN `start_timeIN` TIME, IN `end_timeIN` TIME)   BEGIN
INSERT INTO class_schedules (class_id, day_of_week, start_time, end_time) 
VALUES (class_idIN, day_of_weekIN, start_timeIN, end_timeIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createcontact_messages` (IN `NameIN` VARCHAR(150), IN `EmailIN` VARCHAR(150), IN `MessageIN` TEXT, IN `StatusIN` ENUM('new','in_progress','resolved'), IN `Resolved_atIN` TIMESTAMP, IN `Admin_idIN` INT)   BEGIN
INSERT INTO contact_messages (Name, Email, Message, Status, Resolved_at, Admin_id) 
VALUES (NameIN, EmailIN, MessageIN, StatusIN, Resolved_atIN, Admin_idIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `creategym_info` (IN `gym_nameIN` VARCHAR(100), IN `addressIN` VARCHAR(255), IN `phone_numberIN` VARCHAR(50), IN `premium_machine_numberIN` INT, IN `emailIN` VARCHAR(150), IN `opening_hoursIN` TEXT, IN `users_numberIN` INT, IN `trainers_numberIN` INT)   BEGIN
INSERT INTO gym_info (gym_name, address, phone_number, premium_machine_number, email, opening_hours, users_number, trainers_number) 
VALUES (gym_nameIN, addressIN, phone_numberIN, premium_machine_numberIN, emailIN, opening_hoursIN, users_numberIN, trainers_numberIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createimages` (IN `image_urlIN` VARCHAR(255))   BEGIN
INSERT INTO images (image_url, is_deleted) 
VALUES (image_urlIN, 0);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createlogs` (IN `user_idIN` INT, IN `admin_idIN` INT, IN `actionIN` VARCHAR(100), IN `entity_tableIN` VARCHAR(100), IN `entity_idIN` INT)   BEGIN
    INSERT INTO log(user_id, admin_id, action, entity_table, entity_id)
    VALUES (user_idIN, admin_idIN, actionIN, entity_tableIN, entity_idIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `creatememberships` (IN `duration_daysIN` INT, IN `titleIN` VARCHAR(150), IN `priceIN` DECIMAL(10,2))   BEGIN
INSERT INTO memberships (duration_days, title, price) 
VALUES (duration_daysIN, titleIN, priceIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createpays` (IN `user_idIN` INT, IN `membership_idIN` INT, IN `priceIN` DECIMAL(10,2), IN `statusIN` ENUM('unpaid','paid','cancelled'), IN `email_sentIN` TINYINT, IN `paid_atIN` DATETIME)   BEGIN
INSERT INTO pays (user_id, membership_id, price, status, email_sent, paid_at) 
VALUES (user_idIN, membership_idIN, priceIN, statusIN, email_sentIN, paid_atIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createtrainer` (IN `FirstnameIN` VARCHAR(100), IN `LastnameIN` VARCHAR(100), IN `phone_numberIN` VARCHAR(50), IN `emailIN` VARCHAR(150), IN `titleIN` TEXT)   BEGIN
INSERT INTO trainer (Firstname, Lastname, phone_number, email, title, is_deleted) 
VALUES (FirstnameIN, LastnameIN, phone_numberIN, emailIN, titleIN, 0);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createtrainer_schedules` (IN `trainer_idIN` INT, IN `day_of_weekIN` INT, IN `start_timeIN` TIME, IN `end_timeIN` TIME)   BEGIN
INSERT INTO trainer_schedules (trainer_id, day_of_week, start_time, end_time) 
VALUES (trainer_idIN, day_of_weekIN, start_timeIN, end_timeIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createuser` (IN `image_idIN` INT, IN `usernameIN` VARCHAR(100), IN `emailIN` VARCHAR(150), IN `password_hashIN` VARCHAR(255))   INSERT INTO `user` (image_id, username, email, password_hash)
VALUES (image_idIN, usernameIN, emailIN, password_hashIN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createuser_memberships` (IN `user_idIN` INT, IN `membership_idIN` INT, IN `pays_idIN` INT, IN `start_dateIN` DATETIME, IN `end_dateIN` DATETIME, IN `statusIN` ENUM('pending_payment','active','expired','cancelled'))   BEGIN
INSERT INTO user_memberships (user_id, membership_id, pays_id, start_date, end_date, status) 
VALUES (user_idIN, membership_idIN, pays_idIN, start_dateIN, end_dateIN, statusIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createuser_tokens` (IN `user_idIN` INT, IN `remember_tokenIN` VARCHAR(100), IN `reset_password_tokenIN` VARCHAR(255), IN `reset_password_expiresIN` DATETIME, IN `email_verified_atIN` DATETIME)   BEGIN
INSERT INTO user_tokens (user_id, remember_token, reset_password_token, reset_password_expires, email_verified_at) 
VALUES (user_idIN, remember_tokenIN, reset_password_tokenIN, reset_password_expiresIN, email_verified_atIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createuser_x_trainer` (IN `User_idIN` INT, IN `Trainer_idIN` INT)   BEGIN
INSERT INTO user_x_trainer (User_id, Trainer_id) 
VALUES (User_idIN, Trainer_idIN);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getadmins` ()   SELECT id, image_id, email, password_hash, username, created_at, deleted_at, update_at, is_active, is_deleted
FROM admin
WHERE is_deleted = 0$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getadminsById` (IN `idIN` INT)   SELECT id, image_id, email, password_hash, username, created_at, deleted_at, update_at, is_active, is_deleted
FROM admin
WHERE id = idIN AND is_deleted = 0$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdclasses` (IN `idIN` INT)   BEGIN
SELECT id, name, trainer_id, description, duration_minutes, difficulty_level, room_type, created_at 
FROM classes 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdclass_bookings` (IN `idIN` INT)   BEGIN
SELECT id, user_id, class_schedule_id, booking_date, created_at 
FROM class_bookings 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdclass_schedules` (IN `idIN` INT)   BEGIN
SELECT id, class_id, day_of_week, start_time, end_time, created_at 
FROM class_schedules 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdcontact_messages` (IN `idIN` INT)   BEGIN
SELECT id, Name, Email, Message, Status, created_at, Resolved_at, Admin_id 
FROM contact_messages 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdgym_info` (IN `idIN` INT)   BEGIN
SELECT id, gym_name, address, phone_number, premium_machine_number, email, opening_hours, users_number, trainers_number, updated_at 
FROM gym_info 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdimages` (IN `idIN` INT)   BEGIN
SELECT id, image_url, created_at, deleted_at, update_at, is_deleted 
FROM images 
WHERE id = idIN AND is_deleted = 0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdmemberships` (IN `idIN` INT)   BEGIN
SELECT id, duration_days, title, price 
FROM memberships 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdpays` (IN `idIN` INT)   BEGIN
SELECT id, user_id, membership_id, price, status, email_sent, created_at, paid_at 
FROM pays 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdtrainer` (IN `idIN` INT)   BEGIN
SELECT id, Firstname, Lastname, phone_number, email, title, created_at, deleted_at, update_at, is_deleted 
FROM trainer 
WHERE id = idIN AND is_deleted = 0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIdtrainer_schedules` (IN `idIN` INT)   BEGIN
SELECT id, trainer_id, day_of_week, start_time, end_time, created_at, updated_at 
FROM trainer_schedules 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIduser_memberships` (IN `idIN` INT)   BEGIN
SELECT id, user_id, membership_id, pays_id, start_date, end_date, status, created_at 
FROM user_memberships 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIduser_tokens` (IN `idIN` INT)   BEGIN
SELECT id, user_id, remember_token, reset_password_token, reset_password_expires, email_verified_at, created_at, updated_at 
FROM user_tokens 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getByIduser_x_trainer` (IN `idIN` INT)   BEGIN
SELECT id, User_id, Trainer_id 
FROM user_x_trainer 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getclasses` ()   BEGIN
SELECT id, name, trainer_id, description, duration_minutes, difficulty_level, room_type, created_at 
FROM classes;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getclass_bookings` ()   BEGIN
SELECT id, user_id, class_schedule_id, booking_date, created_at 
FROM class_bookings;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getclass_schedules` ()   BEGIN
SELECT id, class_id, day_of_week, start_time, end_time, created_at 
FROM class_schedules;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getcontact_messages` ()   BEGIN
SELECT id, Name, Email, Message, Status, created_at, Resolved_at, Admin_id 
FROM contact_messages;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getgym_info` ()   BEGIN
SELECT id, gym_name, address, phone_number, premium_machine_number, email, opening_hours, users_number, trainers_number, updated_at 
FROM gym_info;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getimages` ()   BEGIN
SELECT id, image_url, created_at, deleted_at, update_at, is_deleted 
FROM images 
WHERE is_deleted = 0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getlogs` ()   BEGIN
    SELECT id, user_id, admin_id, action, entity_table, entity_id, created_at
    FROM log;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getlogsById` (IN `idIN` INT)   BEGIN
    SELECT id, user_id, admin_id, action, entity_table, entity_id, created_at
    FROM log
    WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getmemberships` ()   BEGIN
SELECT id, duration_days, title, price 
FROM memberships;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getpays` ()   BEGIN
SELECT id, user_id, membership_id, price, status, email_sent, created_at, paid_at 
FROM pays;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `gettrainer` ()   BEGIN
SELECT id, Firstname, Lastname, phone_number, email, title, created_at, deleted_at, update_at, is_deleted 
FROM trainer 
WHERE is_deleted = 0;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `gettrainer_schedules` ()   BEGIN
SELECT id, trainer_id, day_of_week, start_time, end_time, created_at, updated_at 
FROM trainer_schedules;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getuser` ()   SELECT id, image_id, username, email, password_hash, created_at, deleted_at, update_at, is_active, is_deleted
FROM `user`
WHERE is_deleted = 0$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getuserById` (IN `idIN` INT)   SELECT id, image_id, username, email, password_hash, created_at, deleted_at, update_at, is_active, is_deleted
FROM `user`
WHERE id = idIN AND is_deleted = 0$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getuser_memberships` ()   BEGIN
SELECT id, user_id, membership_id, pays_id, start_date, end_date, status, created_at 
FROM user_memberships;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getuser_tokens` ()   BEGIN
SELECT id, user_id, remember_token, reset_password_token, reset_password_expires, email_verified_at, created_at, updated_at 
FROM user_tokens;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getuser_x_trainer` ()   BEGIN
SELECT id, User_id, Trainer_id 
FROM user_x_trainer;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `softDeleteadmins` (IN `idIN` INT)   UPDATE admins
SET is_deleted = 1, deleted_at = NOW()
WHERE id = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `softDeleteimages` (IN `idIN` INT)   BEGIN
UPDATE images 
SET is_deleted = 1, deleted_at = NOW() 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `softDeletelogs` (IN `idIN` INT)   BEGIN
    DELETE FROM log WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `softdeletetrainer` (IN `idIN` INT)   BEGIN
UPDATE trainer 
SET is_deleted = 1, deleted_at = NOW() 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `softDeleteuser` (IN `idIN` INT)   UPDATE `user`
SET is_deleted = 1, deleted_at = NOW()
WHERE id = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateadmins` (IN `idIN` INT, IN `image_idIN` INT, IN `emailIN` VARCHAR(150), IN `usernameIN` VARCHAR(100), IN `is_activeIN` TINYINT)   UPDATE admin
SET image_id = image_idIN, email = emailIN, username = usernameIN, is_active = is_activeIN, update_at = NOW()
WHERE id = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateclasses` (IN `idIN` INT, IN `nameIN` ENUM('gerinctorna','joga','noi_kondicionalo_torna'), IN `trainer_idIN` INT, IN `descriptionIN` TEXT, IN `duration_minutesIN` VARCHAR(50), IN `difficulty_levelIN` VARCHAR(50), IN `room_typeIN` ENUM('A_terem','B_terem','kuzdoter'))   BEGIN
UPDATE classes 
SET name = nameIN, trainer_id = trainer_idIN, description = descriptionIN, duration_minutes = duration_minutesIN, difficulty_level = difficulty_levelIN, room_type = room_typeIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateclass_bookings` (IN `idIN` INT, IN `user_idIN` INT, IN `class_schedule_idIN` INT, IN `booking_dateIN` DATETIME)   BEGIN
UPDATE class_bookings 
SET user_id = user_idIN, class_schedule_id = class_schedule_idIN, booking_date = booking_dateIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateclass_schedules` (IN `idIN` INT, IN `class_idIN` INT, IN `day_of_weekIN` ENUM('monday','tuesday','wednesday','thursday','friday','saturday','sunday'), IN `start_timeIN` TIME, IN `end_timeIN` TIME)   BEGIN
UPDATE class_schedules 
SET class_id = class_idIN, day_of_week = day_of_weekIN, start_time = start_timeIN, end_time = end_timeIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatecontact_messages` (IN `idIN` INT, IN `NameIN` VARCHAR(150), IN `EmailIN` VARCHAR(150), IN `MessageIN` TEXT, IN `StatusIN` ENUM('new','in_progress','resolved'), IN `Resolved_atIN` TIMESTAMP, IN `Admin_idIN` INT)   BEGIN
UPDATE contact_messages 
SET Name = NameIN, Email = EmailIN, Message = MessageIN, Status = StatusIN, Resolved_at = Resolved_atIN, Admin_id = Admin_idIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updategym_info` (IN `idIN` INT, IN `gym_nameIN` VARCHAR(100), IN `addressIN` VARCHAR(255), IN `phone_numberIN` VARCHAR(50), IN `premium_machine_numberIN` INT, IN `emailIN` VARCHAR(150), IN `opening_hoursIN` TEXT, IN `users_numberIN` INT, IN `trainers_numberIN` INT)   BEGIN
UPDATE gym_info 
SET gym_name = gym_nameIN, address = addressIN, phone_number = phone_numberIN, premium_machine_number = premium_machine_numberIN, email = emailIN, opening_hours = opening_hoursIN, users_number = users_numberIN, trainers_number = trainers_numberIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateimages` (IN `idIN` INT, IN `image_urlIN` VARCHAR(255))   BEGIN
UPDATE images 
SET image_url = image_urlIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatelogs` (IN `idIN` INT, IN `user_idIN` INT, IN `admin_idIN` INT, IN `actionIN` VARCHAR(100), IN `entity_tableIN` VARCHAR(100), IN `entity_idIN` INT)   BEGIN
    UPDATE log
    SET user_id = user_idIN,
        admin_id = admin_idIN,
        action = actionIN,
        entity_table = entity_tableIN,
        entity_id = entity_idIN
    WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatememberships` (IN `idIN` INT, IN `duration_daysIN` INT, IN `titleIN` VARCHAR(150), IN `priceIN` DECIMAL(10,2))   BEGIN
UPDATE memberships 
SET duration_days = duration_daysIN, title = titleIN, price = priceIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatepays` (IN `idIN` INT, IN `user_idIN` INT, IN `membership_idIN` INT, IN `priceIN` DECIMAL(10,2), IN `statusIN` ENUM('unpaid','paid','cancelled'), IN `email_sentIN` TINYINT, IN `paid_atIN` DATETIME)   BEGIN
UPDATE pays 
SET user_id = user_idIN, membership_id = membership_idIN, price = priceIN, status = statusIN, email_sent = email_sentIN, paid_at = paid_atIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatetrainer` (IN `idIN` INT, IN `FirstnameIN` VARCHAR(100), IN `LastnameIN` VARCHAR(100), IN `phone_numberIN` VARCHAR(50), IN `emailIN` VARCHAR(150), IN `titleIN` TEXT)   BEGIN
UPDATE trainer 
SET Firstname = FirstnameIN, Lastname = LastnameIN, phone_number = phone_numberIN, email = emailIN, title = titleIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updatetrainer_schedules` (IN `idIN` INT, IN `trainer_idIN` INT, IN `day_of_weekIN` INT, IN `start_timeIN` TIME, IN `end_timeIN` TIME)   BEGIN
UPDATE trainer_schedules 
SET trainer_id = trainer_idIN, day_of_week = day_of_weekIN, start_time = start_timeIN, end_time = end_timeIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateuser` (IN `idIN` INT, IN `image_idIN` INT, IN `usernameIN` VARCHAR(100), IN `emailIN` VARCHAR(150), IN `is_activeIN` TINYINT)   UPDATE `user`
SET image_id = image_idIN, username = usernameIN, email = emailIN, is_active = is_activeIN
WHERE id = idIN$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateuser_memberships` (IN `idIN` INT, IN `user_idIN` INT, IN `membership_idIN` INT, IN `pays_idIN` INT, IN `start_dateIN` DATETIME, IN `end_dateIN` DATETIME, IN `statusIN` ENUM('pending_payment','active','expired','cancelled'))   BEGIN
UPDATE user_memberships 
SET user_id = user_idIN, membership_id = membership_idIN, pays_id = pays_idIN, start_date = start_dateIN, end_date = end_dateIN, status = statusIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateuser_tokens` (IN `idIN` INT, IN `user_idIN` INT, IN `remember_tokenIN` VARCHAR(100), IN `reset_password_tokenIN` VARCHAR(255), IN `reset_password_expiresIN` DATETIME, IN `email_verified_atIN` DATETIME)   BEGIN
UPDATE user_tokens 
SET user_id = user_idIN, remember_token = remember_tokenIN, reset_password_token = reset_password_tokenIN, reset_password_expires = reset_password_expiresIN, email_verified_at = email_verified_atIN 
WHERE id = idIN;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateuser_x_trainer` (IN `idIN` INT, IN `User_idIN` INT, IN `Trainer_idIN` INT)   BEGIN
UPDATE user_x_trainer 
SET User_id = User_idIN, Trainer_id = Trainer_idIN 
WHERE id = idIN;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `image_id` int(11) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  `is_active` tinyint(4) NOT NULL DEFAULT '1',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `admin`
--

INSERT INTO `admin` (`id`, `image_id`, `email`, `password_hash`, `username`, `created_at`, `deleted_at`, `update_at`, `is_active`, `is_deleted`) VALUES
(3, NULL, 'dfgdfgdfgdfg.ekak@example.com', '$2a$10$vQzIYHQzHVsPsbU/yS5ciu/V1Y/vmVB4jX/7N3gThQXODVSuiZziK', 'gdgdfgdf', '2026-11-03 09:22:21', NULL, NULL, 1, 0),
(4, NULL, 'fjfghgfhfgh.ekak@example.com', '$2a$10$l1TEP9eg7DthBC1YFvCD6.MfBh3.pXxS3.qhZp6Xf4PVYVdEr0LvO', 'bgnghnghngh', '2026-11-03 11:27:42', NULL, NULL, 1, 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `classes`
--

CREATE TABLE `classes` (
  `id` int(11) NOT NULL,
  `name` enum('gerinctorna','joga','noi_kondicionalo_torna','cross_training','latin_fitness','alakformalo') DEFAULT NULL,
  `trainer_id` int(11) DEFAULT NULL,
  `description` text,
  `duration_minutes` varchar(50) NOT NULL,
  `difficulty_level` varchar(50) NOT NULL,
  `room_type` enum('A_terem','B_terem','kuzdoter') DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `class_bookings`
--

CREATE TABLE `class_bookings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `class_schedule_id` int(11) NOT NULL,
  `booking_date` datetime NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `class_schedules`
--

CREATE TABLE `class_schedules` (
  `id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  `day_of_week` enum('monday','tuesday','wednesday','thursday','friday','saturday','sunday') NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `contact_messages`
--

CREATE TABLE `contact_messages` (
  `id` int(11) NOT NULL,
  `Name` varchar(150) NOT NULL,
  `Email` varchar(150) NOT NULL,
  `Message` text NOT NULL,
  `Status` enum('new','in_progress','resolved') NOT NULL DEFAULT 'new',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Resolved_at` timestamp NULL DEFAULT NULL,
  `Admin_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `gym_info`
--

CREATE TABLE `gym_info` (
  `id` int(11) NOT NULL,
  `gym_name` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `premium_machine_number` int(11) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `opening_hours` text,
  `users_number` int(11) DEFAULT NULL,
  `trainers_number` int(11) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `log`
--

CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `action` varchar(100) NOT NULL,
  `entity_table` varchar(100) DEFAULT NULL,
  `entity_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `memberships`
--

CREATE TABLE `memberships` (
  `id` int(11) NOT NULL,
  `duration_days` int(11) DEFAULT NULL,
  `title` varchar(150) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `pays`
--

CREATE TABLE `pays` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `membership_id` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `status` enum('unpaid','paid','cancelled') NOT NULL DEFAULT 'unpaid',
  `email_sent` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paid_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `trainer`
--

CREATE TABLE `trainer` (
  `id` int(11) NOT NULL,
  `Firstname` varchar(100) NOT NULL,
  `Lastname` varchar(100) NOT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `title` text,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `trainer_schedules`
--

CREATE TABLE `trainer_schedules` (
  `id` int(11) NOT NULL,
  `trainer_id` int(11) NOT NULL,
  `day_of_week` int(11) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `pays_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `payment_method` enum('cash','card','transfer') NOT NULL DEFAULT 'cash',
  `status` enum('pending','completed','failed','refunded') NOT NULL DEFAULT 'pending',
  `transaction_ref` varchar(100) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `completed_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `image_id` int(11) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`id`, `image_id`, `username`, `email`, `password_hash`, `created_at`, `deleted_at`, `update_at`, `is_active`, `is_deleted`) VALUES
(1, NULL, 'teszt_elek', 'teszt.elek@example.com', '$2a$10$adDeMN989GxJAi68F66QqeQdxl1p8egEBQuVvV3/psqIIl6ivW/Te', '2026-10-20 07:11:38', '2026-11-03 11:17:03', '2026-11-03 11:17:03', 1, 1),
(2, NULL, 'miaufdfdgdggdfg', 'teszti.ekak@example.com', '$2a$10$pnuKsD3R7Ws/Il/kjUQpO.CJZ3PT24dUl/8YlLe8KUWDK//ysvy2q', '2026-10-21 09:58:29', '2026-11-03 11:17:09', '2026-11-03 11:17:09', 1, 1),
(3, NULL, 'dfgdffg', 'teszasti.ekak@example.com', '$2a$10$17yLk2wj.EDTKqFLSIjYX.BUU2JAB0RCFEDEUwehn0rSxfW9v7DhO', '2026-10-21 10:02:36', NULL, NULL, 1, 0),
(4, NULL, 'uj_felhasznalonev', 'uj.email@example.com', '$2a$10$jALsU2xfqxE0qNbWZwdI3.Orr0l3wiz5jqZRC.oIbhRyhNCsUXtmW', '2026-10-31 20:39:03', NULL, '2026-11-03 11:18:07', 1, 0),
(5, NULL, 'gfg', 'gdfgd.ekak@example.com', '$2a$10$EfLKq2LhaKqOywZhm1VouuqMtFCmPdMSJ4Gegh7slyMtn9okNqpyO', '2026-11-03 09:08:06', NULL, NULL, 1, 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user_memberships`
--

CREATE TABLE `user_memberships` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `membership_id` int(11) DEFAULT NULL,
  `pays_id` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `status` enum('pending_payment','active','expired','cancelled') DEFAULT 'pending_payment',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user_tokens`
--

CREATE TABLE `user_tokens` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  `reset_password_expires` datetime DEFAULT NULL,
  `email_verified_at` datetime DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user_x_trainer`
--

CREATE TABLE `user_x_trainer` (
  `id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL,
  `Trainer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_admins_image` (`image_id`);

--
-- A tábla indexei `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_classes_trainer` (`trainer_id`);

--
-- A tábla indexei `class_bookings`
--
ALTER TABLE `class_bookings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_cb_user` (`user_id`),
  ADD KEY `idx_cb_class` (`class_schedule_id`);

--
-- A tábla indexei `class_schedules`
--
ALTER TABLE `class_schedules`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `contact_messages`
--
ALTER TABLE `contact_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_contact_admin` (`Admin_id`);

--
-- A tábla indexei `gym_info`
--
ALTER TABLE `gym_info`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_log_user` (`user_id`),
  ADD KEY `fk_log_admin` (`admin_id`);

--
-- A tábla indexei `memberships`
--
ALTER TABLE `memberships`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `pays`
--
ALTER TABLE `pays`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_pays_user` (`user_id`),
  ADD KEY `idx_pays_membership` (`membership_id`);

--
-- A tábla indexei `trainer`
--
ALTER TABLE `trainer`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `trainer_schedules`
--
ALTER TABLE `trainer_schedules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_schedule_trainer` (`trainer_id`);

--
-- A tábla indexei `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_transactions_ref` (`transaction_ref`),
  ADD KEY `idx_transactions_pays` (`pays_id`),
  ADD KEY `idx_transactions_user` (`user_id`),
  ADD KEY `idx_transactions_admin` (`admin_id`);

--
-- A tábla indexei `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `idx_user_image` (`image_id`);

--
-- A tábla indexei `user_memberships`
--
ALTER TABLE `user_memberships`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_um_user` (`user_id`),
  ADD KEY `idx_um_membership` (`membership_id`),
  ADD KEY `idx_um_pays` (`pays_id`);

--
-- A tábla indexei `user_tokens`
--
ALTER TABLE `user_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_tokens_user` (`user_id`);

--
-- A tábla indexei `user_x_trainer`
--
ALTER TABLE `user_x_trainer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_uxt_user` (`User_id`),
  ADD KEY `idx_uxt_trainer` (`Trainer_id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT a táblához `classes`
--
ALTER TABLE `classes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `class_bookings`
--
ALTER TABLE `class_bookings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `class_schedules`
--
ALTER TABLE `class_schedules`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `contact_messages`
--
ALTER TABLE `contact_messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `gym_info`
--
ALTER TABLE `gym_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `log`
--
ALTER TABLE `log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `memberships`
--
ALTER TABLE `memberships`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `pays`
--
ALTER TABLE `pays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `trainer`
--
ALTER TABLE `trainer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `trainer_schedules`
--
ALTER TABLE `trainer_schedules`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `user_memberships`
--
ALTER TABLE `user_memberships`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `user_tokens`
--
ALTER TABLE `user_tokens`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `user_x_trainer`
--
ALTER TABLE `user_x_trainer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_admins_image` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Megkötések a táblához `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `fk_classes_trainer` FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Megkötések a táblához `class_bookings`
--
ALTER TABLE `class_bookings`
  ADD CONSTRAINT `fk_cb_class` FOREIGN KEY (`class_schedule_id`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_cb_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `contact_messages`
--
ALTER TABLE `contact_messages`
  ADD CONSTRAINT `fk_contact_messages_admin` FOREIGN KEY (`Admin_id`) REFERENCES `admin` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Megkötések a táblához `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `fk_log_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_log_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL;

--
-- Megkötések a táblához `pays`
--
ALTER TABLE `pays`
  ADD CONSTRAINT `fk_pays_membership` FOREIGN KEY (`membership_id`) REFERENCES `memberships` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_pays_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `trainer_schedules`
--
ALTER TABLE `trainer_schedules`
  ADD CONSTRAINT `fk_schedules_trainer` FOREIGN KEY (`trainer_id`) REFERENCES `trainer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `fk_transactions_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_transactions_pays` FOREIGN KEY (`pays_id`) REFERENCES `pays` (`id`),
  ADD CONSTRAINT `fk_transactions_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Megkötések a táblához `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_image` FOREIGN KEY (`image_id`) REFERENCES `images` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Megkötések a táblához `user_memberships`
--
ALTER TABLE `user_memberships`
  ADD CONSTRAINT `fk_um_membership` FOREIGN KEY (`membership_id`) REFERENCES `memberships` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_um_pays` FOREIGN KEY (`pays_id`) REFERENCES `pays` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_um_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `user_tokens`
--
ALTER TABLE `user_tokens`
  ADD CONSTRAINT `fk_user_tokens_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `user_x_trainer`
--
ALTER TABLE `user_x_trainer`
  ADD CONSTRAINT `fk_uxt_trainer` FOREIGN KEY (`Trainer_id`) REFERENCES `trainer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_uxt_user` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
