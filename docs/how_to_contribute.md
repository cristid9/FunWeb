How to contribute
=================

Formatul commiturilor
---------------------
* Commiturile sunt, de preferat, cat mai atomice
* Fiecare commit incapsuleaza o modificare punctuala
* Mesajul commiturilor este la timpul trecut si incep cu litera mare
* Mesajul commiturilor trebuie sa fie pe cat posibil mai scurt


Mod general de a contribui
--------------------------
* Se adauga taskul, descrierea lui, responsabilul (sau responsabilii, dupa caz)
  de solutionarea taskului pe [trello](https://trello.com/b/5DrmwIrh/funweb) 

* Unde sunt intrebari sau nelamuriri se discuta pe [slack](http://fiifw.slack.com), in caz ca nu 
  ne putem vedea in lumea fizica.

* Pentru fiecare task individual se creaza un branch nou din `develop`
  Spre exemplu, pentru taskul `hot-fix-in-ui` se creeaza un branch nou
  care deriva din master cu numele `hot-fix-in-ui`

* Se pushuieste branchul respectiv pe `upstream` si se asteapta revizuirea schimbarilor
  de catre un reviewer.

* Dupa acceptului unui reviewer se mergeuieste branchul nou cu `develop`

* La fiecare release nou se mergeuieste `develop` cu `master`. `develop` devenind
  noul `master`.

* Sub nicio forma nu se face `rebase` la cod deja pushuit!

* Nu se face `push --force`

Contribuitori
-------------
* Exista un repo principal. Fiecare contribuitor este adaugat de maintainerul
  repoului.

* Inainte de a da push se deschide un topic nou pe `review-board` si se asteapta
  mesajul `pass` de la un reviewer.
