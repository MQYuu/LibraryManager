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

INSERT INTO books (book_id, entry_date, unit_price, quantity, publisher, type, conditionBook, tax) VALUES
('GT001', '2024-10-01', 150000, 50, 'NXB Giáo Dục', 'TextBook', 'Mới', NULL),
('GT002', '2024-10-02', 180000, 60, 'NXB Giáo Dục', 'TextBook', 'Mới', NULL),
('GT003', '2024-10-03', 120000, 40, 'NXB Đại Học', 'TextBook', 'Cũ', NULL),
('GT004', '2024-10-04', 160000, 45, 'NXB Đại Học', 'TextBook', 'Mới', NULL),
('GT005', '2024-10-05', 140000, 55, 'NXB Giáo Dục', 'TextBook', 'Cũ', NULL),
('GT006', '2024-10-06', 175000, 50, 'NXB Đại Học', 'TextBook', 'Mới', NULL),
('GT007', '2024-10-07', 165000, 60, 'NXB Giáo Dục', 'TextBook', 'Cũ', NULL),
('GT008', '2024-10-08', 150000, 50, 'NXB Đại Học', 'TextBook', 'Mới', NULL),
('GT009', '2024-10-09', 120000, 55, 'NXB Giáo Dục', 'TextBook', 'Cũ', NULL),
('GT010', '2024-10-10', 130000, 60, 'NXB Đại Học', 'TextBook', 'Mới', NULL),
('GT011', '2024-10-11', 155000, 50, 'NXB Giáo Dục', 'TextBook', 'Mới', NULL),
('GT012', '2024-10-12', 145000, 55, 'NXB Đại Học', 'TextBook', 'Cũ', NULL),
('GT013', '2024-10-13', 125000, 50, 'NXB Giáo Dục', 'TextBook', 'Mới', NULL),
('GT014', '2024-10-14', 160000, 40, 'NXB Đại Học', 'TextBook', 'Cũ', NULL),
('GT015', '2024-10-15', 170000, 60, 'NXB Giáo Dục', 'TextBook', 'Mới', NULL),
('GT016', '2024-10-16', 145000, 50, 'NXB Đại Học', 'TextBook', 'Mới', NULL),
('GT017', '2024-10-17', 155000, 55, 'NXB Giáo Dục', 'TextBook', 'Cũ', NULL),
('GT018', '2024-10-18', 130000, 60, 'NXB Đại Học', 'TextBook', 'Mới', NULL),
('GT019', '2024-10-19', 140000, 50, 'NXB Giáo Dục', 'TextBook', 'Mới', NULL),
('GT020', '2024-10-20', 160000, 55, 'NXB Đại Học', 'TextBook', 'Cũ', NULL);


INSERT INTO books (book_id, entry_date, unit_price, quantity, publisher, type, conditionBook, tax) VALUES
('RF001', '2024-10-01', 250000, 30, 'NXB Khoa Học', 'ReferenceBook', NULL, 10),
('RF002', '2024-10-02', 300000, 25, 'NXB Khoa Học', 'ReferenceBook', NULL, 12),
('RF003', '2024-10-03', 220000, 40, 'NXB Kinh Tế', 'ReferenceBook', NULL, 8),
('RF004', '2024-10-04', 280000, 35, 'NXB Kinh Tế', 'ReferenceBook', NULL, 15),
('RF005', '2024-10-05', 240000, 50, 'NXB Khoa Học', 'ReferenceBook', NULL, 9),
('RF006', '2024-10-06', 310000, 30, 'NXB Kinh Tế', 'ReferenceBook', NULL, 13),
('RF007', '2024-10-07', 330000, 45, 'NXB Khoa Học', 'ReferenceBook', NULL, 14),
('RF008', '2024-10-08', 250000, 40, 'NXB Kinh Tế', 'ReferenceBook', NULL, 10),
('RF009', '2024-10-09', 270000, 60, 'NXB Khoa Học', 'ReferenceBook', NULL, 11),
('RF010', '2024-10-10', 290000, 30, 'NXB Kinh Tế', 'ReferenceBook', NULL, 10),
('RF011', '2024-10-11', 310000, 50, 'NXB Khoa Học', 'ReferenceBook', NULL, 13),
('RF012', '2024-10-12', 260000, 40, 'NXB Kinh Tế', 'ReferenceBook', NULL, 9),
('RF013', '2024-10-13', 240000, 45, 'NXB Khoa Học', 'ReferenceBook', NULL, 8),
('RF014', '2024-10-14', 300000, 60, 'NXB Kinh Tế', 'ReferenceBook', NULL, 12),
('RF015', '2024-10-15', 320000, 25, 'NXB Khoa Học', 'ReferenceBook', NULL, 14),
('RF016', '2024-10-16', 250000, 35, 'NXB Kinh Tế', 'ReferenceBook', NULL, 11),
('RF017', '2024-10-17', 230000, 50, 'NXB Khoa Học', 'ReferenceBook', NULL, 10),
('RF018', '2024-10-18', 290000, 30, 'NXB Kinh Tế', 'ReferenceBook', NULL, 12),
('RF019', '2024-10-19', 260000, 40, 'NXB Khoa Học', 'ReferenceBook', NULL, 9),
('RF020', '2024-10-20', 280000, 45, 'NXB Kinh Tế', 'ReferenceBook', NULL, 13);


SELECT * FROM books
