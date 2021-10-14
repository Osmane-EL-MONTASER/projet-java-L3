Classe Pirate.
=========================

.. toctree::
   :maxdepth: 2

.. class:: public class Pirate(int totalPirates)

:Attributs:
   **private static char** - *n* : Sert à récupérer le prochain nom du pirate en question.

   **private char** - *nom* : Le nom du pirate (Lettre de A à Z).

   **private ArrayList<Boolean>** - *relations* : Structure représentant les relations entre les pirates. **True** si les pirates ne s'aiment pas et **False** si les pirates s'entendent bien.

   **private ArrayList<Tresor>** - *preferences* : Structure représentant les préférences du pirate. Les préférences sont rangées dans l'ordre chronologique de ses préférences.

   **private Tresor** *objetRecu* : Ceci est le trésor que le pirate reçoit lors du partage du butin. Peut-être null s'il n'a pas encore reçu de trésor.

Cette classe représente un pirate de l'équipage avec qui partager le butin.
Pour initialiser celui-ci il faut lui passer le nombre total de pirates qu'il y a dans l'équipage en paramètre.

.. warning::

   La version naïve ne peut gérer qu'un maximum de 26 pirates à la fois. Cela sera plus le cas dans la version améliorée.

.. method:: public Pirate(int totalPirates)

   TO-DO

.. method:: public boolean relationAvec(Pirate p)

   TO-DO

.. method:: public boolean estJaloux(ArrayList<Pirate> pirates)

   TO-DO

.. method:: public ArrayList<Boolean> getRelations()

   TO-DO

.. method:: public boolean getRelation(int i)

   TO-DO

.. method:: public ArrayList<Tresor> getPreferences()
   
   TO-DO

.. method:: public char getNom()

   TO-DO