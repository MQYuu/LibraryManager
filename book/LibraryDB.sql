CREATE DATABASE LibraryDB;

USE LibraryDB;

CREATE TABLE books (
    book_id VARCHAR(50) PRIMARY KEY,
    entry_date DATE,
    unit_price DOUBLE,
    quantity INT,
    publisher VARCHAR(100),
    type VARCHAR(50),        -- "TextBook" or "ReferenceBook"
    conditionBook VARCHAR(10),    -- only for TextBook
    tax DOUBLE                -- only for ReferenceBook
);


INSERT INTO books (book_id, entry_date, unit_price, quantity, publisher, type, conditionBook)
VALUES
    ('BK003', '2024-11-19', 120.00, 20, 'Publisher C', 'TextBook', 'Used'),
    ('BK004', '2024-11-20', 180.00, 8, 'Publisher D', 'TextBook', 'New');

INSERT INTO books (book_id, entry_date, unit_price, quantity, publisher, type, tax)
VALUES
    ('BK005', '2024-11-21', 250.00, 12, 'Publisher E', 'ReferenceBook', 20.00),
    ('BK006', '2024-11-22', 300.00, 3, 'Publisher F', 'ReferenceBook', 25.00);
    
	

SELECT * FROM books
