CREATE TABLE Transactions(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    card VARCHAR(50) NOT NULL,
    paymentValue DECIMAL(10,2) NOT NULL,
    establishment VARCHAR(50) NOT NULL,
    paymentType VARCHAR(50) NOT NULL,
    invoces VARCHAR(50) NOT NULL,
    nsu VARCHAR(50) NOT NULL,
    authorizationCode VARCHAR(50),
    status VARCHAR(50) NOT NULL,
    dateTime DATETIME NOT NULL
)