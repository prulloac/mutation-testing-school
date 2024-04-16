-- Populating Races
INSERT INTO franchise (id, name) VALUES
(1, 'Star Wars'),
(2, 'Harry Potter'),
(3, 'Marvel Cinematic Universe'),
(4, 'The Lord of the Rings'),
(5, 'Pokémon');

-- Populating Person table
INSERT INTO person (id, name, nationality, birth_date) VALUES
(1, 'George Lucas', 'American', '1944-05-14'),
(2, 'J.K. Rowling', 'British', '1965-07-31'),
(3, 'Stan Lee', 'American', '1922-12-28'),
(4, 'J.R.R. Tolkien', 'British', '1892-01-03'),
(5, 'Satoshi Tajiri', 'Japanese', '1965-08-28');

-- Populating Book table
INSERT INTO book (author_id, franchise_id, id, release_date, status, category, isbn, name, publisher) VALUES
(2, 2, 1, '1997-06-26', 1, 'Fantasy', '978-0-7475-3269-6', 'Harry Potter and the Philosopher''s Stone', 'Bloomsbury Publishing'),
(2, 2, 2, '1998-07-02', 1, 'Fantasy', '978-0-7475-3849-0', 'Harry Potter and the Chamber of Secrets', 'Bloomsbury Publishing'),
(2, 2, 3, '1999-07-08', 1, 'Fantasy', '978-0-7475-4216-9', 'Harry Potter and the Prisoner of Azkaban', 'Bloomsbury Publishing'),
(2, 2, 4, '2000-07-08', 2, 'Fantasy', '978-0-7475-4624-2', 'Harry Potter and the Goblet of Fire', 'Bloomsbury Publishing'),
(4, 4, 5, '1954-07-29', 1, 'Fantasy', '978-0-618-34625-4', 'The Lord of the Rings: The Fellowship of the Ring', 'Houghton Mifflin'),
(4, 4, 6, '1954-11-11', 1, 'Fantasy', '978-0-618-34626-1', 'The Lord of the Rings: The Two Towers', 'Houghton Mifflin'),
(4, 4, 7, '1955-10-20', 1, 'Fantasy', '978-0-618-34627-8', 'The Lord of the Rings: The Return of the King', 'Houghton Mifflin');
