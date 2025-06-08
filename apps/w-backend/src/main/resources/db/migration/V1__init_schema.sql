-- changeset splitStatements:false
CREATE TABLE "categories" ("id" UUID NOT NULL, "created_at" BIGINT NOT NULL, "description" VARCHAR(255), "name" VARCHAR(255) NOT NULL, CONSTRAINT "categories_pkey" PRIMARY KEY ("id"));
