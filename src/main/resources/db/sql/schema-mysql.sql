CREATE TABLE
    IF NOT EXISTS elaborations (
        id VARCHAR(256),
        name VARCHAR(256),
        state VARCHAR(256),       
        PRIMARY KEY (id)
    ) ENGINE = InnoDB ROW_FORMAT = DYNAMIC;