DROP TABLE IF EXISTS "t_cat_reg";
CREATE TABLE "t_cat_reg" ("name" TEXT DEFAULT (null) ,"amount" DOUBLE,"desc" TEXT);
INSERT INTO "t_cat_reg" VALUES('FUEL',NULL,NULL);
INSERT INTO "t_cat_reg" VALUES('FUEL',-20,'Diesel');
INSERT INTO "t_cat_reg" VALUES('FUEL',-10,'Diesel');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',-3.05,'Toster');
INSERT INTO "t_cat_reg" VALUES('Baba',20,'te taka');
INSERT INTO "t_cat_reg" VALUES('Baba',20,'te taka');
INSERT INTO "t_cat_reg" VALUES('Baba',20,'te taka');
INSERT INTO "t_cat_reg" VALUES('Dqdo',50,'te taka');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',NULL,NULL);
INSERT INTO "t_cat_reg" VALUES('OBSHTI',20,'Coffee');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',50,'GUMI');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('OBSHTI',200,'CAR');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',-2.5,'Toster');
INSERT INTO "t_cat_reg" VALUES('FOOD',NULL,NULL);
DROP TABLE IF EXISTS "t_categories";
CREATE TABLE "t_categories" ("category_name" TEXT check(typeof("category_name") = 'text') );
INSERT INTO "t_categories" VALUES('FUEL');
INSERT INTO "t_categories" VALUES('OBSHTI');
INSERT INTO "t_categories" VALUES('FOOD');
DROP TABLE IF EXISTS "t_users";
CREATE TABLE "t_users" ("userId" INTEGER PRIMARY KEY  NOT NULL , "username" TEXT check(typeof("username") = 'text') , "password" TEXT NOT NULL  check(typeof("password") = 'text') , "age" INTEGER, "email" TEXT check(typeof("email") = 'text') );
INSERT INTO "t_users" VALUES(1,'speksy','speksy',24,'svetoslav.r.toshkov@gmail.com');
INSERT INTO "t_users" VALUES(2,'moni','moni',18,'moni@moni.com');