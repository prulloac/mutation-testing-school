-- Table for Races
CREATE TABLE Races (
    RaceID INT PRIMARY KEY,
    RaceName VARCHAR(50) NOT NULL,
    BaseHP INT NOT NULL,
    BaseMP INT NOT NULL,
    BaseStrength INT NOT NULL,
    BaseDefense INT NOT NULL,
    BaseSpeed INT NOT NULL,
    BaseAgility INT NOT NULL,
    BaseStamina INT NOT NULL,
    BaseIntelligence INT NOT NULL,
    BaseWisdom INT NOT NULL
);

-- Table for Classes
CREATE TABLE Classes (
    ClassID INT PRIMARY KEY,
    ClassName VARCHAR(50) NOT NULL,
    BaseHP INT NOT NULL,
    BaseMP INT NOT NULL,
    BaseStrength INT NOT NULL,
    BaseDefense INT NOT NULL,
    BaseSpeed INT NOT NULL,
    BaseAgility INT NOT NULL,
    BaseStamina INT NOT NULL,
    BaseIntelligence INT NOT NULL,
    BaseWisdom INT NOT NULL
);

-- Table for Special Abilities
CREATE TABLE SpecialAbilities (
    AbilityID INT PRIMARY KEY,
    AbilityName VARCHAR(100) NOT NULL
);

-- Intermediate Table for Race Special Abilities
CREATE TABLE RaceAbilities (
    RaceID INT,
    AbilityID INT,
    FOREIGN KEY (RaceID) REFERENCES Races(RaceID),
    FOREIGN KEY (AbilityID) REFERENCES SpecialAbilities(AbilityID),
    PRIMARY KEY (RaceID, AbilityID)
);

-- Intermediate Table for Class Special Abilities
CREATE TABLE ClassAbilities (
    ClassID INT,
    AbilityID INT,
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID),
    FOREIGN KEY (AbilityID) REFERENCES SpecialAbilities(AbilityID),
    PRIMARY KEY (ClassID, AbilityID)
);

-- Table for Characters
CREATE TABLE Characters (
    CharacterID INT PRIMARY KEY,
    RaceID INT,
    Class1ID INT,
    Class2ID INT,
    HP INT,
    MP INT,
    Strength INT,
    Defense INT,
    Speed INT,
    Agility INT,
    Stamina INT,
    Intelligence INT,
    Wisdom INT,
    Luck INT,
    FOREIGN KEY (RaceID) REFERENCES Races(RaceID),
    FOREIGN KEY (Class1ID) REFERENCES Classes(ClassID),
    FOREIGN KEY (Class2ID) REFERENCES Classes(ClassID)
);

-- Table for Consumable Items
CREATE TABLE ConsumableItems (
    ItemID INT PRIMARY KEY,
    ItemName VARCHAR(100) NOT NULL,
    EffectType VARCHAR(50) NOT NULL,
    EffectValue INT NOT NULL -- Can be positive or negative depending on effect
);

-- Table for Equipment Slots
CREATE TABLE EquipmentSlots (
    SlotID INT PRIMARY KEY,
    SlotName VARCHAR(50) NOT NULL
);

-- Table for Equipment
CREATE TABLE Equipment (
    EquipmentID INT PRIMARY KEY,
    SlotID INT,
    ItemName VARCHAR(100) NOT NULL,
    EffectType VARCHAR(50) NOT NULL,
    EffectValue INT NOT NULL, -- Can be positive or negative depending on effect
    Durability INT,
    FOREIGN KEY (SlotID) REFERENCES EquipmentSlots(SlotID)
);

-- Intermediate Table for Character Equipment
CREATE TABLE CharacterEquipment (
    CharacterID INT,
    EquipmentID INT,
    FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID),
    FOREIGN KEY (EquipmentID) REFERENCES Equipment(EquipmentID),
    PRIMARY KEY (CharacterID, EquipmentID)
);
