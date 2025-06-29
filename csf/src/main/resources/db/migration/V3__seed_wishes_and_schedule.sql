-------------------------------Seed Wishes----------------------------------

-- Seed wishes employee1 - EARLY - 2025-08-30
INSERT INTO schedule_wish(date, shift, user_id)
SELECT '2025-08-30', 'EARLY', 2
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_wish WHERE user_id = 2 AND date = '2025-08-30');

-- Seed wishes employee2 - EARLY - 2025-08-30
INSERT INTO schedule_wish(date, shift, user_id)
SELECT '2025-08-30', 'EARLY', 3
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_wish WHERE user_id = 3 AND date = '2025-08-30');

-- Seed wishes employee3 - EARLY - 2025-08-30
INSERT INTO schedule_wish(date, shift, user_id)
SELECT '2025-08-30', 'EARLY', 4
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_wish WHERE user_id = 4 AND date = '2025-08-30');

-- Seed wishes employee4 - LATE - 2025-08-30
INSERT INTO schedule_wish(date, shift, user_id)
SELECT '2025-08-30', 'LATE', 5
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_wish WHERE user_id = 5 AND date = '2025-08-30');

-- Seed wishes employee5 - LATE - 2025-08-30
INSERT INTO schedule_wish(date, shift, user_id)
SELECT '2025-08-30', 'LATE', 6
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_wish WHERE user_id = 6 AND date = '2025-08-30');

-- Seed wishes employee1 - LATE - 2025-08-31
INSERT INTO schedule_wish(date, shift, user_id)
SELECT '2025-08-31', 'LATE', 2
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_wish WHERE user_id = 2 AND date = '2025-08-31');



-------------------------------Seed Schedule----------------------------------

-- Seed schedule_assigned employee1 - EARLY - 2025-07-28
INSERT INTO schedule_assigned(date, shift, user_id, assigned_by)
SELECT '2025-07-28', 'EARLY', 2, 1
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_assigned WHERE user_id = 2 AND date = '2025-07-28');

-- Seed schedule_assigned employee2 - EARLY - 2025-07-28
INSERT INTO schedule_assigned(date, shift, user_id, assigned_by)
SELECT '2025-07-28', 'EARLY', 3, 1
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_assigned WHERE user_id = 3 AND date = '2025-07-28');

-- Seed schedule_assigned employee3 - LATE - 2025-07-28
INSERT INTO schedule_assigned(date, shift, user_id, assigned_by)
SELECT '2025-07-28', 'LATE', 4, 1
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_assigned WHERE user_id = 4 AND date = '2025-07-28');

-- Seed schedule_assigned employee4 - LATE - 2025-07-28
INSERT INTO schedule_assigned(date, shift, user_id, assigned_by)
SELECT '2025-07-28', 'LATE', 5, 1
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_assigned WHERE user_id = 5 AND date = '2025-07-28');

-- Seed schedule_assigned employee5 - EARLY - 2025-07-29
INSERT INTO schedule_assigned(date, shift, user_id, assigned_by)
SELECT '2025-07-29', 'LATE', 6, 1
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_assigned WHERE user_id = 6 AND date = '2025-07-29');

-- Seed schedule_assigned employee1 - LATE - 2025-07-29
INSERT INTO schedule_assigned(date, shift, user_id, assigned_by)
SELECT '2025-07-29', 'LATE', 2, 1
WHERE NOT EXISTS
    (SELECT 1 FROM schedule_assigned WHERE user_id = 2 AND date = '2025-07-29');