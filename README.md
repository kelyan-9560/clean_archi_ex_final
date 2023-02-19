# Clean Architecture : Exercice Final


## 1. Membre du groupe :
* `Lucas JEHANNO`
* `Kélyan BERVIN`
* `Mouhamadou DIONE`

## 2. Schéma de l'architecture :
[Clean Archi Exercice Final.drawio.pdf](schema%2FClean%20Archi%20Exercice%20Final.drawio.pdf)

## 3. Fontionnement de l'application :
    
- Le code est découpé en 3 packages : `domain`, `infrastructure` et `utils`.
- Dans le package `domain` se trouve les classes métiers et les interfaces de services.
    - `exceptions` : contient les exceptions personnalisées.
    - `model` : contient les classes métiers.
    - `services` : contient le service de taches ainsi que les classes nécessaire afin de parser l'entrée utilisateur.
    - `kernel` : contient l'interface de notre repository.
  
- Dans le package `infrastructure` se trouve les classes de persistance.
- Dans le package `utils` se trouve les classes utilitaires.
    - `DateUtils` : permet de formater une date.