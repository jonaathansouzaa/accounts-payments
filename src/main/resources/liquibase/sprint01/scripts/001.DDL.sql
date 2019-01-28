CREATE TABLE accounts_payment (
  accounts_payment_id 	INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(60) 		NOT NULL,
  original_value		DECIMAL(12,2) NOT NULL,
  final_value			DECIMAL(12,2) NULL,
  days_of_delay			INT NULL,
  expiration_date 		DATE NOT NULL,
  date_of_payment 		DATE NOT NULL,
  PRIMARY KEY (accounts_payment_id)
);
