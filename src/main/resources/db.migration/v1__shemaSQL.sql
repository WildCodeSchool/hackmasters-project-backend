--
-- Table structure for table "allergens"
--

DROP TABLE IF EXISTS "allergens";
CREATE TABLE "allergens"
(
    "id"            serial PRIMARY KEY,
    "allergen_name" varchar(255) DEFAULT NULL
);

--
-- Table structure for table "category"
--

DROP TABLE IF EXISTS "category";
CREATE TABLE "category"
(
    "id"            serial PRIMARY KEY,
    "category_name" varchar(255) DEFAULT NULL
);

--
-- Table structure for table "country"
--

DROP TABLE IF EXISTS "country";
CREATE TABLE "country"
(
    "id"           serial PRIMARY KEY,
    "country_name" varchar(255) DEFAULT NULL
);

--
-- Table structure for table "diets"
--

DROP TABLE IF EXISTS "diets";
CREATE TABLE "diets"
(
    "id"        serial PRIMARY KEY,
    "diet_name" varchar(255) DEFAULT NULL
);

--
-- Table structure for table "ingredients"
--

DROP TABLE IF EXISTS "ingredients";
CREATE TABLE "ingredients"
(
    "id"              serial PRIMARY KEY,
    "ingredient_name" varchar(255) DEFAULT NULL
);

DROP TABLE IF EXISTS "recipes";
CREATE TABLE "recipes"
(
    "id"          serial PRIMARY KEY,
    "recipe_name" varchar(255)   DEFAULT NULL,
    "category_id" integer        DEFAULT NULL,
    "country_id"  integer        DEFAULT NULL,
    "prep_time"   integer        DEFAULT NULL,
    "cook_time"   integer        DEFAULT NULL,
    "price"       numeric(38, 2) DEFAULT NULL,
    "imgurl"      varchar(255)   DEFAULT NULL,
    "description" varchar(255)   DEFAULT NULL,
    CONSTRAINT "recipes_country_id_fkey" FOREIGN KEY ("country_id") REFERENCES "country" ("id"),
    CONSTRAINT "recipes_category_id_fkey" FOREIGN KEY ("category_id") REFERENCES "category" ("id")
);

--
-- Table structure for table "recipe_allergens"
--


DROP TABLE IF EXISTS "recipe_allergens";
CREATE TABLE "recipe_allergens"
(
    "id"          serial PRIMARY KEY,
    "recipe_id"   integer DEFAULT NULL,
    "allergen_id" integer DEFAULT NULL,
    CONSTRAINT "recipe_allergens_recipe_id_fkey" FOREIGN KEY ("recipe_id") REFERENCES "recipes" ("id"),
    CONSTRAINT "recipe_allergens_allergen_id_fkey" FOREIGN KEY ("allergen_id") REFERENCES "allergens" ("id")
);

--
-- Table structure for table "recipe_diets"
--

DROP TABLE IF EXISTS "recipe_diets";
CREATE TABLE "recipe_diets"
(
    "id"        bigserial PRIMARY KEY,
    "recipe_id" integer DEFAULT NULL,
    "diet_id"   integer DEFAULT NULL,
    CONSTRAINT "recipe_diets_recipe_id_fkey" FOREIGN KEY ("recipe_id") REFERENCES "recipes" ("id"),
    CONSTRAINT "recipe_diets_diet_id_fkey" FOREIGN KEY ("diet_id") REFERENCES "diets" ("id")
);

--
-- Table structure for table "recipes"
--



--
-- Table structure for table "recipes_ingredients"
--

DROP TABLE IF EXISTS "recipes_ingredients";
CREATE TABLE "recipes_ingredients"
(
    "id"            bigserial PRIMARY KEY,
    "recipe_id"     integer      DEFAULT NULL,
    "ingredient_id" integer      DEFAULT NULL,
    "quantity"      varchar(255) DEFAULT NULL,
    CONSTRAINT "recipes_ingredients_recipe_id_fkey" FOREIGN KEY ("recipe_id") REFERENCES "recipes" ("id"),
    CONSTRAINT "recipes_ingredients_ingredient_id_fkey" FOREIGN KEY ("ingredient_id") REFERENCES "ingredients" ("id")
);

--
-- Table structure for table "steps"
--

DROP TABLE IF EXISTS "steps";
CREATE TABLE "steps"
(
    "id"               serial PRIMARY KEY,
    "recipe_id"        integer      DEFAULT NULL,
    "step_description" varchar(255) DEFAULT NULL,
    CONSTRAINT "steps_recipe_id_fkey" FOREIGN KEY ("recipe_id") REFERENCES "recipes" ("id")
);
