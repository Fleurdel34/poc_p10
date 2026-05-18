# poc_p10

## backend[demo]
Le projet est développé avec spring boot 4.0.0 maven plugin et JAVA 21 est requis

### Débuter le projet back
#### Dependences
org.springframework.boot: spring-boot-starter-data-jdbc spring-boot-starter-data-jpa spring-boot-starter-web spring-boot-devtools spring-boot-starter-tomcat 
org.projectlombok: lombok

#### Base de données en local:
org.mariadb.jdbc: mariadb-java-client uniquement pour le PoC en local


#### Base de données en production:
org.postgresql: postgresql 

##### A configurer dans le fichier "application.properties":
spring.datasource.driver-class-name=spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=${DATA_URL}
spring.datasource.username=${DATA_USERNAME}
spring.datasource.password=${DATA_PASSWORD}
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

##### fichier .env à créer pour la gestion des secrets et pour la création de variable d'environnement:
DATA_URL=jdbc:postgresql://localhost:5432/ma_base (exemple en local)
DATA_USERNAME=nom_de_l_administrateur (ne jamais mettre "root")
DATA_PASSWORD=mot_de_passe (ne jamais mettre "password")


#### Démarrer le project
mvn clean install mvn spring-boot:run

#### Package de l'application
controller: API REST
pojo: entité (pojo Message: colonne user_id à mettre en nullable = false après en production)
repository = couche interaction avec la base de données
service = logique métier

