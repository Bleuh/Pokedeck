# Pokedeck en Java
Le but de ce projet est de modéliser et implémenter un Pokedex en Java.
**Attention**, dans ce projet j'ai utilisé la library externe "json-simple" pour enregistrer mes données en json.
Il est possible que la library soit manquante, si cela se produit il faudra l'ajouté manuellement.


----------


## Somaire
 - [Architecture (Diagramme de classe)](#architecture)
 - [Fonctionnement et fonction implémenté](#fonctionnement-et-fonction-implémenté)
  - [Utilisateur](#utilisateur)
  - [Technique](#technique)
 - [Conclusion](#conclusion)


----------

##Architecture 

![enter image description here](https://lh3.googleusercontent.com/-5PT7FlbCd2E/WLgh0woxi1I/AAAAAAAACTA/9VddTEUSj4cr_LJPyQSlVjq0Kodk3sZ_wCLcB/s0/Diagramme+de+classe+java.png "Diagramme de classe java.png")
Card est abstract.

Pokemon contient un Type et plusieurs Abilitie.

FileUtil gère les fichiers de sauvegarde et contiens un liste de Pokemon.

Window gère l'interface utilisateur.


----------


##Fonctionnement et fonction implémenté

###Utilisateur
L'utilisateur arrive sur l'accueil. Il a le choix entre 2 possibilités, ajouter un Pokémon ou bien le voir / modifier.

**Ajouter un Pokémon**

Sur cette page l'utilisateur peut seulement remplir le formulaire pour enregistrer un pokemon. Par la suite il est redirigé sur l'accueil.

**Voir/modifier un Pokémon**

Sur celle-ci il peut voir à partir de la liste déroulante un pokemon de son choix, les informations affichées peuvent être changées, s'il appuie sur "modifier" le pokedex enregistrera les changements apporter.
L'utilisateur peut aussi chercher un Pokemon par rapport à son id ou son nom. Les 2 sont aussi bien indépendants que complémentaires.

Le bouton accueil est disponible partout pour revenir à l'accueil.

###Technique
**Package Card**

Fonctionnement classique comme dans le mini projet RPG avec les armes.
Quelque spécificité :
La classe **Type** contient une liste static *pokemonType* qui contient la liste de tous les types du jeu Pokemon. Elle permet d'afficher la liste des types dans la liste déroulante lors d'ajouts d'un pokemon.
La classe **Item** devait représenter les objets du jeu Pokemon mais n'ai pas utilisé pour l'instant.
La classe **Abilitie** devait représenter chaque capacité d'une carte Pokemon mais n'est pas utilisé pour l'instant.

**Fichier**

Toutes les données sont sauvegardé dans un ficher texte au format JSON.
La sauvegarde se produit lors de l'ajout et la modification d'une carte pour permettre l'ouverture de plusieurs fenêtres.
Lors de l'ouverture de l'application, la classe **FileUtil** charge toutes les pokemon du fichier texte data.txt dans une liste qui sera utilisé dans la vue.
J'ai utilisé le format JSON pour pouvoir enregistrer les classes plus facilement.

**L'interface utilisateur**

Cela a été une partie longue et difficile.
Lorsque la classe **Window** est créée, pour faciliter le code, elle appelle la fonction *addToFrame()* qui va par la suite appeler chaque fonction *GetTemplate()*.  Chacune de ces fonctions retourne le template créé. Elles sont par la suite ajoutées dans un **CardLayout** qui sert de gestionnaire ici. La classe **ChangeCard** permet de changer de template grâce au nom que je lui donne. La fonction *pokemonUpdate()* permet de mettre à jour les template quand un pokemon est ajouté ou mis à jour.


----------


##Conclusion
Le projet était intéressant, peut-être un peu difficile à mettre en place sur la partie utilisateur. J'aurais aimé allez jusqu'au bout de ma modélisation de données, c'est-à-dire mettre en place les capacités. Hélas, je les avais mis avant de passer à l'interface, mais par la suite j'ai eu beaucoup de mal à mettre en place l'interface, je l'ai abandonnée en route.
