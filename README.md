# ProjetJavaDBHH

Equipe:

Dominik
Benjamin
Haider
Hugor
Murat

Nota bene:
GitHub ne permet de gerrer gratuitement qu'un maximum de 4 collaborateurs, un 5émé nous a été imposer nous avons donc du utiliser plusieurs repo git :      
- ProjetJavaDBHH pour le backoffice               
- ProjetJava qui etait le git de debut et qui est devenu le git du front office      

Installation:

1 - installer intelij community    
2 - Dans php my admin avec comme login: "root" et mdp: "root" et creer une database nommé projetjavadomben2(vide: juste un" create database projetjavadomben2")       
3 - Cliquez droit sur le sous-dossier "Projet" ouvrir avec intellij      
4 - Attendre le chargement des dependences et des plug ins en fonction de la connection cela peut etre long       
5 - Cliquez sur onglet a droite nommé "maven"  
6 - S'il ne trouve pas de projet cliquez sur + et ajouter le fichier pom.xml    
7 - Cliquez sur les 2 fleches qui forment un rond afin d'actualiser    
8 - Validez le add plugins en pop up si il s'affiche    
9 - Puis dans plugins -> spring-boot>spring-boot:run (dans l'onglet maven) pour lancer le projet   
10- Allez sur la page http://localhost:8082/simville/accueil

Créer le fichier Jar:                 
Il est aussi possible de creer un fichier .JAR                     
Dans l'invite de commander rendez vous dans le dossier projet qui se trouve dans le dossier \ProjetJavaDBHH                         
Entrez la commande:                    
mvn clean package                     
cela var créer un fichier \Projet-0.0.1-SNAPSHOT.jar dans le dossier ProjetJavaDBHH\Projet\target                                  
                  
Ouvrir une invite de commande et taper:                    
java -jar -Xmx1G -Xms1G c:\...\Projet-0.0.1-SNAPSHOT.jar ( ...sont a remplacer par le chemin du fichier)                  
                
une fois le JAR executé ou intellij en spring:run
ouvrir firefox/chrome/edge a l'adresse http://localhost:8082/simville/accueil
