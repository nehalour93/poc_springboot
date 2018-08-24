CREATE TABLE poc.store(  
   storeId INT NOT NULL AUTO_INCREMENT,  
   storeName VARCHAR(100) NOT NULL, 
   merchantId  INT NOT NULL,
   PRIMARY KEY(storeId ),
   FOREIGN KEY (merchantId) REFERENCES poc.user(id)
);
 