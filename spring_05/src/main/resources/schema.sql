DROP TABLE IF EXISTS BOOKS;
DROP TABLE IF EXISTS AUTHORS;
DROP TABLE IF EXISTS GENERS;
CREATE TABLE AUTHORS
(
  ID        IDENTITY PRIMARY KEY,
  NAME      VARCHAR(255),
  LASTNAME  VARCHAR(255)
);
CREATE TABLE GENRES
(
  ID    IDENTITY PRIMARY KEY,
  NAME  VARCHAR(255)
);
CREATE TABLE BOOKS
(
    ID          IDENTITY PRIMARY KEY,
    BOOK_NAME        VARCHAR(255),
    AUTHOR_ID   BIGINT REFERENCES AUTHORS(ID),
    GENRE_ID    BIGINT REFERENCES GENRES(ID)
);
