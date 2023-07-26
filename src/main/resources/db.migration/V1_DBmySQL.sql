CREATE DATABASE  IF NOT EXISTS `chef_compass` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `chef_compass`;

-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: chef_compass
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allergens`
--

DROP TABLE IF EXISTS `allergens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allergens` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `allergen_name` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allergens`
--

LOCK TABLES `allergens` WRITE;
/*!40000 ALTER TABLE `allergens` DISABLE KEYS */;
INSERT INTO `allergens` VALUES (1,'Dairy'),(2,'Gluten'),(3,'Eggs'),(4,'Fish'),(5,'Peanuts'),(6,'Shellfish'),(7,'Soy'),(8,'Tree Nuts'),(9,'Wheat'),(10,'Sesame'),(11,'Celery'),(12,'Mustard'),(13,'Sulphites'),(14,'Lupin'),(15,'Molluscs');
/*!40000 ALTER TABLE `allergens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `category_name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'appetizers'),(2,'breakfasts'),(3,'mainDishes'),(4,'sideDishes'),(5,'desserts');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `country_name` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Mexico'),(2,'Italy'),(3,'Greece'),(4,'Unknown'),(5,'Various'),(6,'France'),(7,'United States'),(8,'China'),(9,'Thailand'),(10,'Russia'),(11,'Belgium'),(12,'India');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `create_recipes`
--

DROP TABLE IF EXISTS `create_recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `create_recipes` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `user_id` bigint DEFAULT NULL,
                                  `recipe_id` int DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `user_id` (`user_id`),
                                  KEY `recipe_id` (`recipe_id`),
                                  CONSTRAINT `fk_create_table_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`),
                                  CONSTRAINT `fk_create_table_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `create_recipes`
--

LOCK TABLES `create_recipes` WRITE;
/*!40000 ALTER TABLE `create_recipes` DISABLE KEYS */;
/*!40000 ALTER TABLE `create_recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diets`
--

DROP TABLE IF EXISTS `diets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diets` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `diet_name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diets`
--

LOCK TABLES `diets` WRITE;
/*!40000 ALTER TABLE `diets` DISABLE KEYS */;
INSERT INTO `diets` VALUES (1,'vegan'),(2,'vegetarian'),(3,'pescatarian');
/*!40000 ALTER TABLE `diets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_recipes`
--

DROP TABLE IF EXISTS `favorite_recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_recipes` (
                                    `id` bigint NOT NULL AUTO_INCREMENT,
                                    `user_id` bigint DEFAULT NULL,
                                    `recipe_id` int DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `user_id` (`user_id`),
                                    KEY `recipe_id` (`recipe_id`),
                                    CONSTRAINT `fk_favorite_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`),
                                    CONSTRAINT `fk_favorite_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_recipes`
--

LOCK TABLES `favorite_recipes` WRITE;
/*!40000 ALTER TABLE `favorite_recipes` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite_recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredients` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `ingredient_name` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,'Ripe Avocados'),(2,'Lime'),(3,'Red Onion'),(4,'Tomato'),(5,'Fresh Cilantro'),(6,'Jalapeño Pepper'),(7,'Salt'),(8,'Tortilla Chips'),(9,'Cherry Tomatoes'),(10,'Fresh Mozzarella Balls'),(11,'Fresh Basil Leaves'),(12,'Balsamic Glaze'),(13,'Black Pepper'),(14,'Button mushrooms'),(15,'Spinach'),(16,'Feta cheese'),(17,'Garlic clove'),(18,'Olive oil'),(19,'Bread crumbs'),(20,'Baguette'),(21,'Balsamic vinegar'),(22,'Pie crust'),(23,'Eggs'),(24,'Milk'),(25,'Broccoli florets'),(26,'Red bell pepper'),(27,'Mushrooms'),(28,'Cheddar cheese'),(29,'Smoked salmon'),(30,'Cream cheese'),(31,'Fresh dill'),(32,'Lemon juice'),(33,'Shrimp'),(34,'Fresh parsley'),(35,'Skewers'),(36,'All-Purpose Flour'),(37,'Sugar'),(38,'Baking Powder'),(39,'Frozen mixed berries'),(40,'Banana'),(41,'Greek yogurt'),(42,'Almond milk'),(43,'Toppings (granola, sliced fruits, chia seeds, etc.)'),(44,'Assorted fresh fruits (berries, sliced bananas, diced mango, etc.)'),(45,'Granola'),(46,'Honey or maple syrup'),(47,'Whipped cream'),(48,'Powdered sugar'),(49,'Butter'),(50,'Apple'),(51,'Cucumber'),(52,'Pineapple chunks'),(53,'Coconut water or almond milk'),(54,'Chia seeds'),(55,'Bagel'),(56,'Red onion'),(57,'Capers'),(58,'Quinoa'),(59,'Water or vegetable broth'),(60,'Dried fruits (raisins, cranberries, apricots, etc.)'),(61,'Nuts (almonds, walnuts, pecans, etc.)'),(62,'Salted Butter'),(63,'Chocolate'),(64,'Mascarpone Cheese'),(65,'Ladyfingers'),(66,'Espresso Coffee'),(67,'Cocoa Powder'),(68,'Nutmeg'),(69,'Lemon Juice'),(70,'Ladyfinger Biscuits'),(71,'Fresh Strawberries'),(72,'Gelatin Sheets'),(73,'Whipping Cream'),(74,'Water'),(75,'Mint Leaves'),(76,'Vanilla bean'),(77,'Chicken Breast'),(78,'Ginger'),(79,'Soy Sauce'),(80,'Oyster Sauce'),(81,'Sesame Oil'),(82,'Cornstarch'),(83,'Chicken Thighs'),(84,'Thai Chilies'),(85,'Fish Sauce'),(86,'Sour Cream'),(87,'Dijon Mustard'),(88,'Worcestershire Sauce'),(89,'Mussels'),(90,'Shallots'),(91,'Parsley'),(92,'Potatoes'),(93,'Brussels Sprouts'),(94,'Black Pepper'),(95,'Garlic Powder'),(96,'Balsamic Glaze'),(97,'Crushed red pepper flakes'),(98,'Corn kernels'),(99,'Baby spinach leaves'),(100,'Red onion'),(101,'Toasted almonds'),(102,'Green beans'),(103,'Onion powder'),(104,'Yellow cornmeal'),(105,'Bacon slices'),(106,'Maple syrup '),(107,'Cucumber'),(108,'Tomato juice'),(109,'Red wine vinegar'),(110,'lemon wedge'),(111,'Cinnamon'),(114,'¨P.'),(115,'ùù:'),(116,':'),(117,'^pkl'),(118,'okp'),(119,'POK'),(120,'a'),(121,'sz'),(122,'s');
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_allergens`
--

DROP TABLE IF EXISTS `recipe_allergens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe_allergens` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `recipe_id` int DEFAULT NULL,
                                    `allergen_id` int DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `recipe_id` (`recipe_id`),
                                    KEY `allergen_id` (`allergen_id`),
                                    CONSTRAINT `recipe_allergens_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`),
                                    CONSTRAINT `recipe_allergens_ibfk_2` FOREIGN KEY (`allergen_id`) REFERENCES `allergens` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_allergens`
--

LOCK TABLES `recipe_allergens` WRITE;
/*!40000 ALTER TABLE `recipe_allergens` DISABLE KEYS */;
INSERT INTO `recipe_allergens` VALUES (1,2,2),(2,3,2),(3,3,1),(4,7,2),(5,7,1),(6,7,1),(7,9,1),(8,10,2),(9,10,1),(10,10,3),(11,11,2),(12,11,1),(13,11,3),(14,12,1),(15,13,2),(16,13,1),(17,13,3),(18,14,2),(19,14,1),(20,14,3),(21,15,2),(22,15,1),(23,15,3),(24,16,1),(25,20,2),(26,21,1),(27,23,1),(28,24,1),(29,26,1),(30,27,1),(31,28,1),(32,29,1),(33,50,2);
/*!40000 ALTER TABLE `recipe_allergens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_diets`
--

DROP TABLE IF EXISTS `recipe_diets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe_diets` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `recipe_id` int DEFAULT NULL,
                                `diet_id` int DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `recipe_id` (`recipe_id`),
                                KEY `diet_id` (`diet_id`),
                                CONSTRAINT `recipe_diets_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`),
                                CONSTRAINT `recipe_diets_ibfk_2` FOREIGN KEY (`diet_id`) REFERENCES `diets` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_diets`
--

LOCK TABLES `recipe_diets` WRITE;
/*!40000 ALTER TABLE `recipe_diets` DISABLE KEYS */;
INSERT INTO `recipe_diets` VALUES (1,1,2),(2,2,2),(3,4,1),(4,4,2),(5,16,2),(6,17,3),(7,21,2),(8,22,2),(9,22,3),(10,23,2),(11,24,2),(12,25,3),(13,26,2),(14,27,2),(15,28,2),(16,29,2),(17,30,3),(18,21,2),(19,33,2),(20,33,3),(21,34,2),(22,35,1),(23,36,1),(24,37,2),(25,38,2),(26,39,1),(27,40,2),(28,41,2),(29,42,2),(30,43,2),(31,44,2),(32,45,2),(33,46,2),(34,47,2),(35,48,2),(36,49,2);
/*!40000 ALTER TABLE `recipe_diets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `recipe_slug` varchar(255) DEFAULT NULL,
                           `recipe_name` varchar(255) DEFAULT NULL,
                           `category_id` int DEFAULT NULL,
                           `country_id` int DEFAULT NULL,
                           `prep_time` int DEFAULT NULL,
                           `cook_time` int DEFAULT NULL,
                           `price` decimal(38,2) DEFAULT NULL,
                           `imgurl` varchar(255) DEFAULT NULL,
                           `description` TEXT DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `country_id` (`country_id`),
                           KEY `category_id` (`category_id`),
                           CONSTRAINT `recipes_ibfk_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
                           CONSTRAINT `recipes_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (1,'chocolate-cake','Chocolate Cake',5,6,20,30,20.00,'https://images.pexels.com/photos/4110008/pexels-photo-4110008.jpeg','Indulgent and moist chocolate cake, perfect for chocoholics and special occasions.'),(2,'tiramisu','Tiramisu',5,2,30,0,10.00,'https://images.pexels.com/photos/14766327/pexels-photo-14766327.jpeg','Luscious Italian dessert with layers of coffee-soaked ladyfingers and creamy mascarpone cheese, dusted with cocoa.'),(3,'spaghetti-bolognese','Spaghetti Bolognese',3,2,15,60,5.00,'https://images.pexels.com/photos/15500451/pexels-photo-15500451/free-photo-of-nourriture-diner-repas-pates.jpeg','Hearty Italian pasta dish with rich tomato sauce, ground beef, and aromatic herbs.'),(4,'guacamole','Guacamole',1,1,10,0,10.00,'https://images.pexels.com/photos/3535380/pexels-photo-3535380.jpeg','Creamy avocado dip with fresh lime juice, red onion, tomato, cilantro, and jalapeño pepper.'),(5,'chicken-stir-Fry','Chicken Stir Fry',3,8,15,15,15.00,'https://images.pexels.com/photos/16108119/pexels-photo-16108119/free-photo-of-nourriture-poulet-viande-barbecue.jpeg','Quick and healthy dish with tender chicken, crisp vegetables, and a savory stir-fry sauce.'),(6,'scrambled-egg','Scrambled Egg',2,4,5,5,25.00,'https://images.pexels.com/photos/8055162/pexels-photo-8055162.jpeg','Light and fluffy scrambled eggs, perfect for a quick and satisfying breakfast.'),(7,'Apple-Pie','Apple Pie',5,7,30,50,30.00,'https://images.pexels.com/photos/1282279/pexels-photo-1282279.jpeg','Classic dessert with a buttery crust and sweet and tangy apple filling, perfect for special occasions.'),(8,'spicy-thai-basil-chicken','Spicy Thai Basil Chicken',3,9,15,10,15.00,'https://thewoksoflife.com/wp-content/uploads/2016/06/thai-basil-chicken-8.jpg','Fiery stir-fry dish with chicken, Thai basil leaves, chili peppers, and a blend of savory sauces.'),(9,'garlic-mashed-potatoes','Garlic Mashed Potatoes',4,5,15,20,25.00,'https://www.recette-americaine.com/wp-content/uploads/2013/12/mashed-potatoes.jpg','Creamy mashed potatoes infused with aromatic garlic, creating a flavorful side dish.'),(10,'vanilla-cupcakes','Vanilla Cupcakes',5,4,15,20,10.00,'https://images.pexels.com/photos/913134/pexels-photo-913134.jpeg','Soft and moist vanilla-flavored cupcakes crowned with creamy frosting, perfect for special occasions.'),(11,'beef-troganoff','Beef Troganoff',3,10,15,30,55.00,'https://images.pexels.com/photos/16134562/pexels-photo-16134562/free-photo-of-nourriture-diner-viande-delicieux.jpeg','Comforting dish of tender beef strips, mushrooms, and onions cooked in a creamy sauce, ideal for special occasions.'),(12,'caprese-skewers','Caprese Skewers',1,2,15,0,17.00,'https://cdn.loveandlemons.com/wp-content/uploads/2021/08/caprese-skewers-1-746x1024.jpg','Refreshing skewers featuring fresh mozzarella, cherry tomatoes, and basil leaves, drizzled with balsamic glaze.'),(13,'pancakes','Pancakes',2,5,10,15,10.00,'https://images.pexels.com/photos/2516025/pexels-photo-2516025.jpeg','Fluffy and golden breakfast staples, perfect for a leisurely morning meal.'),(14,'chicken-parmesan','Chicken Parmesan',3,7,20,30,150.00,'https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fpublic-assets.meredithcorp.io%2F4b067cd907c6d86384b336405a8c505e%2F168523035350920230527_183149.jpg','Breaded chicken cutlets topped with marinara sauce, melted cheese, and served with spaghetti.'),(15,'chocolate-chip-cookies','Chocolate Chip Cookies',5,7,15,10,20.00,'https://images.pexels.com/photos/189536/pexels-photo-189536.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2','Irresistible homemade cookies loaded with chunks of rich chocolate for chocoholics and special occasions.'),(16,'roasted-brussels-sprouts','Roasted Brussels Sprouts',4,5,10,25,50.00,'https://cookieandkate.com/images/2019/11/best-roasted-brussels-sprouts-recipe-1-768x1154.jpg','Crispy and flavorful roasted Brussels sprouts seasoned with olive oil, garlic, and a touch of salt.'),(17,'sushi-rolls','Sushi Rolls',3,6,30,0,10.00,'https://images.pexels.com/photos/8951148/pexels-photo-8951148.jpeg','Traditional Japanese rolls made with vinegared rice, fresh seafood or vegetables, and wrapped in seaweed.'),(18,'moules-frites','Moules Frites',3,3,20,15,20.00,'https://static.750g.com/images/640-420/203a157ca1eec1f5271705aa553435f4/adobestock-184713102.jpeg','Classic Belgian dish of steamed mussels served with crispy golden fries.'),(19,'veal-blanquette','Veal Blanquette',3,8,30,120,30.00,'https://assets.afcdn.com/recipe/20190529/93191_w1024h1024c1cx4330cy2886.jpg','Tender veal stew cooked in a creamy white sauce with vegetables, herbs, and aromatic spices.'),(20,'strawberry-charlotte','Strawberry Charlotte',5,6,30,240,15.00,'https://www.spatuladesserts.com/wp-content/uploads/2022/02/Strawberry-charlotte-cake-2170260.jpg','Delightful dessert with layers of sponge cake and fresh strawberries, topped with a creamy filling.'),(21,'Spinach-and-Feta-Stuffed-Mushrooms','Spinach and Feta Stuffed Mushrooms',1,3,20,25,25.00,'https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_680/https://www.appetizeraddiction.com/wp-content/uploads/2018/11/spinach-feta-stuffed-mushrooms-picture.jpg','Delicious button mushrooms stuffed with a flavorful mixture of spinach, feta cheese, and bread crumbs.'),(22,'Tomato-and-Basil-Bruschetta','Tomato and Basil Bruschetta',1,2,15,5,20.00,'https://cookieandkate.com/images/2019/08/tomato-bruschetta-with-balsamic-drizzle-768x1155.jpg','Delicious toasted baguette slices topped with fresh tomatoes, basil, and a drizzle of olive oil.'),(23,'Mini-Vegetable-Quiches','Mini Vegetable Quiches',1,4,20,25,15.00,'https://media.soscuisine.com/images/recettes/large/4081.jpg','Individual-sized quiches filled with a medley of vegetables and savory custard, baked to perfection.'),(24,'Smoked-Salmon-and-Cream-Cheese-Canapés','Smoked Salmon and Cream Cheese Canapés',1,4,15,0,25.00,'https://www.hintofhealthy.com/wp-content/uploads/2021/10/Smoked-Salmon-Canapes-3.jpg','Delicate canapés with creamy herbed cheese and smoked salmon, perfect for elegant occasions.'),(25,'Grilled-Shrimp-Skewers','Grilled Shrimp Skewers',1,4,20,8,35.00,'https://feelgoodfoodie.net/wp-content/uploads/2020/05/Grilled-Shrimp-Skewers-6.jpg','Juicy and flavorful grilled shrimp skewers marinated in a zesty lemon garlic sauce.'),(26,'Vanilla-Crème-Brûlée','Vanilla Crème Brûlée',2,4,20,60,18.00,'https://static01.nyt.com/images/2017/12/13/dining/15COOKING-CREME-BRULEE1/15COOKING-CREME-BRULEE1-articleLarge.jpg','Silky smooth vanilla custard with a caramelized sugar crust, a classic French dessert.'),(27,'Molten-Chocolate-Lava-Cake','Molten Chocolate Lava Cake',2,4,15,12,22.00,'https://www.billyparisi.com/wp-content/uploads/2022/02/lava-cake-1.jpg','Decadent individual chocolate cakes with a warm and gooey chocolate center, a true chocolate lovers delight.'),(28,'Classic Tiramisu','Classic Tiramisu',2,2,30,0,20.00,'https://img.cuisineaz.com/660x660/2016/04/28/i57769-tiramisu-simple.webp','Layered dessert of coffee-soaked ladyfingers and creamy mascarpone cheese, dusted with cocoa powder.'),(29,'Light-and-Creamy-Chocolate-Mousse','Light and Creamy Chocolate Mousse',2,4,20,0,16.00,'https://www.recipetineats.com/wp-content/uploads/2018/09/Chocolate-Mousse_9.jpg','Airy and smooth chocolate mousse with a velvety texture, a delightful treat for chocolate enthusiasts.'),(30,'Mussels-in-Garlic-and-White-Wine-Sauce','Mussels in Garlic and White Wine Sauce',3,6,15,10,18.00,'https://foodandjourneys.net/wp-content/uploads/2023/04/Mussels-in-White-Wine-PIC3.jpg','Fresh mussels cooked in a delicious garlic and white wine sauce, a classic dish from French cuisine.'),(31,'Vegetarian-Spinach-and-Ricotta-Lasagna','Vegetarian Spinach and Ricotta Lasagna',3,2,30,40,22.00,'https://www.simplyrecipes.com/thmb/4VWpZSlGbHzQiJIzJhhl_gXaaLE=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Simply-Recipes-Vegetarian-Spinach-Mushroom-Lasagna-Lead-16-0902d3dd54d3489c8c013a1f147a1878.jpg','Layers of lasagna sheets filled with a savory mixture of spinach, ricotta cheese, and tomato sauce, topped with melted mozzarella cheese.'),(32,'Indian-Chicken-Tikka','Indian Chicken Tikka',3,12,30,40,25.00,'https://cafedelites.com/wp-content/uploads/2018/04/Best-Chicken-Tikka-Masala-IMAGE-1.jpg','Tender chicken pieces marinated in aromatic spices, cooked in a creamy tomato-based sauce, and served with soft and fluffy naan bread.'),(33,'Seafood-Risotto','Seafood Risotto',3,2,15,30,30.00,'https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/seafoodrisotto_5522_16x9.jpg','Creamy and rich risotto cooked with a variety of fresh seafood, resulting in a luxurious and satisfying dish.'),(34,'Stuffed-Bell-Peppers-with-Rice-and-Ground-Meat','Stuffed Bell Peppers with Rice and Ground Meat',3,5,20,45,15.00,'https://thecozycook.com/wp-content/uploads/2022/08/Stuffed-Bell-Peppers-1.jpg','Bell peppers filled with a savory mixture of rice, ground meat, and tomato sauce, topped with melted cheese and baked to perfection.'),(35,'Smoothie Bowl','Smoothie Bowl',2,5,10,0,10.00,'https://www.lesfruitsetlegumesfrais.com/app/uploads/cache/2017/02/r781-2-smoothie-bowl-framboises/1251983193.jpg','A thick and creamy smoothie made with mixed berries, banana, spinach, and Greek yogurt, topped with delicious and wholesome toppings.'),(36,'Fruit Yogurt with Granola','Fruit Yogurt with Granola',2,5,5,0,8.00,'https://images.radio-canada.ca/q_auto,w_844/v1/ici-tele/16x9/savourer-recette-9-octobre-yogourt-aux-fruits-maison.jpg','A delicious and nutritious breakfast bowl consisting of creamy Greek yogurt, a variety of fresh fruits, crunchy granola, and optional sweet drizzle.'),(37,'Fruit-Crepes','Fruit Crepes',2,6,15,20,12.00,'https://img.cuisineaz.com/660x660/2017/02/07/i120939-crepes-aux-fruits-rouges-et-creme-chantilly.webp','Thin and delicate crepes filled with a selection of fresh fruits, topped with whipped cream and a dusting of powdered sugar, creating a delightful breakfast delicacy.'),(38,'Scrambled-Eggs','Scrambled Eggs',2,4,5,5,5.00,'https://www.ptitchef.com/imgupl/recipe/oeufs-brouilles-la-vraie-recette--md-456485p710185.jpg','Light and fluffy scrambled eggs cooked to perfection, offering a simple yet delicious breakfast option that can be customized with various toppings.'),(39,'Green-Smoothie','Green Smoothie',2,4,5,0,8.00,'https://healthyisthenewcool.com/wp-content/uploads/2021/07/DSC06914.jpg','A vibrant and healthy smoothie packed with spinach, fruits, and hydrating coconut water or almond milk, offering a refreshing way to start your day.'),(40,'Salmon-Bagel','Salmon Bagel',2,5,10,0,10.00,'https://feelgoodfoodie.net/wp-content/uploads/2021/03/smoked-salmon-bagel-13.jpg','A classic breakfast combination of a toasted bagel spread with cream cheese, topped with smoked salmon, red onion, capers, and fresh dill, creating a flavorful and filling meal.'),(41,'Quinoa-with-Dried-Fruits','Quinoa with Dried Fruits',2,5,15,15,12.00,'https://chefcuisto.com/files/2019/05/salade-fruits-quinoa-1024x837.jpg','A protein-packed breakfast bowl of cooked quinoa mixed with a variety of dried fruits and nuts, offering a delightful combination of flavors, textures, and nutrients.'),(42,'Sautéed-Garlic-Vegetables','Sautéed Garlic Vegetables',4,5,10,10,8.00,'https://www.allrecipes.com/thmb/Hv6LB4S2e3mk6LEyPryfYcacmkk=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/Garlic-Sauteed-Vegetables-57a3ddca9b2346a0b598e21885c6705e.jpg','A colorful medley of assorted vegetables sautéed with garlic, olive oil, and seasonings, creating a quick and tasty side dish that complements any main course.'),(43,'Cheesy-Corn','Cheesy Corn',4,5,5,15,6.00,'https://i.pinimg.com/564x/6a/8b/fd/6a8bfd809a419fa0387966e0684be2bf.jpg','A creamy and cheesy corn dish made with fresh corn kernels, melted cheddar cheese, and a touch of herbs, delivering a delightful side to complement any meal.'),(44,'Spinach-Salad','Spinach Salad',4,5,10,0,7.00,'https://www.gimmesomeoven.com/wp-content/uploads/2016/09/Apple-Spinach-Recipe-4-1024x1536.jpg','A vibrant salad featuring fresh baby spinach leaves, cherry tomatoes, red onion, feta cheese, and toasted almonds, dressed with a tangy balsamic vinaigrette for a burst of flavors and textures.'),(45,'Potato-Pancakes','Potato Pancakes',4,5,20,10,5.00,'https://cooktoria.com/wp-content/uploads/2022/04/Potato-Pancakes-8-768x1152.jpg','Crispy and golden potato pancakes made from grated potatoes, onion, and seasonings, creating a delicious and satisfying side dish that pairs well with various main courses.'),(46,'Oven-Roasted-GreenBeans','Oven Roasted Green Beans',4,5,5,15,4.00,'https://healthyrecipesblogs.com/wp-content/uploads/2015/04/Roasted-Green-Beans-1-2021-853x640.jpg','Fresh green beans tossed with olive oil and seasonings, then roasted to perfection in the oven, resulting in tender yet slightly crisp green beans with a delightful savory flavor.'),(47,'Cornbread','Cornbread',4,5,10,25,3.00,'https://www.hervecuisine.com/wp-content/uploads/2020/09/cornbread-ou-pain-au-mais.jpeg','A classic cornbread made with a combination of yellow cornmeal, all-purpose flour, milk, and butter, resulting in a moist and flavorful side that pairs perfectly with various meals.'),(48,'Crispy-Bacon','Crispy Bacon',4,5,5,15,4.00,'https://madaboutfood.co/wp-content/uploads/2018/11/2021-07-04-10.04.53-768x1024.jpg','Thin strips of bacon baked in the oven until crispy, offering a savory and irresistible side dish or a versatile ingredient to enhance various recipes.'),(49,'Chilled-Tomato-Soup','Chilled Tomato Soup',4,5,10,0,5.00,'https://www.thespruceeats.com/thmb/FxTobUKOXKRm04EkO8lEJ2atR0A=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/gazpacho-andaluz-spanish-cold-tomato-soup-3083558-hero-01-8dd745933dff4d07924152b22f84ae55.jpg','A refreshing and chilled soup made with ripe tomatoes, cucumber, red bell pepper, and a hint of garlic, offering a burst of summer flavors and a cool respite on hot days.'),(50,'Ranch-Chicken-Tacos','Ranch Chicken Tacos',3,1,20,5,9.99,'https://www.allrecipes.com/thmb/x6Yk0Ls8AvEXE84NIMWAI3FE2SE=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/972962-ranch-chicken-tacos-Deb-C-4x3-1-b9a394dfd01f4a198ca9ed7a32c5e191.jpg','These ranch chicken tacos are a great change from regular Mexican-style tacos. A quick, cool summer dinner made with leftover rotisserie chicken. Naturally, you can use any type of chicken cooked the way you like; just be sure to shred it for real tacos.');
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes_ingredients`
--

DROP TABLE IF EXISTS `recipes_ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes_ingredients` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `recipe_id` int DEFAULT NULL,
                                       `ingredient_id` int DEFAULT NULL,
                                       `quantity` varchar(255) DEFAULT NULL,
                                       PRIMARY KEY (`id`),
                                       KEY `recipe_id` (`recipe_id`),
                                       KEY `ingredient_id` (`ingredient_id`),
                                       CONSTRAINT `recipes_ingredients_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`),
                                       CONSTRAINT `recipes_ingredients_ibfk_2` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=512 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes_ingredients`
--

LOCK TABLES `recipes_ingredients` WRITE;
/*!40000 ALTER TABLE `recipes_ingredients` DISABLE KEYS */;
INSERT INTO `recipes_ingredients` VALUES (1,1,62,'200 gr'),(2,1,65,'200 gr'),(3,1,24,'4'),(4,1,37,'150 gr'),(5,1,40,'80 gr'),(6,2,64,'250 gr'),(7,2,66,'24'),(8,2,67,'250 ml'),(9,2,37,'80 gr'),(10,2,68,'2 tablespoons'),(11,3,42,'300 g'),(12,3,77,'500 g'),(13,3,52,'1'),(14,3,51,'2'),(15,3,70,'400 g'),(16,3,71,'2 tablespoons'),(17,3,80,'100 ml'),(18,3,81,'2 tablespoons'),(19,3,72,'1 teaspoon'),(20,3,73,'1 teaspoon'),(21,3,11,'To taste'),(22,3,14,'To taste'),(23,3,76,'For garnish'),(24,4,1,'2'),(25,4,2,'1'),(26,4,57,'1/4'),(27,4,4,'1'),(28,4,5,'2 tablespoons'),(29,4,6,'1'),(30,4,7,'To taste'),(31,4,8,'For serving'),(32,5,77,'500 g'),(33,5,79,'4'),(34,5,78,'3 tablespoons'),(35,5,80,'2 tablespoons'),(36,5,81,'1 tablespoon'),(37,5,82,'1 tablespoon'),(38,5,11,'To taste'),(39,5,14,'To taste'),(40,5,84,'2 tablespoons'),(41,6,24,'4'),(42,6,25,'2 tablespoons'),(43,6,49,'2 tablespoons'),(44,6,11,'To taste'),(45,6,14,'To taste'),(46,7,22,'2 (store-bought or homemade)'),(47,7,50,'6'),(48,7,37,'3/4 cup'),(49,7,36,'2 tablespoons'),(50,7,38,'1 teaspoon'),(51,7,68,'1/2 teaspoon'),(52,7,11,'1/4 teaspoon'),(53,7,69,'1 tablespoon'),(54,7,49,'2 tablespoons'),(55,7,24,'1 (beaten, for egg wash)'),(56,8,77,'500 g'),(57,8,51,'4'),(58,8,84,'4'),(59,8,52,'1'),(60,8,53,'1'),(61,8,79,'2 tablespoons'),(62,8,78,'1 tablespoon'),(63,8,80,'1 tablespoon'),(64,8,37,'1 teaspoon'),(65,8,92,'1 cup'),(66,8,84,'2 tablespoons'),(67,8,11,'To taste'),(68,8,14,'To taste'),(69,9,92,'1 kg'),(70,9,17,'4'),(71,9,49,'4 tablespoons'),(72,9,24,'1/2 cup'),(73,9,72,'To taste'),(74,9,13,'To taste'),(75,9,90,'For garnish'),(76,10,36,'1 1/2 cups'),(77,10,38,'1 1/2 teaspoons'),(78,10,72,'1/4 teaspoon'),(79,10,49,'1/2 cup (softened)'),(80,10,37,'1 cup'),(81,10,25,'2'),(82,10,66,'2 teaspoons'),(83,10,24,'1/2 cup'),(84,10,46,'For decorating'),(85,11,77,'500 g'),(86,11,56,'1'),(87,11,17,'2'),(88,11,27,'250 g'),(89,11,81,'1 cup'),(90,11,86,'1/2 cup'),(91,11,87,'1 tablespoon'),(92,11,85,'1 tablespoon'),(93,11,36,'1 tablespoon'),(94,11,49,'2 tablespoons'),(95,11,21,'1 tablespoon'),(96,11,74,'1/2 teaspoon'),(97,11,13,'To taste'),(98,11,72,'For serving'),(99,12,9,'20'),(100,12,10,'20'),(101,12,11,'20'),(102,12,72,'For drizzling'),(103,12,72,'To taste'),(104,12,13,'To taste'),(105,13,36,'1 1/2 cups'),(106,13,37,'2 tablespoons'),(107,13,38,'2 teaspoons'),(108,13,72,'1/2 teaspoon'),(109,13,37,'1 1/4 cups'),(110,13,25,'1'),(111,13,49,'2 tablespoons'),(112,13,101,'1 teaspoon'),(113,13,103,'1/2 cup'),(114,13,98,'For serving'),(115,14,77,'2'),(116,14,36,'1/2 cup'),(117,14,25,'2'),(118,14,97,'1 cup'),(119,14,35,'1/2 cup'),(120,14,14,'1/2 teaspoon'),(121,14,18,'1/2 teaspoon'),(122,14,22,'1/4 teaspoon'),(123,14,30,'For frying'),(124,14,97,'1 1/2 cups'),(125,14,28,'1 cup'),(126,14,11,'For garnish'),(127,14,103,'For serving'),(128,15,36,'2 1/4 cups'),(129,15,72,'1/2 teaspoon'),(130,15,72,'1/2 teaspoon'),(131,15,49,'1 cup'),(132,15,37,'3/4 cup'),(133,15,37,'3/4 cup'),(134,15,25,'2'),(135,15,101,'1 teaspoon'),(136,15,98,'For serving'),(137,16,96,'500 g'),(138,16,18,'3 tablespoons'),(139,16,72,'1/2 teaspoon'),(140,16,72,'1/4 teaspoon'),(141,16,14,'1/4 teaspoon'),(142,16,13,'1/4 cup (grated)'),(143,16,72,'For drizzling'),(144,17,102,'2 cups (cooked and seasoned)'),(145,17,101,'10'),(146,17,109,'For filling'),(147,17,34,'For filling'),(148,17,92,'For dipping'),(149,17,108,'For serving'),(150,17,110,'For serving'),(151,18,89,'1 kg'),(152,18,90,'2'),(153,18,17,'2'),(154,18,27,'200 g'),(155,18,49,'50 g'),(156,18,72,'A handful'),(157,18,92,'500 g'),(158,18,108,'For frying'),(159,18,72,'To taste'),(160,18,72,'To taste'),(161,19,83,'800 gr'),(162,19,56,'1'),(163,19,17,'2'),(164,19,27,'2'),(165,19,14,'200 gr'),(166,19,49,'50 gr'),(167,19,36,'50 gr'),(168,19,81,'500 ml'),(169,19,37,'100 ml'),(170,19,25,'2'),(171,19,69,'1 tablespoon'),(172,19,72,'A handful'),(173,19,72,'To taste'),(174,19,72,'To taste'),(250,19,1,'800 gr'),(251,19,2,'1'),(252,19,3,'2'),(253,19,4,'2'),(254,19,5,'200 gr'),(255,19,6,'50 gr'),(256,19,7,'50 gr'),(257,19,8,'500 ml'),(258,19,9,'100 ml'),(259,19,10,'2'),(260,19,11,'1 tablespoon'),(261,19,12,'A handful'),(262,19,13,'To taste'),(263,19,14,'To taste'),(264,20,55,'200 gr'),(265,20,56,'500 gr'),(266,20,57,'6'),(267,20,58,'300 ml'),(268,20,59,'100 gr'),(269,20,60,'1 teaspoon'),(270,20,61,'1 tablespoon'),(271,20,62,'100 ml'),(272,20,75,'For garnish'),(273,21,14,'12'),(274,21,15,'1 cup, chopped'),(275,21,16,'1/2 cup, crumbled'),(276,21,17,'1, minced'),(277,21,18,'2 tablespoons'),(278,21,19,'1/4 cup'),(279,21,20,'To taste'),(280,22,55,'1'),(281,22,52,'4, diced'),(282,22,11,'1/2 cup, chopped'),(283,22,17,'2, minced'),(284,22,18,'3 tablespoons'),(285,22,21,'1 tablespoon'),(286,22,22,'To taste'),(287,23,22,'1'),(288,23,23,'4'),(289,23,24,'1/2 cup'),(290,23,25,'1 cup'),(291,23,26,'1/2 cup'),(292,23,27,'1/2 cup'),(293,23,28,'1/2 cup, shredded'),(294,23,13,'To taste'),(295,23,14,'To taste'),(296,24,55,'1'),(297,24,29,'4 ounces'),(298,24,30,'1/2 cup'),(299,24,31,'1 tablespoon'),(300,24,13,'To taste'),(301,24,32,'To taste'),(302,25,33,'1 pound, peeled and deveined'),(303,25,34,'1, juiced'),(304,25,17,'2, minced'),(305,25,18,'2 tablespoons'),(306,25,35,'2 tablespoons, chopped'),(307,25,13,'To taste'),(308,25,14,'To taste'),(309,26,64,'2 cups'),(310,26,76,'1'),(311,26,23,'5'),(312,26,37,'1/2 cup'),(313,26,14,'1/4 teaspoon'),(314,27,49,'1/2 cup'),(315,27,63,'4 ounces, chopped'),(316,27,23,'2'),(317,27,24,'2'),(318,27,38,'1/4 cup'),(319,27,14,'1/4 teaspoon'),(320,27,39,'2 tablespoons'),(321,28,70,'24'),(322,28,36,'1 1/2 cups, cooled'),(323,28,64,'8 ounces'),(324,28,23,'3'),(325,28,38,'1/2 cup'),(326,28,41,'2 tablespoons'),(327,28,42,'1 teaspoon'),(328,28,43,'For garnish'),(329,29,63,'8 ounces, chopped'),(330,29,23,'4'),(331,29,44,'1/4 cup'),(332,29,14,'1/4 teaspoon'),(333,29,45,'1 cup'),(334,29,46,'1 teaspoon'),(335,30,89,'2 kg'),(336,30,17,'4 cloves, minced'),(337,30,1,'1, chopped'),(338,30,75,'1/4 cup, chopped'),(339,30,13,'4 tablespoons'),(340,30,47,'1 cup'),(341,30,13,'To taste'),(342,30,14,'To taste'),(343,31,48,'12'),(344,31,53,'500 grams'),(345,31,54,'500 grams'),(346,31,55,'1 cup'),(347,31,56,'200 grams, shredded'),(348,31,1,'1, chopped'),(349,31,17,'3 cloves, minced'),(350,31,57,'2 cups'),(351,31,16,'2 tablespoons'),(352,31,13,'To taste'),(353,31,14,'To taste'),(354,32,58,'500 grams, cut into bite-sized pieces'),(355,32,19,'1 cup'),(356,32,20,'2 tablespoons'),(357,32,21,'1 tablespoon'),(358,32,17,'1 tablespoon'),(359,32,22,'1 teaspoon'),(360,32,23,'1/2 teaspoon'),(361,32,24,'1/2 teaspoon'),(362,32,25,'1/2 teaspoon'),(363,32,13,'To taste'),(364,32,26,'2 tablespoons'),(365,32,1,'1, finely chopped'),(366,32,59,'1 cup'),(367,32,60,'1/2 cup'),(368,32,61,'For garnish'),(369,32,62,'For serving'),(370,33,27,'1 cup'),(371,33,16,'2 tablespoons'),(372,33,1,'1, finely chopped'),(373,33,17,'2 cloves, minced'),(374,33,63,'1/2 cup'),(375,33,66,'4 cups'),(376,33,33,'250 grams, peeled and deveined'),(377,33,67,'250 grams'),(378,33,68,'250 grams, cleaned and debearded'),(379,33,69,'1/2 cup, grated'),(380,33,70,'1/4 cup, chopped'),(381,33,13,'To taste'),(382,33,14,'To taste'),(383,34,71,'4, any color'),(384,34,72,'500 grams'),(385,34,73,'2 cups'),(386,34,1,'1, finely chopped'),(387,34,17,'2 cloves, minced'),(388,34,74,'1 cup'),(389,34,75,'1/2 cup'),(390,34,16,'2 tablespoons'),(391,34,13,'To taste'),(392,34,14,'To taste'),(393,35,76,'1 cup'),(394,35,77,'1, sliced'),(395,35,78,'1/2 cup'),(396,35,79,'1 handful'),(397,35,80,'1/2 cup'),(398,35,81,'As desired'),(399,36,82,'1 cup'),(400,36,83,'As desired'),(401,36,84,'1/4 cup'),(402,36,85,'drizzle (optional)'),(403,37,86,'1 cup'),(404,37,87,'2'),(405,37,88,'1 1/2 cups'),(406,37,89,'1 tablespoon'),(407,37,90,'1/4 teaspoon'),(408,37,91,'For filling'),(409,37,92,'For topping'),(410,37,93,'For dusting'),(411,38,87,'3'),(412,38,94,'2 tablespoons'),(413,38,90,'1/4 teaspoon'),(414,38,14,'To taste'),(415,38,95,'1 tablespoon'),(416,38,96,'grated cheese, chopped herbs, diced tomatoes, etc.'),(417,39,79,'2 cups'),(418,39,77,'1'),(419,39,97,'1, cored and chopped'),(420,39,98,'1/2, peeled and chopped'),(421,39,99,'1/2 cup'),(422,39,100,'1 cup'),(423,39,101,'1 tablespoon (optional)'),(424,40,55,'1'),(425,40,29,'3-4 slices'),(426,40,30,'2 tablespoons'),(427,40,56,'thinly sliced'),(428,40,57,'1 tablespoon'),(429,40,31,'a few sprigs'),(430,40,110,'1'),(431,41,59,'1 cup'),(432,41,60,'2 cups'),(433,41,61,'1/2 cup'),(434,41,62,'1/4 cup, chopped'),(435,41,45,'2 tablespoons (optional)'),(436,41,111,'1/2 teaspoon (optional)'),(437,42,99,'4 cups, sliced or chopped'),(438,42,95,'3, minced'),(439,42,18,'2 tablespoons'),(440,42,97,'1/2 teaspoon'),(441,42,98,'1/4 teaspoon'),(442,42,105,'1/4 teaspoon (optional)'),(443,43,98,'1 pound, trimmed'),(444,43,18,'2 tablespoons'),(445,43,96,'1 teaspoon'),(446,43,104,'1 teaspoon'),(447,43,97,'1/2 teaspoon'),(448,44,17,'4 cups'),(449,44,9,'1 cup, halved'),(450,44,56,'1/4 cup, thinly sliced'),(451,44,16,'1/2 cup, crumbled'),(452,44,101,'1/4 cup, sliced'),(453,44,21,'2 tablespoons'),(454,44,22,'2 tablespoons'),(455,44,23,'1/2 teaspoon'),(456,44,24,'1/4 teaspoon'),(457,45,100,'4 medium-sized, peeled and grated'),(458,45,56,'1 small, grated'),(459,45,23,'1, beaten'),(460,45,36,'1/4 cup'),(461,45,25,'1/2 teaspoon'),(462,45,26,'1/4 teaspoon'),(463,45,73,'1/4 cup (for frying)'),(464,45,86,'1/2 cup (for serving)'),(465,45,88,'2 tablespoons (for garnish)'),(466,46,102,'1 pound, trimmed'),(467,46,18,'2 tablespoons'),(468,46,95,'1 teaspoon'),(469,46,104,'1 teaspoon'),(470,46,25,'1/2 teaspoon'),(471,46,97,'1/4 teaspoon'),(472,47,4,'1 cup'),(473,47,6,'1, peeled and chopped'),(474,47,27,'1, chopped'),(475,47,56,'1/2, chopped'),(476,47,95,'1 minced'),(477,47,108,'2 cups'),(478,47,19,'2 tablespoons'),(479,47,20,'2 tablespoons'),(480,47,25,'1 teaspoon'),(481,47,26,'1/2 teaspoon'),(482,47,75,'1/4 cup, chopped (for garnish)'),(483,48,39,'8'),(484,48,95,'1/4 teaspoon'),(485,48,106,'for drizzling'),(486,49,8,'4 large, chopped'),(487,49,51,'1, peeled and chopped'),(488,49,52,'1, chopped'),(489,49,56,'1/2, chopped'),(490,49,18,'1 minced'),(491,49,109,'2 cups'),(492,49,19,'2 tablespoons'),(493,49,20,'2 tablespoons'),(494,49,25,'1 teaspoon'),(495,49,26,'1/2 teaspoon'),(496,49,75,'1/4 cup, chopped (for garnish)'),(497,50,1,'2'),(498,50,2,'1'),(499,50,3,'3');
/*!40000 ALTER TABLE `recipes_ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_users`
--

DROP TABLE IF EXISTS `review_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_users` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `recipe_id` int DEFAULT NULL,
                                `user_id` bigint DEFAULT NULL,
                                `comments` varchar(255) DEFAULT NULL,
                                `rating` decimal(38,2) DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `recipe_id` (`recipe_id`),
                                KEY `user_id` (`user_id`),
                                CONSTRAINT `fk_review_users_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`),
                                CONSTRAINT `fk_review_users_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_users`
--

LOCK TABLES `review_users` WRITE;
/*!40000 ALTER TABLE `review_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `review_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `role_id` bigint NOT NULL AUTO_INCREMENT,
                         `authority` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steps`
--

DROP TABLE IF EXISTS `steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `steps` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `recipe_id` int DEFAULT NULL,
                         `step_description` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `recipe_id` (`recipe_id`),
                         CONSTRAINT `steps_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=472 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steps`
--

LOCK TABLES `steps` WRITE;
/*!40000 ALTER TABLE `steps` DISABLE KEYS */;
INSERT INTO `steps` VALUES (1,1,'Melt the butter with the chocolate.'),(2,1,'Beat the eggs with the sugar.'),(3,1,'Add the flour to the eggs and sugar.'),(4,1,'Mix the melted butter/chocolate with the eggs/sugar/flour until obtaining a homogeneous batter.'),(5,1,'Butter and flour a cake pan.'),(6,1,'Pour the mixture into the pan and bake at 190 degrees for 15 to 20 minutes.'),(7,2,'Brew the espresso coffee and let it cool.'),(8,2,'In a mixing bowl, combine the mascarpone cheese and sugar. Mix well until smooth.'),(9,2,'Dip each ladyfinger into the cooled espresso coffee, ensuring they are evenly soaked but not overly saturated.'),(10,2,'Place a layer of soaked ladyfingers in a serving dish or individual glasses.'),(11,2,'Spread a layer of the mascarpone mixture over the ladyfingers.'),(12,2,'Repeat the layers until all the ladyfingers and mascarpone mixture are used, finishing with a layer of mascarpone on top.'),(13,2,'Dust the top layer with cocoa powder.'),(14,2,'Refrigerate for at least 4 hours or overnight before serving.'),(15,2,'Sprinkle additional cocoa powder on top before serving, if desired.'),(16,3,'Heat olive oil in a large saucepan over medium heat.'),(17,3,'Add the onion and garlic, and sauté until softened.'),(18,3,'Add the ground beef and cook until browned.'),(19,3,'Pour in the red wine and let it simmer until reduced.'),(20,3,'Add the canned tomatoes, tomato paste, dried basil, dried oregano, salt, and black pepper. Stir well.'),(21,3,'Reduce the heat to low, cover, and let it simmer for 1 hour, stirring occasionally.'),(22,3,'Meanwhile, cook the spaghetti according to the package instructions until al dente.'),(23,3,'Serve the Bolognese sauce over the cooked spaghetti.'),(24,3,'Garnish with grated Parmesan cheese.'),(25,3,'Enjoy!'),(26,4,'Cut the avocados in half, remove the pits, and scoop out the flesh into a bowl.'),(27,4,'Mash the avocados with a fork or potato masher until desired consistency.'),(28,4,'Squeeze the juice of the lime onto the mashed avocados and mix well.'),(29,4,'Finely chop the red onion, tomato, fresh cilantro, and jalapeño pepper. Add them to the avocado mixture.'),(30,4,'Season with salt to taste and mix until well combined.'),(31,4,'Serve the guacamole with tortilla chips.'),(32,4,'Enjoy!'),(33,5,'Slice the chicken breast into thin strips.'),(34,5,'In a small bowl, combine soy sauce, oyster sauce, sesame oil, cornstarch, salt, and black pepper. Mix well to make the sauce.'),(35,5,'Heat vegetable oil in a wok or large skillet over high heat.'),(36,5,'Add the garlic and ginger, and stir-fry for about 1 minute until fragrant.'),(37,5,'Add the chicken strips and stir-fry until they are cooked through and lightly browned.'),(38,5,'Add the bell peppers, broccoli, carrot, and snow peas. Stir-fry for another 3-4 minutes until the vegetables are crisp-tender.'),(39,5,'Pour the sauce over the stir-fry and toss well to coat everything evenly.'),(40,5,'Cook for an additional 2 minutes until the sauce thickens.'),(41,5,'Serve the chicken stir-fry with steamed rice or noodles.'),(42,5,'Enjoy!'),(43,6,'In a bowl, whisk together the eggs and milk. Season with salt and black pepper.'),(44,6,'Heat the butter in a non-stick skillet over medium heat.'),(45,6,'Pour the egg mixture into the skillet and let it cook undisturbed for a few seconds until the edges start to set.'),(46,6,'Using a spatula, gently push the cooked edges towards the center, allowing the uncooked eggs to flow to the edges.'),(47,6,'Continue stirring and folding the eggs until they are mostly cooked but still slightly runny.'),(48,6,'Remove the skillet from the heat and let the residual heat finish cooking the eggs while keeping them moist.'),(49,6,'Serve the scrambled eggs hot.'),(50,6,'Enjoy!'),(51,7,'Preheat the oven to 190 degrees Celsius (375 degrees Fahrenheit).'),(52,7,'Peel, core, and slice the apples.'),(53,7,'In a large mixing bowl, combine the sliced apples, sugar, flour, cinnamon, nutmeg, salt, and lemon juice. Toss until the apples are evenly coated.'),(54,7,'Roll out one of the pie crusts and place it in a pie dish.'),(55,7,'Pour the apple mixture into the pie crust, spreading it out evenly.'),(56,7,'Dot the top of the apples with small pieces of butter.'),(57,7,'Roll out the second pie crust and place it over the apples. Trim any excess crust and crimp the edges to seal the pie.'),(58,7,'Cut slits in the top crust to allow steam to escape during baking.'),(59,7,'Brush the top crust with the beaten egg for a golden finish.'),(60,7,'Place the pie on a baking sheet and bake in the preheated oven for 45-50 minutes or until the crust is golden brown and the filling is bubbly.'),(61,7,'Remove from the oven and let the pie cool for at least 1 hour before serving.'),(62,7,'Serve the apple pie warm or at room temperature.'),(63,7,'Enjoy!'),(64,8,'Slice the chicken thighs into thin strips.'),(65,8,'Mince the garlic cloves and Thai chilies.'),(66,8,'Slice the onion and bell pepper into thin strips.'),(67,8,'In a small bowl, mix together the fish sauce, soy sauce, oyster sauce, and sugar. Set aside.'),(68,8,'Heat vegetable oil in a wok or large skillet over high heat.'),(69,8,'Add the minced garlic and Thai chilies, and stir-fry for about 1 minute until fragrant.'),(70,8,'Add the chicken strips and stir-fry until they are cooked through and lightly browned.'),(71,8,'Add the sliced onion and bell pepper. Stir-fry for another 2-3 minutes until the vegetables are slightly softened.'),(72,8,'Pour the sauce mixture over the chicken and vegetables. Toss well to coat everything evenly.'),(73,8,'Add the holy basil leaves and stir-fry for another 1 minute until the basil leaves wilt.'),(74,8,'Season with salt and black pepper to taste.'),(75,8,'Serve the spicy Thai basil chicken over steamed rice.'),(76,8,'Enjoy!'),(77,9,'Peel and dice the potatoes into small cubes.'),(78,9,'Place the diced potatoes in a large pot of salted water and bring to a boil.'),(79,9,'Cook the potatoes until they are fork-tender, about 15-20 minutes.'),(80,9,'While the potatoes are cooking, mince the garlic cloves.'),(81,9,'Drain the cooked potatoes and return them to the pot.'),(82,9,'Add the minced garlic, butter, and milk to the pot.'),(83,9,'Mash the potatoes with a potato masher or fork until smooth and creamy. Add more milk if needed to achieve the desired consistency.'),(84,9,'Season with salt and black pepper to taste.'),(85,9,'Transfer the mashed potatoes to a serving dish and garnish with chopped chives.'),(86,9,'Serve the garlic mashed potatoes hot.'),(87,9,'Enjoy!'),(88,10,'Preheat the oven to 180 degrees Celsius (350 degrees Fahrenheit).'),(89,10,'In a mixing bowl, whisk together the all-purpose flour, baking powder, and salt.'),(90,10,'In a separate bowl, cream together the softened unsalted butter and sugar until light and fluffy.'),(91,10,'Beat in the eggs one at a time, followed by the vanilla extract.'),(92,10,'Gradually add the dry ingredients to the butter mixture, alternating with the milk. Begin and end with the dry ingredients, mixing until just combined.'),(93,10,'Line a muffin tin with cupcake liners.'),(94,10,'Divide the batter evenly among the cupcake liners, filling each about two-thirds full.'),(95,10,'Bake in the preheated oven for 18-20 minutes or until a toothpick inserted into the center of a cupcake comes out clean.'),(96,10,'Remove the cupcakes from the oven and let them cool completely on a wire rack.'),(97,10,'Once cooled, decorate the cupcakes with buttercream frosting or any desired toppings.'),(98,10,'Enjoy the vanilla cupcakes!'),(99,11,'Slice the beef sirloin into thin strips.'),(100,11,'Finely chop the onion and mince the garlic cloves.'),(101,11,'Slice the mushrooms.'),(102,11,'In a small bowl, whisk together the beef broth, sour cream, Dijon mustard, and Worcestershire sauce. Set aside.'),(103,11,'In a large skillet, melt the butter over medium heat.'),(104,11,'Add the sliced onion and minced garlic. Sauté until the onion is translucent.'),(105,11,'Add the sliced mushrooms and continue cooking until the mushrooms are softened and slightly browned.'),(106,11,'Remove the onion and mushroom mixture from the skillet and set aside.'),(107,11,'In the same skillet, add the beef strips and cook until they are browned on all sides. Season with salt and black pepper.'),(108,11,'Sprinkle the flour over the beef and stir well to coat the meat.'),(109,11,'Pour the beef broth and sour cream mixture into the skillet with the beef. Stir well to combine.'),(110,11,'Return the onion and mushroom mixture to the skillet. Stir everything together.'),(111,11,'Simmer the stroganoff mixture for about 10 minutes, stirring occasionally, until the sauce has thickened.'),(112,11,'While the stroganoff is simmering, cook the egg noodles according to the package instructions until al dente.'),(113,11,'Serve the beef stroganoff over the cooked egg noodles.'),(114,11,'Enjoy!'),(115,12,'Rinse the cherry tomatoes and pat them dry.'),(116,12,'Thread a cherry tomato, a fresh mozzarella ball, and a fresh basil leaf onto a skewer. Repeat until all ingredients are used.'),(117,12,'Arrange the caprese skewers on a serving platter.'),(118,12,'Drizzle balsamic glaze over the skewers.'),(119,12,'Season with salt and black pepper to taste.'),(120,12,'Serve the caprese skewers as a delicious appetizer.'),(121,12,'Enjoy!'),(122,13,'In a mixing bowl, whisk together the all-purpose flour, sugar, baking powder, and salt.'),(123,13,'In a separate bowl, whisk together the milk, egg, melted unsalted butter, and vanilla extract.'),(124,13,'Pour the wet ingredients into the dry ingredients and whisk until just combined. The batter may have some lumps, which is normal.'),(125,13,'Heat a non-stick griddle or skillet over medium heat. Lightly grease with butter or cooking spray.'),(126,13,'Pour 1/4 cup of batter onto the griddle for each pancake.'),(127,13,'Cook until bubbles form on the surface of the pancake and the edges start to look set, about 2-3 minutes.'),(128,13,'Flip the pancakes and cook for an additional 1-2 minutes until golden brown.'),(129,13,'Transfer the cooked pancakes to a plate and keep warm.'),(130,13,'Repeat with the remaining batter, greasing the griddle as needed.'),(131,13,'Serve the pancakes hot with maple syrup or any desired toppings.'),(132,13,'Enjoy a stack of fluffy pancakes!'),(133,14,'Preheat the oven to 190 degrees Celsius (375 degrees Fahrenheit).'),(134,14,'Slice each chicken breast in half horizontally to create 4 thin cutlets.'),(135,14,'In separate shallow bowls, place the all-purpose flour, beaten eggs, and breadcrumbs.'),(136,14,'To the breadcrumbs, add the grated Parmesan cheese, Italian seasoning, garlic powder, salt, and black pepper. Mix well.'),(137,14,'Dredge each chicken cutlet in the flour, shaking off any excess.'),(138,14,'Dip the cutlets into the beaten eggs, allowing any excess to drip off.'),(139,14,'Coat the cutlets with the breadcrumb mixture, pressing lightly to adhere.'),(140,14,'Heat vegetable oil in a large skillet over medium heat.'),(141,14,'Fry the breaded chicken cutlets for about 3-4 minutes per side until golden brown and crispy.'),(142,14,'Transfer the cooked cutlets to a baking dish.'),(143,14,'Top each cutlet with marinara sauce and shredded mozzarella cheese.'),(144,14,'Bake in the preheated oven for 20-25 minutes or until the cheese is melted and bubbly.'),(145,14,'While the chicken is baking, cook the spaghetti according to the package instructions until al dente.'),(146,14,'Serve the chicken Parmesan over cooked spaghetti.'),(147,14,'Garnish with fresh basil.'),(148,14,'Enjoy!'),(149,15,'Preheat the oven to 190 degrees Celsius (375 degrees Fahrenheit).'),(150,15,'In a mixing bowl, whisk together the all-purpose flour, baking soda, and salt.'),(151,15,'In a separate bowl, cream together the softened unsalted butter, granulated sugar, and brown sugar until light and fluffy.'),(152,15,'Beat in the eggs, one at a time, followed by the vanilla extract.'),(153,15,'Gradually add the dry ingredients to the butter mixture, mixing until just combined.'),(154,15,'Stir in the chocolate chips by hand.'),(155,15,'Drop rounded tablespoonfuls of dough onto ungreased baking sheets, spacing them about 2 inches apart.'),(156,15,'Bake in the preheated oven for 9-11 minutes or until the edges are golden brown.'),(157,15,'Remove the cookies from the oven and let them cool on the baking sheets for 5 minutes.'),(158,15,'Transfer the cookies to a wire rack to cool completely.'),(159,15,'Enjoy delicious homemade chocolate chip cookies!'),(160,16,'Preheat the oven to 190 degrees Celsius (375 degrees Fahrenheit).'),(161,16,'In a small bowl, combine the melted unsalted butter, minced garlic, lemon juice, lemon zest, salt, and black pepper.'),(162,16,'Place the salmon fillets on a baking sheet lined with parchment paper.'),(163,16,'Brush the lemon-garlic butter mixture over the salmon fillets, coating them evenly.'),(164,16,'Bake in the preheated oven for 12-15 minutes or until the salmon is cooked through and flakes easily with a fork.'),(165,16,'Remove the salmon from the oven and let it rest for a few minutes.'),(166,16,'Garnish with fresh chopped parsley.'),(167,16,'Serve the lemon garlic salmon with a side of steamed vegetables or your choice of sides.'),(168,16,'Enjoy a healthy and flavorful salmon dish!'),(169,17,'In a blender, combine the mixed berries, banana, yogurt, milk, honey, and ice cubes.'),(170,17,'Blend on high speed until smooth and creamy.'),(171,17,'Taste the smoothie and adjust the sweetness by adding more honey if desired.'),(172,17,'Pour the berry smoothie into glasses.'),(173,17,'Garnish with fresh berries or a sprig of mint.'),(174,17,'Serve the refreshing berry smoothie immediately.'),(175,17,'Enjoy!'),(176,18,'In a small bowl, whisk together the minced garlic, Dijon mustard, Worcestershire sauce, anchovy paste, lemon juice, salt, and black pepper.'),(177,18,'Slowly drizzle in the olive oil while whisking continuously to emulsify the dressing.'),(178,18,'In a large salad bowl, combine the torn romaine lettuce leaves and grated Parmesan cheese.'),(179,18,'Pour the Caesar dressing over the lettuce and toss until well coated.'),(180,18,'Add the croutons to the salad and toss again.'),(181,18,'Garnish with additional grated Parmesan cheese and freshly ground black pepper.'),(182,18,'Serve the classic Caesar salad as a side dish or add grilled chicken or shrimp for a complete meal.'),(183,18,'Enjoy!'),(184,19,'Heat olive oil in a large skillet or pot over medium heat.'),(185,19,'Add the diced onion, minced garlic cloves, and chopped carrots. Sauté until the vegetables are softened.'),(186,19,'Add the ground beef to the skillet and cook until browned, breaking it up with a spoon.'),(187,19,'Stir in the tomato paste and cook for a minute to enhance the flavor.'),(188,19,'Pour in the crushed tomatoes, beef broth, red wine, dried oregano, dried basil, salt, and black pepper. Stir to combine.'),(189,19,'Reduce the heat to low and simmer the sauce for at least 30 minutes to allow the flavors to meld together. You can simmer it for longer if desired.'),(190,19,'While the sauce is simmering, cook the spaghetti according to the package instructions until al dente.'),(191,19,'Drain the cooked spaghetti and divide it among plates.'),(192,19,'Top the spaghetti with the Bolognese sauce.'),(193,19,'Garnish with grated Parmesan cheese and fresh basil leaves.'),(194,19,'Serve the delicious spaghetti Bolognese and enjoy!'),(195,20,'Line the sides of a round cake pan with ladyfinger biscuits, placing them vertically and side by side.'),(196,20,'Hull the strawberries and cut them into thin slices.'),(197,20,'In a small bowl, soak the gelatin sheets in cold water for about 5 minutes or until soft.'),(198,20,'In a saucepan, heat the sugar, vanilla extract, lemon juice, and water over low heat until the sugar dissolves.'),(199,20,'Remove the gelatin sheets from the water, squeeze out any excess water, and add them to the saucepan. Stir until the gelatin dissolves.'),(200,20,'In a separate bowl, whip the whipping cream until soft peaks form.'),(201,20,'Gently fold the strawberry slices into the whipped cream.'),(202,20,'Pour the gelatin mixture into the strawberry cream mixture and stir well to combine.'),(203,20,'Pour the strawberry cream into the prepared cake pan, spreading it evenly.'),(204,20,'Cover the pan with plastic wrap and refrigerate for at least 4 hours or until set.'),(205,20,'Once set, remove the plastic wrap and carefully remove the charlotte from the pan.'),(206,20,'Garnish with fresh mint leaves.'),(207,20,'Serve the strawberry charlotte chilled.'),(208,20,'Enjoy!'),(209,21,'Preheat the oven to 375°F (190°C).'),(210,21,'Remove the stems from the mushrooms and set aside.'),(211,21,'In a skillet, heat olive oil and sauté minced garlic until fragrant.'),(212,21,'Add chopped spinach and cook until wilted.'),(213,21,'In a bowl, combine the cooked spinach, crumbled feta cheese, bread crumbs, salt, and pepper.'),(214,21,'Fill each mushroom cap with the spinach and feta mixture.'),(215,21,'Place the stuffed mushrooms on a baking sheet.'),(216,21,'Bake in the preheated oven for about 20-25 minutes, until the mushrooms are tender and the filling is golden.'),(217,21,'Serve the spinach and feta stuffed mushrooms warm.'),(218,21,'Enjoy!'),(219,22,'Preheat the oven to 375°F (190°C).'),(220,22,'Slice the baguette into diagonal pieces and place them on a baking sheet.'),(221,22,'In a bowl, mix together diced tomatoes, minced garlic, chopped basil, olive oil, balsamic vinegar, salt, and pepper.'),(222,22,'Top each baguette slice with the tomato and basil mixture.'),(223,22,'Bake in the preheated oven for about 5 minutes, until the bread is toasted and the tomatoes are warm.'),(224,22,'Serve the tomato and basil bruschetta warm.'),(225,22,'Enjoy!'),(226,23,'Preheat the oven to 375°F (190°C).'),(227,23,'Roll out the pie crust and cut out small circles to fit into a muffin tin.'),(228,23,'In a bowl, beat eggs and milk together. Season with salt and pepper.'),(229,23,'Divide chopped broccoli, diced red bell pepper, sliced mushrooms, and shredded cheddar cheese among the pie crusts.'),(230,23,'Pour the egg mixture into each crust, filling them about three-fourths full.'),(231,23,'Bake in the preheated oven for about 20-25 minutes, until the quiches are set and golden on top.'),(232,23,'Remove from the oven and let cool for a few minutes before serving.'),(233,23,'Serve the mini vegetable quiches warm or at room temperature.'),(234,23,'Enjoy!'),(235,24,'Slice the baguette into diagonal pieces and arrange them on a serving platter.'),(236,24,'In a bowl, mix together cream cheese, chopped fresh dill, lemon juice, salt, and pepper.'),(237,24,'Spread a thin layer of the cream cheese mixture onto each baguette slice.'),(238,24,'Top each slice with a piece of smoked salmon.'),(239,24,'Garnish with additional dill, if desired.'),(240,24,'Serve the smoked salmon and cream cheese canapés chilled.'),(241,24,'Enjoy!'),(242,25,'Preheat the grill to medium-high heat.'),(243,25,'In a bowl, combine lemon juice, minced garlic, olive oil, chopped parsley, salt, and pepper.'),(244,25,'Thread the shrimp onto skewers, about 4-5 shrimp per skewer.'),(245,25,'Brush the shrimp skewers with the lemon garlic marinade, coating them evenly.'),(246,25,'Grill the shrimp skewers for about 2-3 minutes per side, until the shrimp turn pink and opaque.'),(247,25,'Remove from the grill and let rest for a minute before serving.'),(248,25,'Serve the grilled shrimp skewers hot, garnished with additional parsley if desired.'),(249,25,'Enjoy!'),(250,26,'Melt the chopped dark chocolate in a heatproof bowl set over a pan of simmering water. Stir until smooth and let cool slightly.'),(251,26,'In a mixing bowl, beat the egg yolks, granulated sugar, and salt until pale and creamy.'),(252,26,'Slowly pour the melted chocolate into the egg yolk mixture, whisking constantly.'),(253,26,'In a separate bowl, beat the egg whites until stiff peaks form.'),(254,26,'Gently fold the beaten egg whites into the chocolate mixture, followed by the whipped cream and vanilla extract.'),(255,26,'Continue folding until the mousse is light and well combined.'),(256,26,'Divide the chocolate mousse among individual serving dishes or glasses.'),(257,26,'Refrigerate the mousse for at least 2 hours to set.'),(258,26,'Before serving, garnish with additional whipped cream and chocolate shavings, if desired.'),(259,26,'Serve the light and creamy chocolate mousse chilled.'),(260,26,'Enjoy the fluffy and indulgent chocolate dessert!'),(261,27,'Preheat the oven to 425°F (220°C).'),(262,27,'Grease and flour 4 ramekins or individual baking dishes.'),(263,27,'In a microwave-safe bowl, melt the butter and chopped dark chocolate together, stirring until smooth.'),(264,27,'In a separate bowl, whisk together the eggs, egg yolks, and granulated sugar until well combined.'),(265,27,'Slowly pour the melted chocolate mixture into the egg mixture, whisking constantly.'),(266,27,'Add the all-purpose flour and salt, and whisk until just combined.'),(267,27,'Divide the batter evenly among the prepared ramekins.'),(268,27,'Bake in the preheated oven for about 10-12 minutes, or until the edges are set but the center is still soft.'),(269,27,'Remove from the oven and let the cakes cool in the ramekins for a minute.'),(270,27,'Carefully invert each ramekin onto a serving plate, and gently tap to release the cakes.'),(271,27,'Dust the molten chocolate lava cakes with powdered sugar.'),(272,27,'Serve the cakes immediately while the centers are still warm and gooey.'),(273,27,'Enjoy the indulgent molten chocolate lava cakes!'),(274,28,'In a shallow dish, pour the cooled coffee.'),(275,28,'In a mixing bowl, whisk together mascarpone cheese, egg yolks, granulated sugar, cocoa powder, and vanilla extract until smooth and creamy.'),(276,28,'Dip each ladyfinger into the coffee, soaking them briefly on each side.'),(277,28,'Arrange a layer of soaked ladyfingers in the bottom of a serving dish or individual glasses.'),(278,28,'Spread a layer of the mascarpone mixture over the ladyfingers.'),(279,28,'Repeat the layers, alternating soaked ladyfingers and mascarpone mixture, until all ingredients are used.'),(280,28,'Cover the tiramisu and refrigerate for at least 4 hours or overnight to allow the flavors to meld and the dessert to set.'),(281,28,'Before serving, garnish the tiramisu with a dusting of cocoa powder and dark chocolate shavings.'),(282,28,'Serve the classic tiramisu chilled.'),(283,28,'Enjoy the rich and creamy classic Italian dessert!'),(284,29,'Melt the chopped dark chocolate in a heatproof bowl set over a pan of simmering water. Stir until smooth and let cool slightly.'),(285,29,'In a mixing bowl, beat the egg yolks, granulated sugar, and salt until pale and creamy.'),(286,29,'Slowly pour the melted chocolate into the egg yolk mixture, whisking constantly.'),(287,29,'In a separate bowl, beat the egg whites until stiff peaks form.'),(288,29,'Gently fold the beaten egg whites into the chocolate mixture, followed by the whipped cream and vanilla extract.'),(289,29,'Continue folding until the mousse is light and well combined.'),(290,29,'Divide the chocolate mousse among individual serving dishes or glasses.'),(291,29,'Refrigerate the mousse for at least 2 hours to set.'),(292,29,'Before serving, garnish with additional whipped cream and chocolate shavings, if desired.'),(293,29,'Serve the light and creamy chocolate mousse chilled.'),(294,29,'Enjoy the fluffy and indulgent chocolate dessert!'),(295,30,'Clean the mussels by rinsing them under cold water and removing the beards.'),(296,30,'In a large pot, melt the butter over medium heat.'),(297,30,'Add the minced garlic and chopped onion to the pot and sauté until tender and lightly golden.'),(298,30,'Add the mussels and white wine to the pot.'),(299,30,'Cover the pot and simmer for about 5 minutes, or until the mussels have opened.'),(300,30,'Remove the mussels from the pot using a slotted spoon and reserve them in a warm dish.'),(301,30,'Increase the heat under the pot and let the sauce reduce for a few minutes.'),(302,30,'Add the chopped parsley to the sauce and mix well.'),(303,30,'Pour the sauce over the mussels and serve immediately.'),(304,30,'Enjoy the moules marinières with fries or fresh bread.'),(305,30,'Bon appétit!'),(306,31,'Preheat the oven to 180°C (350°F).'),(307,31,'In a large pot of boiling water, cook the lasagna sheets according to the package instructions. Drain and set aside.'),(308,31,'In a pan, heat olive oil over medium heat. Add the chopped onion and minced garlic, and sauté until golden brown.'),(309,31,'Add the fresh spinach to the pan and cook until wilted. Remove from heat and set aside.'),(310,31,'In a bowl, mix together the ricotta cheese, grated Parmesan cheese, salt, and pepper.'),(311,31,'Spread a thin layer of tomato sauce on the bottom of a baking dish.'),(312,31,'Place a layer of cooked lasagna sheets on top of the sauce.'),(313,31,'Spread half of the spinach mixture over the lasagna sheets, followed by half of the ricotta cheese mixture.'),(314,31,'Repeat the layers with the remaining ingredients, finishing with a layer of lasagna sheets on top.'),(315,31,'Top with the shredded mozzarella cheese.'),(316,31,'Cover the baking dish with aluminum foil and bake for 25 minutes.'),(317,31,'Remove the foil and bake for an additional 15 minutes, or until the cheese is golden and bubbly.'),(318,31,'Let the lasagna cool for a few minutes before serving.'),(319,31,'Serve the vegetarian spinach and ricotta lasagna with a fresh green salad.'),(320,31,'Enjoy!'),(321,32,'In a bowl, combine yogurt, lemon juice, ginger paste, garlic paste, garam masala, turmeric powder, cumin powder, coriander powder, red chili powder, and salt.'),(322,32,'Add the chicken pieces to the bowl and mix well. Marinate for at least 1 hour, or overnight for better flavor.'),(323,32,'Heat vegetable oil in a large pan over medium heat. Add the chopped onion and sauté until golden brown.'),(324,32,'Add the marinated chicken to the pan and cook until browned on all sides.'),(325,32,'Pour in the tomato puree and cook for a few minutes, stirring occasionally.'),(326,32,'Reduce the heat to low, cover the pan, and simmer for about 15 minutes, or until the chicken is cooked through and tender.'),(327,32,'Stir in the heavy cream and cook for an additional 5 minutes.'),(328,32,'Garnish with fresh coriander leaves.'),(329,32,'Serve the Indian chicken tikka masala with naan bread and steamed basmati rice.'),(330,32,'Enjoy the flavorful and spicy chicken tikka masala!'),(331,33,'In a large pan, heat olive oil over medium heat. Add the chopped onion and minced garlic, and sauté until golden brown.'),(332,33,'Add the Arborio rice to the pan and stir to coat the rice with the oil.'),(333,33,'Pour in the white wine and cook until the wine has evaporated.'),(334,33,'Add a ladleful of fish or seafood stock to the pan and stir until the liquid is absorbed. Repeat this process, adding more stock each time, until the rice is cooked al dente.'),(335,33,'Meanwhile, in a separate pan, heat olive oil over medium heat. Add the shrimp, scallops, and mussels. Cook until the seafood is cooked through and the mussels have opened. Remove from heat and set aside.'),(336,33,'Once the rice is cooked, remove the pan from heat. Stir in the grated Parmesan cheese and chopped parsley.'),(337,33,'Gently fold in the cooked seafood.'),(338,33,'Season with salt and pepper to taste.'),(339,33,'Let the risotto rest for a few minutes before serving.'),(340,33,'Serve the seafood risotto with a sprinkle of grated Parmesan cheese and a garnish of fresh parsley.'),(341,33,'Enjoy the creamy and flavorful seafood risotto!'),(342,34,'Preheat the oven to 180°C (350°F).'),(343,34,'Cut the tops off the bell peppers and remove the seeds and membranes from the inside.'),(344,34,'In a large pan, heat olive oil over medium heat. Add the chopped onion and minced garlic, and sauté until golden brown.'),(345,34,'Add the ground meat to the pan and cook until browned.'),(346,34,'Stir in the cooked rice and tomato sauce. Season with salt and pepper to taste.'),(347,34,'Stuff the bell peppers with the meat and rice mixture.'),(348,34,'Place the stuffed bell peppers in a baking dish and cover with aluminum foil.'),(349,34,'Bake for 30 minutes, then remove the foil and sprinkle grated cheese on top of the peppers.'),(350,34,'Bake for an additional 15 minutes, or until the cheese is melted and golden.'),(351,34,'Remove from the oven and let cool for a few minutes before serving.'),(352,34,'Serve the stuffed bell peppers as a hearty and delicious main course.'),(353,34,'Enjoy the flavorful combination of rice, ground meat, and melted cheese!'),(354,35,'In a blender, combine the frozen mixed berries, banana slices, Greek yogurt, spinach leaves, and almond milk.'),(355,35,'Blend until smooth and creamy.'),(356,35,'Pour the smoothie into a bowl.'),(357,35,'Top the smoothie with your choice of toppings, such as granola, sliced fruits, and chia seeds.'),(358,35,'Enjoy the refreshing and nutritious smoothie bowl as a healthy breakfast option!'),(359,36,'In a serving bowl, spoon the Greek yogurt.'),(360,36,'Top the yogurt with assorted fresh fruits.'),(361,36,'Sprinkle granola over the fruits.'),(362,36,'Drizzle honey or maple syrup, if desired, for added sweetness.'),(363,36,'Enjoy the delightful combination of creamy yogurt, fresh fruits, and crunchy granola for a satisfying breakfast.'),(364,37,'In a mixing bowl, whisk together the flour, eggs, milk, sugar, and salt until smooth.'),(365,37,'Heat a non-stick skillet over medium heat and lightly grease it with butter or oil.'),(366,37,'Pour about 1/4 cup of the crepe batter into the pan, swirling it around to evenly coat the bottom.'),(367,37,'Cook for about 1-2 minutes until the edges start to brown, then flip the crepe and cook for an additional minute.'),(368,37,'Repeat with the remaining batter to make several crepes.'),(369,37,'Fill each crepe with a variety of fresh fruits.'),(370,37,'Fold the crepes or roll them up.'),(371,37,'Top with whipped cream and dust with powdered sugar.'),(372,37,'Serve the fruit crepes as a delightful and elegant breakfast treat.'),(373,38,'In a bowl, whisk together the eggs, milk, salt, and black pepper until well combined.'),(374,38,'Heat a non-stick skillet over medium heat and melt the butter.'),(375,38,'Pour the egg mixture into the skillet and let it cook undisturbed for a few seconds.'),(376,38,'Using a spatula, gently scramble the eggs by pushing them from the edges toward the center.'),(377,38,'Continue cooking and stirring until the eggs are soft and slightly runny.'),(378,38,'Remove from heat as the residual heat will continue to cook the eggs to perfection.'),(379,38,'Serve the scrambled eggs on a plate and add optional toppings such as grated cheese, chopped herbs, or diced tomatoes if desired.'),(380,38,'Enjoy a classic and satisfying breakfast with creamy and fluffy scrambled eggs!'),(381,39,'In a blender, combine the spinach, banana, apple, cucumber, pineapple chunks, and coconut water or almond milk.'),(382,39,'Blend until smooth and creamy.'),(383,39,'If desired, add chia seeds and blend for a few more seconds.'),(384,39,'Pour into a glass and serve immediately for a refreshing and nutritious green smoothie!'),(385,40,'Slice the bagel in half horizontally.'),(386,40,'Spread cream cheese on both sides of the bagel.'),(387,40,'Layer the smoked salmon on one half of the bagel.'),(388,40,'Top with thinly sliced red onion, capers, and fresh dill.'),(389,40,'Squeeze lemon juice over the toppings.'),(390,40,'Cover with the other half of the bagel.'),(391,40,'Serve the salmon bagel as a delicious and satisfying breakfast sandwich.'),(392,41,'Rinse the quinoa under cold water to remove any bitterness.'),(393,41,'In a saucepan, bring the water or vegetable broth to a boil.'),(394,41,'Add the quinoa and reduce the heat to low.'),(395,41,'Cover and simmer for about 15 minutes until the quinoa is tender and the liquid is absorbed.'),(396,41,'Remove from heat and let it sit for a few minutes.'),(397,41,'Fluff the quinoa with a fork.'),(398,41,'Stir in the dried fruits and chopped nuts.'),(399,41,'If desired, add honey or maple syrup and cinnamon for extra sweetness and flavor.'),(400,41,'Serve the quinoa with dried fruits as a wholesome and nutritious breakfast option.'),(401,42,'Heat olive oil in a large skillet over medium heat.'),(402,42,'Add minced garlic and sauté for about 1 minute until fragrant.'),(403,42,'Add the sliced or chopped vegetables to the skillet.'),(404,42,'Season with salt, black pepper, and crushed red pepper flakes (if desired).'),(405,42,'Sauté the vegetables for about 8-10 minutes until they are tender-crisp.'),(406,42,'Remove from heat and serve the sautéed garlic vegetables as a flavorful and healthy side dish.'),(407,43,'In a saucepan, melt the butter over medium heat.'),(408,43,'Add the corn kernels and sauté for about 5 minutes until they are cooked and slightly golden.'),(409,43,'Reduce the heat to low.'),(410,43,'Add the shredded cheddar cheese, milk, salt, and black pepper to the saucepan.'),(411,43,'Stir until the cheese is melted and the mixture is well combined.'),(412,43,'Remove from heat and garnish with chopped fresh parsley.'),(413,43,'Serve the cheesy corn as a comforting and flavorful side dish.'),(414,44,'In a large salad bowl, combine the baby spinach leaves, cherry tomatoes, red onion, crumbled feta cheese, and toasted almonds.'),(415,44,'In a small bowl, whisk together the balsamic vinegar, olive oil, salt, and black pepper to make the dressing.'),(416,44,'Drizzle the dressing over the salad ingredients.'),(417,44,'Toss gently to coat the salad with the dressing.'),(418,44,'Serve the spinach salad as a refreshing and nutritious side dish.'),(419,45,'Place the grated potatoes in a clean kitchen towel and squeeze out the excess moisture.'),(420,45,'In a mixing bowl, combine the grated potatoes, grated onion, beaten egg, all-purpose flour, salt, and black pepper.'),(421,45,'Heat vegetable oil in a large skillet over medium heat.'),(422,45,'Drop spoonfuls of the potato mixture into the skillet, flattening them slightly with the back of the spoon.'),(423,45,'Cook the potato pancakes for about 4-5 minutes on each side until they are golden brown and crispy.'),(424,45,'Remove the pancakes from the skillet and place them on a paper towel to drain excess oil.'),(425,45,'Serve the potato pancakes hot with a dollop of sour cream and a sprinkle of chopped fresh chives.'),(426,46,'Preheat the oven to 425°F (220°C).'),(427,46,'In a large bowl, toss the green beans with olive oil, garlic powder, onion powder, salt, and black pepper until evenly coated.'),(428,46,'Spread the seasoned green beans in a single layer on a baking sheet.'),(429,46,'Roast the green beans in the preheated oven for about 12-15 minutes, stirring once halfway through, until they are tender and slightly charred.'),(430,46,'Remove from the oven and serve the oven-roasted green beans as a nutritious and flavorful side dish.'),(431,47,'Preheat the oven to 400°F (200°C).'),(432,47,'Line a baking sheet with aluminum foil for easy cleanup.'),(433,47,'Arrange the bacon slices in a single layer on the prepared baking sheet.'),(434,47,'Sprinkle black pepper evenly over the bacon slices.'),(435,47,'Bake in the preheated oven for about 12-15 minutes until the bacon is crispy and browned to your liking.'),(436,47,'Remove from the oven and transfer the bacon to a paper towel-lined plate to drain excess grease.'),(437,47,'Drizzle with maple syrup if desired and serve the crispy bacon as a flavorful and indulgent side dish or a topping for other dishes.'),(438,48,'Preheat the oven to 400°F (200°C).'),(439,48,'Line a baking sheet with aluminum foil for easy cleanup.'),(440,48,'Arrange the bacon slices in a single layer on the prepared baking sheet.'),(441,48,'Sprinkle black pepper evenly over the bacon slices.'),(442,48,'Bake in the preheated oven for about 12-15 minutes until the bacon is crispy and browned to your liking.'),(443,48,'Remove from the oven and transfer the bacon to a paper towel-lined plate to drain excess grease.'),(444,48,'Drizzle with maple syrup if desired and serve the crispy bacon as a flavorful and indulgent side dish or a topping for other dishes.'),(445,49,'In a blender or food processor, combine the chopped tomatoes, cucumber, red bell pepper, red onion, and minced garlic.'),(446,49,'Blend until smooth and well combined.'),(447,49,'Pour the tomato mixture into a large bowl.'),(448,49,'Stir in the tomato juice, olive oil, red wine vinegar, salt, and black pepper.'),(449,49,'Cover the bowl and refrigerate for at least 2 hours or until chilled.'),(450,49,'Before serving, garnish the chilled tomato soup with fresh chopped basil leaves.'),(451,49,'Serve the refreshing chilled tomato soup as a delightful side dish or a light appetizer.'),(452,50,'Préchauffer le four à 200°C.'),(453,50,'Assaisonner les poitrines de poulet avec le mélange d\'assaisonnement ranch.'),(454,50,'Cuire le poulet au four pendant 15-20 minutes ou jusqu\'à ce qu\'il soit bien cuit.'),(455,50,'Couper le poulet cuit en morceaux.'),(456,50,'Chauffer les tortillas de maïs dans une poêle.'),(457,50,'Garnir chaque tortilla avec du poulet, de la laitue, des tomates, de la coriandre, de la crème sure et du fromage râpé.'),(458,50,'Replier les tortillas en tacos.'),(459,50,'Servir les Ranch Chicken Tacos chauds.');
/*!40000 ALTER TABLE `steps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_junction`
--

DROP TABLE IF EXISTS `user_role_junction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role_junction` (
                                      `user_id` bigint NOT NULL,
                                      `role_id` bigint NOT NULL,
                                      PRIMARY KEY (`user_id`,`role_id`),
                                      KEY `FKhybpcwvq8snjhbxawo25hxous` (`role_id`),
                                      CONSTRAINT `FK5aqfsa7i8mxrr51gtbpcvp0v1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
                                      CONSTRAINT `FKhybpcwvq8snjhbxawo25hxous` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_junction`
--

LOCK TABLES `user_role_junction` WRITE;
/*!40000 ALTER TABLE `user_role_junction` DISABLE KEYS */;
INSERT INTO `user_role_junction` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `user_role_junction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `user_id` bigint NOT NULL AUTO_INCREMENT,
                         `email` varchar(255) NOT NULL,
                         `firstname` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'zoe.marvin@hotmail.com','user_first_name','$2a$10$IpT3SAR0amGhBzex74xUCOsA7dYAZXQ49kjQN6cp5saa4cD6K5RS.','jacqueline'),(2,'bernardo.nitzsche@hotmail.com','admin_first_name','$2a$10$rMRpH3q/e3eRlnik8I4AieNOKSj1.1Zw/45jdE2cTb/Ua0T8x2EmC','micheline');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-26 14:52:38
