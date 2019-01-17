# Streash
Interface shell qui permet de manipuler des streams de nombres rationnels.
## Utilisation de la console
### Ecriture d'une expression
L'écriture d'une expression doit se faire en utilisant la `notation polonaise inverse` (RPN: Reverse Polish Notation).
```
a = expression
```
Permet d'affecter la valeur calculée de l'espression dans la variable locale a.
```
expression
```
Ecrire une expression sans mettre de variable au début affecte la valeur calculée de l'expression à une variable temporaire (tmp0, tmp1...).
### Exemples
Affecter à une variable la somme de tous les nombres entre 1 et 100 (inclus):
```
somme = 1 <integers> 0 99 <slice> <sum>
```
Générer un stream d'entiers de 1 jusqu'à l'infini, le mélanger, garder les 10 premiers termes et le trier:
```
1 <integers> -1 <shuffle> 0 10 <slice> <sorted>
```
On peut réutiliser une variable affectée:
```
suite = 1 <integers> 0 99 <slice>
somme = suite <sum>       # 5050
moyenne = suite <average> # 50.5
produit = suite <product> # 100!
```
### Liste des méta-instructions
- ```/printvar x``` : affiche le contenu de la variable x. Si x est un stream, affiche l'enchaînement de sources et de traitements définissant ce stream. Si x est un rationnel, affiche le numérateur, le dénominateur ainsi qu'une valeur flottante approchée.
- ```/printvars``` : affiche le contenu de toutes les variables définies.
- ```/quit``` : permet de quitter le shell.
## Liste des fonctions disponnibles
### Manipulation des rationnels
- ```x y <add>``` : permet d'additionner des rationnels (x + y).
- ```x y <sub>``` : permet de soustraire des rationnels (x - y).
- ```x y <mul>``` : permet de multiplier des rationnels (x * y).
- ```x y <div>``` : permet de diviser des rationnels (x / y).
#### Sources de streams 
- ```x <integers>``` : produit un stream avec les entiers de x jusqu'à plus l'infini.
- ```x <revintegers>``` : produit un stream avec les entiers de x jusqu'à moins l'infini.
- ```x y <fibo>``` : produit un stream avec le snombres de Fibonacci avec x et y comme deux premiers termes.
- ```x y seed <random>``` : produit un stream d'entiers pseudo-aléatoires compris entre x et y. Si le seed est différent de -1, il est utilisé pour initialiser le générateur d enombres pseudo-aléatoires. Si le seed est -1, un seed pseudo-aléatoire est utilisé.
### Traitements sur les streams
- ```stream x b <slice>``` : extrait une tranche du stream débutant à l'élément d'indice x (inclus) et se terminant à l'élément d'indice y (inclus).
- ```stream x <repeat>``` : répète x fois un stream. Si x n'est pas un entier (rationnel non entier), il est possible que la dernière répétition du stream ne soit pas complète (par exemple ```stream 1/2 <repeat>``` permet d'obtenir que la moitié des termes d'un stream).
- ```stream1 stream2 <concat>``` : concatène deux streams.
- ```stream1 stream2 <inter>``` : retourne les éléments de stream1 qui sont également présents dans stream2 (dans l'ordre de stream1).
- ```stream <sorted>``` : trie un stream.
- ```stream seed <shuffle>``` : mélange un stream (avec un paramètre seed utilisé pour l'initialisation du générateur de nombres pseudo-aléatoires).
### Puits
- ```stream <print>``` : écrit les éléments du stream (un élément par ligne).
- ```stream <len>``` : retourne la longueur d'un stream.
- ```stream <sum>``` : retourne la somme de tous les éléments d'un stream.
- ```stream <product>``` : retourne le produit de tous les éléments d'un stream.
- ```stream <average>``` : retourne la moyenne d'un stream.
- ```stream <max>``` : retourne le maximum d'un stream.
- ```stream <min>``` : retourne le minimum d'un stream.
- ```stream x <get>``` : retourne l'élément d'indice x du stream.

 
