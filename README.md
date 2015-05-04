# Configuration for elasticsearch/shield

This configuration used a self-signed certificate.


Links to Elastic site :
* Server side :
  * [Securing nodes](http://www.elastic.co/guide/en/shield/current/securing-nodes.html)
  * [Separating node to node and client traffic](http://www.elastic.co/guide/en/shield/current/separating-node-client-traffic.html)
* Client side :
  * [Configuring Java clients](http://www.elastic.co/guide/en/shield/current/_java_clients.html)


## Generate keys

Self-signed certificate :

``` bash

$ keytool.exe  -genkey -alias shield -keystore shield-node.jks -keyalg RSA -keysize 2048 -validity 712
Entrez le mot de passe du fichier de clés : elastic
Ressaisissez le nouveau mot de passe :
Quels sont vos nom et prénom ?
  [Unknown]:  <hostname>
Quel est le nom de votre unité organisationnelle ?
  [Unknown]:  test
Quel est le nom de votre entreprise ?
  [Unknown]:  test.com
Quel est le nom de votre ville de résidence ?
  [Unknown]:  nantes
Quel est le nom de votre état ou province ?
  [Unknown]:  l.a.
Quel est le code pays à deux lettres pour cette unité ?
  [Unknown]:  fr
Est-ce CN=elasticsearch, OU=test, O=test.com, L=nantes, ST=l.a., C=fr ?
  [non]:  oui

Entrez le mot de passe de la clé pour <shield>
        (appuyez sur Entrée s'il s'agit du mot de passe du fichier de clés) : shield
Ressaisissez le nouveau mot de passe :

```

Export the certificate :

``` bash

$ keytool -export -alias shield -file shield.cer -keystore shield-node.jks
Entrez le mot de passe du fichier de clés : elastic
Certificat stocké dans le fichier <shield.cer>

```

Import the certificate in a trusted keystore :
``` bash

$ keytool -import -file shield.cer -keystore shield-trust.jks
Entrez le mot de passe du fichier de clés : elastic
Certificat stocké dans le fichier <shield.cer>

Propriétaire : CN=<hostname>, OU=test, O=test.com, L=nantes, ST=l.a., C=fr
Emetteur : CN=<hostname>, OU=test, O=test.com, L=nantes, ST=l.a., C=fr
Numéro de série : aebe633
Valide du : Mon May 04 13:22:11 CEST 2015 au : Sat Apr 15 13:22:11 CEST 2017
Empreintes du certificat :
         MD5:  9C:DA:BD:93:7C:54:90:E3:61:6A:B0:D1:AA:8A:60:9A
         SHA1 : A5:63:5A:47:C6:93:DF:B0:DC:56:4A:8F:9C:B8:42:49:A1:49:41:82
         SHA256 : E7:54:5F:2A:7B:5D:2F:1A:41:D8:39:C1:3B:0C:82:94:52:26:4A:78:FC:0F:9E:DF:C0:F9:D3:5F:9B:8A:E4:9D
         Nom de l'algorithme de signature : SHA256withRSA
         Version : 3

Extensions :

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: A3 C0 AC FE 68 18 5E 71   90 C6 0D 27 DA 42 A0 EA  ....h.^q...'.B..
0010: 00 F2 94 13                                        ....
]
]

Faire confiance à ce certificat ? [non] :  oui
Certificat ajouté au fichier de clés

```

## Configuration

* The `config-node` contains the configuration files for an elasticsearch node.
* The `config-client` contains the configuration files for a client.


## Test

``` bash

mvn clean exec:java

```
