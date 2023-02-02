# Energy Market Simulator

## Descriere Generală

Aplicația implementează un joc ce simulează o piață energetică la care participă consumatori și distribuitori. Distribuitorii pun la dispoziție contracte la diferite prețuri pentru consumatori, iar consumatorii își aleg contractele în funcție de veniturile lor. Contractele pot expira iar consumatorii și distribuitorii pot deveni falimentari. Datele de intrare și ieșire privind jocul sunt oferite în format JSON.


## How to use

Aplicația rulează prin intermediul clasei Main. Se pot efectua teste de corectitudine folosind clasa Test. Ambele fisiere se găsesc în folderul utils/. Implementarea a fost efectuată în pachetul databases, input și output, jocul fiind simulat in clasa Game.


## Detalii suplimentare despre implementare

Pentru rezolvarea proiectului, vom folosi scenariul următor:
1. Vom citi datele de intrare în clasele de tip *InputData, clase ce se găsesc în pachetul input.
2. Vom muta datele din clasele *InputData in clasele de tip *Data din pachetul databases, deoarece simularea va folosi acest tip de clase.
3. Rulăm simularea jocului curent. Lucrul acesta are loc in metoda runGame din clasa databases.Game.
4. După terminarea simulării, convertim clasele de timp *Input în clase de tip *OutputData, din pachetul output, pregătindu-le astfel pentru scrierea în fișiere de tip JSON.
5. Scriem datele din OutputData in fișiere JSON.

Clasele din pachetul input respectă formatul obiectelor din fișierele JSON ale checker-ului(așadar avem aceleași câmpuri, alături de getteri, setteri și metode toString).

Înainte de simularea propriu-zisă a jocului, are loc conversia de la *InputData la *Data. Lucrul acesta se realizează în constructorul clasei Game, elementele de tip ConsumerData și DistributorData fiind create folosind clasa EnergeticEntityFactory, ea îmbinând SingletonPattern și FactoryPattern. Clasele ConsumerData și DistributorData implementează interfața EnergeticEntity pentru a avea o structura comună, ce poate fi returnată de către clasa Factory.

Simularea jocului respectă următorii pași:
1. Se aduc actualizări(în cazul rundei 0, acest pas nu se aplică).
2. Se caută distribuitorul cu contractul care are cel mai mic preț de pe piață.
3. Se elimină toate contractele care au expirat în runda curentă.
4. Se plătesc veniturile lunare ale consumatorilor.
5. Se semnează contractele cu consumatorii care nu au un contract activ și distribuitorul identificat la punctul b.
6. Consumatorii plătesc taxele către distribuitori. În cazul în care sunt în imposibilitate de plată pentru prima dată, sunt marcați ca având datorii. Dacă aceștia se află la a 2-a rundă consecutivă în care nu pot plăti datoria, sunt marcați ca fiind falimentari, iar contractele lor vor primi o valoare negativă pentru lunile rămase. Se ține cont de faptul că aceștia vor trebui să plătească factura lunii trecute, factura curentă, plus 20% din valoarea facturii.
7. Distribuitorii plătesc costurile de infrastructură și/sau producție. Dacă nu pot plăti suma, sunt marcați ca fiind falimentari.
8. Sunt eliminate toate contractele care nu au fost respectate in ultimele 2 luni.
9. Se verifică dacă mai sunt Distribuitori în joc. În cazul în care toți sunt falimentari, oprim simularea.

După finalizarea simulării, datele din clasa Game sunt transformate in clase de tip *OutputData, din pachetul output, în vederea scrierii rezultatului în fișiere JSON.

Legătura dintre Consumers si Distributors este realizată prin contractele ce pot fi găsite în lista de contracte a unui distribuitor.

Nu s-au tratat cazurile în care, dacă un consumator este restant, își poate căuta un contract mai convenabil(pentru acest lucru se folosesc contractele ce au -2 ca valoare pentru lunile ramase).
