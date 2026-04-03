/*M!999999\- enable the sandbox mode */
-- MariaDB dump 10.19-11.8.6-MariaDB, for debian-linux-gnu (x86_64)
-- -- Host: localhost Database: kanboard
-- ------------------------------------------------------
-- Server version 11.8.6-MariaDB-ubu2404
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;
-- -- Table structure for table action_has_params
--
DROP TABLE IF EXISTS action_has_params;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE action_has_params ( id int(11) NOT NULL AUTO_INCREMENT, action_id int(11) NOT NULL, name mediumtext NOT NULL, value mediumtext NOT NULL, PRIMARY KEY (id), KEY action_id (action_id), CONSTRAINT action_has_params_ibfk_1 FOREIGN KEY (action_id) REFERENCES actions (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table action_has_params
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES action_has_params WRITE;
/*!40000 ALTER TABLE action_has_params DISABLE KEYS */;
/*!40000 ALTER TABLE action_has_params ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table actions
--
DROP TABLE IF EXISTS actions;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE actions ( id int(11) NOT NULL AUTO_INCREMENT, project_id int(11) NOT NULL, event_name mediumtext NOT NULL, action_name mediumtext NOT NULL, PRIMARY KEY (id), KEY project_id (project_id), CONSTRAINT actions_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table actions
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES actions WRITE;
/*!40000 ALTER TABLE actions DISABLE KEYS */;
/*!40000 ALTER TABLE actions ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table column_has_move_restrictions
--
DROP TABLE IF EXISTS column_has_move_restrictions;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE column_has_move_restrictions ( restriction_id int(11) NOT NULL AUTO_INCREMENT, project_id int(11) NOT NULL, role_id int(11) NOT NULL, src_column_id int(11) NOT NULL, dst_column_id int(11) NOT NULL, only_assigned tinyint(1) DEFAULT 0, PRIMARY KEY (restriction_id), UNIQUE KEY role_id (role_id,src_column_id,dst_column_id), KEY project_id (project_id), KEY src_column_id (src_column_id), KEY dst_column_id (dst_column_id), CONSTRAINT column_has_move_restrictions_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE, CONSTRAINT column_has_move_restrictions_ibfk_2 FOREIGN KEY (role_id) REFERENCES project_has_roles (role_id) ON DELETE CASCADE, CONSTRAINT column_has_move_restrictions_ibfk_3 FOREIGN KEY (src_column_id) REFERENCES columns (id) ON DELETE CASCADE, CONSTRAINT column_has_move_restrictions_ibfk_4 FOREIGN KEY (dst_column_id) REFERENCES columns (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table column_has_move_restrictions
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES column_has_move_restrictions WRITE;
/*!40000 ALTER TABLE column_has_move_restrictions DISABLE KEYS */;
/*!40000 ALTER TABLE column_has_move_restrictions ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table column_has_restrictions
--
DROP TABLE IF EXISTS column_has_restrictions;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE column_has_restrictions ( restriction_id int(11) NOT NULL AUTO_INCREMENT, project_id int(11) NOT NULL, role_id int(11) NOT NULL, column_id int(11) NOT NULL, rule varchar(191) NOT NULL, PRIMARY KEY (restriction_id), UNIQUE KEY role_id (role_id,column_id,rule), KEY project_id (project_id), KEY column_id (column_id), CONSTRAINT column_has_restrictions_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE, CONSTRAINT column_has_restrictions_ibfk_2 FOREIGN KEY (role_id) REFERENCES project_has_roles (role_id) ON DELETE CASCADE, CONSTRAINT column_has_restrictions_ibfk_3 FOREIGN KEY (column_id) REFERENCES columns (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table column_has_restrictions
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES column_has_restrictions WRITE;
/*!40000 ALTER TABLE column_has_restrictions DISABLE KEYS */;
/*!40000 ALTER TABLE column_has_restrictions ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table columns
--
DROP TABLE IF EXISTS columns;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE columns ( id int(11) NOT NULL AUTO_INCREMENT, title varchar(191) NOT NULL, position int(11) NOT NULL, project_id int(11) NOT NULL, task_limit int(11) DEFAULT 0, description mediumtext DEFAULT NULL, hide_in_dashboard int(11) NOT NULL DEFAULT 0, PRIMARY KEY (id), UNIQUE KEY idx_title_project (title,project_id), KEY columns_project_idx (project_id), CONSTRAINT columns_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table columns
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES columns WRITE;
/*!40000 ALTER TABLE columns DISABLE KEYS */;
INSERT INTO columns VALUES (1,'Backlog',1,1,0,'',0), (2,'Ready',2,1,0,'',0), (3,'Work in progress',3,1,0,'',0), (4,'Done',4,1,0,'',0), (5,'Backlog',1,2,0,'',0), (6,'Ready',2,2,0,'',0), (7,'Work in progress',3,2,0,'',0), (8,'Done',4,2,0,'',0);
/*!40000 ALTER TABLE columns ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table comments
--
DROP TABLE IF EXISTS comments;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE comments ( id int(11) NOT NULL AUTO_INCREMENT, task_id int(11) NOT NULL, user_id int(11) DEFAULT 0, date_creation bigint(20) DEFAULT NULL, comment mediumtext DEFAULT NULL, reference varchar(191) DEFAULT '', date_modification bigint(20) DEFAULT NULL, visibility varchar(25) NOT NULL DEFAULT 'app-user', PRIMARY KEY (id), KEY user_id (user_id), KEY comments_reference_idx (reference), KEY comments_task_idx (task_id), CONSTRAINT comments_ibfk_1 FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table comments
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES comments WRITE;
/*!40000 ALTER TABLE comments DISABLE KEYS */;
/*!40000 ALTER TABLE comments ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table currencies
--
DROP TABLE IF EXISTS currencies;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE currencies ( currency char(3) NOT NULL, rate float DEFAULT 0, UNIQUE KEY currency (currency) )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table currencies
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES currencies WRITE;
/*!40000 ALTER TABLE currencies DISABLE KEYS */;
/*!40000 ALTER TABLE currencies ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table custom_filters
--
DROP TABLE IF EXISTS custom_filters;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE custom_filters ( id int(11) NOT NULL AUTO_INCREMENT, filter mediumtext NOT NULL, project_id int(11) NOT NULL, user_id int(11) NOT NULL, name mediumtext NOT NULL, is_shared tinyint(1) DEFAULT 0, append tinyint(1) DEFAULT 0, PRIMARY KEY (id), KEY project_id (project_id), KEY user_id (user_id), CONSTRAINT custom_filters_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE, CONSTRAINT custom_filters_ibfk_2 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table custom_filters
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES custom_filters WRITE;
/*!40000 ALTER TABLE custom_filters DISABLE KEYS */;
/*!40000 ALTER TABLE custom_filters ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table group_has_users
--
DROP TABLE IF EXISTS group_has_users;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE group_has_users ( group_id int(11) NOT NULL, user_id int(11) NOT NULL, UNIQUE KEY group_id (group_id,user_id), KEY user_id (user_id), CONSTRAINT group_has_users_ibfk_1 FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE, CONSTRAINT group_has_users_ibfk_2 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table group_has_users
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES group_has_users WRITE;
/*!40000 ALTER TABLE group_has_users DISABLE KEYS */;
/*!40000 ALTER TABLE group_has_users ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table groups
--
DROP TABLE IF EXISTS groups;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE groups ( id int(11) NOT NULL AUTO_INCREMENT, external_id varchar(255) DEFAULT '', name varchar(191) NOT NULL, PRIMARY KEY (id), UNIQUE KEY name (name) )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table groups
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES groups WRITE;
/*!40000 ALTER TABLE groups DISABLE KEYS */;
/*!40000 ALTER TABLE groups ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table invites
--
DROP TABLE IF EXISTS invites;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE invites ( email varchar(191) NOT NULL, project_id int(11) NOT NULL, token varchar(191) NOT NULL, PRIMARY KEY (email,token) )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table invites
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES invites WRITE;
/*!40000 ALTER TABLE invites DISABLE KEYS */;
/*!40000 ALTER TABLE invites ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table last_logins
--
DROP TABLE IF EXISTS last_logins;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE last_logins ( id int(11) NOT NULL AUTO_INCREMENT, auth_type varchar(25) DEFAULT NULL, user_id int(11) DEFAULT NULL, ip varchar(45) DEFAULT NULL, user_agent varchar(255) DEFAULT NULL, date_creation bigint(20) DEFAULT NULL, PRIMARY KEY (id), KEY user_id (user_id), CONSTRAINT last_logins_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE )
    ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table last_logins
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES last_logins WRITE;
/*!40000 ALTER TABLE last_logins DISABLE KEYS */;
INSERT INTO last_logins VALUES (1,'Database',1,'172.18.0.1','Mozilla/5.0 (Windows NT 10.0;
Win64;
x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36',1773411018), (2,'Database',1,'172.18.0.1','Mozilla/5.0 (Windows NT 10.0;
Win64;
x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36',1773412411);
/*!40000 ALTER TABLE last_logins ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table links
--
DROP TABLE IF EXISTS links;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE links ( id int(11) NOT NULL AUTO_INCREMENT, label varchar(191) NOT NULL, opposite_id int(11) DEFAULT 0, PRIMARY KEY (id), UNIQUE KEY label (label) )
    ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table links
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES links WRITE;
/*!40000 ALTER TABLE links DISABLE KEYS */;
INSERT INTO links VALUES (1,'relates to',0), (2,'blocks',3), (3,'is blocked by',2), (4,'duplicates',5), (5,'is duplicated by',4), (6,'is a parent of',7), (7,'is a child of',6), (8,'is a milestone of',9), (9,'targets milestone',8), (10,'is fixed by',11), (11,'fixes',10);
/*!40000 ALTER TABLE links ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table password_reset
--
DROP TABLE IF EXISTS password_reset;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE password_reset ( token varchar(80) NOT NULL, user_id int(11) NOT NULL, date_expiration int(11) NOT NULL, date_creation int(11) NOT NULL, ip varchar(45) NOT NULL, user_agent varchar(255) NOT NULL, is_active tinyint(1) NOT NULL, PRIMARY KEY (token), KEY user_id (user_id), CONSTRAINT password_reset_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table password_reset
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES password_reset WRITE;
/*!40000 ALTER TABLE password_reset DISABLE KEYS */;
/*!40000 ALTER TABLE password_reset ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table plugin_schema_versions
--
DROP TABLE IF EXISTS plugin_schema_versions;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE plugin_schema_versions ( plugin varchar(80) NOT NULL, version int(11) NOT NULL DEFAULT 0, PRIMARY KEY (plugin) )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table plugin_schema_versions
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES plugin_schema_versions WRITE;
/*!40000 ALTER TABLE plugin_schema_versions DISABLE KEYS */;
/*!40000 ALTER TABLE plugin_schema_versions ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table predefined_task_descriptions
--
DROP TABLE IF EXISTS predefined_task_descriptions;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE predefined_task_descriptions ( id int(11) NOT NULL AUTO_INCREMENT, project_id int(11) NOT NULL, title mediumtext NOT NULL, description mediumtext NOT NULL, PRIMARY KEY (id), KEY project_id (project_id), CONSTRAINT predefined_task_descriptions_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table predefined_task_descriptions
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES predefined_task_descriptions WRITE;
/*!40000 ALTER TABLE predefined_task_descriptions DISABLE KEYS */;
/*!40000 ALTER TABLE predefined_task_descriptions ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_activities
--
DROP TABLE IF EXISTS project_activities;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_activities ( id int(11) NOT NULL AUTO_INCREMENT, date_creation bigint(20) DEFAULT NULL, event_name mediumtext NOT NULL, creator_id int(11) DEFAULT NULL, project_id int(11) DEFAULT NULL, task_id int(11) DEFAULT NULL, data mediumtext DEFAULT NULL, PRIMARY KEY (id), KEY creator_id (creator_id), KEY project_id (project_id), KEY task_id (task_id), CONSTRAINT project_activities_ibfk_1 FOREIGN KEY (creator_id) REFERENCES users (id) ON DELETE CASCADE, CONSTRAINT project_activities_ibfk_2 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE, CONSTRAINT project_activities_ibfk_3 FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_activities
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_activities WRITE;
/*!40000 ALTER TABLE project_activities DISABLE KEYS */;
/*!40000 ALTER TABLE project_activities ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_daily_column_stats
--
DROP TABLE IF EXISTS project_daily_column_stats;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_daily_column_stats ( id int(11) NOT NULL AUTO_INCREMENT, day char(10) NOT NULL, project_id int(11) NOT NULL, column_id int(11) NOT NULL, total int(11) NOT NULL DEFAULT 0, score int(11) NOT NULL DEFAULT 0, PRIMARY KEY (id), UNIQUE KEY project_daily_column_stats_idx (day,project_id,column_id), KEY column_id (column_id), KEY project_id (project_id), CONSTRAINT project_daily_column_stats_ibfk_1 FOREIGN KEY (column_id) REFERENCES columns (id) ON DELETE CASCADE, CONSTRAINT project_daily_column_stats_ibfk_2 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_daily_column_stats
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_daily_column_stats WRITE;
/*!40000 ALTER TABLE project_daily_column_stats DISABLE KEYS */;
/*!40000 ALTER TABLE project_daily_column_stats ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_daily_stats
--
DROP TABLE IF EXISTS project_daily_stats;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_daily_stats ( id int(11) NOT NULL AUTO_INCREMENT, day char(10) NOT NULL, project_id int(11) NOT NULL, avg_lead_time int(11) NOT NULL DEFAULT 0, avg_cycle_time int(11) NOT NULL DEFAULT 0, PRIMARY KEY (id), UNIQUE KEY project_daily_stats_idx (day,project_id), KEY project_id (project_id), CONSTRAINT project_daily_stats_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_daily_stats
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_daily_stats WRITE;
/*!40000 ALTER TABLE project_daily_stats DISABLE KEYS */;
/*!40000 ALTER TABLE project_daily_stats ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_has_categories
--
DROP TABLE IF EXISTS project_has_categories;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_has_categories ( id int(11) NOT NULL AUTO_INCREMENT, name varchar(191) NOT NULL, project_id int(11) NOT NULL, description mediumtext DEFAULT NULL, color_id varchar(50) DEFAULT NULL, PRIMARY KEY (id), UNIQUE KEY idx_project_category (project_id,name), KEY categories_project_idx (project_id), CONSTRAINT project_has_categories_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_has_categories
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_has_categories WRITE;
/*!40000 ALTER TABLE project_has_categories DISABLE KEYS */;
/*!40000 ALTER TABLE project_has_categories ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_has_files
--
DROP TABLE IF EXISTS project_has_files;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_has_files ( id int(11) NOT NULL AUTO_INCREMENT, project_id int(11) NOT NULL, name mediumtext NOT NULL, path mediumtext NOT NULL, is_image tinyint(1) DEFAULT 0, size int(11) NOT NULL DEFAULT 0, user_id int(11) NOT NULL DEFAULT 0, date int(11) NOT NULL DEFAULT 0, PRIMARY KEY (id), KEY project_id (project_id), CONSTRAINT project_has_files_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_has_files
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_has_files WRITE;
/*!40000 ALTER TABLE project_has_files DISABLE KEYS */;
/*!40000 ALTER TABLE project_has_files ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_has_groups
--
DROP TABLE IF EXISTS project_has_groups;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_has_groups ( group_id int(11) NOT NULL, project_id int(11) NOT NULL, role varchar(255) NOT NULL, UNIQUE KEY group_id (group_id,project_id), KEY project_id (project_id), CONSTRAINT project_has_groups_ibfk_1 FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE, CONSTRAINT project_has_groups_ibfk_2 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_has_groups
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_has_groups WRITE;
/*!40000 ALTER TABLE project_has_groups DISABLE KEYS */;
/*!40000 ALTER TABLE project_has_groups ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_has_metadata
--
DROP TABLE IF EXISTS project_has_metadata;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_has_metadata ( project_id int(11) NOT NULL, name varchar(50) NOT NULL, value varchar(255) DEFAULT '', changed_by int(11) NOT NULL DEFAULT 0, changed_on int(11) NOT NULL DEFAULT 0, UNIQUE KEY project_id (project_id,name), CONSTRAINT project_has_metadata_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_has_metadata
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_has_metadata WRITE;
/*!40000 ALTER TABLE project_has_metadata DISABLE KEYS */;
/*!40000 ALTER TABLE project_has_metadata ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_has_notification_types
--
DROP TABLE IF EXISTS project_has_notification_types;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_has_notification_types ( id int(11) NOT NULL AUTO_INCREMENT, project_id int(11) NOT NULL, notification_type varchar(50) NOT NULL, PRIMARY KEY (id), UNIQUE KEY project_id (project_id,notification_type), CONSTRAINT project_has_notification_types_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_has_notification_types
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_has_notification_types WRITE;
/*!40000 ALTER TABLE project_has_notification_types DISABLE KEYS */;
/*!40000 ALTER TABLE project_has_notification_types ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_has_roles
--
DROP TABLE IF EXISTS project_has_roles;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_has_roles ( role_id int(11) NOT NULL AUTO_INCREMENT, role varchar(191) NOT NULL, project_id int(11) NOT NULL, PRIMARY KEY (role_id), UNIQUE KEY project_id (project_id,role), CONSTRAINT project_has_roles_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_has_roles
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_has_roles WRITE;
/*!40000 ALTER TABLE project_has_roles DISABLE KEYS */;
/*!40000 ALTER TABLE project_has_roles ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_has_users
--
DROP TABLE IF EXISTS project_has_users;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_has_users ( project_id int(11) NOT NULL, user_id int(11) NOT NULL, role varchar(255) NOT NULL, UNIQUE KEY idx_project_user (project_id,user_id), KEY user_id (user_id), CONSTRAINT project_has_users_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE, CONSTRAINT project_has_users_ibfk_2 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_has_users
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_has_users WRITE;
/*!40000 ALTER TABLE project_has_users DISABLE KEYS */;
INSERT INTO project_has_users VALUES (1,1,'project-manager'), (1,4,'project-manager'), (1,5,'project-member'), (1,6,'project-viewer'), (2,1,'project-manager');
/*!40000 ALTER TABLE project_has_users ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table project_role_has_restrictions
--
DROP TABLE IF EXISTS project_role_has_restrictions;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE project_role_has_restrictions ( restriction_id int(11) NOT NULL AUTO_INCREMENT, project_id int(11) NOT NULL, role_id int(11) NOT NULL, rule varchar(191) NOT NULL, PRIMARY KEY (restriction_id), UNIQUE KEY role_id (role_id,rule), KEY project_id (project_id), CONSTRAINT project_role_has_restrictions_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE, CONSTRAINT project_role_has_restrictions_ibfk_2 FOREIGN KEY (role_id) REFERENCES project_has_roles (role_id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table project_role_has_restrictions
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES project_role_has_restrictions WRITE;
/*!40000 ALTER TABLE project_role_has_restrictions DISABLE KEYS */;
/*!40000 ALTER TABLE project_role_has_restrictions ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table projects
--
DROP TABLE IF EXISTS projects;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE projects ( id int(11) NOT NULL AUTO_INCREMENT, name mediumtext NOT NULL, is_active tinyint(4) DEFAULT 1, token varchar(255) DEFAULT NULL, last_modified bigint(20) DEFAULT NULL, is_public tinyint(1) DEFAULT 0, is_private tinyint(1) DEFAULT 0, description mediumtext DEFAULT NULL, identifier varchar(50) DEFAULT '', start_date varchar(10) DEFAULT '', end_date varchar(10) DEFAULT '', owner_id int(11) DEFAULT 0, priority_default int(11) DEFAULT 0, priority_start int(11) DEFAULT 0, priority_end int(11) DEFAULT 3, email mediumtext DEFAULT NULL, predefined_email_subjects mediumtext DEFAULT NULL, per_swimlane_task_limits int(11) NOT NULL DEFAULT 0, task_limit int(11) DEFAULT 0, enable_global_tags tinyint(1) NOT NULL DEFAULT 1, PRIMARY KEY (id) )
    ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table projects
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES projects WRITE;
/*!40000 ALTER TABLE projects DISABLE KEYS */;
INSERT INTO projects VALUES (1,'TP1',1,'',1773411796,0,0,NULL,'','','',1,0,0,3,NULL,NULL,0,0,1), (2,'PP1',1,'',1773411807,0,1,NULL,'','','',1,0,0,3,NULL,NULL,0,0,1);
/*!40000 ALTER TABLE projects ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table remember_me
--
DROP TABLE IF EXISTS remember_me;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE remember_me ( id int(11) NOT NULL AUTO_INCREMENT, user_id int(11) DEFAULT NULL, ip varchar(45) DEFAULT NULL, user_agent varchar(255) DEFAULT NULL, token varchar(255) DEFAULT NULL, sequence varchar(255) DEFAULT NULL, expiration int(11) DEFAULT NULL, date_creation bigint(20) DEFAULT NULL, PRIMARY KEY (id), KEY user_id (user_id), CONSTRAINT remember_me_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE )
    ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table remember_me
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES remember_me WRITE;
/*!40000 ALTER TABLE remember_me DISABLE KEYS */;
INSERT INTO remember_me VALUES (2,1,'172.18.0.1','Mozilla/5.0 (Windows NT 10.0;
Win64;
x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36','680f275a895525a89959feed3f4ce1cf16ffcf4ffa585b44f1afadee67909412','0693909fd856dc18081788b9d7175ff21c05fd2e0a3c1d87bbe09a7539b8',1778596411,1773412411);
/*!40000 ALTER TABLE remember_me ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table schema_version
--
DROP TABLE IF EXISTS schema_version;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE schema_version ( version int(11) DEFAULT 0 )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table schema_version
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES schema_version WRITE;
/*!40000 ALTER TABLE schema_version DISABLE KEYS */;
INSERT INTO schema_version VALUES (139);
/*!40000 ALTER TABLE schema_version ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table sessions
--
DROP TABLE IF EXISTS sessions;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE sessions ( id varchar(191) NOT NULL, expire_at int(11) NOT NULL, data longtext DEFAULT NULL, PRIMARY KEY (id) )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table sessions
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES sessions WRITE;
/*!40000 ALTER TABLE sessions DISABLE KEYS */;
INSERT INTO sessions VALUES ('0267a293d8ce067c7d574ef27b3d3b15',1773416198,'a:6:{s:8:\"csrf_key\";s:64:\"102c102189c3045dd1a0a44cb1d048c89d9caa078e20fb522ec0dc8d3a31d6fe\";s:13:\"hasRememberMe\";b:1;s:4:\"user\";a:24:{s:2:\"id\";i:1;s:8:\"username\";s:5:\"admin\";s:12:\"is_ldap_user\";b:0;s:4:\"name\";N;s:5:\"email\";N;s:9:\"google_id\";N;s:9:\"github_id\";N;s:21:\"notifications_enabled\";i:0;s:8:\"timezone\";N;s:8:\"language\";N;s:18:\"disable_login_form\";i:0;s:19:\"twofactor_activated\";b:0;s:16:\"twofactor_secret\";N;s:5:\"token\";s:0:\"\";s:20:\"notifications_filter\";i:4;s:15:\"nb_failed_login\";i:0;s:20:\"lock_expiration_date\";i:0;s:9:\"gitlab_id\";N;s:4:\"role\";s:9:\"app-admin\";s:9:\"is_active\";i:1;s:11:\"avatar_path\";N;s:16:\"api_access_token\";N;s:6:\"filter\";N;s:5:\"theme\";s:5:\"light\";}s:27:\"postAuthenticationValidated\";b:0;s:20:\"hasSubtaskInProgress\";b:0;s:5:\"flash\";a:0:{}}');
/*!40000 ALTER TABLE sessions ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table settings
--
DROP TABLE IF EXISTS settings;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE settings ( option varchar(100) NOT NULL, value text DEFAULT NULL, changed_by int(11) NOT NULL DEFAULT 0, changed_on int(11) NOT NULL DEFAULT 0, PRIMARY KEY (option) )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table settings
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES settings WRITE;
/*!40000 ALTER TABLE settings DISABLE KEYS */;
INSERT INTO settings VALUES ('api_token','9997c2070525b0f2c87231d82f184fd0f98c27b3c9fb7d275c33605d97d7',0,0), ('application_currency','USD',0,0), ('application_date_format','m/d/Y',0,0), ('application_language','en_US',0,0), ('application_stylesheet','',0,0), ('application_timezone','UTC',0,0), ('application_url','',0,0), ('board_columns','',0,0), ('board_highlight_period','172800',0,0), ('board_private_refresh_interval','10',0,0), ('board_public_refresh_interval','60',0,0), ('calendar_project_tasks','date_started',0,0), ('calendar_user_subtasks_time_tracking','0',0,0), ('calendar_user_tasks','date_started',0,0), ('cfd_include_closed_tasks','1',0,0), ('default_color','yellow',0,0), ('integration_gravatar','0',0,0), ('password_reset','1',0,0), ('project_categories','',0,0), ('subtask_restriction','0',0,0), ('subtask_time_tracking','1',0,0), ('webhook_token','769522a75189d9900206cb48e1af8556738f7f2ca99887b59b4119a23c73',0,0), ('webhook_url','',0,0);
/*!40000 ALTER TABLE settings ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table subtask_time_tracking
--
DROP TABLE IF EXISTS subtask_time_tracking;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE subtask_time_tracking ( id int(11) NOT NULL AUTO_INCREMENT, user_id int(11) NOT NULL, subtask_id int(11) NOT NULL, start bigint(20) DEFAULT NULL, end bigint(20) DEFAULT NULL, time_spent float DEFAULT 0, PRIMARY KEY (id), KEY user_id (user_id), KEY subtask_id (subtask_id), CONSTRAINT subtask_time_tracking_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE, CONSTRAINT subtask_time_tracking_ibfk_2 FOREIGN KEY (subtask_id) REFERENCES subtasks (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table subtask_time_tracking
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES subtask_time_tracking WRITE;
/*!40000 ALTER TABLE subtask_time_tracking DISABLE KEYS */;
/*!40000 ALTER TABLE subtask_time_tracking ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table subtasks
--
DROP TABLE IF EXISTS subtasks;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE subtasks ( id int(11) NOT NULL AUTO_INCREMENT, title mediumtext NOT NULL, status int(11) DEFAULT 0, time_estimated float DEFAULT NULL, time_spent float DEFAULT NULL, task_id int(11) NOT NULL, user_id int(11) DEFAULT NULL, position int(11) DEFAULT 1, PRIMARY KEY (id), KEY subtasks_task_idx (task_id), CONSTRAINT subtasks_ibfk_1 FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table subtasks
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES subtasks WRITE;
/*!40000 ALTER TABLE subtasks DISABLE KEYS */;
/*!40000 ALTER TABLE subtasks ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table swimlanes
--
DROP TABLE IF EXISTS swimlanes;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE swimlanes ( id int(11) NOT NULL AUTO_INCREMENT, name varchar(191) NOT NULL, position int(11) DEFAULT 1, is_active int(11) DEFAULT 1, project_id int(11) DEFAULT NULL, description mediumtext DEFAULT NULL, task_limit int(11) DEFAULT 0, PRIMARY KEY (id), UNIQUE KEY name (name,project_id), KEY swimlanes_project_idx (project_id), CONSTRAINT swimlanes_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table swimlanes
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES swimlanes WRITE;
/*!40000 ALTER TABLE swimlanes DISABLE KEYS */;
INSERT INTO swimlanes VALUES (1,'Default swimlane',1,1,1,'',0), (2,'Default swimlane',1,1,2,'',0);
/*!40000 ALTER TABLE swimlanes ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table tags
--
DROP TABLE IF EXISTS tags;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE tags ( id int(11) NOT NULL AUTO_INCREMENT, name varchar(191) NOT NULL, project_id int(11) NOT NULL, color_id varchar(50) DEFAULT NULL, PRIMARY KEY (id), UNIQUE KEY project_id (project_id,name) )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table tags
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES tags WRITE;
/*!40000 ALTER TABLE tags DISABLE KEYS */;
/*!40000 ALTER TABLE tags ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table task_has_external_links
--
DROP TABLE IF EXISTS task_has_external_links;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE task_has_external_links ( id int(11) NOT NULL AUTO_INCREMENT, link_type varchar(100) NOT NULL, dependency varchar(100) NOT NULL, title mediumtext NOT NULL, url mediumtext NOT NULL, date_creation int(11) NOT NULL, date_modification int(11) NOT NULL, task_id int(11) NOT NULL, creator_id int(11) DEFAULT 0, PRIMARY KEY (id), KEY task_id (task_id), CONSTRAINT task_has_external_links_ibfk_1 FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table task_has_external_links
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES task_has_external_links WRITE;
/*!40000 ALTER TABLE task_has_external_links DISABLE KEYS */;
/*!40000 ALTER TABLE task_has_external_links ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table task_has_files
--
DROP TABLE IF EXISTS task_has_files;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE task_has_files ( id int(11) NOT NULL AUTO_INCREMENT, name mediumtext NOT NULL, path mediumtext NOT NULL, is_image tinyint(1) DEFAULT 0, task_id int(11) NOT NULL, date bigint(20) DEFAULT NULL, user_id int(11) NOT NULL DEFAULT 0, size int(11) NOT NULL DEFAULT 0, PRIMARY KEY (id), KEY files_task_idx (task_id), CONSTRAINT task_has_files_ibfk_1 FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table task_has_files
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES task_has_files WRITE;
/*!40000 ALTER TABLE task_has_files DISABLE KEYS */;
/*!40000 ALTER TABLE task_has_files ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table task_has_links
--
DROP TABLE IF EXISTS task_has_links;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE task_has_links ( id int(11) NOT NULL AUTO_INCREMENT, link_id int(11) NOT NULL, task_id int(11) NOT NULL, opposite_task_id int(11) NOT NULL, PRIMARY KEY (id), UNIQUE KEY task_has_links_unique (link_id,task_id,opposite_task_id), KEY opposite_task_id (opposite_task_id), KEY task_has_links_task_index (task_id), CONSTRAINT task_has_links_ibfk_1 FOREIGN KEY (link_id) REFERENCES links (id) ON DELETE CASCADE, CONSTRAINT task_has_links_ibfk_2 FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE, CONSTRAINT task_has_links_ibfk_3 FOREIGN KEY (opposite_task_id) REFERENCES tasks (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table task_has_links
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES task_has_links WRITE;
/*!40000 ALTER TABLE task_has_links DISABLE KEYS */;
/*!40000 ALTER TABLE task_has_links ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table task_has_metadata
--
DROP TABLE IF EXISTS task_has_metadata;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE task_has_metadata ( task_id int(11) NOT NULL, name varchar(50) NOT NULL, value varchar(255) DEFAULT '', changed_by int(11) NOT NULL DEFAULT 0, changed_on int(11) NOT NULL DEFAULT 0, UNIQUE KEY task_id (task_id,name), CONSTRAINT task_has_metadata_ibfk_1 FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table task_has_metadata
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES task_has_metadata WRITE;
/*!40000 ALTER TABLE task_has_metadata DISABLE KEYS */;
/*!40000 ALTER TABLE task_has_metadata ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table task_has_tags
--
DROP TABLE IF EXISTS task_has_tags;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE task_has_tags ( task_id int(11) NOT NULL, tag_id int(11) NOT NULL, UNIQUE KEY tag_id (tag_id,task_id), KEY task_id (task_id), CONSTRAINT task_has_tags_ibfk_1 FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE, CONSTRAINT task_has_tags_ibfk_2 FOREIGN KEY (tag_id) REFERENCES tags (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table task_has_tags
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES task_has_tags WRITE;
/*!40000 ALTER TABLE task_has_tags DISABLE KEYS */;
/*!40000 ALTER TABLE task_has_tags ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table tasks
--
DROP TABLE IF EXISTS tasks;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE tasks ( id int(11) NOT NULL AUTO_INCREMENT, title mediumtext NOT NULL, description mediumtext DEFAULT NULL, date_creation bigint(20) DEFAULT NULL, date_completed bigint(20) DEFAULT NULL, date_due bigint(20) DEFAULT NULL, color_id varchar(50) DEFAULT NULL, project_id int(11) NOT NULL, column_id int(11) NOT NULL, owner_id int(11) DEFAULT 0, position int(11) DEFAULT NULL, score int(11) DEFAULT NULL, is_active tinyint(4) DEFAULT 1, category_id int(11) DEFAULT 0, creator_id int(11) DEFAULT 0, date_modification int(11) DEFAULT 0, reference varchar(191) DEFAULT '', date_started bigint(20) DEFAULT NULL, time_spent float DEFAULT 0, time_estimated float DEFAULT 0, swimlane_id int(11) NOT NULL, date_moved bigint(20) DEFAULT NULL, recurrence_status int(11) NOT NULL DEFAULT 0, recurrence_trigger int(11) NOT NULL DEFAULT 0, recurrence_factor int(11) NOT NULL DEFAULT 0, recurrence_timeframe int(11) NOT NULL DEFAULT 0, recurrence_basedate int(11) NOT NULL DEFAULT 0, recurrence_parent int(11) DEFAULT NULL, recurrence_child int(11) DEFAULT NULL, priority int(11) DEFAULT 0, external_provider varchar(255) DEFAULT NULL, external_uri varchar(255) DEFAULT NULL, PRIMARY KEY (id), KEY idx_task_active (is_active), KEY column_id (column_id), KEY tasks_reference_idx (reference), KEY tasks_project_idx (project_id), KEY tasks_swimlane_ibfk_1 (swimlane_id), CONSTRAINT tasks_ibfk_1 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE, CONSTRAINT tasks_ibfk_2 FOREIGN KEY (column_id) REFERENCES columns (id) ON DELETE CASCADE, CONSTRAINT tasks_swimlane_ibfk_1 FOREIGN KEY (swimlane_id) REFERENCES swimlanes (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table tasks
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES tasks WRITE;
/*!40000 ALTER TABLE tasks DISABLE KEYS */;
/*!40000 ALTER TABLE tasks ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table transitions
--
DROP TABLE IF EXISTS transitions;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE transitions ( id int(11) NOT NULL AUTO_INCREMENT, user_id int(11) NOT NULL, project_id int(11) NOT NULL, task_id int(11) NOT NULL, src_column_id int(11) NOT NULL, dst_column_id int(11) NOT NULL, date bigint(20) DEFAULT NULL, time_spent int(11) DEFAULT 0, PRIMARY KEY (id), KEY src_column_id (src_column_id), KEY dst_column_id (dst_column_id), KEY transitions_task_index (task_id), KEY transitions_project_index (project_id), KEY transitions_user_index (user_id), CONSTRAINT transitions_ibfk_1 FOREIGN KEY (src_column_id) REFERENCES columns (id) ON DELETE CASCADE, CONSTRAINT transitions_ibfk_2 FOREIGN KEY (dst_column_id) REFERENCES columns (id) ON DELETE CASCADE, CONSTRAINT transitions_ibfk_3 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE, CONSTRAINT transitions_ibfk_4 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE, CONSTRAINT transitions_ibfk_5 FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table transitions
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES transitions WRITE;
/*!40000 ALTER TABLE transitions DISABLE KEYS */;
/*!40000 ALTER TABLE transitions ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table user_has_metadata
--
DROP TABLE IF EXISTS user_has_metadata;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE user_has_metadata ( user_id int(11) NOT NULL, name varchar(50) NOT NULL, value varchar(255) DEFAULT '', changed_by int(11) NOT NULL DEFAULT 0, changed_on int(11) NOT NULL DEFAULT 0, UNIQUE KEY user_id (user_id,name), CONSTRAINT user_has_metadata_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table user_has_metadata
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES user_has_metadata WRITE;
/*!40000 ALTER TABLE user_has_metadata DISABLE KEYS */;
/*!40000 ALTER TABLE user_has_metadata ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table user_has_notification_types
--
DROP TABLE IF EXISTS user_has_notification_types;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE user_has_notification_types ( id int(11) NOT NULL AUTO_INCREMENT, user_id int(11) NOT NULL, notification_type varchar(50) DEFAULT NULL, PRIMARY KEY (id), UNIQUE KEY user_has_notification_types_user_idx (user_id,notification_type), CONSTRAINT user_has_notification_types_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table user_has_notification_types
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES user_has_notification_types WRITE;
/*!40000 ALTER TABLE user_has_notification_types DISABLE KEYS */;
/*!40000 ALTER TABLE user_has_notification_types ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table user_has_notifications
--
DROP TABLE IF EXISTS user_has_notifications;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE user_has_notifications ( user_id int(11) NOT NULL, project_id int(11) NOT NULL, UNIQUE KEY user_has_notifications_unique_idx (user_id,project_id), KEY user_has_notifications_ibfk_2 (project_id), CONSTRAINT user_has_notifications_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE, CONSTRAINT user_has_notifications_ibfk_2 FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table user_has_notifications
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES user_has_notifications WRITE;
/*!40000 ALTER TABLE user_has_notifications DISABLE KEYS */;
/*!40000 ALTER TABLE user_has_notifications ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table user_has_unread_notifications
--
DROP TABLE IF EXISTS user_has_unread_notifications;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE user_has_unread_notifications ( id int(11) NOT NULL AUTO_INCREMENT, user_id int(11) NOT NULL, date_creation bigint(20) NOT NULL, event_name mediumtext NOT NULL, event_data mediumtext NOT NULL, PRIMARY KEY (id), KEY user_id (user_id), CONSTRAINT user_has_unread_notifications_ibfk_1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE )
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table user_has_unread_notifications
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES user_has_unread_notifications WRITE;
/*!40000 ALTER TABLE user_has_unread_notifications DISABLE KEYS */;
/*!40000 ALTER TABLE user_has_unread_notifications ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
-- -- Table structure for table users
--
DROP TABLE IF EXISTS users;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE users ( id int(11) NOT NULL AUTO_INCREMENT, username varchar(191) NOT NULL, password varchar(255) DEFAULT NULL, is_ldap_user tinyint(1) DEFAULT 0, name varchar(255) DEFAULT NULL, email varchar(255) DEFAULT NULL, google_id varchar(30) DEFAULT NULL, github_id varchar(30) DEFAULT NULL, notifications_enabled tinyint(1) DEFAULT 0, timezone varchar(50) DEFAULT NULL, language varchar(11) DEFAULT NULL, disable_login_form tinyint(1) DEFAULT 0, twofactor_activated tinyint(1) DEFAULT 0, twofactor_secret char(16) DEFAULT NULL, token varchar(255) DEFAULT '', notifications_filter int(11) DEFAULT 4, nb_failed_login int(11) DEFAULT 0, lock_expiration_date bigint(20) DEFAULT NULL, gitlab_id int(11) DEFAULT NULL, role varchar(25) NOT NULL DEFAULT 'app-user', is_active tinyint(1) DEFAULT 1, avatar_path varchar(255) DEFAULT NULL, api_access_token varchar(255) DEFAULT NULL, filter mediumtext DEFAULT NULL, theme varchar(50) NOT NULL DEFAULT 'light', PRIMARY KEY (id), UNIQUE KEY users_username_idx (username) )
    ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
-- -- Dumping data for table users
-- SET @OLD_AUTOCOMMIT=@@AUTOCOMMIT, @@AUTOCOMMIT=0;
LOCK TABLES users WRITE;
/*!40000 ALTER TABLE users DISABLE KEYS */;
INSERT INTO users VALUES (1,'admin','$2y$12$v20YqFOdj1CTd/Kr4NNXAOtRM2E6a3uEK/rX9xj16XDxVLSEDv8Yy',0,NULL,NULL,NULL,NULL,0,NULL,NULL,0,0,NULL,'',4,0,0,NULL,'app-admin',1,NULL,NULL,NULL,'light'), (2,'u_manager','$2y$12$ElzCH6Zi.wM7mjB3UH.0De0uZkJ9sE0JMrVf/lZkEkg.pddOV.YJO',0,'U_MANAGER','',NULL,NULL,0,'','',0,0,NULL,'',4,0,NULL,NULL,'app-manager',1,NULL,NULL,'','light'), (3,'u_user','$2y$12$R416RNEOFJVAaPsStR9b1OJNMoWmveq/fSjWOST6Dyh4BQ5pPdvDS',0,'U_USER','',NULL,NULL,0,'','',0,0,NULL,'',4,0,NULL,NULL,'app-user',1,NULL,NULL,'','light'), (4,'u_pm','$2y$12$yHgabNQgdFMBSQTlilrsmujIswX.1HUMHz.yXQJibYMmmrQIafygC',0,'U_PM','',NULL,NULL,0,'','',0,0,NULL,'',4,0,NULL,NULL,'app-user',1,NULL,NULL,'','light'), (5,'u_mem','$2y$12$DIWVnENggSlxzskS/pWsu.H3dUvKIdnBmQ29X0CQN/1mB9q.sgsGm',0,'U_MEM','',NULL,NULL,0,'','',0,0,NULL,'',4,0,NULL,NULL,'app-user',1,NULL,NULL,'','light'), (6,'u_view','$2y$12$MQ53ktrFkLTWoJ8WvD.vBeyAyrnscUwCxPoN3hgh9T39f3LseygbO',0,'U_VIEW','',NULL,NULL,0,'','',0,0,NULL,'',4,0,NULL,NULL,'app-user',1,NULL,NULL,'','light'), (7,'u_cust','$2y$12$F7HUGjW8e4fNrPIS8k8GsOsQWoP4fszVkfD2DwaSFXsE6Ubsl0Dtm',0,'U_CUST','',NULL,NULL,0,'','',0,0,NULL,'',4,0,NULL,NULL,'app-user',1,NULL,NULL,'','light');
/*!40000 ALTER TABLE users ENABLE KEYS */;
UNLOCK TABLES;
COMMIT;
SET AUTOCOMMIT=1;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;
-- Dump completed on 2026-03-13 15:22:04