# DQ-Express-2-Codebeispiele

## Allgemein

Diese Codebeispiele sollen den generellen Kommunikationsweg für DQ Express 2 in mehreren Programmiersprachen demonstrieren.
Zu beachten ist jedoch, dass die hier gezeigten Beispiele lediglich zur Demonstration geeignet sind. Bevor der Beispielcode auf einem Produktivstem umgesetzt wird, sollte dieser ausgiebig evaluiert werden.

Folgende Programmiersprachen sind als Beispiel vorhanden:
* [C#](https://github.com/Omikron-Data-Quality-GmbH/DQ-Express-2-Codebeispiele/tree/master/C%23)
* [Java](https://github.com/Omikron-Data-Quality-GmbH/DQ-Express-2-Codebeispiele/tree/master/Java)
* [Javascript](https://github.com/Omikron-Data-Quality-GmbH/DQ-Express-2-Codebeispiele/tree/master/Javascript)
* [PHP](https://github.com/Omikron-Data-Quality-GmbH/DQ-Express-2-Codebeispiele/tree/master/PHP)

## Verwendung

Damit die Beispiele verwendet werden können, müssen folgende Parameter entsprechend angepasst werden:

| Parameter | Bedeutung |
| ------ | ------ |
| OAuth Client Id | Eine eindeutige Id des Clients. Client Id und Secret dienen zur Autorisierung des Clients. Nur vertraute Clients können die DQ Express 2 Api verwenden. |
| OAuth Client Secret | Ein eindeutiges Kennwort des Clients. Client Id und Secret dienen zur Autorisierung des Clients. Nur vertraute Clients können die DQ Express 2 Api verwenden.|
| Authorization Service Url | API-Endpunkt der Autorisierung |
| Customer Service Module Execution Url | API-Endpunkt des Moduleservices |
| Username | Persönlicher Benutzername |
| Password | Persönliches Passwort |

Die genannten Parameter werden Ihnen dann von uns zur Verfügung gestellt.

**Wichtig:** Die Client Id, der Client Secret sowie Ihre persönlichen Anmeldedaten dürfen niemals ungeschützt der Öffentlichkeit zur Verfügung gestellt werden. Jeder der diese Daten einsehen kann, kann Ihre gebuchten Transaktionen verbrauchen. Deswegen sollte zum Beispiel der Anmeldeprozess und am besten auch die Modulausführung nicht in Javascript ausgeführt werden, wenn dieser von Dritten eingesehen werden kann.
