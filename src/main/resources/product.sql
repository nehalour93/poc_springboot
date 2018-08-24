CREATE TABLE poc.product(  
   productId INT NOT NULL AUTO_INCREMENT,  
   productName VARCHAR(100) NOT NULL,  
   price INT NOT NULL,
   prodStoreId INT NOT NULL,
   PRIMARY KEY ( productId )  
   FOREIGN KEY (prodStoreId) REFERENCES poc.store(storeId)
);

CREATE TABLE poc.payment(  
   paymentId INT NOT NULL AUTO_INCREMENT,  
   paymentType VARCHAR(100) NOT NULL,  
   price INT NOT NULL,
   storeId INT NOT NULL,
   noOfProducts VARCHAR(100) NOT NULL, 
   PRIMARY KEY ( paymentId )  
);
