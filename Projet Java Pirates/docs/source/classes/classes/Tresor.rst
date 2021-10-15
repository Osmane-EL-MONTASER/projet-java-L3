Classe Tresor.
=========================

.. toctree::
   :maxdepth: 2

.. class:: public class Tresor()

:Attributs:
   **private static int** - *n* : Sert à récupérer le numéro du prochain trésor. Ce numéro sert à nommer le nom du trésor.

   .. note::
      Dans une prochaine version cette variable disparaîtra et les noms seront directement extrait d'un fichier texte comportant des noms de trésors.

   **private String** - *nom* : Le nom du trésor.
   
   **private int** - *num* : Le numéro du trésor.


Cette classe représente un trésor récupéré par l'équipage. Il possède un nom composé du caractère 'o' suivi du numéro du trésor.

.. method:: public Tresor()

   Le constructeur de la classe **Tresor** génère automatiquement le nom du trésor.

.. method:: @Override public String toString()

   Cette méthode permet de retourner une chaîne de caractère contenant le nom du trésor à partir d'une référence de type **Tresor**.