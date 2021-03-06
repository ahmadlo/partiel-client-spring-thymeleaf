# partiel_spring_thymeleaf
Evolution du projet Gestion Ecole avec springMVC et thymeleaf

## Développeurs 

- [Benjamin Boutrois](https://github.com/BenjaminBoutrois)
- [Ahmadou LO](https://github.com/ahmadlo)
- [Giovanni Simon](https://github.com/GioSimon)

## PREREQUIS: 

Installer [maven](https://maven.apache.org/download.cgi)

Avoir un serveur de Base de données MYSQL ou MARIADB

## INSTALLATION:

### ETAPE 1 : 

  Clonez le projet a partir du repository

  
### ETAPE 2 : 

* Ouvrir le fichier *application.properties* ( Partiel1_2021-Serveur/src/main/resources) 

* Verifier et Mettre à jour les informations de connexions à la base de données ( username et password )

* Vérifier et Mettre à jour  le port de connexion avec votre serveur de base de données ( datasource.url) 

* Créer une base de données vide sous le nom *partielspringboot* 

* Importer le fichier de base de données *partielspringboot.sql* dans votre Serveur de base de données  
  
 Maintenant vous pouvez passer à l'étape suivante à savoir Démarrer le Serveur 
    
 ### ETAPE 3 :
 
 #### Démarrer le Serveur 
  
   - Ouvrir une fenêtre de commande à partir du dossier Partiel1_2021-Serveur
    
   - Lancer la commande suivante pour démarrer le Serveur :
    
      > mvn spring-boot:run 
      
   - Vérifiez bien que le Serveur tourne
   
   - Vérifier vos paramètres en cas d'erreurs ( n'hésitez pas à nous contacter pour plus de support )
    
   - Lancer votre  navigateur et aller à l'url pour vérifier : [webservice-api](http://localhost:8004/SpringMVC/servlet/course/getAll)
     
   - Vérifier que la votre base de données n'est pas vide et ajouté une Personne de type USER avec comme Profil D ( Directeur ) pour pouvoir accéder à l'application client


### ETAPE 4 :
 
 #### Démarrer l'application Client 
  
   - Ouvrir une fenêtre de commande à partir de la racine du projet 
    
   - Lancer la commande suivante pour démarrer le l'application Client :
    
      > mvn spring-boot:run 
    
   - Vérifiez bien que le l'application Client tourne  ( n'hésitez pas à nous contacter pour plus de support )
    
   - Lancer votre  navigateur et aller à l'url pour vérifier : [gesco-client](http://localhost:8080/)
     
   - Se connecter avec les infos  login : admin  - password : admin

Maintenant vous pouvez profitez pleinement !!!


