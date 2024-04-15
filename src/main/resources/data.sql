-- Populating Races
INSERT INTO Races (RaceID, RaceName, BaseHP, BaseMP, BaseStrength, BaseDefense, BaseSpeed, BaseAgility, BaseStamina, BaseIntelligence, BaseWisdom)
VALUES
    (1, 'Hylian', 100, 50, 10, 10, 10, 10, 10, 10, 10),
    (2, 'Sheikah', 90, 60, 8, 8, 12, 12, 12, 12, 12),
    (3, 'Goron', 120, 30, 15, 15, 5, 5, 15, 5, 5),
    (4, 'Zora', 110, 40, 12, 12, 8, 15, 10, 10, 10),
    (5, 'Deku', 80, 70, 5, 5, 15, 15, 5, 15, 15),
    (6, 'Twili', 95, 55, 9, 9, 11, 11, 11, 13, 13),
    (7, 'Gerudo', 100, 50, 11, 11, 9, 10, 12, 11, 11),
    (8, 'Rito', 105, 45, 10, 10, 11, 13, 10, 12, 12),
    (9, 'Zonai', 125, 25, 18, 18, 4, 4, 18, 3, 3),
    (10, 'Moblin', 115, 35, 14, 14, 7, 7, 14, 7, 7),
    (11, 'Demon', 130, 20, 20, 20, 2, 2, 20, 2, 2);

-- Populating Classes
INSERT INTO Classes (ClassID, ClassName, BaseHP, BaseMP, BaseStrength, BaseDefense, BaseSpeed, BaseAgility, BaseStamina, BaseIntelligence, BaseWisdom)
VALUES
    (1, 'Swordsman', 110, 40, 12, 12, 8, 8, 12, 8, 8),
    (2, 'Ranger', 100, 50, 10, 10, 12, 12, 10, 10, 10),
    (3, 'Magician', 90, 60, 8, 8, 10, 10, 8, 12, 12),
    (4, 'Warlock', 95, 55, 9, 9, 9, 9, 9, 15, 15),
    (5, 'Sorcerer', 85, 65, 7, 7, 7, 7, 7, 18, 18),
    (6, 'Priest', 105, 45, 11, 11, 9, 9, 11, 12, 12),
    (7, 'Monk', 115, 35, 13, 13, 6, 6, 13, 10, 10),
    (8, 'Bard', 95, 55, 10, 10, 11, 11, 10, 12, 12),
    (9, 'Thief', 100, 50, 11, 11, 12, 12, 10, 8, 8),
    (10, 'Paladin', 120, 30, 14, 14, 8, 8, 14, 10, 10);
