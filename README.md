# Projet Java L3 Partage de butin - Version Naïve
## Description
Ceci est la documentation de notre projet Java L3 Partage de butin pour la version naïve. 

**Aucune fonction de la version améliorée ne sera donc décrite ici.**

## Prérequis
Il est important de savoir que pour éxecuter le projet, il vous faudra **la version 12 de Java**.

## Documentation de notre code
Vous trouverez ici la documentation de chaque fonction, méthode, classe ou attribut que nous avons utilisé.
 
### Classe Equipage
 ```
 public abstract class Equipage()
 ```
#### Description
 &nbsp; &nbsp; &nbsp; &nbsp;Représente l'équipage avec tous ses pirates et ses trésors. Cette classe est abstraite est ne peut donc pas être instanciée. Elle sert uniquement à gérer les pirates et les trésors.
### Fonction echanger
 ```
 public static void echanger(Pirate p1, Pirate p2)
 ```
#### Description
 &nbsp; &nbsp; &nbsp; &nbsp;Cette fonction permet d'échanger le trésor du pirate p1 avec celui du pirate p2. Elle est utilisée lors de la résolution du problème de partage.
#### Paramètres
&nbsp; &nbsp; &nbsp; &nbsp;**Pirate p1** - Premier pirate avec qui échanger le trésor
&nbsp; &nbsp; &nbsp; &nbsp;**Pirate p2** - Deuxième pirate avec qui échanger le trésor
### Classe Tresor
 ```
 public class Tresor()
 ```
 #### Description
 &nbsp; &nbsp; &nbsp; &nbsp;Représente un trésor de l'équipage. Il possède son propre nom composé du caractère 'o' suivi du numéro correspondant au nombre d'objets déjà instanciés au moment de son instanciation. 
 **Le nommage des objets est donc automatique**.
