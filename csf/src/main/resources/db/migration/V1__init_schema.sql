
-- Create Role table
CREATE TABLE IF NOT EXISTS roles (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(15) UNIQUE NOT NULL,
    description TEXT
);

-- Create User table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    surname VARCHAR(25),
    role_id BIGINT NOT NULL,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Create Schedule_Wish table
CREATE TABLE IF NOT EXISTS schedule_wish (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    date DATE NOT NULL,
    shift VARCHAR(5) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_wish_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Create Schedule_Assigned table
CREATE TABLE IF NOT EXISTS schedule_assigned (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    date DATE NOT NULL,
    shift VARCHAR(5) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_assigned_user FOREIGN KEY (user_id) REFERENCES users(id),
    assigned_by BIGINT,
    CONSTRAINT fk_assigned_by_user FOREIGN KEY (assigned_by) REFERENCES users(id)
);

-- Seed role 'ADMIN' inside table roles
INSERT INTO roles (name, description)
SELECT 'ADMIN', 'Administrator role'
WHERE NOT EXISTS
    (SELECT 1 FROM roles WHERE name = 'ADMIN');


-- Seed role 'EMPLOYEE' inside table roles
INSERT INTO roles (name, description)
SELECT 'EMPLOYEE', 'Employee role'
WHERE NOT EXISTS
    (SELECT 1 FROM roles WHERE name = 'EMPLOYEE');