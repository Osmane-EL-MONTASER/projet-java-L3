Classe Pirate.
=========================

.. toctree::
   :maxdepth: 2

.. class:: public class Pirate(int totalPirates)

:Attributs:
   **private static char** - *n* : Sert à récupérer le prochain nom du pirate en question.

   **private char** - *nom* : Le nom du pirate.
   
   .. note::
      Les noms de pirates sont représentés par une lettre de l'alphabet (A à Z). Dans la version suivante les noms seront remplacés par des chaînes de caractères.

   **private ArrayList<Boolean>** - *relations* : Structure représentant les relations entre les pirates. **True** si les pirates ne s'aiment pas et **False** si les pirates s'entendent bien.

   **private ArrayList<Tresor>** - *preferences* : Structure représentant les préférences du pirate. Les préférences sont rangées dans l'ordre chronologique de ses préférences.

   **private Tresor** *objetRecu* : Ceci est le trésor que le pirate reçoit lors du partage du butin. Peut-être null s'il n'a pas encore reçu de trésor.

Cette classe représente un pirate de l'équipage avec qui partager le butin.
Pour initialiser celui-ci il faut lui passer le nombre total de pirates qu'il y a dans l'équipage en paramètre.

.. warning::

   La version naïve ne peut gérer qu'un maximum de 26 pirates à la fois. Cela sera plus le cas dans la version améliorée.

.. method:: public Pirate(int totalPirates)

   :param: **int** - *totalPirates* : Le nombre total des pirates de l'équipage.

   Le constructeur de la classe **Pirate** génère automatiquement le nom du pirate, initialise ses préférences avec une liste vide et ses relations entre pirates avec une liste de booléens tous initialisés à **False** et son objet reçu de base est **null**.

   .. warning::

      Il ne faut surtout pas oublier d'ajouter les préférences et les relations. Surtout les préférences, sinon le programme lèvera une **NullPointerException**.

.. method:: public boolean relationAvec(Pirate p)

   :param: **Pirate** - *p* : Le pirate avec qui comparer la relation.
   :return: **boolean** : **Vrai** si les pirates ne s'entendent pas, **False** sinon.

.. method:: public boolean estJaloux(ArrayList<Pirate> pirates)

   :param: **ArrayList<Pirate>** - *pirates* : La liste des pirates de l'équipage entier.
   :return: **boolean** : **Vrai** si le pirate est jaloux d'au moins un autre pirate de l'équipage, **False** sinon.

.. method:: public ArrayList<Boolean> getRelations()

   :return: **ArrayList<Boolean>** : La liste des booléens représentant les relations entre pirates. **Vrai** s'ils ne s'entendent pas, **Faux** sinon.

.. method:: public boolean getRelation(int i)

   :param: **int** - *i* : L'index du pirate de la liste des pirates de l'équipage.
   :return: **boolean** : Le booléen représentant la relation avec un pirate de la liste à l'index *i*. **Vrai** s'ils ne s'entendent pas, **Faux** sinon.

.. method:: public ArrayList<Tresor> getPreferences()

   :return: **ArrayList<Tresor>** : La liste de ses préférences rangées dans l'ordre croissant. Plus l'index de l'objet est faible, plus il voudra cet objet.

.. method:: public char getNom()

   :return: **char** : Le nom du *Pirate* correspondant à une lettre de l'alphabet.

.. method:: public Tresor getObjetRecu()

   :return: **Tresor** : Le trésor que le pirate a reçu.

   .. note::

      L'objet reçu est initialisé à **null** dans le constructeur. Pensez donc bien à lui attribuer un objet avant d'utiliser cette valeur !

.. method:: public void setObjetRecu(Tresor objetRecu)

   :param: **Tresor** - *objetRecu* : L'objet que le pirate doit recevoir lors du partage du butin.

.. method:: public void setRelation(int i, boolean b)

   :param: **int** - *i* : L'index du pirate auquel changer la relation.
   :param: **boolean** - *b* : **Vrai** s'ils ne s'entendent pas, **Faux** sinon.

.. method:: public void setPreference(int i, Tresor t)

   :param: **int** - *i* : L'ordre de la préférence pour le **Tresor** *t*.
   :param: **Tresor** - *t* : Le trésor correspondant à la préférence.