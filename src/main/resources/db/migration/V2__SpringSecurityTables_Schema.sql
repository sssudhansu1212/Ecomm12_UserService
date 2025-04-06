-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Table structure for table `authorization`
--

DROP TABLE IF EXISTS `authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorization` (
  `access_token_expires_at` datetime(6) DEFAULT NULL,
  `access_token_issued_at` datetime(6) DEFAULT NULL,
  `authorization_code_expires_at` datetime(6) DEFAULT NULL,
  `authorization_code_issued_at` datetime(6) DEFAULT NULL,
  `device_code_expires_at` datetime(6) DEFAULT NULL,
  `device_code_issued_at` datetime(6) DEFAULT NULL,
  `oidc_id_token_expires_at` datetime(6) DEFAULT NULL,
  `oidc_id_token_issued_at` datetime(6) DEFAULT NULL,
  `refresh_token_expires_at` datetime(6) DEFAULT NULL,
  `refresh_token_issued_at` datetime(6) DEFAULT NULL,
  `user_code_expires_at` datetime(6) DEFAULT NULL,
  `user_code_issued_at` datetime(6) DEFAULT NULL,
  `access_token_type` varchar(255) DEFAULT NULL,
  `authorization_code_metadata` varchar(255) DEFAULT NULL,
  `authorization_grant_type` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `principal_name` varchar(255) DEFAULT NULL,
  `registered_client_id` varchar(255) DEFAULT NULL,
  `access_token_metadata` text,
  `access_token_scopes` text,
  `access_token_value` text,
  `attributes` text,
  `authorization_code_value` text,
  `authorized_scopes` text,
  `device_code_metadata` text,
  `device_code_value` text,
  `oidc_id_token_claims` text,
  `oidc_id_token_metadata` text,
  `oidc_id_token_value` text,
  `refresh_token_metadata` text,
  `refresh_token_value` text,
  `state` text,
  `user_code_metadata` text,
  `user_code_value` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `authorization_consent`
--

DROP TABLE IF EXISTS `authorization_consent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorization_consent` (
  `authorities` varchar(1000) DEFAULT NULL,
  `principal_name` varchar(255) NOT NULL,
  `registered_client_id` varchar(255) NOT NULL,
  PRIMARY KEY (`principal_name`,`registered_client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `authorizationconsent`
--

DROP TABLE IF EXISTS `authorizationconsent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorizationconsent` (
  `registeredClientId` varchar(255) NOT NULL,
  `principalName` varchar(255) NOT NULL,
  `authorities` varchar(1000) NOT NULL,
  PRIMARY KEY (`registeredClientId`,`principalName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `client_id_issued_at` datetime(6) DEFAULT NULL,
  `client_secret_expires_at` datetime(6) DEFAULT NULL,
  `authorization_grant_types` varchar(1000) DEFAULT NULL,
  `client_authentication_methods` varchar(1000) DEFAULT NULL,
  `post_logout_redirect_uris` varchar(1000) DEFAULT NULL,
  `redirect_uris` varchar(1000) DEFAULT NULL,
  `scopes` varchar(1000) DEFAULT NULL,
  `client_settings` varchar(2000) DEFAULT NULL,
  `token_settings` varchar(2000) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



