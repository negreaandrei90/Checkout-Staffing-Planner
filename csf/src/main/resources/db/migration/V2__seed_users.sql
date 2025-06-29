-- the admin account will have email = 'admin@admin.com' and password = 'admin'
-- all employees have email = 'employee<nr>@employee.com' and password = 'employee'
-- seeding a total of 1 admin & 6 employees

-- Seed admin account in 'user' table
INSERT INTO users(name, email, password, role_id)
SELECT 'admin', 'admin@admin.com', '$2a$10$5ApfVmPpPtupoleKMJQVROAw1ADrMRUtkkz3Glx8SygyYdEqn4bUq', 1
WHERE NOT EXISTS
    (SELECT 1 FROM users WHERE email = 'admin@admin.com');

-- Seed employee1 account in 'user' table
INSERT INTO users(name, email, password, role_id)
SELECT 'employee1', 'employee1@employee.com', '$2a$10$CmfE3TstJPxumWcc2Kb1tOUBhP/HHokqP9C7IftymJ5fY5pPahlYi', 2
WHERE NOT EXISTS
    (SELECT 1 FROM users WHERE email = 'employee1@employee.com');

-- Seed employee2 account in 'user' table
INSERT INTO users(name, email, password, role_id)
SELECT 'employee2', 'employee2@employee.com', '$2a$10$CmfE3TstJPxumWcc2Kb1tOUBhP/HHokqP9C7IftymJ5fY5pPahlYi', 2
WHERE NOT EXISTS
    (SELECT 1 FROM users WHERE email = 'employee2@employee.com');

-- Seed employee3 account in 'user' table
INSERT INTO users(name, email, password, role_id)
SELECT 'employee3', 'employee3@employee.com', '$2a$10$CmfE3TstJPxumWcc2Kb1tOUBhP/HHokqP9C7IftymJ5fY5pPahlYi', 2
WHERE NOT EXISTS
    (SELECT 1 FROM users WHERE email = 'employee3@employee.com');

-- Seed employee4 account in 'user' table
INSERT INTO users(name, email, password, role_id)
SELECT 'employee4', 'employee4@employee.com', '$2a$10$CmfE3TstJPxumWcc2Kb1tOUBhP/HHokqP9C7IftymJ5fY5pPahlYi', 2
WHERE NOT EXISTS
    (SELECT 1 FROM users WHERE email = 'employee4@employee.com');

-- Seed employee5 account in 'user' table
INSERT INTO users(name, email, password, role_id)
SELECT 'employee5', 'employee5@employee.com', '$2a$10$CmfE3TstJPxumWcc2Kb1tOUBhP/HHokqP9C7IftymJ5fY5pPahlYi', 2
WHERE NOT EXISTS
    (SELECT 1 FROM users WHERE email = 'employee5@employee.com');

-- Seed employee6 account in 'user' table
INSERT INTO users(name, email, password, role_id)
SELECT 'employee6', 'employee6@employee.com', '$2a$10$CmfE3TstJPxumWcc2Kb1tOUBhP/HHokqP9C7IftymJ5fY5pPahlYi', 2
WHERE NOT EXISTS
    (SELECT 1 FROM users WHERE email = 'employee6@employee.com');