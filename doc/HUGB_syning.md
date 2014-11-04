#HUGB sýning

Pusha kóða sem brýtur build (compile error):

``` diff
--- a/src/main/java/is/ru/geitibandi/geitimyllu/GeitIMyllu.java
+++ b/src/main/java/is/ru/geitibandi/geitimyllu/GeitIMyllu.java
@@ -141,7 +141,7 @@ public class GeitIMyllu {
         else if (getGameState() == 2 || getGameState() == 4)
             return 'O';
         else
-            return '-';
+            return ''-';
     }
```

Keyra `./gradlew check` - sjá buildið faila:


```
:compileJava FAILED
```

Pusha kóða sem failar unit test:

``` diff
--- a/src/main/java/is/ru/geitibandi/geitimyllu/GeitIMyllu.java
+++ b/src/main/java/is/ru/geitibandi/geitimyllu/GeitIMyllu.java
@@ -141,7 +141,7 @@ public class GeitIMyllu {
         else if (getGameState() == 2 || getGameState() == 4)
             return 'O';
         else
-            return '-';
+            return '=';
     }
```

Keyra `./gradlew check` - sjá build faila:

```
    23 tests completed, 1 failed
    :test FAILED
```

Pusha kóða sem brýtur checkstyle (code inspection failure) og breytir útliti á síðu:

``` diff
--- a/src/main/java/is/ru/geitibandi/geitimyllu/GeitIMyllu.java
+++ b/src/main/java/is/ru/geitibandi/geitimyllu/GeitIMyllu.java
@@ -136,7 +136,7 @@ public class GeitIMyllu {

     // Returns char of player playing currently or winner
     public char playerMark() {
-        if (getGameState() == 1 || getGameState() == 3)
+        if(getGameState() == 1 || getGameState() == 3)
             return 'X';
         else if (getGameState() == 2 || getGameState() == 4)
             return 'O';
--- a/src/main/resources/public/index.html
+++ b/src/main/resources/public/index.html
@@ -30,6 +30,7 @@ body {
/*margin-bottom: 60px;*/
  font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
  font-size: 14px;
 +background-color: #bada55;
}

```

"Gleyma" að gera ./gradle check, pusha á repo.

``` bash
$ git add src/main/java/is/ru/geitibandi/geitimyllu/GeitIMyllu.java
$ git commit -m "Totally nothing wrong with this code. Nope."
$ git push
```

Sjá Travis build faila:

```
[ant:checkstyle] /Users/fresnik/Dropbox/HR/20143/HUGB/geit-i-myllu/src/main/java/is/ru/geitibandi/geitimyllu/GeitIMyllu.java:139:11: 'if' is not followed by whitespace.
:checkstyleMain FAILED
```

Sjá tölvupóst berast frá Travis um að build hafi failað.

Laga style villuna.

Pusha kóða sem bætir við/lagar virkni. Byrja á að opna code coverage (http://geitibandi.github.io/geit-i-myllu/) í tveim gluggum.

``` bash
$ git merge developer
$ git push
```

Sjá tölvupóst berast frá Travis um að build hafi tekist.

Sjá code coverage batna - refresha annan code coverage gluggann.
