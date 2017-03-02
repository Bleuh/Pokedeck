# Pokedeck en Java
Le but de se projet est de modélisé et implémenté un Pokedex en Java.
**Attention**, dans ce projet j'ai utilisé la library externe "json-simple" pour enregistré mes données en json.
Il est possible que la library sois manquant, si cela se produit il faudra l'ajouté manuellement.


----------


## Somaire
 - Architecture (Diagramme de classe)
 - Fonctionnement et fonction implémenté
  - Utilisateur
  - Technique
 - Conclusion


----------

##Architecture 

![enter image description here](https://lh3.googleusercontent.com/-5PT7FlbCd2E/WLgh0woxi1I/AAAAAAAACTA/9VddTEUSj4cr_LJPyQSlVjq0Kodk3sZ_wCLcB/s0/Diagramme+de+classe+java.png "Diagramme de classe java.png")
Card est abstract.
Pokemon contient un Type et plusieurs Abilitie.
FileUtil gère les fichiers de sauvegarde et contiens un liste de Pokemon.
Window gère l'interface utilisateur.

##Fonctionnement et fonction implémenté

###Utilisateur
L'utilisateur arrive sur l'accueil. Il a le choix entre 2 possibilité, ajouter un Pokémon ou bien le voir / modifier.

**Ajouter un Pokémon**
Sur cette page l'utilisateur peut seulement remplir le formulaire pour enregistré un pokemon. Par la suite il est rediriger sur l'accueil.

**Voir/modifier un Pokémon**
Sur celle ci il peut voir a partir de la liste deroulante un pokemon de son choix, les informations afficher peuvent être changer, si il appuis sur "modifier" le pokedex enregistrera les changement apporter.
L'utilisateur peut aussi chercher un Pokemon par rapport a son id ou son nom. Les 2 sont aussi bien indépendant que complémentaire.

Le bouton accueil est disponible partout pour revenir a l'accueil.

###Technique
**Package Card**
Fonctionnement classique comme dans le mini projet RPG avec les armes.
Quelque spécificité :
La classe **Type** contient une liste static *pokemonType* qui contient la liste de tout les types du jeu Pokemon. Elle permet d'affiché la liste des type dans la liste déroulante lors d'ajout d'un pokemon.
La classe **Item** devais représenté les objets du jeu Pokemon mais n'ai pas utilisé pour l'instant.
La classe **Abilitie** devais représenté chaque capacité d'une carte Pokemon mais n'ai pas utilisé pour l'instant.

**Fichier**
Toutes les données sont sauvegarder dans un ficher texte au format JSON.
La sauvegarde se produit lors de l'ajout et la modification d'une carte pour permettre l'ouverture de plusieurs fenêtres.
Lors de l'ouverture de l'application, la classe **FileUtil** charge toute les pokemon du fichier texte data.txt dans une liste qui sera utilisé dans la vue.
J'ai utilisé le format JSON pour pouvoir enregistré les classes plus facilement.

**L'interface utilisateur**
Cela a été une partie longue et difficile.
