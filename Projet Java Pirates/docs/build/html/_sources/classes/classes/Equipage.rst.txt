Classe Equipage.
=========================

.. toctree::
   :maxdepth: 2

.. class:: public abstract class Equipage()

:Attributs:
   **public static ArrayList<Pirate>** - *pirates* : Contient la liste de tous les pirates de l'équipage.

   .. warning::
      Dans la version naïve, pirates.size() < 26 !
   
   **public static ArrayList<Tresor>** - *tresors* : Contient la liste de tous les trésors de l'équipage.

   **public static Scanner** - *sc* : L'objet Scanner permettant de récupérer les entrées de la sortie standard.

   Cette classe ne peut pas être initialisée. Elle comporte uniquement des méthodes statiques utilitaires permettant de créer un équipage à partir des 2 listes statiques fournies en tant qu'attributs de **Equipage**.

.. method:: public static void echanger(Pirate p1, Pirate p2)

   :param: **Pirate** - *p1* : Le premier pirate avec lequel échanger le trésor.
   :param: **Pirate** - *p2* : Le deuxième pirate avec lequel échanger le trésor.

   Cette méthode permet d'échanger les trésors que possède un **Pirate** *p1* avec un **Pirate** *p2*.

.. method:: public static int calculerCoutNaif()

   :return: **int** : Le coût de la solution naïve en fonction du nombre de pirates jaloux.

   Cette méthode utilise la liste des pirates des attributs statiques.

   .. warning::
      Si vous voulez calculer le coût d'une solution, vous pouvez en appelant d'abord la méthode solutionNaïve(), ou tout simplement en implémentant votre propre méthode de partage de butin.